import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main 类 (系统逻辑 / 控制中心)
 * 
 * 资深工程师视角：
 * 3. 为什么要在 Main 下面看到它？
 * 你在 Main.java 里看到这行代码：
 * ArrayList<Student> list = new ArrayList<>();
 * 这里的 <Student> 就像是在告诉 Java：“嘿，这个列表是一个专门放‘学生包’的集装箱，别的东西（比如纯数字或纯字符串）都不许放进来。”
 * 
 * 💡 资深工程师的专业建议：
 * 这种“数据”与“逻辑”分离的做法，就是大厂项目里最基础的规范。
 * - Student.java 只管数据结构（静态的）。
 * - Main.java 只管业务逻辑（动态的，比如怎么增删改查）。
 */
public class Main {
    public static void main(String[] args) {
        // 创建学生列表 (集装箱)
        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("------ 学生管理系统 ------");
            System.out.println("1 添加学生 | 2 查看学生 | 5 退出系统");
            System.out.print("请输入选择: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                addStudent(list, sc);
            } else if (choice == 2) {
                showStudents(list);
            } else if (choice == 5) {
                System.out.println("系统已退出，再见！");
                break;
            } else {
                System.out.println("输入有误，请重新选择。");
            }
        }
    }

    /**
     * 功能：添加学生
     */
    public static void addStudent(ArrayList<Student> list, Scanner sc) {
        System.out.println("--- 添加学生 ---");
        Student s = new Student();
        System.out.print("请输入学号: "); s.id = sc.next();
        System.out.print("请输入姓名: "); s.name = sc.next();
        System.out.print("请输入年龄: "); s.age = sc.nextInt();
        list.add(s);
        System.out.println("添加成功！");
    }

    /**
     * 功能：查看学生
     */
    public static void showStudents(ArrayList<Student> list) {
        System.out.println("--- 学生列表 ---");
        if (list.size() == 0) {
            System.out.println("当前系统无数据。");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println("学号: " + s.id + ", 姓名: " + s.name + ", 年龄: " + s.age);
        }
    }
}
