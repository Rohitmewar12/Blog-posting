
package com.tech.blog.dao;
import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    Connection con;
    
    public PostDao(Connection con){
        this.con=con;
    }
    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> list=new ArrayList<>();
        
        try{
            String q="select * from categories";
            Statement st=this.con.createStatement();
            ResultSet set=st.executeQuery(q);
            
            while(set.next())
            {
                int cid=set.getInt("cid");
                String name=set.getString("name");
                String description=set.getString("description");
                Category c=new Category(cid,name,description);
                list.add(c);
            }
            
        }catch(Exception e){
        e.printStackTrace();    
        }
        return list;
    }
    public boolean savePost(Post p){
        boolean f=false;
        try{
            
            String q="insert into posts(pid,pTitle,pContent,pCode,pPic,cId,userId) values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(q);
            pstmt.setInt(1,p.getPid());
            pstmt.setString(2,p.getpTitle());
            pstmt.setString(3,p.getpContent());
            pstmt.setString(4,p.getpCode());
            pstmt.setString(5,p.getpPic());
            pstmt.setInt(6,p.getcId());
            pstmt.setInt(7,p.getuserId());
            
            pstmt.executeUpdate();
            f=true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }
    
    public List<Post> getAllPosts()
    {
        List<Post> list=new ArrayList<>();
        //fetch all the Post
        try{
             PreparedStatement p=con.prepareStatement("select * from posts order by pid desc");
             ResultSet set=p.executeQuery();
             
             while(set.next())
             {
                 int pid=set.getInt("pid");
                 String pTitle=set.getString("pTitle");
                 String pContent=set.getString("pContent");
                 String pCode=set.getString("pCode");
                 String pPic=set.getString("pPic");
                 int cId=set.getInt("cid");
                 int userId=set.getInt("userId");
                 Post post = new Post(pid, pTitle, pContent, pCode, pPic, cId, userId);
                 
                 list.add(post);
             }
        }catch(Exception e){
                    e.printStackTrace();
                }
        return list;
    }
    
    public List<Post> getPostByCid(int cId)
    {
        List<Post> list=new ArrayList<>();
        //fetch all post by id
         try{
             PreparedStatement p=con.prepareStatement("select * from posts where cid=?");
             p.setInt(1, cId);
             ResultSet set=p.executeQuery();
             
             while(set.next())
             {
                 int pid=set.getInt("pid");
                 String pTitle=set.getString("pTitle");
                 String pContent=set.getString("pContent");
                 String pCode=set.getString("pCode");
                 String pPic=set.getString("pPic");
                 int userId=set.getInt("userId");
                 Post post = new Post(pid, pTitle, pContent, pCode, pPic, cId, userId);
                 
                 list.add(post);
             }
        }catch(Exception e){
                    e.printStackTrace();
                }
        return list;
    }
    
    public Post getPostByPostId(int postId) throws SQLException
    {
        Post post=null;
        String q="select * from posts where pid= ? ";
        
        try{
            PreparedStatement p=con.prepareStatement(q);
                p.setInt(1, postId);
                
                ResultSet set=p.executeQuery();
                
                if(set.next()){
                    
                    int pid=set.getInt("pid");
                 String pTitle=set.getString("pTitle");
                 String pContent=set.getString("pContent");
                 String pCode=set.getString("pCode");
                 String pPic=set.getString("pPic");
                 int cId=set.getInt("cid");
                 int userId=set.getInt("userId");
                 post = new Post(pid, pTitle, pContent, pCode, pPic, cId, userId);
                    
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        return post;
    }
}
