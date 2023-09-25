package com.natWest.webService.payloads.request.dao;


import java.util.Map;

public class Data {
    private String color;
    private String capacity;
    private String price;
    private String currency;

    public Data(Map<String, String> dataMap) {
        this.color =  dataMap.get("color");
        this.capacity = dataMap.get("capacity");
        this.price =  dataMap.get("price");
        this.currency = dataMap.get("currency");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Data{" +
                "color='" + color + '\'' +
                ", capacity='" + capacity + '\'' +
                ", price='" + price + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
