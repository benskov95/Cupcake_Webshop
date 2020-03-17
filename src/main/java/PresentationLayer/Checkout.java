package PresentationLayer;

import FunctionLayer.Cupcake;
import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Checkout extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        int totalPrice = (int) session.getAttribute("totalPrice");

        int purchase = customer.getCredit() - totalPrice;
        customer.setCredit(purchase);

        return "checkout";
    }
}
