package com.codegym.model;

public class Cart {
    protected int id;
    protected int storyId;
    protected int partId;
    protected int userId;
    protected String storyName;
    protected String storyPart;
    protected String img;
    protected int quantity;
    protected int price;
    protected int payMoney;

    public Cart(int id, int storyId, int partId, int userId, String storyName, String storyPart, String img, int quantity, int price, int payMoney) {
        this.id = id;
        this.storyId = storyId;
        this.partId = partId;
        this.userId = userId;
        this.storyName = storyName;
        this.storyPart = storyPart;
        this.img = img;
        this.quantity = quantity;
        this.price = price;
        this.payMoney = payMoney;
    }

    public Cart(int storyId, int partId, int userId, String storyName, String storyPart, String img, int quantity, int price, int payMoney) {
        this.storyId = storyId;
        this.partId = partId;
        this.userId = userId;
        this.storyName = storyName;
        this.storyPart = storyPart;
        this.img = img;
        this.quantity = quantity;
        this.price = price;
        this.payMoney = payMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryPart() {
        return storyPart;
    }

    public void setStoryPart(String storyPart) {
        this.storyPart = storyPart;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(int payMoney) {
        this.payMoney = payMoney;
    }
}
