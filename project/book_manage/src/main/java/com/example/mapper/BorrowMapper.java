package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.entity.Borrow;

public interface BorrowMapper {

    @Insert("INSERT INTO db_borrow(sid, bid) VALUES(#{sid}, #{bid})")
    int addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Results({
            @Result(column = "sid", property = "student", one = @One(select = "com.example.mapper.StudentMapper.selectStudentById")),
            @Result(column = "bid", property = "book", one = @One(select = "com.example.mapper.BookMapper.selectBookById"))
    })
    @Select("SELECT id, sid, bid FROM db_borrow")
    List<Borrow> selectAllBorrow();

}
