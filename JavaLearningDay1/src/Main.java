import java.util.Scanner;

public class Main {
      public static void main(String[] args) {
        // 🚀 练习 04：成绩判断
        Scanner sc = new Scanner(System.in);
        
        System.out.println("请输入你的成绩：");
        int score = sc.nextInt();
        
        if (score >= 60) {
            System.out.println("恭喜你，及格了！继续保持。🎓");
        } else {
            System.out.println("不及格，加油哦，下次一定可以！💪");
        }
    }
}
