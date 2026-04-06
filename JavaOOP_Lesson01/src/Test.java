/* [资深工程师寄语-指挥篇]：这里是系统的门户与心脏。作为指挥官，你要在这里统领全局。 */
public class Test {
    public static void main(String[] args) {
        System.out.println("【1】我要点餐");
        System.out.println("【0】退出系统");
        System.out.println("--------------------------");

        Person p = new Person("王二小", 12);
        p.eat();
        p.speak();

        System.out.println("--------------------------");
        Person p1 = new Person("张三", 18);
        Person p2 = new Person("李四", 20);
        p1.speak();
        p2.speak();

        System.out.println("--------------------------");
        System.out.println("通过 Getter 获取姓名：" + p.getName());
        System.out.print("尝试把年龄改成 -5：");
        p.setAge(-5); 
        
        System.out.println("\n--------------------------");
        System.out.println("【实战：使用我自己设计的 Student 类】");
        Student s1 = new Student("林黛玉", "S1001");
        s1.study("Java 面向对象");
        s1.takeExam(98.5);
    }
}
