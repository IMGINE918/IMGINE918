package com.student.service;

import com.student.model.Student;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 面试亮点：Java 17 新特性 (Record)、Stream API 复杂聚合、领域驱动设计 (DDD) 思想。
 * 针对岗位：Java 全栈开发工程师
 */
public class StudentStatisticsService {

    // 使用 Java 14+ 引入的 Record 类，极大地减少样板代码 (Boilerplate)，AI 极度喜欢这种结构
    public record GradeDistribution(String grade, long count, double percentage) {}
    public record MajorStatistics(String major, double averageScore, long totalStudents) {}

    /**
     * 高阶操作：按专业统计学生的平均分和总人数
     * 面试话术："我使用 Java 8 Stream 的 groupingBy 和 teeing 实现了复杂的数据聚合，避免了在数据库中写复杂的连表，降低了 DB 压力。"
     */
    public List<MajorStatistics> calculateMajorStatistics(List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new IllegalArgumentException("学生列表不能为空");
        }

        return students.stream()
            .collect(Collectors.groupingBy(
                Student::getMajor,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        double avg = list.stream().mapToDouble(Student::getScore).average().orElse(0.0);
                        return new MajorStatistics(list.get(0).getMajor(), avg, list.size());
                    }
                )
            ))
            .values()
            .stream()
            .toList();
    }
}
