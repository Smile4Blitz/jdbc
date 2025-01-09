/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.iii.jdbclabo.data;

import java.util.Objects;

import be.iii.jdbclabo.model.IProduct;

public class Product implements IProduct {

    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
    private Double buyPrice;
    private Double msrp;

    public Product(String productCode, String productName, String productLine,
            String productScale, String productVendor, String productDescription,
            Integer quantityInStock, Double buyPrice, Double msrp) {
        this.productCode = productCode;
        this.productName = productName;
        this.productLine = productLine;
        this.productScale = productScale;
        this.productVendor = productVendor;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
        this.msrp = msrp;
    }

    @Override
    public Double getMsrp() {
        return msrp;
    }

    @Override
    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    @Override
    public Double getBuyPrice() {
        return buyPrice;
    }

    @Override
    public void setBuyPrice(double price) {
        this.buyPrice = price;
    }

    @Override
    public String getProductCode() {
        return productCode;
    }

    @Override
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String getProductDescription() {
        return productDescription;
    }

    @Override
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String getProductLine() {
        return productLine;
    }

    @Override
    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getProductScale() {
        return productScale;
    }

    @Override
    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    @Override
    public String getProductVendor() {
        return productVendor;
    }

    @Override
    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    @Override
    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    @Override
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public boolean equals(Object o) {
        IProduct product = (Product) o;
        return product.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, productName, productLine, productScale, productVendor, productDescription,
                quantityInStock, buyPrice, msrp);
    }

}
