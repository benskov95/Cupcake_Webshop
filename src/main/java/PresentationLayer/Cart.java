package PresentationLayer;

import DBAccess.BottomMapper;
import DBAccess.ToppingMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cart extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        ArrayList<Cupcake> cupcakes = (ArrayList<Cupcake>) session.getAttribute("cupcakes");
        String bottom;
        String topping;
        String noneSelected = "";
        int quantity;

        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
            bottom = request.getParameter("bottom");
            topping = request.getParameter("topping");

            if (bottom.length() < 1 || topping.length() < 1) {
                noneSelected = "Du skal lige vælge noget, før du går videre.";
                request.setAttribute("noneSelected", noneSelected);
                return "start";
            }
        } catch (Exception e) {
        noneSelected = "Du skal lige vælge noget, før du går videre.";
        request.setAttribute("noneSelected", noneSelected);
        return "start";
    }

        if (topping.equals("Blue")) {
            topping = "Blue cheese";
        }

        ArrayList<Bottom> bottoms = BottomMapper.getAllBottoms();
        ArrayList<Topping> toppings = ToppingMapper.getAllToppings();
        int bottomPrice = 0;
        int toppingPrice = 0;

        for (Bottom bottom1 : bottoms) {
            if (bottom1.getBottomName().equals(bottom)) {
                bottomPrice = bottom1.getPrice();
            }
        }

        for (Topping topping1 : toppings) {
            if (topping1.getToppingName().equals(topping)) {
                toppingPrice = topping1.getPrice();
            }
        }

        int price = (bottomPrice + toppingPrice) * quantity;

        int count = cupcakes.size();
        int total = 0;

        cupcakes.add(new Cupcake(count, bottom, topping, quantity, price));

        for (Cupcake cupcake : cupcakes) {
            total += cupcake.getCombinedPrice();
        }

        session.setAttribute("count", count);
        session.setAttribute("cupcakes", cupcakes);
        session.setAttribute("totalPrice", total);

        return "cart";
    }
}
