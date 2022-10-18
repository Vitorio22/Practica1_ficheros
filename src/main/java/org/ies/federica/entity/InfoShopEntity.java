package org.ies.federica.entity;

public class InfoShopEntity {

    private String article;

    private String type;

    private String saleDate;

    private double salePrice;

    private double derivedCosts;

    private double productionCosts;

    private double taxes;

    private double benefit;

    public InfoShopEntity(String article, String type, String saleDate, double salePrice, double derivedCosts, double productionCosts, double taxes, double benefit) {
        this.article = article;
        this.type = type;
        this.saleDate = saleDate;
        this.salePrice = salePrice;
        this.derivedCosts = derivedCosts;
        this.productionCosts = productionCosts;
        this.taxes = taxes;
        this.benefit = benefit;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getDerivedCosts() {
        return derivedCosts;
    }

    public void setDerivedCosts(double derivedCosts) {
        this.derivedCosts = derivedCosts;
    }

    public double getProductionCosts() {
        return productionCosts;
    }

    public void setProductionCosts(double productionCosts) {
        this.productionCosts = productionCosts;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit = benefit;
    }
}
