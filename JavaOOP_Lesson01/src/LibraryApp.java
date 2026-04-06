import java.util.ArrayList;
import java.util.List;

/**
 * Superpowers Project: LibraryApp (工程级重构版)
 * 职责：图书管理业务逻辑中心
 */
public class LibraryApp {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("成功入库图书: " + book.getTitle());
    }

    public void showAllBooks() {
        System.out.println("\n--- 当前馆藏清单 ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void borrowBook(String isbn, Student student) {
        System.out.println("\n正在处理借书请求: 学员 " + student.getName() + ", ISBN: " + isbn);
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                if (!b.isBorrowed()) {
                    b.setBorrowed(true);
                    System.out.println("✅ 借书成功！" + student.getName() + " 借阅了《" + b.getTitle() + "》");
                } else {
                    System.out.println("❌ 失败：图书《" + b.getTitle() + "》已被借出。");
                }
                return;
            }
        }
        System.out.println("❌ 错误：未找到 ISBN 为 " + isbn + " 的图书。");
    }

    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        
        // 初始化数据
        app.addBook(new Book("1001", "Java 核心技术", "Cay S. Horstmann"));
        app.addBook(new Book("1002", "Effective Java", "Joshua Bloch"));

        // 创建学员
        Student s1 = new Student("郑寒萍", "STU2026");

        // 模拟借书流程 (Superpowers 验收路径)
        app.showAllBooks();
        app.borrowBook("1001", s1);
        app.borrowBook("1001", s1); // 测试重复借阅
        app.showAllBooks();
    }
}
