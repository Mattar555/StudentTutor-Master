package com.hmses.demo.controller;

import com.hmses.demo.domain.StudentTutor;
import com.hmses.demo.service.RabbitSender;
import com.hmses.demo.service.StudentTutorService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping(path = "/demo")
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
    public @ResponseBody StudentTutor addNewEntry (@RequestParam String student,
                                                   @RequestParam String tutor,
                                                   @RequestParam String email) {
        return studentTutorService.addEntry(student, tutor, email);
    }

    @PostMapping(value = "/producer")
    public String producer(@RequestParam(value="message") String message) throws IOException {
        rabbitSender.send(message);
        return "Message sent to the RabbitMQ Successfully";
    }

}
