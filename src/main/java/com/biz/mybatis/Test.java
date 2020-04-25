package com.biz.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.concurrent.Executors;

/**
 * @author xjn
 * @since 2020-03-12
 */
public class Test {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = new FileInputStream(new File("/Users/xujinniu/code/algorithms/src/main/java/com/biz/mybatis/mybatis.xml"));
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getConfiguration().addMapper(DebtMapper.class);
        DebtMapper mapper = sqlSession.getMapper(DebtMapper.class);
        BigDecimal select = mapper.select();
        System.out.println(select);
    }
}
