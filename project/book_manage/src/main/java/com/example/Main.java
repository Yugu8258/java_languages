package com.example;

import java.io.IOException;
import java.util.logging.LogManager;

import org.apache.ibatis.io.Resources;

import com.example.service.BookService;
import com.example.service.BorrowService;
import com.example.service.StudentService;
import com.example.util.InputUtil;

import lombok.extern.java.Log;

@Log
public class Main {
    private static String prompt = """
            ====== 圖書管理系統 ======
            1. 錄入學生信息
            2. 錄入書籍信息
            3. 錄入借閱信息
            4. 查詢學生信息
            5. 查詢書籍信息
            6. 查詢借閱情況
            7. 退出系統
            請輸入選項 (1-7):
            """;

    public static void main(String[] args) {
        try {
            init();
        } catch (IOException e) {
            log.warning("logging 文件初始化失敗: " + e.getMessage());
        }
    }

    public static void init() throws IOException {
        LogManager.getLogManager().readConfiguration(Resources.getResourceAsStream("logging.properties"));

        while (true) {
            int choice = InputUtil.nextInt(prompt);
            if (choice <= 6 && choice >= 1) {
                switch (choice) {
                    case 1 -> StudentService.addStudent();
                    case 2 -> BookService.addBook();
                    case 3 -> BorrowService.addBorrow();
                    case 4 -> StudentService.listStudents();
                    case 5 -> BookService.listBooks();
                    case 6 -> BorrowService.listBorrows();
                }
            } else if (choice == 7) {
                System.out.println("退出系統，謝謝使用！");
                break;
            } else {
                System.out.println("無效選項，請重新輸入。");
            }
        }
        System.out.println("系統已關閉。");
    }
}
