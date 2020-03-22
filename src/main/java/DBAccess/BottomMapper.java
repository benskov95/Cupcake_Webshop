package DBAccess;

import FunctionLayer.Bottom;
import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BottomMapper {

    public static ArrayList<Bottom> getAllBottoms() throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Bottom> bottomList = new ArrayList<>();

        String sql = "select * from bottom";
        Connection con = Connector.connection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("bottom_id");
                String name = resultSet.getString("bname");
                int price = resultSet.getInt("price");

                Bottom bottom = new Bottom(name, price);
                bottom.setBottomId(id);
                bottomList.add(bottom);
            }
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return bottomList;
    }

}
