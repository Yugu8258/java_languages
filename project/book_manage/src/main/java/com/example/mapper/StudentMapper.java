package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.example.entity.Student;

public interface StudentMapper {

    @Insert("INSERT INTO db_student (name, gender, age) VALUES (#{name}, #{gender}, #{age})")
    int insertStudent(Student student);

    @Select("SELECT sid, name, gender, age FROM db_student;")
    List<Student> selectAllStudent();

    @Select("SELECT sid, name, gender, age FROM db_student WHERE sid = #{sid}")
    Student selectStudentById(int sid);

}
