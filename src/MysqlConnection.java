/**
 * Created by Evilina on 2016/3/19.
 */

import java.io.File;
import java.sql.*;
import test.lucene.*;
import test.lucene.preprocess.FilePreprocess;

public class MysqlConnection {
    //jdbc driver name and database URL
    // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
    // 避免中文乱码要指定useUnicode和characterEncoding
    // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
    // 下面语句之前就要先创建javademo数据库
    //String url = "jdbc:mysql://localhost:3306/javademo?"
    //        + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/av";


    //Database credentials
    static final String USER = "root";
    static final String PASS = "19941126";

    public static void connectionMysql()
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // step2 register jdbc driver
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();
            Class.forName("com.mysql.jdbc.Driver");

            //step3 open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //step4 execute a query
            stmt = conn.createStatement();
            String sql = null;
            sql = "select * from film";
            ResultSet rs = stmt.executeQuery(sql);

            //step5 extract data from result set
            while (rs.next())
            {
                //retrieve by column name
                int id = rs.getInt("id");
                System.out.println("id" + id);
            }

            //step6 clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException se)
        {
            //handle errors for jdbc
            se.printStackTrace();

        }
        catch (Exception e)
        {
            //handle errors for class.forName
            e.printStackTrace();
        }
        finally
        {
            //finally block used to close resources
            try
            {
                if(stmt != null)
                {
                    stmt.close();
                }
            }
            catch (SQLException se2)
            {
                //nothing we can do
            }
            try
            {
                if(conn != null)
                {
                    conn.close();
                }
            }
            catch (SQLException se3)
            {
                se3.printStackTrace();
            }
        }

    }

    public static void main(String[] args)
    {

        //connectionMysql();
        //new FilePreprocess().doIt();
    }

}
