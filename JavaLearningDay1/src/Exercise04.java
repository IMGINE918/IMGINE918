import java.util.Scanner;

public class Exercise04 {
    public static void main(String[] args) {

        {
            Scanner sc = new Scanner(System.in);

            try {
                System.out.println("请输入你的成绩：");

                if (!sc.hasNextInt()) {
                    System.out.println("输入无效，请输入一个整数！");
                    return;
                }

                int score = sc.nextInt();

                if (score < 0 || score > 100) {
                    System.out.println("成绩必须在 0-100 之间！");
                    return;
                }

                if (score >= 60) {
                    System.out.println("恭喜你，及格了！继续保持。🎓");
                } else {
                    System.out.println("不及格，加油哦，下次一定可以！💪");
                }
            } finally {
                sc.close();
            }
        }
    }
}



