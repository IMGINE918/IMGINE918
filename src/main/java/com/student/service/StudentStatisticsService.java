package com.student.service;

import com.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentStatisticsService {

    public record GradeDistribution(String grade, long count, double percentage) {}

    public record MajorStatistics(String major, double averageScore, long totalStudents) {}

    public List<MajorStatistics> calculateMajorStatistics(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return List.of();
        }

        return students.stream()
            .collect(Collectors.groupingBy(
                Student::getMajor,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> new MajorStatistics(
                        list.get(0).getMajor(),
                        list.stream().mapToDouble(Student::getScore).average().orElse(0.0),
                        list.size()
                    )
                )
            ))
            .values()
            .stream()
            .toList();
    }

    public List<GradeDistribution> calculateGradeDistribution(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return List.of();
        }

        long total = students.size();
        return students.stream()
            .collect(Collectors.groupingBy(this::toGrade, Collectors.counting()))
            .entrySet()
            .stream()
            .map(entry -> new GradeDistribution(
                entry.getKey(),
                entry.getValue(),
                entry.getValue() * 100.0 / total
            ))
            .toList();
    }

    private String toGrade(Student student) {
        double score = student.getScore();
        if (score >= 90) {
            return "Excellent";
        }
        if (score >= 80) {
            return "Good";
        }
        if (score >= 70) {
            return "Average";
        }
        if (score >= 60) {
            return "Pass";
        }
        return "Fail";
    }
}
