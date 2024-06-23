/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Lenovo
 */
public class UserAuthenticationServlet extends HttpServlet {

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
        String targetUrl = "error.jsp";
        String action = "";
        if(request.getParameter("action") != null){
            action = request.getParameter("action");
        }
        try {
            switch (action) {
                case "login":
                    // Login Aciton
                    loginProcess(request, response);
                    break;
                case "logout":
                    // Logout Action
                    logoutProcess(request, response);
                    break;
                default:
                    showLogin(request, response);
                    break;
            }
        } catch (AssertionError | IOException | ServletException ex) {
            System.out.println(ex.getMessage());
            RequestDispatcher disDispatcher = request.getRequestDispatcher(targetUrl);
            disDispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserAuthenticationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    private void loginProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String destPage = "login.jsp";
        String accountName = request.getParameter("accountName");
        String password = request.getParameter("password");
        AccountDao accountDao = new AccountDao();
        
        
        Account user = accountDao.loginSuccess(accountName, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30*60);
            destPage = "product-manager";
        } else {
            String message = "Invalid Account or Password!";
            request.setAttribute("message", message);
        }

        response.sendRedirect(destPage);
    }

    private void logoutProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destPage = "login.jsp";
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        }
    }
    
    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destPage = "login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    

    
}
