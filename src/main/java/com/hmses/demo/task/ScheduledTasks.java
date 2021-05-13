package com.hmses.demo.task;

import com.hmses.demo.domain.StudentTutor;
import com.hmses.demo.service.RabbitSender;
import com.hmses.demo.service.StudentTutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private RabbitSender rabbitSender;


    @Autowired
    private StudentTutorService studentTutorService;

    @Scheduled(cron = "0 0 6 * * *") // Every day, at 6AM.
    // @Scheduled(fixedDelay = 600000) => Every 10 minutes
    // @Scheduled(fixedDelay = 60000) => Every minute
    public void sendNotifications() {
        List<StudentTutor> studentTutors = studentTutorService.getEmailToSend();
        studentTutors.forEach(studentTutor ->
                rabbitSender.send(studentTutor.getEmail())
        );
    }
}
