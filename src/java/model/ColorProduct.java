/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class ColorProduct {
    private int colorId;
    private String color;
    private String image;

    public ColorProduct(int colorId, String color, String image) {
        this.colorId = colorId;
        this.color = color;
        this.image = image;
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

    
    
    @Override
    public String toString() {
        return "ColorProduct{" + "color=" + color + ", image=" + image + '}';
    }
    
}
