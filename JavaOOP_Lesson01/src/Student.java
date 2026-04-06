/**
 * Superpowers Task 1: 补全封装并修复乱码
 */
public class Student {
    private String name;
    private String id;
    private double score;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.score = 0.0;
    }

    // 核心业务方法
    public void study(String course) {
        System.out.println("学生 " + this.name + " 正在努力学习 " + course);
    }

    public void takeExam(double result) {
        this.score = result;
        System.out.println("学号 " + this.id + " 的考试成绩为：" + this.score);
    }

    // 补全 Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', id='" + id + "', score=" + score + "}";
    }
}
