package FunctionLayer;

import DBAccess.CustomerMapper;

import java.sql.SQLException;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static Customer login(String email, String password ) throws LoginSampleException, SQLException, ClassNotFoundException {
        return CustomerMapper.login( email, password );
    } 

    public static Customer createUser(String name, String email, String password) throws LoginSampleException, SQLException, ClassNotFoundException {
        Customer customer = new Customer(name, email, password, "customer");
        CustomerMapper.createCustomer(customer);
        return customer;
    }

}
