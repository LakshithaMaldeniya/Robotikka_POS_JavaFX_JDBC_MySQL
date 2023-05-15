package com.devstack.pos.entity;

public class ProductDetails implements SuperEntity {
    private String code;
    private String barcode;
    private int qtyOnHand;
    private double sellingPrice;
    private boolean discountAvailability;
    private double showPrice;
    private int productCode;
    private double buyingPrice;

    @Override
    public String toString() {
        return "ProductDetails{" +
                "code='" + code + '\'' +
                ", barcode='" + barcode + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", sellingPrice=" + sellingPrice +
                ", discountAvailability=" + discountAvailability +
                ", showPrice=" + showPrice +
                ", productCode=" + productCode +
                ", buyingPrice=" + buyingPrice +
                '}';
    }

    public ProductDetails() {
    }

    public ProductDetails(String code, String barcode, int qtyOnHand, double sellingPrice, boolean discountAvailability, double showPrice, int productCode, double buyingPrice) {
        this.code = code;
        this.barcode = barcode;
        this.qtyOnHand = qtyOnHand;
        this.sellingPrice = sellingPrice;
        this.discountAvailability = discountAvailability;
        this.showPrice = showPrice;
        this.productCode = productCode;
        this.buyingPrice = buyingPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean isDiscountAvailability() {
        return discountAvailability;
    }

    public void setDiscountAvailability(boolean discountAvailability) {
        this.discountAvailability = discountAvailability;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }
}
