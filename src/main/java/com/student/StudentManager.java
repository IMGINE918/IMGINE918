package com.student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生管理类
 * 实现学生的增删改查功能
 * @author AI Assistant
 * @version 1.0
 * @since 2026-01-20
 */
public class StudentManager {
    
    // 使用 ArrayList 存储学生
    private List<Student> students;
    
    /**
     * 构造方法
     * 初始化空的 ArrayList
     */
    public StudentManager() {
        this.students = new ArrayList<>();
    }
    
    // ===== 添加学生 =====
    
    /**
     * 添加学生
     * @param student 学生对象
     * @return 添加成功返回 true，失败返回 false
     */
    public boolean addStudent(Student student) {
        // 验证学号是否已存在
        for (Student s : students) {
            if (s.getStudentId().equals(student.getStudentId())) {
                System.out.println("❌ 添加失败：学号 " + student.getStudentId() + " 已存在！");
                return false;
            }
        }
        
        // 添加到列表
        students.add(student);
        System.out.println("✅ 添加成功：" + student.getName());
        return true;
    }
    
    /**
     * 批量添加学生
     * @param studentList 学生列表
     * @return 成功添加的数量
     */
    public int addStudents(List<Student> studentList) {
        int count = 0;
        for (Student student : studentList) {
            if (addStudent(student)) {
                count++;
            }
        }
        return count;
    }
    
    // ===== 查询学生 =====
    
    /**
     * 查询所有学生
     * @return 所有学生的列表副本
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    /**
     * 按学号精确查询学生
     * @param studentId 学号
     * @return 匹配的学生对象，未找到返回 null
     */
    public Student getStudentById(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }
    
