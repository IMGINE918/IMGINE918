package com.student.model;

import java.time.LocalDate;

public class Student {

    private String studentId;
    private String name;
    private int age;
    private String gender;
    private String major;
    private String className;
    private double score;
    private LocalDate enrollDate;

    public Student() {
    }

    public Student(String studentId, String name, int age, String gender, String major, String className, double score,
                   LocalDate enrollDate) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.major = major;
        this.className = className;
        this.score = score;
        this.enrollDate = enrollDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public boolean isPassed() {
        return score >= 60;
    }
}
