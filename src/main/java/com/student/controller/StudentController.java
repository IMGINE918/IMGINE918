package com.student.controller;

import com.student.model.Student;
import com.student.service.EarlyWarningService;
import com.student.service.StudentService;
import com.student.service.StudentStatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentStatisticsService statisticsService;
    private final EarlyWarningService earlyWarningService;

    public StudentController(
        StudentService studentService,
        StudentStatisticsService statisticsService,
        EarlyWarningService earlyWarningService
    ) {
        this.studentService = studentService;
        this.statisticsService = statisticsService;
        this.earlyWarningService = earlyWarningService;
    }

    @GetMapping
    public List<Student> listStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable String studentId) {
        Student student = studentService.findById(studentId);
        if (student == null) {
            throw new StudentNotFoundException(studentId);
        }
        return student;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/statistics/majors")
    public List<StudentStatisticsService.MajorStatistics> majorStatistics() {
        return statisticsService.calculateMajorStatistics(studentService.findAll());
    }

    @GetMapping("/statistics/grades")
    public List<StudentStatisticsService.GradeDistribution> gradeStatistics() {
        return statisticsService.calculateGradeDistribution(studentService.findAll());
    }

    @GetMapping("/warnings")
    public List<EarlyWarningService.WarningCase> warningCases() {
        return earlyWarningService.identifyAtRiskStudents(studentService.findAll());
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("status", "ok", "studentCount", studentService.findAll().size());
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleStudentNotFound(StudentNotFoundException exception) {
        return Map.of(
            "ok", false,
            "error", exception.getMessage()
        );
    }

    static class StudentNotFoundException extends RuntimeException {
        StudentNotFoundException(String studentId) {
            super("Student not found: " + studentId);
        }
    }
}
