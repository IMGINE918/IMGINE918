package com.student;

import java.time.LocalDate;

/**
 * 学生实体类
 * 包含学生的基本信息
 * @author AI Assistant
 * @version 1.0
 * @since 2026-01-20
 */
public class Student {
    
    // ===== 成员变量 (属性) =====
    private String studentId;      // 学号
    private String name;           // 姓名
    private int age;              // 年龄
    private String gender;         // 性别
    private String major;          // 专业
    private String className;       // 班级
    private double score;          // 成绩
    private LocalDate enrollDate;  // 入学日期
    
    // ===== 构造方法 =====
    
    /**
     * 无参构造方法
     * 创建一个空的学生对象
     */
    public Student() {
        this.studentId = "";
        this.name = "";
        this.age = 0;
        this.gender = "";
        this.major = "";
        this.className = "";
        this.score = 0.0;
        this.enrollDate = LocalDate.now();
    }
    
    /**
     * 全参构造方法
     * @param studentId 学号
     * @param name 姓名
     * @param age 年龄
     * @param gender 性别
     * @param major 专业
     * @param className 班级
     * @param score 成绩
     * @param enrollDate 入学日期
     */
    public Student(String studentId, String name, int age, String gender, 
                 String major, String className, double score, LocalDate enrollDate) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.major = major;
        this.className = className;
        this.score = score;
        this.enrollDate = enrollDate;
    }
    
    /**
     * 部分参数构造方法
     * @param studentId 学号
     * @param name 姓名
     * @param age 年龄
     * @param major 专业
     */
    public Student(String studentId, String name, int age, String major) {
        this(studentId, name, age, "男", major, "2024级1班", 0.0, LocalDate.now());
    }
    
    // ===== Getter 方法 =====
    
    /**
     * 获取学号
     * @return 学号
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * 获取姓名
     * @return 姓名
     */
    public String getName() {
        return name;
    }
    
    /**
     * 获取年龄
     * @return 年龄
     */
    public int getAge() {
        return age;
    }
    
    /**
     * 获取性别
     * @return 性别
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * 获取专业
     * @return 专业
     */
    public String getMajor() {
        return major;
    }
    
    /**
     * 获取班级
     * @return 班级
     */
    public String getClassName() {
        return className;
    }
    
    /**
     * 获取成绩
     * @return 成绩
     */
    public double getScore() {
        return score;
    }
    
    /**
     * 获取入学日期
     * @return 入学日期
     */
    public LocalDate getEnrollDate() {
        return enrollDate;
    }
    
    // ===== Setter 方法 =====
    
    /**
     * 设置学号
     * @param studentId 学号
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * 设置姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 设置年龄
     * @param age 年龄（范围：10-120）
     */
    public void setAge(int age) {
        if (age > 0 && age < 120) {
            this.age = age;
        } else {
            System.out.println("❌ 年龄不合法！范围：10-120");
        }
    }
    
    /**
     * 设置性别
     * @param gender 性别（男/女）
     */
    public void setGender(String gender) {
        if ("男".equals(gender) || "女".equals(gender)) {
            this.gender = gender;
        } else {
            System.out.println("❌ 性别不合法！只能输入'男'或'女'");
        }
    }
    
    /**
     * 设置专业
     * @param major 专业
     */
    public void setMajor(String major) {
        this.major = major;
    }
    
    /**
     * 设置班级
     * @param className 班级
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
    /**
     * 设置成绩
     * @param score 成绩（范围：0-100）
     */
    public void setScore(double score) {
        if (score >= 0 && score <= 100) {
            this.score = score;
        } else {
            System.out.println("❌ 成绩不合法！范围：0-100");
        }
    }
    
    /**
     * 设置入学日期
     * @param enrollDate 入学日期
     */
    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }
    
    // ===== 业务方法 =====
    
    /**
     * 判断是否及格
     * @return true 表示及格，false 表示不及格
     */
    public boolean isPassed() {
        return score >= 60;
    }
    
    /**
     * 获取成绩等级
     * @return 成绩等级字符串
     */
    public String getGrade() {
        if (score >= 90) return "优秀";
        else if (score >= 80) return "良好";
        else if (score >= 70) return "中等";
        else if (score >= 60) return "及格";
        else return "不及格";
    }
    
    /**
     * 获取学习时长（天）
     * @return 从入学到现在的天数
     */
    public long getStudyDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(enrollDate, LocalDate.now()).getDays();
    }
    
    /**
     * 转换为字符串显示
     * @return 格式化的学生信息字符串
     */
    @Override
    public String toString() {
        return String.format(
            "学号: %s, 姓名: %s, 年龄: %d, 性别: %s, 专业: %s, 班级: %s, 成绩: %.1f, 等级: %s",
            studentId, name, age, gender, major, className, score, getGrade()
        );
    }
}