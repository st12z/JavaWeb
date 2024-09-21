/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class Order {
    private int orderId;
    private int customerID;
    private String fullName;
    private String address;
    private String phone;
    private double totalMoney;
    public static int cnt=1;
    public Order(int orderId, int customerID, String fullName, String address, String phone, double totalMoney) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.totalMoney = totalMoney;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    
}
