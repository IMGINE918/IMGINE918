package com.student;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎓 欢迎使用学生管理系统 (控制台版)\n");

        // 1. 初始化管理器
        StudentManager manager = new StudentManager();

        // 2. 添加测试学生
        System.out.println("⏳ 正在初始化数据...");
        manager.addStudent(new Student("2026001", "张三", 19, "男", "软件工程", "软件1班", 85.5));
        manager.addStudent(new Student("2026002", "李四", 20, "女", "计算机科学", "计科2班", 92.0));
        manager.addStudent(new Student("2026003", "王五", 19, "男", "软件工程", "软件1班", 58.0));

        // 3. 显示所有学生
        manager.displayAllStudents();

        // 4. 统计成绩分布
        manager.showScoreDistribution();

        // 5. 查询特定学生
        System.out.println("\n🔍 查找学号为 2026002 的学生：");
        Student s = manager.getStudentById("2026002");
        manager.displayStudentDetail(s);
        
        System.out.println("\n🎉 测试流程执行完毕！");
    }
}
