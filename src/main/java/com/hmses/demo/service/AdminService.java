package com.hmses.demo.service;


import com.hmses.demo.domain.AdminUser;
import com.hmses.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AdminService {

    @Value("${web.auth.username}")
    private String username;

    @Value("${web.auth.password}")
    private String password;

    @Autowired
    private AdminRepository adminRepository;

    @PostConstruct
    private void populateAdminUsers() {
        if (adminRepository.count() == 0) {
            AdminUser adminUser = new AdminUser();
            adminUser.setUsername(username);
            adminUser.setPassword(password);
            adminRepository.save(adminUser);
        }
    }

    public boolean validateAdmin(AdminUser adminUser) {
        List<AdminUser> adminUserList = adminRepository.findByPassword(adminUser.getPassword());
        if (!adminUserList.isEmpty()) {
            return adminUserList.get(0).getUsername().equals(adminUser.getUsername());
        }
        return false;
    }
}
