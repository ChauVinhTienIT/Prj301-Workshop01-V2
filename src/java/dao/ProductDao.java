/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.Product;

/**
 *
 * @author Lenovo
 */
public class ProductDao implements Accessible<Product> {

    private Connection connection;
    private ServletContext sc;

    private PreparedStatement ps;
    private ResultSet rs;

    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE productId =?";
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM products";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE productId=?";
    private static final String ADD_PRODUCT = "INSERT INTO products VALUES(?,?,?,?,?,?,?,?,?,?)";
    
    
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_IMAGE = "productImage";
    private static final String BRIEF = "brief";
    private static final String POSTED_DATE = "postedDate";
    private static final String TYPE_ID = "typeId";
    private static final String ACCOUNT = "account";
    private static final String UNIT = "unit";
    private static final String PRICE = "price";
    private static final String DISCOUNT = "discount";

    public ProductDao(){
    }

    public ProductDao(ServletContext sc) {
        this.sc = sc;
    }
    
    private Connection getConnect() throws ClassNotFoundException, SQLException {
        Connection conn;
        if(sc == null){
            DBContext dBContext = new DBContext();
            conn = dBContext.getConnection();
        }else{
            DBContext dBContext = new DBContext(sc);
            conn = dBContext.getConnection();
        }
        return conn;
    }

    @Override
    public int insertRec(Product obj) throws SQLException {
        int result = 0;
        try {
            makeConnection();
            ps = connection.prepareStatement(ADD_PRODUCT);
            ps.setString(1, obj.getProductId());
            ps.setNString(2, obj.getProductName());
            ps.setString(3, obj.getProductImage());
            ps.setString(4, obj.getBrief());
            ps.setDate(5, obj.getPostedDate());
            ps.setInt(6, obj.getTypeId());
            ps.setString(7, obj.getAccount());
            ps.setNString(8, obj.getUnit());
            ps.setInt(9, obj.getPrice());
            ps.setInt(10, obj.getDiscount());
            
            result = ps.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        ps.close();
        disConnect();
        return result;
    }

    @Override
    public int updateRec(Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteRec(Product obj) throws SQLException {
        int result = 0;
        String productId = obj.getProductId();
        try {
            makeConnection();
            ps = connection.prepareStatement(DELETE_PRODUCT);
            ps.setString(1,productId);
            result = ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
        disConnect();
        
        return result;
    }

    @Override
    public Product getObjectById(String id) throws SQLException {
        Product product = null;
        try {
            makeConnection();
            ps = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productId = rs.getString(PRODUCT_ID);
                String productName = rs.getNString(PRODUCT_NAME);
                String productImage = rs.getString(PRODUCT_IMAGE);
                String brief = rs.getNString(BRIEF);
                Date postedDate = rs.getDate(POSTED_DATE);
                int typeId = rs.getInt(TYPE_ID);
                String account = rs.getString(ACCOUNT);
                String unit = rs.getNString(UNIT);
                int price = rs.getInt(PRICE);
                int discount = rs.getInt(DISCOUNT);
                
                product = new Product(productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
        rs.close();
        disConnect();
        return product;
    }

    @Override
    public List<Product> listAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        try {
            makeConnection();
            ps = connection.prepareStatement(SELECT_ALL_PRODUCT);
            rs = ps.executeQuery();

            while (rs.next()) {
                String productId = rs.getString(PRODUCT_ID);
                String productName = rs.getNString(PRODUCT_NAME);
                String productImage = rs.getString(PRODUCT_IMAGE);
                String brief = rs.getNString(BRIEF);
                Date postedDate = rs.getDate(POSTED_DATE);
                int typeId = rs.getInt(TYPE_ID);
                String account = rs.getString(ACCOUNT);
                String unit = rs.getNString(UNIT);
                int price = rs.getInt(PRICE);
                int discount = rs.getInt(DISCOUNT);
                
                Product product = new Product(productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount);
                productList.add(product);
           
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
        rs.close();
        disConnect();
        return productList;
    }
    
    private void makeConnection() throws ClassNotFoundException, SQLException{
        if(connection == null || connection.isClosed()){
            connection = getConnect();
        }
    }
    
    private void disConnect() throws SQLException{
        if(connection != null && !connection.isClosed()){
            connection.close();
        }
    }
}
