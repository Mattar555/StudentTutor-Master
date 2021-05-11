package com.hmses.demo.controller;

import com.hmses.demo.domain.StudentTutor;
import com.hmses.demo.service.RabbitSender;
import com.hmses.demo.service.StudentTutorService;

import org.springframework.beans.factory.annotation.Autowired;

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
    private RabbitSender rabbitSender;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<StudentTutor> getAllEntries() {
        return studentTutorService.getAllEntries();
    }

    @PostMapping(path="/add")
    public @ResponseBody StudentTutor addNewEntry (@RequestBody StudentTutor studentTutor) {
        return studentTutorService.addEntry(studentTutor);
    }

    @PostMapping(value = "/producer")
    public String producer(@RequestParam(value="message") String message) throws IOException {
        rabbitSender.send(message);
        return "Message sent to the RabbitMQ Successfully";
    }

}
