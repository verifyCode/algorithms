package com.biz.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author xjn
 * @since 2020-03-12
 */
public class Test {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = new FileInputStream(new File("/Users/xujinniu/code/algorithms/src/main/java/com/mybatis/mybatis.xml"));
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getConfiguration().addMapper(DebtMapper.class);
        DebtMapper mapper = sqlSession.getMapper(DebtMapper.class);
        System.out.println(mapper.selectDebt());
//        List<Map> maps1 = mapper.selectDebt();
//        List<Map> maps3 = mapper.selectDebt();
//
//        SqlSession sqlSession2 = sqlSessionFactory.openSession();
//        DebtMapper mapper1 = sqlSession2.getMapper(DebtMapper.class);
//        List<Map> maps2 = mapper1.selectDebt();
//        System.out.println(maps1 == maps2);
//        System.out.println(maps1 == maps3);
    }
}
