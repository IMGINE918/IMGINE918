package com.library.model;

/* [资深工程师寄语-模型篇]：数据是系统的血肉。在 Entity 层，你要学会对现实世界进行精准的‘数字化建模’。 */
public class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void showInfo() {
        System.out.println("📖 书名：《" + title + "》 | 作者：" + author + " | 价格：" + price);
    }

    public String getTitle() { return title; }
}
