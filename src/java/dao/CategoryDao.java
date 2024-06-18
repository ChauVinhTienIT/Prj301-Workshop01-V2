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
    private Connection connection;

    PreparedStatement ps = null;
    ResultSet rs = null;
    
    private static final String ADD_CATEGORY = "INSERT INTO categories(categoryName,memo) VALUES(?,?)";
    private static final String DELETE_CATEGORY = "DELETE FROM categories WHERE typeId=?";
    private static final String UPDATE_CATEGORY = "UPDATE categories "
                                               + "SET categoryName = ?, memo = ? "
                                               + "WHERE typeId = ?";
    
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM categories WHERE typeId =?";
    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM categories";
    
    private static final String TYPE_ID = "typeId";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String MEMO = "memo";
    

    public CategoryDao(){
    }

    public CategoryDao(ServletContext sc){
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
    public int insertRec(Category obj) throws SQLException {
        int result = 0;
        try {
            makeConnection();
            ps = connection.prepareStatement(ADD_CATEGORY);
            ps.setNString(1, obj.getCategoryName());
            ps.setNString(2, obj.getMemo());
            
            result = ps.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        ps.close();
        disConnect();
        return result;
    }

    @Override
    public int updateRec(Category obj) throws SQLException {
        int result = 0;
        try {
            connection = getConnect();
            ps = connection.prepareStatement(UPDATE_CATEGORY);
            ps.setNString(1, obj.getCategoryName());
            ps.setNString(2, obj.getMemo());
            ps.setInt(3, obj.getTypeId());
            result = ps.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
        disConnect();
        return result;
    }

    @Override
    public int deleteRec(Category obj) throws SQLException {
        int result = 0;
        int typeId = obj.getTypeId();
        try {
            makeConnection();
            ps = connection.prepareStatement(DELETE_CATEGORY);
            ps.setInt(1,typeId);
            result = ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
        disConnect();
        
        return result;
    }

    @Override
    public Category getObjectById(String id) throws SQLException {
        int typeId = Integer.parseInt(id);
        Category category = null;
        try {
            makeConnection();
            ps = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
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
        } 
        ps.close();
        rs.close();
        disConnect();
        
        return category;
    }

    @Override
    public List<Category> listAll() throws SQLException {
        List<Category> cateList = new ArrayList<>();
        try {
            makeConnection();
            ps = connection.prepareStatement(SELECT_ALL_CATEGORY);
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
        }
        ps.close();
        rs.close();
        disConnect();
        
        return cateList;
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
