package com.student.service;

import com.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarlyWarningService {

    public record WarningCase(Student student, String riskLevel, String urgentAction) {}

    public List<WarningCase> identifyAtRiskStudents(List<Student> students) {
        return students.stream()
            .filter(student -> student.getScore() < 60)
            .map(student -> new WarningCase(
                student,
                "RED_ALERT",
                "Needs a tutoring plan within 48 hours."
            ))
            .toList();
    }

    public String prepareAIPromptContext(Student student) {
        return String.format(
            "Student %s, major %s, currently scored %.2f. Prepare a focused 7-day tutoring plan.",
            student.getName(),
            student.getMajor(),
            student.getScore()
        );
    }
}
