package DBAccess;

import FunctionLayer.Bottom;
import FunctionLayer.Cupcake;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Topping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToppingMapper {

    public static ArrayList<Topping> getAllToppings() throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Topping> toppingList = new ArrayList<>();

        String sql = "select * from topping";
        Connection con = Connector.connection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("topping_id");
                String name = resultSet.getString("tname");
                int price = resultSet.getInt("price");

                Topping topping = new Topping(name, price);
                topping.setToppingId(id);
                toppingList.add(topping);
            }
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return toppingList;
    }
}
