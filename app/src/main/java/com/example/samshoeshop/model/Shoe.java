package com.example.samshoeshop.model;

public class Shoe {
    private String id;
    private String name;
    private double price;
    private String imageUrl;
    private String description;
    private boolean isSold;

    public Shoe() {} // Firebase cần constructor rỗng

    public Shoe(String id, String name, double price, String imageUrl, String description, boolean isSold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isSold = isSold;
    }

    // Getter/Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isSold() { return isSold; }
    public void setSold(boolean sold) { isSold = sold; }
}
