package com.hmses.demo.service;


import com.hmses.demo.domain.StudentTutor;
import com.hmses.demo.repository.StudentTutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StudentTutorService {

    @Autowired
    private StudentTutorRepository studentTutorRepository;

    @PostConstruct
    private void populateWithDummyEntry() {
        StudentTutor studentTutor = makeEntry("Student", "Tutor");
        if (studentTutorRepository.count() == 0) {
            studentTutorRepository.save(studentTutor);
        }
    }

    public Iterable<StudentTutor> getAllEntries() {
        return studentTutorRepository.findAll();
    }

    public StudentTutor addEntry(String student, String tutor) {
        StudentTutor studentTutor = makeEntry(student, tutor);
        studentTutorRepository.save(studentTutor);
        return studentTutor;
    }

    private StudentTutor makeEntry(String student, String tutor) {
        StudentTutor studentTutor = new StudentTutor();
        studentTutor.setStudent(student);
        studentTutor.setTutor(tutor);
        return studentTutor;
    }
}
