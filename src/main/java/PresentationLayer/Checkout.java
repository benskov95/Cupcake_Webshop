package PresentationLayer;

import FunctionLayer.Cupcake;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Checkout extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String email = request.getParameter("email");
        int saldo = Integer.parseInt(request.getParameter("saldo"));
        String navn = request.getParameter("navn");

        HttpSession session = request.getSession();
        ArrayList<Cupcake> cupcakes = (ArrayList<Cupcake>) session.getAttribute("cupcakes");
        int total = 0;

        for (Cupcake cupcake : cupcakes) {
            total += cupcake.getCombinedPrice();
        }

        request.setAttribute("email", email);
        request.setAttribute("saldo", saldo);
        request.setAttribute("navn", navn);
        request.setAttribute("totalPrice", total);

        return "checkout";
    }
}
