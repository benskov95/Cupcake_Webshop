package PresentationLayer;

import DBAccess.BottomMapper;
import DBAccess.OrderMapper;
import DBAccess.ToppingMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Checkout extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        ArrayList<Cupcake> cupcakes = (ArrayList<Cupcake>) session.getAttribute("cupcakes");
        int test = 1;
        int toppingId;
        int bottomId;

        int totalPrice = (int) session.getAttribute("totalPrice");


        for (Cupcake cupcake : cupcakes) {

            OrderMapper.addOrder(customer.getId());
            int orderId = OrderMapper.getOrderId(customer.getId());

            toppingId = getToppingId(cupcake.getToppingName());
            bottomId = getBottomId(cupcake.getBottomName());

            OrderMapper.addOrderLine(orderId, cupcake.getQuantity(), cupcake.getCombinedPrice(), toppingId, bottomId);
        }

        int purchase = customer.getCredit() - totalPrice;
        customer.setCredit(purchase);
        session.setAttribute("test", test);

        return "checkout";
    }


    private int getToppingId(String toppingName) throws LoginSampleException {

        ArrayList<Topping> toppings = ToppingMapper.getAllToppings();

        for (Topping topping : toppings) {
            if (topping.getToppingName().equals(toppingName)) {
                return topping.getToppingId();
            }
        }
        return 0;
    }

    private int getBottomId(String bottomName) throws LoginSampleException {

        ArrayList<Bottom> bottoms = BottomMapper.getAllBottoms();

        for (Bottom bottom : bottoms) {
            if (bottom.getBottomName().equals(bottomName)) {
                return bottom.getBottomId();
            }
        }
        return 0;
    }
}
