/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.Category;

/**
 *
 * @author Lenovo
 */
public class CategoryDao implements Accessible<Category> {

    private ServletContext sc;
    private Connection con;

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM categories WHERE typeId =?";
    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM categories";
    
    private static final String TYPE_ID = "typeId";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String MEMO = "memo";
    

    public CategoryDao(){
    }

    public CategoryDao(ServletContext sc) throws ClassNotFoundException, SQLException {
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
    public int insertRec(Category obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateRec(Category obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteRec(Category obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getObjectById(String id) {
        int typeId = Integer.parseInt(id);
        Category category = null;
        try {
            makeConnection();
            ps = con.prepareStatement(SELECT_CATEGORY_BY_ID);
            ps.setInt(1, typeId);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                typeId = rs.getInt(TYPE_ID);
                String categoryName = rs.getNString(CATEGORY_NAME);
                String memo = rs.getNString(MEMO);
                
                category = new Category(typeId, categoryName, memo);

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeConnect();
        }
        return category;
    }

    @Override
    public List<Category> listAll() {
        List<Category> cateList = new ArrayList<>();
        try {
            makeConnection();
            ps = con.prepareStatement(SELECT_ALL_CATEGORY);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int typeId = rs.getInt(TYPE_ID);
                String categoryName = rs.getNString(CATEGORY_NAME);
                String memo = rs.getNString(MEMO);
                
                Category category = new Category(typeId, categoryName, memo);
                cateList.add(category);
                
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeConnect();
        }
        return cateList;
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
