package com.example.util;

import java.io.IOException;
import java.util.function.Consumer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.extern.java.Log;

@Log
public class SqlUtil {
    private static SqlSessionFactory factory;
    private static SqlSession sqlSession;

    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            sqlSession = factory.openSession(true);
        } catch (IOException e) {
            log.warning("MyBatis 初始化失敗: " + e.getMessage());
        }
    }

    public static <T> void doSqlWork(Class<T> mapperClass, Consumer<T> consumer) {
        consumer.accept(takeMapper(mapperClass));
    }

    public static <T> T takeMapper(Class<T> mapperClass) {
        return sqlSession.getMapper(mapperClass);
    }
}
