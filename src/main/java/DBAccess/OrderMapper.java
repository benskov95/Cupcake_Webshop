package DBAccess;

import FunctionLayer.Bottom;
import FunctionLayer.LoginSampleException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;

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

    public static int getOrdersById(int customerId) throws LoginSampleException {

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

}
