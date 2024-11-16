/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author T
 */
public class Product {
    private String id,name;
    private int quantity;
    private double price;
    private Date releaseDate;
    private String image;
    private Date createdAt,updatedAt;
    private String status;
    private double discountPercentage;
    private String promotion;
    private String warranty;
    private int deleted;
    private Category category;
    private String color;
    private int rating;
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", releaseDate=" + releaseDate + ", image=" + image + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + ", discountPercentage=" + discountPercentage + ", promotion=" + promotion + ", warranty=" + warranty + ", deleted=" + deleted + ", category=" + category + '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public Product(String id, String name, int quantity, double price, Date releaseDate, String image, Date createdAt, Date updatedAt, String status, double discountPercentage, String promotion, String warranty, int deleted, Category category,int rating) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.releaseDate = releaseDate;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.discountPercentage = discountPercentage;
        this.promotion = promotion;
        this.warranty = warranty;
        this.deleted = deleted;
        this.category = category;
        this.rating=rating;
    }

    public String getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }
    
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getImage() {
        return image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getWarranty() {
        return warranty;
    }

    public int getDeleted() {
        return deleted;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
     public String getPriceVND(){
        return helper.helperClass.moneyVND(price);
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
