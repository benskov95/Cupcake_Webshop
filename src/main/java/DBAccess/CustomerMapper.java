package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerMapper {

    public static void createCustomer(Customer customer) throws LoginSampleException {

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

    public static Customer login(String email, String password ) throws LoginSampleException {

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

    public static ArrayList<Customer> getAllCustomers() throws LoginSampleException {

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

    public static void pay(int newCredit, Customer customer) throws LoginSampleException {

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

    public static ArrayList<Customer> findCustomer(int customerId) throws LoginSampleException {
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

    public static void updateCustomer(Customer customer) throws LoginSampleException {
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
