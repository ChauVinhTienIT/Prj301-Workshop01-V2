/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.JWAView;
import dao.ProductDao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;
import model.Product;

/**
 *
 * @author Lenovo
 */

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 50 //50MB
)

public class ProductManagerServlet extends HttpServlet {
    
    private ProductDao productDao;
    private static final String SAVE_DIRECTORY = "images/sanPham";

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
                    showEditForm(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.PRODUCT_LIST_JSP);
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String productId = request.getParameter("productId");
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
        
        String productImage = uploadImage(request, response);
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
        
        Product newProduct = new Product(productId, productName, productImage, brief, postedDate, typeId, currentUser.getAccount(), unit, price, discount);

        int result = productDao.insertRec(newProduct);

        response.sendRedirect("product-manager?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.INSERT_PRODUCT_JSP);
        dispatcher.forward(request, response);
    }
    
    private String uploadImage(HttpServletRequest request, HttpServletResponse response){
        String fileName = "";
        String realPart = "";
        String fileDir = "";
        try {
            
            Part part = request.getPart("image");
            realPart = request.getServletContext().getRealPath(SAVE_DIRECTORY);
            fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Paths.get(realPart))) {
                Files.createDirectory(Paths.get(realPart));
            }
            part.write(realPart + "/" + fileName);
        } catch (Exception ex){
            
        }
        fileDir = String.format("/%s/%s", SAVE_DIRECTORY, fileName);
        System.out.println(fileDir);
        return fileDir;
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String account = request.getParameter("productId");
        Product existProduct = productDao.getObjectById(account);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JWAView.EDIT_PRODUCT_JSP);
        request.setAttribute("existProduct", existProduct);
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
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
        
        String productImage = uploadImage(request, response);
        if(productImage.equals(String.format("/%s/",SAVE_DIRECTORY))){
            productImage = productDao.getObjectById(productId).getProductImage();
        }
        
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
        
        Product newProduct = new Product(productId, productName, productImage, brief, postedDate, typeId, currentUser.getAccount(), unit, price, discount);
        
        int result = productDao.updateRec(newProduct);

        response.sendRedirect("product-manager?action=list");
    }
}
