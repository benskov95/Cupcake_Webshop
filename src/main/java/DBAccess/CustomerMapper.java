package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The purpose of CustomerMapper is to interact with the database
 * and retrieve or alter customer information in it through select,
 * insert, update and delete methods.
 * @author benjamin
 */

public class CustomerMapper {

    /**
     * The createCustomer method adds a customer to the database
     * with the Customer object that is used as an argument in
     * the method.
     * @param customer
     * This object contains the needed information for the SQL insert string.
     * @author benjamin
     * @throws LoginSampleException
     * Thrown if login credentials do not match with
     * any of the registered customers in the database.
     * @throws SQLException
     * Thrown if the provided SQL string in each method
     * has incorrect syntax, unknown keywords etc.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     */
    public static void createCustomer(Customer customer) throws LoginSampleException, SQLException, ClassNotFoundException {

        Connection con = Connector.connection();
        try {
            String SQL = "INSERT INTO customer (name, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString(1, customer.getName());
            ps.setString( 2, customer.getEmail());
            ps.setString( 3, customer.getPassword());
            ps.setString( 4, customer.getRole());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt( 1 );
            customer.setId( id );
        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    /**
     * The login method grants a customer access to the
     * website if their email and password match an
     * existing pair in the database.
     * @param email
     * Customer's chosen email which is used to log in on the website.
     * @param password
     * Customer's chosen password which is used to log in on the website.
     * @return
     * If a match is found, the customer's information is added to a
     * Customer object which is then returned.
     * @author benjamin
     * @throws LoginSampleException
     * Thrown if login credentials do not match with
     * any of the registered customers in the database.
     * @throws SQLException
     * Thrown if the provided SQL string in each method
     * has incorrect syntax, unknown keywords etc.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     */
    public static Customer login(String email, String password ) throws LoginSampleException, SQLException, ClassNotFoundException {

        Connection con = Connector.connection();
        try {
            String SQL = "SELECT customer_id, name, role, credit FROM customer "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString( 1, email );
            ps.setString( 2, password );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                int id = rs.getInt( "customer_id" );
                String name = rs.getString("name");
                String role = rs.getString( "role" );
                int credit = rs.getInt("credit");
                Customer customer = new Customer(name, email, password, role);
                customer.setId( id );
                customer.setCredit(credit);
                return customer;
            } else {
                throw new LoginSampleException( "E-mail eller adgangskode er forkert. Prøv igen." );
            }
        } catch ( SQLException ex ) {
            ex.printStackTrace();
            throw new LoginSampleException(ex.getMessage());
        }
    }

    /**
     * the getAllCustomers method retrieves all customers in
     * the database and adds them to an ArrayList of
     * Customer objects.
     * @return
     * When there are no more rows left to read in the database,
     * the ArrayList is returned.
     * @author benjamin
     * @throws LoginSampleException
     * Thrown if login credentials do not match with
     * any of the registered customers in the database.
     * @throws SQLException
     * Thrown if the provided SQL string in each method
     * has incorrect syntax, unknown keywords etc.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     */
    public static ArrayList<Customer> getAllCustomers() throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Customer> customers = new ArrayList<>();

        String sql = "select * from customer";
        Connection con = Connector.connection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int credit = resultSet.getInt("credit");

                Customer customer = new Customer(id, name, email, credit);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * The pay method simply updates the customer's
     * current credit in the database to account
     * for a finalized order and purchase.
     * @param newCredit
     * The customer's credit after the total price
     * of their order has been subtracted from
     * their initial credit.
     * @param customer
     * The customer making the purchase.
     * @author benjamin
     * @throws LoginSampleException
     * Thrown if login credentials do not match with
     * any of the registered customers in the database.
     * @throws SQLException
     * Thrown if the provided SQL string in each method
     * has incorrect syntax, unknown keywords etc.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     */
    public static void pay(int newCredit, Customer customer) throws LoginSampleException, SQLException, ClassNotFoundException {

        String sql = "update customer set credit = ? where customer_id =  ?";
        Connection con = Connector.connection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newCredit);
            ps.setInt(2, customer.getId());
            customer.setCredit(newCredit);
            ps.executeUpdate();

            } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
    }

    public static ArrayList<Customer> findCustomer(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();

        Connection con = Connector.connection();
        try {
            String SQL = "select * from customer where customer_id = ?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setInt( 1, customerId);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int id = rs.getInt( "customer_id" );
                String name = rs.getString("name");
                String email = rs.getString("email");
                int credit = rs.getInt("credit");
                Customer customer = new Customer(id, name, email, credit);
                customers.add(customer);
            }
        } catch ( SQLException ex ) {
            ex.printStackTrace();
            throw new LoginSampleException(ex.getMessage());
        }
        return customers;
    }

    public static void updateCustomer(Customer customer) throws LoginSampleException, SQLException, ClassNotFoundException {
        String sql = "update customer set credit = ? where customer_id = ?";
        Connection con = Connector.connection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customer.getCredit());
            ps.setInt(2, customer.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }


    }


}
