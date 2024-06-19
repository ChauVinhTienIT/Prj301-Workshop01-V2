/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Product;

/**
 *
 * @author Lenovo
 */
public class ProductManagerServlet extends HttpServlet {
    
    private ProductDao productDao;

    @Override
    public void init() {
        productDao = new ProductDao(getServletContext());
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
                    insertProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "edit":
                    //showEditForm(request, response);
                    break;
                case "update":
                    //updateAccount(request, response);
                    break;
                case "changeStatus":
                    //changeStatus(request, response);
                    break;
                default:
                    listProduct(request, response);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Product> productList = productDao.listAll();
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String productId = request.getParameter("productId");
        System.out.println(productId);
        Product toDelete = new Product();
        toDelete.setProductId(productId);
        int result = productDao.deleteRec(toDelete);
        response.sendRedirect("product-manager?action=list");
    }
    
    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
        request.setCharacterEncoding("UTF-8");
        
        
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String brief = request.getParameter("brief");
        String typeIdRaw = request.getParameter("typeId");
        String unitRaw = request.getParameter("unit");
        
        String priceRaw = request.getParameter("price");
        String discountRaw = request.getParameter("discount");
        
        int typeId = Integer.parseInt(typeIdRaw);
        int price = Integer.parseInt(priceRaw);
        int discount = Integer.parseInt(discountRaw);
        
        Account currentUser = new Account();
        currentUser.setAccount("admin");
        Date postedDateRaw = new Date();
        java.sql.Date postedDate = new java.sql.Date(postedDateRaw.getTime());
        
        //String productImage = ;
        //Date postedDate;
        
        String unit = "";
        switch (unitRaw){
            case "1":
                unit = "Cái";
                break;
            case "2":
                unit = "Chiếc";
                break;
            case "3":
                unit = "Bộ";
                break;
        }
        
        Product newProduct = new Product(productId, productName, "/notYet", brief, postedDate, typeId, currentUser.getAccount(), unit, price, discount);

        int result = productDao.insertRec(newProduct);

        response.sendRedirect("product-manager?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("insertProductForm.jsp");
        dispatcher.forward(request, response);
    }
    
    
}
