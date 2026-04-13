package com.student.service;

import com.student.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {

    private final Map<String, Student> students = new ConcurrentHashMap<>();

    @PostConstruct
    void seedData() {
        save(new Student("2026001", "张三", 19, "男", "软件工程", "软件1班", 85.5, LocalDate.of(2024, 9, 1)));
        save(new Student("2026002", "李四", 20, "女", "计算机科学", "计科2班", 92.0, LocalDate.of(2024, 9, 1)));
        save(new Student("2026003", "王五", 19, "男", "软件工程", "软件1班", 58.0, LocalDate.of(2024, 9, 1)));
    }

    public List<Student> findAll() {
        return students.values().stream().sorted((a, b) -> a.getStudentId().compareTo(b.getStudentId())).toList();
    }

    public Student findById(String studentId) {
        return students.get(studentId);
    }

    public Student save(Student student) {
        if (student.getEnrollDate() == null) {
            student.setEnrollDate(LocalDate.now());
        }
        students.put(student.getStudentId(), student);
        return student;
    }
}
