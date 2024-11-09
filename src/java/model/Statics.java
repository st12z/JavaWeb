/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class Statics {
    private Product product;
    private String screen,camera,
            processor,graphics,storage,battery,weight;

    public Statics(Product product, String screen, String camera, String processor, String graphics, String storage, String battery, String weight) {
        this.product = product;
        this.screen = screen;
        this.camera = camera;
        this.processor = processor;
        this.graphics = graphics;
        this.storage = storage;
        this.battery = battery;
        this.weight = weight;
    }

    public Product getProduct() {
        return product;
    }

    public String getScreen() {
        return screen;
    }

    public String getCamera() {
        return camera;
    }

    public String getProcessor() {
        return processor;
    }

    public String getGraphics() {
        return graphics;
    }

    public String getStorage() {
        return storage;
    }

    public String getBattery() {
        return battery;
    }

    public String getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Statics{" + "product=" + product + ", screen=" + screen + ", camera=" + camera + ", processor=" + processor + ", graphics=" + graphics + ", storage=" + storage + ", battery=" + battery + ", weight=" + weight + '}';
    }
    
}
