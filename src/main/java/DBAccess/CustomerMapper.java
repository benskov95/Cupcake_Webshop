package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 The purpose of UserMapper is to...

 @author kasper
 */
public class CustomerMapper {

    public static void createCustomer(Customer customer) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
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
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    public static Customer login(String email, String password ) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
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
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static ArrayList<Customer> getAllCustomers() {

        ArrayList<Customer> customers = new ArrayList<>();

        String sql = "select * from customer";
        try (Connection con = Connector.connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                int credit = resultSet.getInt("credit");

                Customer customer = new Customer(id, name, email, credit);
                customers.add(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return customers;
    }

}
