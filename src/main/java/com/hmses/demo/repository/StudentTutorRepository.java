package com.hmses.demo.repository;

import com.hmses.demo.domain.StudentTutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentTutorRepository extends CrudRepository<StudentTutor, Integer> {

    List<StudentTutor> findByEmail(String email);
}
