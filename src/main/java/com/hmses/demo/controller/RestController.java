package com.hmses.demo.controller;

import com.hmses.demo.domain.AdminUser;
import com.hmses.demo.domain.StudentTutor;
import com.hmses.demo.service.AdminService;
import com.hmses.demo.service.RabbitSender;
import com.hmses.demo.service.StudentTutorService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping(path = "/demo")
// Obviously, only allow only the name of the docker container here (http://docker_container_name_here:port_here)
@CrossOrigin
public class RestController {

    @Autowired
    private StudentTutorService studentTutorService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RabbitSender rabbitSender;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<StudentTutor> getAllEntries() {
        return studentTutorService.getAllEntries();
    }

    @PostMapping(path="/add")
    public @ResponseBody StudentTutor addNewEntry(@RequestBody StudentTutor studentTutor) {
        return studentTutorService.addEntry(studentTutor);
    }

    @PutMapping(path = "/update")
    public @ResponseBody StudentTutor updateExistingEntry(@RequestBody StudentTutor studentTutor) {
        return studentTutorService.updateEntry(studentTutor);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody void deleteEntry(@RequestParam(name = "id") int id) {
        studentTutorService.deleteEntry(id);
    }

    @PutMapping(path = "/validate")
    public ResponseEntity<?> validateCredentials(@RequestBody AdminUser adminUser) {
        return new ResponseEntity<>(adminService.validateAdmin(adminUser) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }

}
