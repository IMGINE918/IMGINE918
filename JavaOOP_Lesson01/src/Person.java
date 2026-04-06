/**
 * Superpowers Task 1: 补全封装并修复乱码
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 核心业务方法
    public void eat() {
        System.out.println(this.name + " 真能吃！吃的真香！");
    }

    public void speak() {
        System.out.println(this.name + " 说得妙！说的真好听！");
    }

    // 补全 Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age > 0 && age < 150) {
            this.age = age;
        } else {
            System.out.println("错误：年龄不合法！");
        }
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
