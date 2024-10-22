/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class OrderItem {
    private int orderId;
    private int productId;
    private int quantity;
    private int colorId;

    public OrderItem(int orderId, int productId, int quantity, int colorId) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.colorId = colorId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getColorId() {
        return colorId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + ", colorId=" + colorId + '}';
    }
    
    
}
