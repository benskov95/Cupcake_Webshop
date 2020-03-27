package FunctionLayer;

import DBAccess.BottomMapper;
import DBAccess.CustomerMapper;
import DBAccess.OrderMapper;
import DBAccess.ToppingMapper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of LogicFacade is to serve as
 * a bridge between the data mappers and the
 * regular classes in which the mapper methods
 * are used. All of the method information is
 * stored in individual mappers and accessed
 * through the LogicFacade methods.
 *
 * @author benjamin
 */
public class LogicFacade {

    public static Customer login(String email, String password ) throws LoginSampleException, SQLException, ClassNotFoundException {
        return CustomerMapper.login( email, password );
    } 

    public static Customer createCustomer(String name, String email, String password) throws LoginSampleException, SQLException, ClassNotFoundException {
        Customer customer = new Customer(name, email, password, "customer");
        CustomerMapper.createCustomer(customer);
        return customer;
    }

    public static ArrayList<Customer> getAllCustomers() throws LoginSampleException, SQLException, ClassNotFoundException {
       return CustomerMapper.getAllCustomers();
    }

    public static void pay(int newCredit, Customer customer) throws LoginSampleException, SQLException, ClassNotFoundException {
        CustomerMapper.pay(newCredit, customer);
    }

    public static ArrayList<Customer> findCustomer(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {
        return CustomerMapper.findCustomer(customerId);
    }

    public static void updateCustomer(Customer customer) throws LoginSampleException, SQLException, ClassNotFoundException {
        CustomerMapper.updateCustomer(customer);
    }

    public static ArrayList<Bottom> getAllBottoms() throws LoginSampleException, SQLException, ClassNotFoundException {
        return BottomMapper.getAllBottoms();
    }

    public static ArrayList<Topping> getAllToppings() throws LoginSampleException, SQLException, ClassNotFoundException {
        return ToppingMapper.getAllToppings();
    }

    public static int addOrder(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.addOrder(customerId);
    }

    public static int addOrderLine(int orderId, int quantity, int totalPrice, int toppingId, int bottomId) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.addOrderLine(orderId, quantity, totalPrice, toppingId, bottomId);
    }

    public static int countOrdersById(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.countOrdersById(customerId);
    }

    public static ArrayList<Order> getAllOrders() throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.getAllOrders();
    }

    public static ArrayList<Order> getSpecificOrders(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.getSpecificOrders(customerId);
    }

    public static ArrayList<Order> getCustomerOrders(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.getCustomerOrders(customerId);
    }

    public static int deleteOrderLine(int id) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.deleteOrderLine(id);
    }

    public static int deleteOrder(int id) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.deleteOrder(id);
    }

}
