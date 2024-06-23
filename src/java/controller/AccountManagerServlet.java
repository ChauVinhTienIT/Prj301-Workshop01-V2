/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.JWAView;
import dao.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Lenovo
 */
public class AccountManagerServlet extends HttpServlet {

    private AccountDao accountDAO;

    @Override
    public void init() {
        accountDAO = new AccountDao(getServletContext());
    }

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
        request.setCharacterEncoding("UTF-8");
        String action = "";
        if(request.getParameter("action") != null){
            action = request.getParameter("action");
        }
        
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertAccount(request, response);
                    break;
                case "delete":
                    deleteAccount(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateAccount(request, response);
                    break;
                case "changeStatus":
                    changeStatus(request, response);
                    break;
                default:
                    listAccount(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
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


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.INSERT_ACCOUNT_JSP);
        dispatcher.forward(request, response);
    }

    private void insertAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        
        String account = request.getParameter("accountName");
        String pass = request.getParameter("password");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String phone = request.getParameter("phone");
        String birthDayRaw = request.getParameter("birthDay");
        String genderRaw = request.getParameter("gender");
        String roleInSystemRaw = request.getParameter("roleInSystem");
        String isUseRaw = request.getParameter("isUse");

        Date birthDay = Date.valueOf(birthDayRaw);
        boolean gender = genderRaw.equals("1");
        int roleInSystem = Integer.parseInt(roleInSystemRaw);
        boolean isUse = (isUseRaw != null);

        Account newAccount = new Account(account, pass, lastName, firstName, birthDay, gender, phone, isUse, roleInSystem);

        int result = accountDAO.insertRec(newAccount);

        response.sendRedirect("account-manager?action=list");
    }

    private void listAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        List<Account> accList = accountDAO.listAll();
        request.setAttribute("accList", accList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.ACCOUNT_LIST_JSP);
        dispatcher.forward(request, response);
    }
    
    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        String account = request.getParameter("account");
        Account toDelete = new Account();
        toDelete.setAccount(account);
        accountDAO.deleteRec(toDelete);
        response.sendRedirect("account-manager?action=list");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        String account = request.getParameter("account");
        Account existAccount = accountDAO.getObjectById(account);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.EDIT_ACCOUNT_JSP);
        request.setAttribute("existAccount", existAccount);
        dispatcher.forward(request, response);
    }
    
    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String phone = request.getParameter("phone");
        String birthDayRaw = request.getParameter("birthDay");
        String genderRaw = request.getParameter("gender");
        String roleInSystemRaw = request.getParameter("roleInSystem");
        String isUseRaw = request.getParameter("isUse");

        Date birthDay = Date.valueOf(birthDayRaw);
        boolean gender = genderRaw.equals("1");
        int roleInSystem = Integer.parseInt(roleInSystemRaw);
        boolean isUse = (isUseRaw != null);
        
        Account updateAccount = new Account(account, "", lastName, firstName, birthDay, gender, phone, isUse, roleInSystem);

        
        int result = accountDAO.updateRec(updateAccount);

        response.sendRedirect("account-manager?action=list");
    }
    
    private void changeStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        String account = request.getParameter("account");
        Account changeAccount = accountDAO.getObjectById(account);
        changeAccount.setAccount(account);
        int result = accountDAO.changeAccountStatus(changeAccount);
        response.sendRedirect("account-manager?action=list");
    }
}
