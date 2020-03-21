package PresentationLayer;

import FunctionLayer.Cupcake;
import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class FjernCupcake extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();
        ArrayList<Cupcake> cupcakes = (ArrayList<Cupcake>) session.getAttribute("cupcakes");
        int totalPrice = (int) session.getAttribute("totalPrice");
        int count = Integer.parseInt(request.getParameter("cupcakeNumber"));

        totalPrice = totalPrice - cupcakes.get(count).getCombinedPrice();
        cupcakes.remove(count);

        for (Cupcake cupcake : cupcakes) {
            int reorganize = cupcake.getCount() - 1;
            cupcake.setCount(reorganize);

            if (cupcake.getCount() < 0) {
                cupcake.setCount(0);
            }
        }

        session.setAttribute("totalPrice", totalPrice);
        return "fjerncupcake";
    }
}
