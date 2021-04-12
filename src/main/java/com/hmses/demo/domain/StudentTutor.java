package com.hmses.demo.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity // This defines which objects should be persisted inside the database
@Table(name = "StudentTutor") // The table into which each entry will be inserted to
public class StudentTutor implements Serializable {

    @Id // This defines the primary key
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "Student", nullable = false)
    private String student;

    @Column(name = "Tutor", nullable = false)
    private String tutor;

    @Column(name = "Confirmed")
    private boolean confirmed;

    public int getId() {
        return id;
    }

    public String getStudent() {
        return student;
    }

    public String getTutor() {
        return tutor;
    }

    public boolean getConfirmed() {
        return confirmed;
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

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
