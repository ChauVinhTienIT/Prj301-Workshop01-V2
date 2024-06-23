/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

/**
 *
 * @author Lenovo
 */
public interface JWAView {
    public String APP_CONTEXT = "/Prj301-Workshop01-V2";
    public String PRIVATE_FOLDER = "/private";
    
    public String ERROR_JSP = "error.jsp";
    public String HEADER_JSP = "header.jspf";
    public String LOGIN_JSP = "login.jsp";
    public String HOME_JSP = "home.jsp";
    public String ITEM_JSP = "shopItem.jsp";
  
    public String ACCOUNT_LIST_JSP = String.format("%s/%s", PRIVATE_FOLDER, "listAccount.jsp");
    public String CATEGORY_LIST_JSP = String.format("%s/%s", PRIVATE_FOLDER, "listCategory.jsp");
    public String PRODUCT_LIST_JSP = String.format("%s/%s", PRIVATE_FOLDER, "listProduct.jsp");
    
    public String EDIT_ACCOUNT_JSP = String.format("%s/%s", PRIVATE_FOLDER, "editAccountForm.jsp");
    public String EDIT_CATEGORY_JSP = String.format("%s/%s", PRIVATE_FOLDER, "editCategoryForm.jsp");
    public String EDIT_PRODUCT_JSP = String.format("%s/%s", PRIVATE_FOLDER, "editProductForm.jsp");
    
    public String INSERT_ACCOUNT_JSP = String.format("%s/%s", PRIVATE_FOLDER, "insertAccountForm.jsp");
    public String INSERT_CATEGORY_JSP = String.format("%s/%s", PRIVATE_FOLDER, "insertCategoryForm.jsp");
    public String INSERT_PRODUCT_JSP = String.format("%s/%s", PRIVATE_FOLDER, "insertProductForm.jsp");
    
}
