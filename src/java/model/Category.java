/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Category {
    private int typeId;
    private String categoryName;
    private String memo;

    public Category() {
    }

    public Category(int typeId, String categoryName, String memo) {
        this.typeId = typeId;
        this.categoryName = categoryName;
        this.memo = memo;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getMemo() {
        return memo;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Categories{" + "typeId=" + typeId + ", categoryName=" + categoryName + ", memo=" + memo + '}';
    }
    
    
}
