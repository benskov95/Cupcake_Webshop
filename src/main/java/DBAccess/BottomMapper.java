package DBAccess;

import FunctionLayer.Bottom;
import FunctionLayer.BottomAndTop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BottomMapper {

    public static ArrayList<Bottom> getAllBottoms() {

        ArrayList<Bottom> bottomList = new ArrayList<>();

        String sql = "select * from bottom";
        try (Connection con = Connector.connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("bottom_id");
                String name = resultSet.getString("bname");
                int price = resultSet.getInt("price");

                Bottom bottom = new Bottom(name, price);
                bottom.setBottomId(id);
                bottomList.add(bottom);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return bottomList;
    }

    public static ArrayList<BottomAndTop> getAllBottomsAndToppings() {

        ArrayList<BottomAndTop> bottomsAndToppingsList = new ArrayList<>();

        String sql = "select t.tname, b.bname, t.price as tprice, b.price as bprice from topping t " +
                "inner join bottom b " +
                "on t.topping_id = b.bottom_id";
        try (Connection con = Connector.connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String bName = resultSet.getString("bname");
                String tName = resultSet.getString("tname");
                int bPrice = resultSet.getInt("bprice");
                int tPrice = resultSet.getInt("tprice");

                BottomAndTop bottomAndTop = new BottomAndTop(bName, tName, bPrice, tPrice);
                bottomsAndToppingsList.add(bottomAndTop);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return bottomsAndToppingsList;
    }
}
