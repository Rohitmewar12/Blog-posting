package com.tech.blog.servlets;

import com.tech.blog.dao.PostDao;
import com.tech.blog.entities.Post;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class AddPostServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            int Cid=Integer.parseInt(request.getParameter("Cid"));
            int pid=Integer.parseInt(request.getParameter("pid"));
            String pTitle=request.getParameter("pTitle");
            String pContent=request.getParameter("pContent");
            String pCode=request.getParameter("pCode");
            Part part=request.getPart("pic");
            // getting current user id
            
            HttpSession session=request.getSession();
            User user=(User)session.getAttribute("currentUser");
            
         /*   out.println("Your post is "+pTitle);
            out.println(part.getSubmittedFileName());
            out.println("Your post is "+Cid);
            out.println("Your post is "+pid);
            out.println("Your post is "+pContent);
            out.println("Your post is "+pCode);
           */ 
            
            Post p=new Post(pid, pTitle, pContent, pCode, part.getSubmittedFileName(), Cid, user.getId());
            PostDao dao=new PostDao(ConnectionProvider.getConnection());
            if(dao.savePost(p))
            {
                out.println("Done");
            }else
            {
                out.println("error");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
