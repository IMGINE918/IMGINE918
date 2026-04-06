package com.library.service;

import com.library.model.Book;
import com.library.model.LibraryUser;

/* [资深工程师寄语-业务篇]：Service 层是系统的‘大脑’。在这里，你要学会像主厨一样调度全局。 */
public class BorrowRecord {
    private LibraryUser user; 
    private Book book;        
    private String date;

    public BorrowRecord(LibraryUser user, Book book, String date) {
        this.user = user;
        this.book = book;
        this.date = date;
    }

    public void printRecord() {
        System.out.println("✅ 借阅成功！");
        System.out.println("   时间：" + date);
        System.out.println("   借阅人：" + user.getName());
        System.out.println("   所借书籍：" + book.getTitle());
    }
}
