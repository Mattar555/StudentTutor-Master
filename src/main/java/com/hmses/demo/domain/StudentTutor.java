package com.hmses.demo.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity // This defines which objects should be persisted inside the database
@Table(name = "inventory") // The table into which each entry will be inserted to
public class StudentTutor implements Serializable {

    @Id // This defines the primary key
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "Student", nullable = false)
    private String student;

    @Column(name = "Tutor", nullable = false)
    private String tutor;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Sent")
    private boolean sent;

    @Column(name = "Response")
    private int response = 0;

    public int getId() {
        return id;
    }

    public String getStudent() {
        return student;
    }

    public String getTutor() {
        return tutor;
    }

    public int getResponse() {
        return response;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSent() {
        return sent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public void setEmail(String email) { this.email = email; }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
