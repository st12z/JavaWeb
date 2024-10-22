/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DAO;

/**
 *
 * @author T
 */
public class Item {
    private Product product;
    private int quantity;
    private int colorId;
    private String color;
    private String image;

    public Item(Product product, int quantity, int colorId, String color, String image) {
        this.product = product;
        this.quantity = quantity;
        this.colorId = colorId;
        this.color = color;
        this.image = image;
    }
    
    public String getMoneyVND(){
        return helper.helperClass.moneyVND(product.getPrice()*quantity);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getColorId() {
        return colorId;
    }

    public String getColor() {
        return color;
    }

    public String getImage() {
        return image;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "Item{" + "product=" + product + ", quantity=" + quantity + ", colorId=" + colorId + ", color=" + color + ", image=" + image + '}';
    }

    
    
}
