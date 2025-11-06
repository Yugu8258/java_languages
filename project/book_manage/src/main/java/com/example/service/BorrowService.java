package com.example.service;

import java.util.List;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.Student;
import com.example.mapper.BookMapper;
import com.example.mapper.BorrowMapper;
import com.example.mapper.StudentMapper;
import com.example.util.InputUtil;
import com.example.util.SqlUtil;

import lombok.extern.java.Log;

@Log
public class BorrowService {

    public static void addBorrow() {
        StudentMapper studentMapper = SqlUtil.takeMapper(StudentMapper.class);
        BookMapper bookMapper = SqlUtil.takeMapper(BookMapper.class);

        Student student = InputUtil.nextEntity("請輸入借閱人學號: ", studentMapper::selectStudentById);

        BookService.listBooks();

        bookMapper.selectAllBook();

        Book book = InputUtil.nextEntity("請輸入借閱書籍編號: ", bookMapper::selectBookById);

        SqlUtil.doSqlWork(BorrowMapper.class, mapper -> {
            int count = mapper.addBorrow(student.getSid(), book.getBid());
            if (count > 0) {
                System.out.println("借閱成功" + student.getName() + " 借閱了 " + book.getTitle());
                log.info("用戶: " + student.getName() + " 借閱了 " + book.getTitle());
            } else {
                System.out.println("借閱失敗");
            }
        });
    }

    public static void listBorrows() {
        SqlUtil.doSqlWork(BorrowMapper.class, mapper -> {
            List<Borrow> borrows = mapper.selectAllBorrow();
            if (!borrows.isEmpty()) {
                String format = "%-5s %-10s %-10s \n";
                String outFormat = "%-7s %-10s %-10s \n";

                System.out.printf(format, "編號", "姓名", "書籍");

                borrows.forEach(borrow -> System.out.printf(outFormat, borrow.getStudent().getSid(),
                        borrow.getStudent().getName(), borrow.getBook().getTitle()));
            } else {
                System.out.println("查無借閱信息");
            }
        });
    }

}
