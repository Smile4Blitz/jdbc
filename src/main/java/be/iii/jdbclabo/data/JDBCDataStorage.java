package be.iii.jdbclabo.data;

import be.iii.jdbclabo.model.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
@PropertySource("classpath:sqlopdrachten.properties")
public class JDBCDataStorage implements IDataStorage {
    final DataSource dataSource;
    final JdbcClient jdbcClient;

    @Value("${select_products}")
    String getProductsQuery;

    @Value("${select_customers}")
    String getCustomersQuery;

    @Value("${select_customer}")
    String getCustomerQuery;

    @Value("${select_orders_klant}")
    String getOrdersQuery;

    @Value("${select_max_customer_number}")
    String maxCustomerNumberQuery;

    @Value("${select_max_order_number}")
    String maxOrderNumberQuery;

    @Value("${insert_order}")
    String insertOrderQuery;

    @Value("${insert_customer}")
    String insertCustomerQuery;

    @Value("${update_customer}")
    String updateCustomerQuery;

    @Value("${delete_customer}")
    String deleteCustomer;

    @Value("${procedure_get_total}")
    String getTotalProcedure;

    @Autowired
    public JDBCDataStorage(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @Override
    public List<IProduct> getProducts() throws DataExceptie {
        try {
            return new ArrayList<>(jdbcClient.sql(getProductsQuery).query(Product.class).list());
        } catch (Exception e) {
            throw new DataExceptie();
        }
    }

    @Override
    public List<ICustomer> getCustomers() throws DataExceptie {
        try {
            List<ICustomer> customers = new ArrayList<>();
            ResultSet set = dataSource.getConnection().prepareStatement(getCustomersQuery).executeQuery();
            while (set.next()) {
                customers.add(
                        new Customer(
                                set.getInt("customernumber"),
                                set.getString("customername"),
                                set.getString("contactlastname"),
                                set.getString("contactfirstname"),
                                set.getString("phone"),
                                set.getString("addressline1"),
                                set.getString("addressline2"),
                                set.getString("city"),
                                set.getString("state"),
                                set.getString("postalcode"),
                                set.getString("country"),
                                set.getInt("salesrepemployeenumber"),
                                set.getDouble("creditlimit")));
            }
            return customers;
        } catch (Exception e) {
            throw new DataExceptie();
        }
    }

    public Optional<Customer> getCustomer(int customerNumber) throws DataExceptie {
        return jdbcClient.sql(getCustomerQuery)
                .param(customerNumber)
                .query(Customer.class)
                .optional();
    }

    @Override
    public List<IOrderWithoutDetails> getOrders(int customerNumber) throws DataExceptie {
        try {
            return new ArrayList<>(
                    jdbcClient
                            .sql(getOrdersQuery)
                            .param(customerNumber)
                            .query(OrderWithoutDetails.class)
                            .list());
        } catch (Exception e) {
            throw new DataExceptie();
        }
    }

    @Override
    public int maxCustomerNumber() throws DataExceptie {
        try {
            ResultSet set = dataSource.getConnection().prepareStatement(maxCustomerNumberQuery).executeQuery();
            set.next();
            return Integer.parseInt(set.getString("max"));
        } catch (Exception e) {
            throw new DataExceptie();
        }

    }

    @Override
    public int maxOrderNumber() throws DataExceptie {
        try {
            return jdbcClient.sql(maxOrderNumberQuery).query(int.class).single();
        } catch (Exception e) {
            throw new DataExceptie();
        }
    }

    @Override
    public void addOrder(IOrder order) throws DataExceptie {
        try {
            jdbcClient
                    .sql(insertOrderQuery)
                    .param(order.getOrderNumber())
                    .param(order.getOrderDate())
                    .param(order.getRequiredDate())
                    .param(order.getShippedDate())
                    .param(order.getStatus())
                    .param(order.getComments())
                    .param(order.getCustomerNumber())
                    .update();
        } catch (Exception e) {
            throw new DataExceptie("fout bij toevoegen order");
        }
    }

    @Override
    public void addCustomer(ICustomer customer) throws DataExceptie {
        Optional<Customer> c = this.getCustomer(customer.getCustomerNumber());

        if (c.isPresent())
            throw new DataExceptie("fout bij toevoegen customer");

        jdbcClient
                .sql(insertCustomerQuery)
                .param(customer.getCustomerNumber())
                .param(customer.getCustomerName())
                .param(customer.getContactLastName())
                .param(customer.getContactFirstName())
                .param(customer.getPhone())
                .param(customer.getAddressLine1())
                .param(customer.getAddressLine2())
                .param(customer.getCity())
                .param(customer.getState())
                .param(customer.getPostalCode())
                .param(customer.getCountry())
                .param(customer.getSalesRepEmployeeNumber())
                .param(customer.getCreditLimit())
                .update();
    }

    @Override
    public void modifyCustomer(ICustomer customer) throws DataExceptie {
        try {
            jdbcClient.sql(updateCustomerQuery)
                    .param(customer.getCustomerName())
                    .param(customer.getContactLastName())
                    .param(customer.getContactFirstName())
                    .param(customer.getPhone())
                    .param(customer.getAddressLine1())
                    .param(customer.getAddressLine2())
                    .param(customer.getCity())
                    .param(customer.getState())
                    .param(customer.getPostalCode())
                    .param(customer.getCountry())
                    .param(customer.getSalesRepEmployeeNumber())
                    .param(customer.getCreditLimit())
                    .param(customer.getCustomerNumber())
                    .update();
        } catch (Exception e) {
            throw new DataExceptie();
        }
    }

    @Override
    public void deleteCustomer(int customerNumber) throws DataExceptie {
        try {
            jdbcClient
                    .sql(deleteCustomer)
                    .param(customerNumber)
                    .update();
        } catch (Exception e) {
            throw new DataExceptie();
        }
    }

    @Override
    public double getTotal(int customerNumber) throws DataExceptie {
        try (CallableStatement c = dataSource.getConnection().prepareCall("{?=call get_total(?)}")) {
            c.registerOutParameter(1, Types.DOUBLE);
            c.setInt(2, customerNumber);
    
            c.execute();
    
            return c.getDouble(1);
        } catch (SQLException e) {
            throw new DataExceptie("fout bij toevoegen order");
        }
    }
}
