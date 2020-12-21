package test;

import mybatis.Domain.*;
import org.junit.Test;

import java.sql.*;

public class JDBCTest {

    @Test
    public void TestJDBC() throws SQLException {

        Connection conn = null;
        Statement statement = null;
        Blog blog = new Blog();
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
            statement = conn.createStatement();

            String sql = "SELECT * FROM BLOG";

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Integer bid = rs.getInt("bid");
                String name = rs.getString("name");
                Integer authorId = rs.getInt("author_id");
                blog.setAuthorId(authorId);
                blog.setBid(bid);
                blog.setName(name);
                System.out.println(blog);
            }


            rs.close();
            statement.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (statement != null ) statement.close();
            if (conn != null){ conn.close();
            }
        }


    }
}
