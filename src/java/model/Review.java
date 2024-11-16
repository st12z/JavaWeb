/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author T
 */
public class Review {

    private Product product;
    private User user;
    private String content;
    private int rating;
    private Date createdAt;
    private int id;
    public Review(int id,Product product, User user, String content, int rating, Date createdAt) {
        this.id=id;
        this.product = product;
        this.user = user;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" + "product=" + product + ", user=" + user + ", content=" + content + ", rating=" + rating + '}';
    }

}
