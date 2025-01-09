/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.iii.jdbclabo.model;

public interface ICustomer {

    String getAddressLine1();

    String getAddressLine2();

    String getCity();

    String getContactFirstName();

    String getContactLastName();

    String getCountry();

    Double getCreditLimit();

    String getCustomerName();

    Integer getCustomerNumber();

    String getPhone();

    String getPostalCode();

    Integer getSalesRepEmployeeNumber();

    String getState();

    void setAddressLine1(String addressLine1);

    void setAddressLine2(String addressLine2);

    void setCity(String city);

    void setContactFirstName(String contactFirstName);

    void setContactLastName(String contactLastName);

    void setCountry(String country);

    void setCreditLimit(Double creditLimit);

    void setCustomerName(String customerName);

    void setCustomerNumber(Integer customerNumber);

    void setPhone(String phone);

    void setPostalCode(String postalCode);

    void setSalesRepEmployeeNumber(Integer salesRepEmployeeNumber);

    void setState(String state);
}
