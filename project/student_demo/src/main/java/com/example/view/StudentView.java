package com.example.view;

import java.util.List;
import java.util.Scanner;

import com.example.model.Student;
import com.example.service.StudentService;

// 視圖層: 控制檯交互界面
public class StudentView {
    private StudentService studentService = new StudentService();
    private List<Student> students = null;
    private Scanner scanner = new Scanner(System.in);
    private Student stu = null;

    // 顯示主菜單
    public void init() {
        System.out.println("\n==== 學生管理系統(mvc) ====");
        System.out.println("1. 添加學生");
        System.out.println("2. 查看所有學生");
        System.out.println("3. 按學號查詢學生");
        System.out.println("4. 按學號修改學生");
        System.out.println("5. 按學號刪除學生");
        System.out.println("6. 退出系統");
        System.out.println("請輸入操作選項(1-6): ");

        functionService();
    }

    public Student getStu() {
        System.out.println("請輸入學生資訊(學號 姓名 年齡 性別): ");

        return stu = new Student(scanner.nextLine(),
                scanner.nextLine(),
                Integer.parseInt(scanner.nextLine()),
                scanner.nextLine());
    }

    public void functionService() {
        while (true) {
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // 添加學生
                    stu = studentService.addStudent(getStu());
                    System.out.println(stu);
                    init();
                    break;
                case "2":
                    // 查看所有學生
                    students = studentService.getAllStudents();
                    System.out.println(students);
                    init();
                    break;
                case "3":
                    // 按學號查詢學生
                    stu = studentService.getStudentById(scanner.nextLine());
                    System.out.println(stu);
                    init();
                    break;
                case "4":
                    // 按學號修改學生
                    stu = studentService.updateStudentById(getStu());
                    System.out.println(stu);
                    init();
                    break;
                case "5":
                    // 按學號刪除學生
                    boolean res = studentService.deleteStudentById(scanner.nextLine());
                    if (res) {
                        System.out.println("刪除成功!");
                    } else {
                        System.out.println("刪除失敗!");
                    }
                    init();
                    break;
                case "6":
                    System.out.println("感謝使用學生管理系統，再見!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("輸入有誤，請重新輸入!");
                    init();
                    break;
            }
        }
    }
}
