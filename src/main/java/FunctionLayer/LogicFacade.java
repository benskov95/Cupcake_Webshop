package FunctionLayer;

import DBAccess.CustomerMapper;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static Customer login(String email, String password ) throws LoginSampleException {
        return CustomerMapper.login( email, password );
    } 

    public static Customer createUser(String name, String email, String password) throws LoginSampleException {
        Customer customer = new Customer(name, email, password, "customer");
        CustomerMapper.createCustomer(customer);
        return customer;
    }

}
