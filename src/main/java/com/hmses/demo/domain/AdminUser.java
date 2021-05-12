package com.hmses.demo.domain;

import javax.persistence.*;

@Entity // This defines which objects should be persisted inside the database
@Table(name = "admins") // The table into which each entry will be inserted to
public class AdminUser {

    @Id // This defines the primary key
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
