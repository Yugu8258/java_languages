package com.example.service;

import java.util.List;

import com.example.entity.Book;
import com.example.mapper.BookMapper;
import com.example.util.InputUtil;
import com.example.util.SqlUtil;

import lombok.extern.java.Log;

@Log
public class BookService {

    public static void addBook() {
        String title = InputUtil.nextLine("書籍名稱:");
        String desc = InputUtil.nextLine("書籍簡潔:");

        Book book = new Book(null, title, desc);

        SqlUtil.doSqlWork(BookMapper.class, mapper -> {
            int count = mapper.insertBook(book);
            if (count > 0) {
                System.out.println("書籍信息錄入成功: " + book);
                log.info("用戶錄入書籍信息: " + book + " 成功!");
            } else {
                System.err.println("書籍信息錄入失敗");
            }
        });
    }

    public static void listBooks() {
        SqlUtil.doSqlWork(BookMapper.class, mapper -> {
            List<Book> books = mapper.selectAllBook();
            if (!books.isEmpty()) {
                String format = "%-5s %-10s %10s\n";
                String outFormat = "%-7s %-10s %10s\n";

                System.out.printf(format, "編號", "書名", "簡介");

                books.forEach(
                        book -> System.out.printf(outFormat, book.getBid(), book.getTitle(), book.getDescription()));
            } else {
                System.out.println("查無書籍信息");
            }
        });
    }
}
