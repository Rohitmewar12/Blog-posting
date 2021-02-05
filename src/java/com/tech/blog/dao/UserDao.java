
package com.tech.blog.dao;

import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import static java.lang.System.out;
import java.sql.*;

public class UserDao
{
    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    public UserDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //method to insert user to database:
    
   public boolean saveUser(User user)
   {
       boolean f=false;
       try
       {
           //user----> Database           
          
           PreparedStatement pstmt=con.prepareStatement("insert into users values(?,?,?,?,?,?)");
           pstmt.setInt(1,user.getId());
           pstmt.setString(2,user.getName());
           pstmt.setString(3,user.getEmail());
           pstmt.setString(4,user.getPassword());
           pstmt.setString(5,user.getGender());
           pstmt.setString(6,user.getAbout());
           
           int i=pstmt.executeUpdate();
           if(i>0){
           f=true;
           }
       }catch(SQLException e2)
       {
       System.out.println(e2);
       out.println("Error");
       }
       return f;
   }
    
  //  get user by username and userpassword
    
    public User getUserByEmailAndPassword(String email,String password){
        User user=null;
        
        try{
            
            String query="select * from ROHIT.USERS where email like ? and password like ?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet set=pstmt.executeQuery();
            
           if(set.next())
            {
                user=new User();
                // data from database
                String name=set.getString("name");
                //set to user object
                user.setName(name);
                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
              
            }
            
        }catch(Exception e){
            
        }
        
        
        
        return user;
    }

   
    public boolean updateUser(User user){
        boolean f=false;
        try{
            
            String query="update users set name=? , email=? , password=? , gender=? , about=? where id=?";
            PreparedStatement p=con.prepareStatement(query);
            p.setString(1, user.getName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPassword());
            p.setString(4, user.getGender());
            p.setString(5, user.getAbout());
            p.setInt(6, user.getId());
            
            p.executeUpdate();
            f=true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
    return f;
    }
    
    public User getUserByUserId(int userId) throws SQLException
    {
        User user=null;
      try{
            String q="select * from users where Id=?";
            PreparedStatement ps=this.con.prepareStatement(q);
            ps.setInt(1, userId);
            ResultSet set=ps.executeQuery();
          
            if(set.next())
            {
                user=new User();
                // data from database
                String name=set.getString("name");
                //set to user object
                user.setName(name);
                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
           
            }
            }catch(Exception e)
          {
              e.printStackTrace();
          }
        
        return user;
    }

}
