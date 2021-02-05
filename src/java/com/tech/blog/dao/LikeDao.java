
package com.tech.blog.dao;
import java.sql.*;

public class LikeDao{
    Connection con;

    public LikeDao(Connection con){
        this.con=con;
    }
    public boolean insertLike(int pid,int userId)
    {
        boolean f=false;
        try{
        
        PreparedStatement p=this.con.prepareStatement("insert into likes (pid,userid) values(?,?)");
        //values set...
        p.setInt(1, pid);
        p.setInt(2, userId);
        p.executeUpdate();
        f=true;
                
         }catch(Exception e){
             e.printStackTrace();
         }
        return f;
    }
    public int countLikeOnPost(int pid) throws SQLException
    {
        int count=0;
        
        String q="select count(pid) from likes where pid=?";
        try{
            PreparedStatement p=this.con.prepareStatement(q);
            p.setInt(1, pid);
            ResultSet set=p.executeQuery();
            if(set.next())
            {
                count=set.getInt("count(*)");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return count;
    }
    
    public boolean isLikedByUser(int pid, int userId)
    {
        boolean f=false;
        try{
            PreparedStatement p=this.con.prepareStatement("select * from likes where pid=? and userId=?");
            p.setInt(1, pid);
            p.setInt(2, userId);
            
            ResultSet set=p.executeQuery();
            if(set.next())
            {
                f=true;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return f;
    }
    
    public boolean deleteLike(int pid, int userId)
    {
        boolean f=false;
        try{
            PreparedStatement p=this.con.prepareStatement("delete from likes where pid=? and userId=?");
            p.setInt(1, pid);
            p.setInt(2, userId);
            p.executeUpdate();
            f=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
