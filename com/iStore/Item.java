package com.iStore;

import java.math.BigDecimal;


public class Item {
    private int Id;
    private String Name;
    private BigDecimal Price;
    private int Stock;

    public Item(int Id, String Name, BigDecimal Price, int Stock) {
        this.Id = Id;
        this.Name = Name;
        this.Price = Price;
        this.Stock = Stock;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
}
