/* [资深工程师寄语-指挥篇]：这就是代码的肌肉记忆练习。 */
public class PhoneTest {
    public static void main(String[] args) {
        Phone myPhone = new Phone("华为 Mate 60", 6999.0, "雅川青");
        
        System.out.println("手机品牌：" + myPhone.getBrand());
        myPhone.call("10086");
        myPhone.sendMessage("110", "我学会设计 Java 类了！");
        myPhone.playGame("王者荣耀");
    }
}
