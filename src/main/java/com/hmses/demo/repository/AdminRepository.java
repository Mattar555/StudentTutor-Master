package com.hmses.demo.repository;

import com.hmses.demo.domain.AdminUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<AdminUser, Integer> {
    List<AdminUser> findByPassword(String password);
}
