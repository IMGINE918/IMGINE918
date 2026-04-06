package com.library.model;

/* [资深工程师寄语-模型篇]：尊重数据，因为它是软件世界中唯一的真实。 */
public class LibraryUser {
    private String name;
    private String studentId;

    public LibraryUser(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public void sayHello() {
        System.out.println("👤 借阅者 " + name + " (学号:" + studentId + ") 进入了图书馆。");
    }

    public String getName() { return name; }
}
