package test;

import mybatis.Domain.*;
import mybatis.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException {
        String source = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(source);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void testMybatis() {
        SqlSession sqlSession = null;
        try {

            sqlSession = sqlSessionFactory.openSession();
            Blog blog = (Blog) sqlSession.selectOne("mybatis.mapper.BlogMapper.selectBlogById", 1);
            System.out.println(blog);

        } finally {
            if (null != sqlSession) sqlSession.close();
        }

    }

    @Test
    public void testMybatisMapper() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {

            BlogMapper blogMapper =  sqlSession.getMapper(BlogMapper.class);
            Blog blog = blogMapper.selectBlogById(1);
            System.out.println(blog);

        } finally {
            if (null != sqlSession) sqlSession.close();
        }

    }
}
