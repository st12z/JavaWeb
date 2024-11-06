/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class User {
    private int id ;
    private String fullName,password,token,email,avatar,cartId;

    public User( int id,String fullName, String password, String token, String email, String avatar, String cartId) {
        this.id=id;
        this.fullName = fullName;
        this.password = password;
        this.token = token;
        this.email = email;
        this.avatar = avatar;
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCartId() {
        return cartId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullName=" + fullName + ", password=" + password + ", token=" + token + ", email=" + email + ", avatar=" + avatar + ", cartId=" + cartId + '}';
    }
    
}
