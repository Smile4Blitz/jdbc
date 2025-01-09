package be.iii.jdbclabo.data;

import be.iii.jdbclabo.model.IOrderWithoutDetails;

import java.util.Date;
import java.util.Objects;

public class OrderWithoutDetails implements IOrderWithoutDetails {
    protected Integer orderNumber;
    protected Date orderDate;
    protected Date requiredDate;
    protected Date shippedDate;
    protected String status;
    protected String comments;
    protected Integer customerNumber;

    public OrderWithoutDetails(Integer orderNumber, Date orderDate, Date requiredDate, Date shippedDate, String status, String comments, Integer customerNumber) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.comments = comments;
        this.customerNumber = customerNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int number) {
        this.orderNumber = number;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date ordered) {
        this.orderDate = ordered;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date required) {
        this.requiredDate = required;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shipped) {
        this.shippedDate = shipped;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        IOrderWithoutDetails orderWithoutDetails = (IOrderWithoutDetails) o;
        return orderWithoutDetails.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber);
    }
}
