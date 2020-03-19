package PresentationLayer;

import DBAccess.BottomMapper;
import DBAccess.CustomerMapper;
import DBAccess.OrderMapper;
import DBAccess.ToppingMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 The purpose of Login is to...

 @author kasper
 */
public class Login extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        String email = request.getParameter( "email" );
        String password = request.getParameter( "pass" );
        Customer customer = LogicFacade.login( email, password );
        String kundebesked = "";

        HttpSession session = request.getSession();
        ArrayList<Bottom> bottoms = BottomMapper.getAllBottoms();
        ArrayList<Topping> toppings = ToppingMapper.getAllToppings();
        ArrayList<Cupcake> cupcakes = new ArrayList<>();
        ArrayList<Order> customerOrders = OrderMapper.getCustomerOrders(customer.getId());

        if (customerOrders.size() > 0) {
            kundebesked = "Her kan du se alle dine ordrer.";
        } else {
            kundebesked = "Du har ingen registrerede ordrer på nuværende tidspunkt.";
        }

        session.setAttribute("hasPaid", false);
        session.setAttribute("kundebesked", kundebesked);
        session.setAttribute("cupcakes", cupcakes);
        session.setAttribute("customerOrders", customerOrders);
        session.setAttribute("bottoms", bottoms);
        session.setAttribute("toppings", toppings);
        session.setAttribute( "customer", customer);
        session.setAttribute("email", email);  // ellers skal man skrive  user.email på jsp siderne og det er sgu lidt mærkeligt at man har adgang til private felter. Men måske er det meget fedt , jeg ved det ikke

        if (customer.getRole().equals("admin")) {
            return "adminstart";
        } else {
            return "start";
        }
    }

}
