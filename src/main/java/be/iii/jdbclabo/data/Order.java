/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.iii.jdbclabo.data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import be.iii.jdbclabo.model.IOrder;
import be.iii.jdbclabo.model.IOrderDetail;
import be.iii.jdbclabo.model.IOrderWithoutDetails;

@SuppressWarnings("unused")
public class Order extends OrderWithoutDetails implements IOrder {

    private List<IOrderDetail> details;

    public Order(Integer number, Date ordered, Date required, Date shipped, String status, String comments, Integer customerNumber, List<IOrderDetail> details) {
        super(number, ordered, required, shipped, status, comments, customerNumber);
        this.details = details;
    }

    @Override
    public List<IOrderDetail> getDetails() {
        return details;
    }

    @Override
    public void setDetails(List<IOrderDetail> details) {
        this.details = details;
    }
}
