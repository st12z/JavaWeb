/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DAO;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author T
 */
public class Comment {
    private int commentID;
    private int customerID;
    private Customer customer;
    private String content;
    private Date createAt,updateAt;
    private  String timesince;
    public Comment(int commentID, int customerID,  String content, Date createAt, Date updateAt) {
        DAO d = new DAO();
        this.commentID = commentID;
        this.customerID = customerID;
        this.customer = d.getCustomerByID(customerID);
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        this.timesince = sf.format(createAt);
    }

    public String getTimesince() {
        return timesince;
    }

    public void setTimesince(String timesince) {
        this.timesince = timesince;
    }
    
    public int getCommentID() {
        return commentID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

   
}
