package PresentationLayer;

import DBAccess.CustomerMapper;
import FunctionLayer.Cupcake;
import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Redirect extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();
        String destination = request.getParameter("destination");
        ArrayList<Cupcake> cupcakes = (ArrayList<Cupcake>) session.getAttribute("cupcakes");
        int test = (int) session.getAttribute("test");

        if (destination.equals("fjerncupcake")) {
           FjernCupcake fjernCupcake = new FjernCupcake();
           fjernCupcake.execute(request, response);

            destination = "kurv";
            return destination;
        }

        if (destination.equals("start") && test != 0) {
            cupcakes.clear();
        }


        return request.getParameter("destination");
    }
}
