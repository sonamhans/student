package com.lloyds.student_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String email;

    @Column
    private Integer classroomId;

    public Student(int id, String name, Integer age, String email, Integer classroomId) {
        super();
        this.classroomId = classroomId;
        this.email = email;
        this.age = age;
        this.name = name;
        this.id = id;
    }

    public Student() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }
}
