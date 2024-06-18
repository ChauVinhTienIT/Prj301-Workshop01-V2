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
import model.Account;

/**
 *
 * @author Lenovo
 */
public class AccountDao implements Accessible<Account> {

    private Connection connection;
    private ServletContext sc;

    private PreparedStatement ps;
    private ResultSet rs;

    private static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE account =?";
    private static final String SELECT_ALL_ACCOUNT = "SELECT * FROM accounts";
    private static final String CHECK_LOGIN = "SELECT * FROM accounts WHERE account=? AND pass=?";
    private static final String ADD_ACCOUNT = "INSERT INTO accounts VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_ACCOUNT = "DELETE FROM accounts WHERE account=?";
    private static final String UPDATE_ACCOUNT = "UPDATE accounts "
                                               + "SET lastName = ?, firstName = ?, birthday = ?, gender = ?, phone = ?, isUse = ?, roleInSystem = ? "
                                               + "WHERE account = ?";
    
    private static final String ACCOUNT = "account";
    private static final String PASS = "pass";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String BIRTH_DAY = "birthday";
    private static final String GENDER = "gender";
    private static final String PHONE = "phone";
    private static final String IS_USE = "isUse";
    private static final String ROLE_IN_SYSTEM = "roleInSystem";


    public AccountDao(){
    }
    
    public AccountDao(ServletContext sc){
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
    public int insertRec(Account obj) throws SQLException{
        int result = 0;
        try {
            makeConnection();
            ps = connection.prepareStatement(ADD_ACCOUNT);
            ps.setString(1, obj.getAccount());
            ps.setString(2, obj.getPass());
            ps.setString(3, obj.getLastName());
            ps.setString(4, obj.getFirstName());
            ps.setDate(5, obj.getBirthDay());
            ps.setBoolean(6, obj.isGender());
            ps.setString(7, obj.getPhone());
            ps.setBoolean(8, obj.isIsUse());
            ps.setInt(9, obj.getRoleInSystem());
            
            result = ps.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        ps.close();
        disConnect();
        return result;
    }

    @Override
    public int updateRec(Account obj) throws SQLException {
        int result = 0;
        try {
            connection = getConnect();
            ps = connection.prepareStatement(UPDATE_ACCOUNT);
            ps.setString(1, obj.getLastName());
            ps.setString(2, obj.getFirstName());
            ps.setDate(3, obj.getBirthDay());
            ps.setBoolean(4, obj.isGender());
            ps.setString(5, obj.getPhone());
            ps.setBoolean(6, obj.isIsUse());
            ps.setInt(7, obj.getRoleInSystem());
            ps.setString(8, obj.getAccount());
            result = ps.executeUpdate();
      
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
        disConnect();
        return result;
    }

    @Override
    public int deleteRec(Account obj) throws SQLException {
        int result = 0;
        String account = obj.getAccount();
        try {
            makeConnection();
            ps = connection.prepareStatement(DELETE_ACCOUNT);
            ps.setString(1,account);
            result = ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
        disConnect();
        
        return result;
    }

    @Override
    public Account getObjectById(String id) throws SQLException {
        Account acc = null;
        try {
            makeConnection();
            ps = connection.prepareStatement(SELECT_ACCOUNT_BY_ID);
            ps.setString(1, id);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                String account = rs.getString(ACCOUNT);
                String pass = rs.getString(PASS);
                String lastName = rs.getNString(LAST_NAME);
                String firstName = rs.getNString(FIRST_NAME);
                Date birthDay = rs.getDate(BIRTH_DAY);
                boolean gender = rs.getBoolean(GENDER);
                String phone = rs.getNString(PHONE);
                boolean isUse = rs.getBoolean(IS_USE);
                int roleInSystem = rs.getInt(ROLE_IN_SYSTEM);
                
                acc = new Account(account, pass, lastName, firstName, birthDay, gender, phone, isUse, roleInSystem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        rs.close();
        ps.close();
        disConnect();
        
        return acc;
    }

    @Override
    public List<Account> listAll() throws SQLException {
        List<Account> accList = new ArrayList<>();
        try {
            makeConnection();
            ps = connection.prepareStatement(SELECT_ALL_ACCOUNT);
            rs = ps.executeQuery();

            while (rs.next()) {
                String account = rs.getString(ACCOUNT);
                String pass = rs.getString(PASS);
                String lastName = rs.getNString(LAST_NAME);
                String firstName = rs.getNString(FIRST_NAME);
                Date birthDay = rs.getDate(BIRTH_DAY);
                boolean gender = rs.getBoolean(GENDER);
                String phone = rs.getNString(PHONE);
                boolean isUse = rs.getBoolean(IS_USE);
                int roleInSystem = rs.getInt(ROLE_IN_SYSTEM);
                
                Account acc = new Account(account, pass, lastName, firstName, birthDay, gender, phone, isUse, roleInSystem);
                accList.add(acc);
                
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        rs.close();
        ps.close();
        disConnect();
        
        return accList;
    }

    
    public Account loginSuccess(String account, String password) throws SQLException{
        Account acc = null;
        try {
            makeConnection();
            
            ps = connection.prepareStatement(CHECK_LOGIN);
            ps.setString(1, account);
            ps.setString(2, password);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                String pass = rs.getString(PASS);
                String lastName = rs.getNString(LAST_NAME);
                String firstName = rs.getNString(FIRST_NAME);
                Date birthDay = rs.getDate(BIRTH_DAY);
                boolean gender = rs.getBoolean(GENDER);
                String phone = rs.getNString(PHONE);
                boolean isUse = rs.getBoolean(IS_USE);
                int roleInSystem = rs.getInt(ROLE_IN_SYSTEM);
                
                acc = new Account(account, pass, lastName, firstName, birthDay, gender, phone, isUse, roleInSystem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        rs.close();
        ps.close();
        disConnect();
        
        return acc;
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
