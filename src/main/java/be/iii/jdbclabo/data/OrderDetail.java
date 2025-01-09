/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.iii.jdbclabo.data;

import be.iii.jdbclabo.model.IOrderDetail;

import java.util.Objects;

public class OrderDetail implements IOrderDetail {

    private Integer orderNumber;
    private String productCode;
    private Integer quantity;
    private Double price;
    private Integer orderLineNumber;

    public OrderDetail(Integer orderNumber, String productCode, Integer quantity, Double price, Integer orderLineNumber) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
        this.orderLineNumber = orderLineNumber;
    }

    @Override
    public int getOrderLineNumber() {
        return orderLineNumber;
    }

    @Override
    public void setOrderLineNumber(int orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
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
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        IOrderDetail orderDetail = (IOrderDetail) o;
        return orderDetail.getOrderNumber() == this.getOrderNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode, quantity, price, orderLineNumber);
    }
}
