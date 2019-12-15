/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaServletExample.servlet;

import JavaServletExample.bean.UserBean;
import JavaServletExample.dao.Dao;
import JavaServletExample.dao.UserDao;
import JavaServletExample.service.registrationService;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sam
 */

@WebServlet(name = "UserServlet", urlPatterns = { "/user" }, loadOnStartup = 1)
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        // Set response content type
        response.setContentType("text/html");
        
//        String username = request.getParameter("username");
//        String userpassword = request.getParameter("userpassword");
//        
//        UserBean userbean = new UserBean(username, userpassword);
//        
//        Dao user_dao = new UserDao();
//        user_dao.insert(userbean);
//        
//
//        // Actual logic goes here.
//        PrintWriter out = response.getWriter();
//        out.println("<h2>a: " + userbean.getUsername() + "</h2>");
//        out.println("<h2>b: " + userbean.getPassword() + "</h2>");
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
        
        String action = request.getParameter("action");
        if (action == "registration") {
            /*
                {
                    "action": "registration",
                    "firstname": "firstname",
                    "lastname": "lastname",
                    "email": "email",
                    "username": "username",
                    "password": "password"
                }
            */
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            new registrationService(firstname, lastname, email, username, password).registerUser();          
            
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            Writer out=response.getWriter();
            out.write("¥Î?¦W¡G"+username);
            out.write("±K?¡G"+password);
            out.flush();
            out.close();
        } else if (action == "login") {
            
        }
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