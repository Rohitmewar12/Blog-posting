
package com.tech.blog.helper;
import static java.lang.System.out;
import java.sql.*;

public class ConnectionProvider 
{
    private static Connection con;
    public static Connection getConnection()
    {
        try
        {
            if(con==null){
            //driver class load
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            //create a connection
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/first_projectdb", "rohit", "1997");
            
            }
          } catch(ClassNotFoundException | SQLException e)
        {
            out.println("Error");
        }
        return con;
    }
}
