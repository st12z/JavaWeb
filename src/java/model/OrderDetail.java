/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class OrderDetail {
    private int OrderId;
    private int productId;
    private int quantity;

    public OrderDetail(int OrderId, int productId, int quantity) {
        this.OrderId = OrderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return OrderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
