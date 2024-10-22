/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author T
 */
public class OrderDetail {
    private int orderId;
    private int userId;
    private String fullName;
    private String address;
    private String phone;
    private double totalPayment;
    List<Item> list = new ArrayList<>();
    public OrderDetail(int userId, String fullName, String address, String phone, double totalPayment) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.totalPayment = totalPayment;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
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

    public double getTotalPayment() {
        return totalPayment;
    }
    public String getTotalMoneyVND() {
        return helper.helperClass.moneyVND(totalPayment);

    }
    @Override
    public String toString() {
        return "OrderDetail{" + "orderId=" + orderId + ", userId=" + userId + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone + ", totalPayment=" + totalPayment + ", list=" + list + '}';
    }

    
    
    
}
