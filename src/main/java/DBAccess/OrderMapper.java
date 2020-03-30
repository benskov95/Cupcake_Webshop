package DBAccess;

import FunctionLayer.Order;
import FunctionLayer.LoginSampleException;

import java.sql.*;
import java.util.ArrayList;

/**
 * The purpose of the OrderMapper is to interact with the database
 * and retrieve or alter order information in it through select,
 * insert, update and delete methods.
 * @author benjamin
 */

public class OrderMapper {

    /**
     * The addOrder method is the first part
     * of the final order that is added to
     * the database.
     * @param customerId
     * The customer ID is necessary to make
     * an order.
     * @return
     * Once made, the order id is returned
     * so it can be used in orderlines.
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
    public static int addOrder(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {

        String sql = "insert into cupcakeshop.order(customer_id) value(?)";
        Connection con = Connector.connection();
        int orderId = 0;

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return orderId;
    }

    /**
     * the addOrderLine method adds the second part
     * of an order to the database.
     * @param orderId
     * This order ID is provided by the addOrder
     * method.
     * @param quantity
     * The customer's selected quantity of cupcakes
     * in their order.
     * @param totalPrice
     * The final price of the customer's order,
     * determined by added the price of the
     * selected bottom and topping and
     * multiplying them with the quantity.
     * @param toppingId
     * The ID matching the customer's selected topping.
     * @param bottomId
     * The ID matching the customer's selected bottom.
     * @return
     * The newId variable that this method returns
     * is not used for anything. Could maybe
     * change the method to void return type
     * instead.
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
    public static int addOrderLine(int orderId, int quantity, int totalPrice, int toppingId, int bottomId) throws LoginSampleException, SQLException, ClassNotFoundException {

        int newId = 0;
        String sql = "insert into orderline(order_id, quantity, sum, topping_id, bottom_id) values(?, ?, ?, ?, ?)";
        Connection con = Connector.connection();

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, orderId);
            ps.setInt(2, quantity);
            ps.setInt(3, totalPrice);
            ps.setInt(4, toppingId);
            ps.setInt(5, bottomId);
            ps.executeUpdate();
            ResultSet idResultset = ps.getGeneratedKeys();
            if (idResultset.next()){
                newId = idResultset.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return newId;
    }

    /**
     * the countOrdersById method is exclusively
     * used for the admin's customer page where
     * all customers in the database can be seen.
     * It's sole purpose is to show how many
     * orders the individual customer has made.
     * @param customerId
     * Since the orders are counted for each
     * customer, a customer ID is necessary
     * to find the number of orders tied
     * to the customer.
     * @return
     * The variable 'count' is returned,
     * which is equal to the number of
     * rows found in the database with
     * the SQL string.
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
    public static int countOrdersById(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {

        int count = 0;

        String sql = "select * from cupcakeshop.order where customer_id = ?";
        Connection con = Connector.connection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                count++;
            }

        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return count;
    }

    public static ArrayList<Order> getAllOrders() throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Order> orders = new ArrayList<>();

        String sql = "select * from customer_view order by order_id asc";

        Connection con = Connector.connection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int orderLineId = resultSet.getInt("orderline_id");
                int orderId = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String bname = resultSet.getString("bname");
                String tname = resultSet.getString("tname");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("sum");
                String date = resultSet.getString("order_date");

                Order order = new Order(orderLineId, orderId, customerId, name, email, bname, tname, quantity, price, date);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return orders;
    }

    /**
     * the getSpecificOrders method is used
     * when the admin wishes to see all orders
     * made by a specific customer on the admin
     * order page.
     * @param id
     * The ID of the customer whose orders the
     * admin wants to see.
     * @return
     * All of the orders retrieved are added to
     * an ArrayList and returned so they can be
     * displayed on the admin order page.
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
    public static ArrayList<Order> getSpecificOrders(int id) throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Order> orders = new ArrayList<>();

        String sql = "select * from customer_view where customer_id = ? order by order_id asc";

        Connection con = Connector.connection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int orderLineId = resultSet.getInt("orderline_id");
                int orderId = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String bname = resultSet.getString("bname");
                String tname = resultSet.getString("tname");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("sum");
                String date = resultSet.getString("order_date");

                Order order = new Order(orderLineId, orderId, customerId, name, email, bname, tname, quantity, price, date);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return orders;
    }

    /**
     * the getCustomerOrders method is used
     * for customers who want to see their
     * own registered orders.
     * @param customerId
     * Used to make sure the correct
     * orders are retrieved.
     * @return
     * All orders are added to an
     * ArrayList and returned so
     * they can be displayed on
     * the customer's "myorders" page.
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
    public static ArrayList<Order> getCustomerOrders(int customerId) throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Order> orders = new ArrayList<>();

        String sql = "select * from customer_view where customer_id = ? order by order_date";

        Connection con = Connector.connection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int orderLineId = resultSet.getInt("orderline_id");
                String bname = resultSet.getString("bname");
                String tname = resultSet.getString("tname");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("sum");
                String date = resultSet.getString("order_date");

                Order order = new Order(orderId, orderLineId, bname, tname, quantity, price, date);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return orders;
    }

    public static int deleteOrderLine(int id) throws LoginSampleException, SQLException, ClassNotFoundException {
        int result = 0;
        String sql = "delete from orderline where order_id = ?";
        Connection con = Connector.connection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            result = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return result;
    }

    public static int deleteOrder(int id) throws LoginSampleException, SQLException, ClassNotFoundException {
        int result = 0;
        String sql = "delete from cupcakeshop.order where order_id = ?";
        Connection con = Connector.connection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            result = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return result;
    }

}
