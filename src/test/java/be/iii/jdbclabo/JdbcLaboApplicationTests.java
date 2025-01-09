package be.iii.jdbclabo;

import be.iii.jdbclabo.data.Customer;
import be.iii.jdbclabo.data.Order;
import be.iii.jdbclabo.data.OrderDetail;
import be.iii.jdbclabo.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class JdbcLaboApplicationTests {
    IDataStorage storage;

    @Autowired
    public JdbcLaboApplicationTests(IDataStorage dataStorage) {
        this.storage = dataStorage;
    }

    @Test
    void testGetProducts() throws DataExceptie {
        List<IProduct> products = storage.getProducts();
        assertTrue(products.size() >= 110);
    }

    @Test
    void testOrdersCustomer() throws DataExceptie {
        List<IOrderWithoutDetails> orders = storage.getOrders(141);
        assertTrue(orders.size() >= 26);
    }

    @Test
    void testGetCustomers() throws DataExceptie {
        List<ICustomer> customers = storage.getCustomers();
        assertTrue(customers.size() >= 122);
    }

    @Test
    @DirtiesContext
    void testAddExistingCustomerNumber() {
        Exception exception = assertThrows(DataExceptie.class, () -> {
            ICustomer customer = makeTestCustomerWithExistingNumber();
            storage.addCustomer(customer);
        });
        String expectedMessage = "fout bij toevoegen customer";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DirtiesContext
    void testAddCustomer() throws DataExceptie {
        int maxCustomerNumber = storage.maxCustomerNumber();
        List<ICustomer> customersVoor = storage.getCustomers();
        ICustomer customer = makeTestCustomer(maxCustomerNumber + 1);
        storage.addCustomer(customer);
        List<ICustomer> customersNa = storage.getCustomers();
        assertEquals(customersVoor.size() + 1, customersNa.size());
        assertTrue(customersNa.contains(customer));
        storage.deleteCustomer(customer.getCustomerNumber());
    }

    @Test
    @DirtiesContext
    void testDeleteCustomer() throws DataExceptie {
        int maxCustomerNumber = storage.maxCustomerNumber();
        List<ICustomer> customersVoor = storage.getCustomers();
        ICustomer customer = makeTestCustomer(maxCustomerNumber + 1);
        storage.addCustomer(customer);
        List<ICustomer> customersNa = storage.getCustomers();
        assertEquals(customersVoor.size() + 1, customersNa.size());
        storage.deleteCustomer(maxCustomerNumber + 1);
        customersNa = storage.getCustomers();
        assertEquals(customersVoor.size(), customersNa.size());
        assertFalse(customersNa.contains(customer));
        Exception exception = assertThrows(DataExceptie.class, () -> {
            storage.addOrder(makeTestOrder(maxCustomerNumber + 1, storage.maxOrderNumber() + 1));
        });
        String expectedMessage = "fout bij toevoegen order";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DirtiesContext
    void testModifyCustomer() throws DataExceptie {
        ICustomer customer = makeTestCustomer(storage.maxCustomerNumber() + 1);
        storage.addCustomer(customer);
        List<ICustomer> customersVoor = storage.getCustomers();
        assertTrue(customersVoor.contains(customer));
        ICustomer customerChanged = copyCustomer(customer);
        customerChanged.setAddressLine1("New adres");
        customerChanged.setState("East Flanders");
        storage.modifyCustomer(customerChanged);
        List<ICustomer> customersNa = storage.getCustomers();
        assertFalse(customersVoor.contains(customerChanged));
        assertTrue(customersNa.contains(customerChanged));
        assertFalse(customersNa.contains(customer));
    }

    @Test
    @DirtiesContext
    void addOrder() throws DataExceptie {
        ICustomer customer = makeTestCustomer(storage.maxCustomerNumber() + 1);
        storage.addCustomer(customer);
        IOrder order = makeTestOrder(customer.getCustomerNumber(), storage.maxOrderNumber() + 1);
        storage.addOrder(order);
        List<IOrderWithoutDetails> orders = storage.getOrders(customer.getCustomerNumber());
        assertTrue(orders.contains(order));
        assertEquals(1, orders.size());
    }

    @Test
    void getTotal() throws DataExceptie {
        double totalOrders = storage.getTotal(141);
        assertTrue(totalOrders >= 820689.54);
    }

    private ICustomer makeTestCustomerWithExistingNumber() {
        return new Customer(496, "Bruce Wayne", "Wayne", "Bruce", "555-871364", "Bat-Cave 1", "2nd A",
                "Gotham", null, null, "USA", 1143, 0);
    }

    private ICustomer makeTestCustomer(int number) {
        return new Customer(number, "Bruce Wayne", "Wayne", "Bruce", "555-871364", "Bat-Cave 1", "2nd A",
                "Gotham", null, null, "USA", 1102, 0);
    }

    private ICustomer copyCustomer(ICustomer customer) {
        return new Customer(customer.getCustomerNumber(), customer.getCustomerName(), customer.getContactLastName(),
                customer.getContactFirstName(), customer.getPhone(), customer.getAddressLine1(),
                customer.getAddressLine2(),
                customer.getCity(), customer.getState(), customer.getPostalCode(), customer.getCountry(),
                customer.getSalesRepEmployeeNumber(), customer.getCreditLimit());
    }

    private IOrder makeTestOrder(int customerNumber, int orderNumber) {
        IOrder order = null;
        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        List<IOrderDetail> details = new ArrayList<>();
        details.add(new OrderDetail(orderNumber, "S12_1099", 1, 20.95, 2));
        details.add(new OrderDetail(orderNumber, "S700_1138", 3, 15.95, 4));
        try {
            order = new Order(orderNumber, d.parse("15/12/2010"), d.parse("23/12/2010"), null, "In Process", null,
                    customerNumber, details);
        } catch (ParseException e) {
            Logger.getLogger(JdbcLaboApplicationTests.class.getName()).log(Level.SEVERE, "Fout bij aanmaken testorder");
        }

        return order;
    }

}
