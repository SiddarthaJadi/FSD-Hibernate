package org.example.studentapp;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "student_name", nullable = false)
    private String studentName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "course")
    private String course;
    @Column(name = "age")
    private Integer age;
    // Constructors
    public Student() {

    }
    public Student(String studentName, String email, String course, Integer age) {
        this.studentName = studentName;
        this.email = email;
        this.course = course;
        this.age = age;
    }
    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
