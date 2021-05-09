package com.hmses.demo.service;


import com.hmses.demo.domain.StudentTutor;
import com.hmses.demo.repository.StudentTutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentTutorService {

    @Autowired
    private StudentTutorRepository studentTutorRepository;

    @PostConstruct
    private void populateWithDummyEntry() {
        StudentTutor studentTutor = makeEntry("Student", "Tutor", "Email");
        if (studentTutorRepository.count() == 0) {
            studentTutorRepository.save(studentTutor);
        }
    }

    public Iterable<StudentTutor> getAllEntries() {
        return studentTutorRepository.findAll();
    }

    public List<StudentTutor> getEmailToSend() {
        return StreamSupport.stream(getAllEntries().spliterator(), false)
                .filter(entry -> !entry.isSent() || (entry.isSent() && entry.getResponse() == 0))
                .collect(Collectors.toList());
    }

    public StudentTutor addEntry(String student,
                                 String tutor,
                                 String email) {
        StudentTutor studentTutor = makeEntry(student, tutor, email);
        studentTutorRepository.save(studentTutor);
        return studentTutor;
    }

    private StudentTutor makeEntry(String student,
                                   String tutor,
                                   String email) {
        StudentTutor studentTutor = new StudentTutor();
        studentTutor.setStudent(student);
        studentTutor.setTutor(tutor);
        studentTutor.setEmail(email);
        return studentTutor;
    }
}
