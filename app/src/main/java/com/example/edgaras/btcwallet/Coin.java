package com.example.edgaras.btcwallet;

/**
 * Created by Edgaras on 2/24/2018.
 */

public class Coin {



    private int Id;
    private String name;
    private String abbreviation;
    private String priceRange;
    private String market;
    private String cap;
    private double bought;
    private double sold;
    private double profit;
    private String user;

    public Coin(){
    }

    public Coin(int id, String name, String abbreviation, String priceRange, String market, String cap, double bought,
                double sold, double profit, String user) {
        super();
        this.Id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.priceRange = priceRange;
        this.market = market;
        this.cap = cap;
        this.bought = bought;
        this.sold = sold;
        this.profit = profit;
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public double getBought() {
        return bought;
    }

    public void setBought(double bought) {
        this.bought = bought;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }




}
