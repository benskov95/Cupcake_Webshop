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

        String email = request.getParameter("email");
        int saldo = Integer.parseInt(request.getParameter("saldo"));

        String bottom = request.getParameter("bottom");
        String topping = request.getParameter("topping");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

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
        ArrayList<Cupcake> cupcakes = new ArrayList<>();

        cupcakes.add(new Cupcake(bottom, topping, quantity, price));
        int total = 0;

        for (Cupcake cupcake : cupcakes) {
            total += cupcake.getTotalPrice();
        }

        session.setAttribute("cupcakes", cupcakes);
        request.setAttribute("price", price);
        request.setAttribute("email", email);
        request.setAttribute("saldo", saldo);
        request.setAttribute("totalPrice", total);
        return "kurv";
    }
}
