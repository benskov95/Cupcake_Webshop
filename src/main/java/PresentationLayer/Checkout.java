package PresentationLayer;

import DBAccess.BottomMapper;
import DBAccess.CustomerMapper;
import DBAccess.OrderMapper;
import DBAccess.ToppingMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class Checkout extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        ArrayList<Cupcake> cupcakes = (ArrayList<Cupcake>) session.getAttribute("cupcakes");
        int totalPrice = (int) session.getAttribute("totalPrice");
        int toppingId;
        int bottomId;

        if (cupcakes.size() == 0 || totalPrice <= 0) {
            request.setAttribute("cartEmpty", "Der er ingen cupcakes i din kurv.");
            return "cart";
        }

        if (customer.getCredit() < totalPrice) {
            request.setAttribute("insufficientFunds", "Du har ikke nok penge til at foretage dette køb.");
            request.setAttribute("insufficientFundsTwo", "Gå ind på \"Mine order\" for at få flere.");
            return "cart";
        } else {
            int purchase = customer.getCredit() - totalPrice;
            LogicFacade.pay(purchase, customer);
            session.setAttribute("hasPaid", true);

            int orderId = LogicFacade.addOrder(customer.getId());

            for (Cupcake cupcake : cupcakes) {

                toppingId = getToppingId(cupcake.getToppingName());
                bottomId = getBottomId(cupcake.getBottomName());

                LogicFacade.addOrderLine(orderId, cupcake.getQuantity(), cupcake.getCombinedPrice(), toppingId, bottomId);
            }
        }
        return "checkout";
    }


    private int getToppingId(String toppingName) throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Topping> toppings = LogicFacade.getAllToppings();

        for (Topping topping : toppings) {
            if (topping.getToppingName().equals(toppingName)) {
                return topping.getToppingId();
            }
        }
        return 0;
    }

    private int getBottomId(String bottomName) throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Bottom> bottoms = LogicFacade.getAllBottoms();

        for (Bottom bottom : bottoms) {
            if (bottom.getBottomName().equals(bottomName)) {
                return bottom.getBottomId();
            }
        }
        return 0;
    }
}
