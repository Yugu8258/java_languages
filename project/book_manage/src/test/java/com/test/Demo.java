package com.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.mapper.BookMapper;
import com.example.mapper.BorrowMapper;
import com.example.mapper.StudentMapper;
import com.example.util.SqlUtil;

public class Demo {

    @Test
    @DisplayName("測試學生信息")
    public void selectStudentTest() {
        SqlUtil.doSqlWork(StudentMapper.class, StudentMapper::selectAllStudent);
    }

    @Test
    @DisplayName("測試書籍信息")
    public void selectBookTest() {
        SqlUtil.doSqlWork(BookMapper.class, BookMapper::selectAllBook);
    }

    @Test
    @DisplayName("測試借閱信息")
    public void selectBookByStudentTest() {
        SqlUtil.doSqlWork(BorrowMapper.class, BorrowMapper::selectAllBorrow);
    }

}
