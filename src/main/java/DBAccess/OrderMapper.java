package DBAccess;

import FunctionLayer.Order;
import FunctionLayer.LoginSampleException;

import java.sql.*;
import java.util.ArrayList;

public class OrderMapper {

    public static void addOrder(int customerId) throws LoginSampleException {

        String sql = "insert into cupcakeshop.order(customer_id) value(?)";
        Connection con = Connector.connection();

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
    }

    public static int getOrderId(int customerId) throws LoginSampleException {

        ArrayList<Integer> idList = new ArrayList<>();
        int count = -1;
        int orderId = 0;

        String sql = "select order_id from cupcakeshop.order where customer_id = ?";
        Connection con = Connector.connection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("order_id");
                idList.add(id);
                count++;
            }

            orderId = idList.get(count);

        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return orderId;
    }

    public static int addOrderLine(int orderId, int quantity, int totalPrice, int toppingId, int bottomId) throws LoginSampleException {

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

    public static int countOrdersById(int customerId) throws LoginSampleException {

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

    public static ArrayList<Order> getAllOrders() throws LoginSampleException {

        ArrayList<Order> orders = new ArrayList<>();

        String sql = "select * from customer_view order by orderline_id asc";

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

    public static ArrayList<Order> getSpecificOrders(int id) throws LoginSampleException {

        ArrayList<Order> orders = new ArrayList<>();

        String sql = "select * from customer_view where customer_id = ? order by orderline_id asc";

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

    public static ArrayList<Order> getCustomerOrders(int customerId) throws LoginSampleException {

        ArrayList<Order> orders = new ArrayList<>();

        String sql = "select * from customer_view where customer_id = ?";

        Connection con = Connector.connection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String bname = resultSet.getString("bname");
                String tname = resultSet.getString("tname");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("sum");
                String date = resultSet.getString("order_date");

                Order order = new Order(orderId, bname, tname, quantity, price, date);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return orders;
    }

    public static int deleteOrderLine(int id) throws LoginSampleException {
        int result = 0;
        String sql = "delete from orderLine where order_id = ?";
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

    public static int deleteOrder(int id) throws LoginSampleException {
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
