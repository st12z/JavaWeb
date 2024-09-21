/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DAO;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author T
 */
public class Cart {

    private List<Item> items;

    public Cart() {
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getItemById(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId().equals(id)) {
                return items.get(i);
            }
        }
        return null;
    }

    public int getQuantityById(String id) {
        for (Item i : items) {
            if (i.getProduct().getId().equals(id)) {
                return i.getQuantity();
            }
        }
        return 0;
    }

    public void addItem(Item t) {
        if (getItemById(t.getProduct().getId()) != null) {
            Item m = getItemById(t.getProduct().getId());
            m.setQuantity(t.getQuantity() + m.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(String id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public double getTotalMoney() {
        double total = 0;
        for (Item i : items) {
            total += i.getQuantity() * i.getProduct().getPrice();
        }
        return total;
    }

    public String getTotalMoneyVND() {
        return helper.helperClass.moneyVND(getTotalMoney());

    }

    public Cart(String txt) {
        DAO d = new DAO();
        items = new ArrayList<>();
        System.out.println(txt);
        String a[] = txt.split("-");

        for (String s : a) {
            Product p = d.getProduct(s);
            if (p != null) {
                addItem(new Item(p, 1));
            }
        }
    }

    public static void main(String[] args) {

    }
}
