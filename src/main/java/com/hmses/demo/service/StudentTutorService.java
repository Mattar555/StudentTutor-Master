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
        StudentTutor studentTutor = makeEntry("Student", "Tutor", "fake@gmail.com");
        if (studentTutorRepository.count() == 0) {
            studentTutorRepository.save(studentTutor);
        }
    }

    public void updateEntry(String email, int response) {
        // We enforce uniqueness such that any email entry present in the data set is unique.
        // So we are sure the list of results has a size of 1 always, hence the get(0)
        StudentTutor studentTutor = studentTutorRepository.findByEmail(email).get(0);
        studentTutor.setResponse(response);
        studentTutorRepository.save(studentTutor);
    }


    public Iterable<StudentTutor> getAllEntries() {
        return studentTutorRepository.findAll();
    }

    public List<StudentTutor> getEmailToSend() {
        return StreamSupport.stream(getAllEntries().spliterator(), false)
                .filter(entry -> !entry.isSent() || (entry.isSent() && entry.getResponse() == 0))
                .collect(Collectors.toList());
    }

    public StudentTutor addEntry(StudentTutor studentTutor) {
        // Enforcing uniqueness via email
        if (studentTutorRepository.findByEmail(studentTutor.getEmail()).isEmpty()) {
            studentTutorRepository.save(studentTutor);
            return studentTutor;
        }
        return new StudentTutor();
    }

    public StudentTutor updateEntry(StudentTutor studentTutor) {
        return studentTutorRepository.save(studentTutor);
    }

    public void deleteEntry(int studentTutorID) {
        studentTutorRepository.deleteById(studentTutorID);
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
