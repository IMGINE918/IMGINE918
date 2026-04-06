/* [资深工程师寄语-基础篇]：每一个属性的定义、每一个 this 的使用，都在构建你未来的肌肉记忆。大专三年的基本功，决定了你未来能走多远。 */
public class Phone {
    private String brand;
    private double price;
    private String color;

    public Phone(String brand, double price, String color) {
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public void call(String number) {
        System.out.println("正在使用 " + this.brand + " 手机拨打电话给：" + number);
    }

    public void sendMessage(String number, String content) {
        System.out.println("向 " + number + " 发送短信，内容为：" + content);
    }

    public void playGame(String gameName) {
        System.out.println("正在用这台 " + this.color + " 的手机玩：" + gameName);
    }

    public String getBrand() {
        return brand;
    }
}
