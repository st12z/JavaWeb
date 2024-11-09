/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class Review {
    private Product product;
    private User user;
    private String content;
    private int rating;

    public Review(Product product, User user, String content, int rating) {
        this.product = product;
        this.user = user;
        this.content = content;
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
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