    /**
     * 按姓名模糊查询学生
     * @param name 姓名（支持部分匹配）
     * @return 匹配的学生列表
     */
    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        String lowerName = name.toLowerCase();
        
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(lowerName)) {
                result.add(s);
            }
        }
        
        return result;
    }
    
    /**
     * 按专业模糊查询学生
     * @param major 专业（支持部分匹配）
     * @return 匹配的学生列表
     */
    public List<Student> searchByMajor(String major) {
        List<Student> result = new ArrayList<>();
        String lowerMajor = major.toLowerCase();
        
        for (Student s : students) {
            if (s.getMajor().toLowerCase().contains(lowerMajor)) {
                result.add(s);
            }
        }
        
        return result;
    }
    
    /**
     * 按成绩范围查询学生
     * @param minScore 最低分
     * @param maxScore 最高分
     * @return 符合条件的学生列表
     */
    public List<Student> searchByScoreRange(double minScore, double maxScore) {
        List<Student> result = new ArrayList<>();
        
        for (Student s : students) {
            if (s.getScore() >= minScore && s.getScore() <= maxScore) {
                result.add(s);
            }
        }
        
        return result;
    }
    
    /**
     * 按是否及格查询学生
     * @param passed true 查询及格的，false 查询不及格的
     * @return 符合条件的学生列表
     */
    public List<Student> searchByPassStatus(boolean passed) {
        List<Student> result = new ArrayList<>();
        
        for (Student s : students) {
            if (s.isPassed() == passed) {
                result.add(s);
            }
        }
        
        return result;
    }
    
    // ===== 修改学生 =====
    
    /**
     * 修改学生信息
     * @param student 修改后的学生对象
     * @return 修改成功返回 true，失败返回 false
     */
    public boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(student.getStudentId())) {
                students.set(i, student);
                System.out.println("✅ 修改成功：" + student.getName());
                return true;
            }
        }
        
        System.out.println("❌ 修改失败：学号 " + student.getStudentId() + " 不存在！");
        return false;
    }
    
    /**
     * 批量更新学生成绩
     * @param updates 包含学号和新成绩的列表
     * @return 成功更新的数量
     */
    public int updateScores(List<ScoreUpdate> updates) {
        int count = 0;
        for (ScoreUpdate update : updates) {
            Student student = getStudentById(update.getStudentId());
            if (student != null) {
                student.setScore(update.getNewScore());
                if (updateStudent(student)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    // ===== 删除学生 =====
    
    /**
     * 删除学生
     * @param studentId 学号
     * @return 删除成功返回 true，失败返回 false
     */
    public boolean deleteStudent(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                Student deleted = students.remove(i);
                System.out.println("✅ 删除成功：" + deleted.getName());
                return true;
            }
        }
        
        System.out.println("❌ 删除失败：学号 " + studentId + " 不存在！");
        return false;
    }
    
    /**
     * 批量删除学生
     * @param studentIds 要删除的学号列表
     * @return 成功删除的数量
     */
    public int deleteStudents(List<String> studentIds) {
        int count = 0;
        for (String id : studentIds) {
            if (deleteStudent(id)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * 清空所有学生数据
     */
    public void clearAll() {
        students.clear();
        System.out.println("✅ 已清空所有学生数据！");
    }
    
    // ===== 统计分析 =====
    
    /**
     * 获取学生总数
     * @return 学生数量
     */
    public int getTotalCount() {
        return students.size();
    }
    
    /**
     * 计算平均成绩
     * @return 平均成绩
     */
    public double getAverageScore() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double total = 0;
        for (Student s : students) {
            total += s.getScore();
        }
        
        return total / students.size();
    }
    
    /**
     * 获取最高分
     * @return 最高分
     */
    public double getMaxScore() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double max = students.get(0).getScore();
        for (Student s : students) {
            if (s.getScore() > max) {
                max = s.getScore();
            }
        }
        return max;
    }
    
    /**
     * 获取最低分
     * @return 最低分
     */
    public double getMinScore() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double min = students.get(0).getScore();
        for (Student s : students) {
            if (s.getScore() < min) {
                min = s.getScore();
            }
        }
        return min;
    }
    
    /**
     * 获取成绩标准差
     * @return 标准差
     */
    public double getScoreStandardDeviation() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double mean = getAverageScore();
        double sum = 0;
        
        for (Student s : students) {
            double diff = s.getScore() - mean;
            sum += diff * diff;
        }
        
        return Math.sqrt(sum / students.size());
    }
    
    /**
     * 获取成绩分布
     * 显示各等级的学生数量
     */
    public void showScoreDistribution() {
        int excellent = 0;  // 优秀 (90-100)
        int good = 0;        // 良好 (80-89)
        int medium = 0;      // 中等 (70-79)
        int pass = 0;        // 及格 (60-69)
        int fail = 0;        // 不及格 (0-59)
        
        for (Student s : students) {
            double score = s.getScore();
            if (score >= 90) excellent++;
            else if (score >= 80) good++;
            else if (score >= 70) medium++;
            else if (score >= 60) pass++;
            else fail++;
        }
        
        System.out.println("\n📊 成绩分布：");
        System.out.println("═══════════════════════════");
        System.out.printf("优秀 (90-100):  %2d 人  (%5.1f%%)\n", excellent, getPercentage(excellent));
        System.out.printf("良好 (80-89):   %2d 人  (%5.1f%%)\n", good, getPercentage(good));
        System.out.printf("中等 (70-79):   %2d 人  (%5.1f%%)\n", medium, getPercentage(medium));
        System.out.printf("及格 (60-69):   %2d 人  (%5.1f%%)\n", pass, getPercentage(pass));
        System.out.printf("不及格 (0-59):  %2d 人  (%5.1f%%)\n", fail, getPercentage(fail));
        System.out.println("═══════════════════════════");
        
        System.out.println("\n📈 成绩统计：");
        System.out.println("────────────────────────────────");
        System.out.printf("平均成绩:  %.2f 分\n", getAverageScore());
        System.out.printf("最高成绩:  %.2f 分\n", getMaxScore());
        System.out.printf("最低成绩:  %.2f 分\n", getMinScore());
        System.out.printf("成绩方差:  %.2f\n", getScoreStandardDeviation());
        System.out.println("────────────────────────────────");
    }
    
    /**
     * 计算百分比
     * @param count 数量
     * @return 百分比
     */
    private double getPercentage(int count) {
        if (students.isEmpty()) return 0.0;
        return (double) count / students.size() * 100;
    }
    
    /**
     * 按成绩排序
     * @param ascending true 升序，false 降序
     * @return 排序后的学生列表
     */
    public List<Student> sortByScore(boolean ascending) {
        return students.stream()
            .sorted((a, b) -> ascending 
                ? Double.compare(a.getScore(), b.getScore())
                : Double.compare(b.getScore(), a.getScore()))
            .collect(Collectors.toList());
    }
    
    /**
     * 按年龄排序
     * @param ascending true 升序，false 降序
     * @return 排序后的学生列表
     */
    public List<Student> sortByAge(boolean ascending) {
        return students.stream()
            .sorted((a, b) -> ascending 
                ? Integer.compare(a.getAge(), b.getAge())
                : Integer.compare(b.getAge(), a.getAge()))
            .collect(Collectors.toList());
    }
    
    /**
     * 显示所有学生信息
     */
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\n📭 暂无学生数据！");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📋 学生列表（共 " + students.size() + " 人）");
        System.out.println("=".repeat(80));
        
        System.out.printf("%-5s %-20s %-6s %-8s %-8s %-20s %-10s %-8s %s\n", 
            "序号", "学号", "姓名", "年龄", "专业", "班级", "成绩", "等级");
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%-5d %-20s %-6s %-4d %-8s %-20s %-10s %-6.1f %s\n", 
                i + 1, 
                s.getStudentId(), 
                s.getName(), 
                s.getAge(), 
                s.getMajor(), 
                s.getClassName(), 
                s.getScore(), 
                s.getGrade()
            );
        }
        
        System.out.println("=".repeat(80));
    }
    
    /**
     * 显示单个学生信息（详细版）
     */
    public void displayStudentDetail(Student student) {
        if (student == null) {
            System.out.println("❌ 未找到该学生！");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📚 学生详细信息");
        System.out.println("=".repeat(60));
        System.out.println(student.toString());
        System.out.println("=".repeat(60));
        
        // 额外信息
        System.out.println("\n📊 学习信息：");
        System.out.println("────────────────────────────────");
        System.out.println("是否及格: " + (student.isPassed() ? "✅ 是" : "❌ 否"));
        System.out.println("成绩等级: " + student.getGrade());
        System.out.println("学习天数: " + student.getStudyDays() + " 天");
        System.out.println("入学日期: " + student.getEnrollDate());
        System.out.println("────────────────────────────────");
    }
    
    /**
     * 显示单个学生信息（简化版）
     */
    public void displayStudent(Student student) {
        displayStudentDetail(student);
    }
    
    /**
     * 获取专业统计
     * @return 专业分布统计
     */
    public void showMajorStatistics() {
        if (students.isEmpty()) {
            System.out.println("❌ 暂无学生数据！");
            return;
        }
        
        System.out.println("\n📚 专业分布统计：");
        System.out.println("═══════════════════════════");
        
        // 统计各专业人数
        java.util.Map<String, Integer> majorCount = new java.util.HashMap<>();
        for (Student s : students) {
            String major = s.getMajor();
            majorCount.put(major, majorCount.getOrDefault(major, 0) + 1);
        }
        
        // 显示统计结果
        for (java.util.Map.Entry<String, Integer> entry : majorCount.entrySet()) {
            System.out.printf("%-20s: %2d 人 (%5.1f%%)\n", 
                entry.getKey(), 
                entry.getValue(), 
                (double)entry.getValue() / students.size() * 100
            );
        }
        
        System.out.println("═════════════════════════════");
    }
    
    /**
     * 导出学生数据为 CSV 格式
     */
    public String exportToCSV() {
        StringBuilder csv = new StringBuilder();
        csv.append("学号,姓名,年龄,性别,专业,班级,成绩,等级\n");
        
        for (Student s : students) {
            csv.append(String.format("%s,%s,%d,%s,%s,%s,%.1f,%s\n",
                s.getStudentId(),
                s.getName(),
                s.getAge(),
                s.getGender(),
                s.getMajor(),
                s.getClassName(),
                s.getScore(),
                s.getGrade()
            ));
        }
        
        return csv.toString();
    }
    
    /**
     * 内部类：成绩更新信息
     */
    public static class ScoreUpdate {
        private String studentId;
        private double newScore;
        
        public ScoreUpdate(String studentId, double newScore) {
            this.studentId = studentId;
            this.newScore = newScore;
        }
        
        public String getStudentId() {
            return studentId;
        }
        
        public double getNewScore() {
            return newScore;
        }
    }
}