package com.example.service;

import java.util.List;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.util.InputUtil;
import com.example.util.SqlUtil;

import lombok.extern.java.Log;

@Log
public class StudentService {

    public static void addStudent() {
        String name = InputUtil.nextLine("請輸入學生姓名: ");
        String gender = InputUtil.nextLine("請輸入學生性別('男'/'女'): ", "男", "女");
        int age = InputUtil.nextInt("請輸入學生年齡: ");
        Student student = new Student(null, name, gender, age);
        System.out.println("學生信息錄入成功: " + student);

        SqlUtil.doSqlWork(StudentMapper.class, mapper -> {
            int count = mapper.insertStudent(student);
            if (count > 0) {
                System.out.println("學生信息錄入成功" + student);
                log.info("用回錄入學生信息: " + student + " 成功!");
            } else {
                System.err.println("學生信息錄入失敗");
            }
        });
    }

    public static void listStudents() {
        SqlUtil.doSqlWork(StudentMapper.class, mapper -> {
            List<Student> students = mapper.selectAllStudent();
            if (!students.isEmpty()) {
                String format = "%-5s %-5s %-5s %-5s\n";
                String outFormat = "%-7s %-5s %-5s %-5s\n";

                System.out.printf(format, "學號", "姓名", "性別", "年齡");

                students.forEach(student -> System.out.printf(outFormat, student.getSid(), student.getName(),
                        student.getGender(), student.getAge()));
            } else {
                System.out.println("查無學生信息");
            }
        });
    }

}
