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

    private ServletContext sc;
    private Connection con;

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE productId =?";
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM products";

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

    public ProductDao(ServletContext sc) throws ClassNotFoundException, SQLException {
        this.sc = sc;
        con = getConnect(sc);
    }
    
    private Connection getConnect() throws ClassNotFoundException, SQLException {
        DBContext dBContext = new DBContext();
        Connection conn = dBContext.getConnection();
        return conn;
    }

    private Connection getConnect(ServletContext sc) throws ClassNotFoundException, SQLException {
        DBContext dBContext = new DBContext(sc);
        Connection conn = dBContext.getConnection();
        return conn;
    }

    @Override
    public int insertRec(Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateRec(Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteRec(Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getObjectById(String id) {
        Product product = null;
        try {
            makeConnection();
            ps = con.prepareStatement(SELECT_PRODUCT_BY_ID);
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
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeConnect();
        }
        
        return product;
    }

    @Override
    public List<Product> listAll() {
        List<Product> productList = new ArrayList<>();
        try {
            makeConnection();
            ps = con.prepareStatement(SELECT_ALL_PRODUCT);
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
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeConnect();
        }
        return productList;
    }
    
    private void makeConnection() throws ClassNotFoundException, SQLException{
        if(con == null || con.isClosed()){
            con = getConnect();
        }
    }
    
    private void closeConnect(){
        try {
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
