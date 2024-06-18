/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class Account {
    private String account;
    private String pass;
    private String lastName;
    private String firstName;
    private Date birthDay;
    private boolean gender;
    private String phone;
    private boolean isUse;
    private int roleInSystem;

    public Account() {
    }

    public Account(String account, String pass, String lastName, String firstName, Date birthDay, boolean gender, String phone, boolean isUse, int roleInSystem) {
        this.account = account;
        this.pass = pass;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
        this.isUse = isUse;
        this.roleInSystem = roleInSystem;
    }

    public String getAccount() {
        return account;
    }

    public String getPass() {
        return pass;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getFullName (){
        return firstName + " " + lastName;
    } 

    public Date getBirthDay() {
        return birthDay;
    }

    public boolean isGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isIsUse() {
        return isUse;
    }

    public int getRoleInSystem() {
        return roleInSystem;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsUse(boolean isUse) {
        this.isUse = isUse;
    }

    public void setRoleInSystem(int roleInSystem) {
        this.roleInSystem = roleInSystem;
    }

    @Override
    public String toString() {
        return "Account{" + "account=" + account + ", pass=" + pass + ", lastName=" + lastName + ", firstName=" + firstName + ", birthDay=" + birthDay + ", gender=" + gender + ", phone=" + phone + ", isUse=" + isUse + ", roleInSystem=" + roleInSystem + '}';
    }
    
    
}
