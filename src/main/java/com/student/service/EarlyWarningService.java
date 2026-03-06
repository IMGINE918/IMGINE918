package com.student.service;

import com.student.model.Student;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品思维亮点：主动式风险管理 (Proactive Risk Management)。
 * 核心逻辑：不仅仅是存储不及格数据，而是将其转化为“干预任务”。
 */
public class EarlyWarningService {

    // 定义预警模型，包含学生信息和预警等级
    public record WarningCase(Student student, String riskLevel, String urgentAction) {}

    /**
     * 识别需要“红色预警”的学生（单科低于 60 分）
     * 产品思维话术：“我们不等待期末挂科。系统每录入一笔成绩，就会自动识别风险，并为 AI 补习计划提供上下文数据。”
     */
    public List<WarningCase> identifyAtRiskStudents(List<Student> students) {
        return students.stream()
            .filter(s -> s.getScore() < 60)
            .map(s -> new WarningCase(
                s, 
                "RED_ALERT", 
                "需在 48 小时内生成 AI 补习方案并约谈"
            ))
            .collect(Collectors.toList());
    }

    /**
     * 为 AI 准备的“补习计划”提示词上下文
     * 它将学生的专业、成绩和错题分布（模拟）打包发给 AI。
     */
    public String prepareAIPromptContext(Student student) {
        return String.format(
            "学生姓名：%s，专业：%s，当前成绩：%.2f。该生目前处于不及格边缘，请基于其专业背景，制定一份为期 7 天的『救火式』补习计划。",
            student.getName(), student.getMajor(), student.getScore()
        );
    }
}
