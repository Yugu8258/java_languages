package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.example.entity.Book;

public interface BookMapper {

    @Insert("INSERT INTO db_book(title, description) VALUES(#{title}, #{description})")
    int insertBook(Book book);

    @Select("SELECT bid, title, description FROM db_book")
    List<Book> selectAllBook();

    @Select("SELECT bid, title, description FROM db_book WHERE bid = #{bid}")
    Book selectBookById(int bid);

}
