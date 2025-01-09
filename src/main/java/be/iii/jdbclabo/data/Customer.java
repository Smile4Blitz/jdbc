/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.iii.jdbclabo.data;

import be.iii.jdbclabo.model.ICustomer;

import java.util.Objects;

public class Customer implements ICustomer {
    private Integer customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Integer salesRepEmployeeNumber;
    private Double creditLimit;

    public Customer(Integer customerNumber, String customerName, String contactLastName,
            String contactFirstName, String phone, String addressLine1,
            String addressLine2, String city, String state, String postalCode,
            String country, Integer salesRepEmployeeNumber, double creditLimit) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.contactLastName = contactLastName;
        this.contactFirstName = contactFirstName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
        this.creditLimit = creditLimit;
    }

    @Override
    public String getAddressLine1() {
        return addressLine1;
    }

    @Override
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Override
    public String getAddressLine2() {
        return addressLine2;
    }

    @Override
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getContactFirstName() {
        return contactFirstName;
    }

    @Override
    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    @Override
    public String getContactLastName() {
        return contactLastName;
    }

    @Override
    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public Integer getCustomerNumber() {
        return customerNumber;
    }

    @Override
    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public Integer getSalesRepEmployeeNumber() {
        return salesRepEmployeeNumber;
    }

    @Override
    public void setSalesRepEmployeeNumber(Integer salesRepEmployeeNumber) {
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        ICustomer customer = (ICustomer) o;
        return customer.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1,
                addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit);
    }
}
