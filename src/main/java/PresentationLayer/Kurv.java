package PresentationLayer;

import DBAccess.BottomMapper;
import DBAccess.ToppingMapper;
import FunctionLayer.Bottom;
import FunctionLayer.Cupcake;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Topping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Kurv extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        String bottom = request.getParameter("bottom");
        String topping = request.getParameter("topping");
        int quantity;

        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (Exception e) {
        request.setAttribute("noQuantity", "Du skal lige vælge noget, før du går videre.");
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

        HttpSession session = request.getSession();
        int price = (bottomPrice + toppingPrice) * quantity;
        ArrayList<Cupcake> cupcakes = (ArrayList<Cupcake>) session.getAttribute("cupcakes");

        int count = cupcakes.size();
        int total = 0;

        cupcakes.add(new Cupcake(count, bottom, topping, quantity, price));

        for (Cupcake cupcake : cupcakes) {
            total += cupcake.getCombinedPrice();
        }

        session.setAttribute("count", count);
        session.setAttribute("cupcakes", cupcakes);
        session.setAttribute("totalPrice", total);
        return "kurv";
    }
}
