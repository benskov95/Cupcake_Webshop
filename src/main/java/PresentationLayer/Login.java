package PresentationLayer;

import DBAccess.BottomMapper;
import DBAccess.CustomerMapper;
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

        HttpSession session = request.getSession();
        ArrayList<Bottom> bottoms = BottomMapper.getAllBottoms();
        ArrayList<Topping> toppings = ToppingMapper.getAllToppings();
        ArrayList<Cupcake> cupcakes = new ArrayList<>();

        session.setAttribute("test", 0);
        session.setAttribute("cupcakes", cupcakes);
        session.setAttribute("bottoms", bottoms);
        session.setAttribute("toppings", toppings);
        session.setAttribute( "customer", customer);
        session.setAttribute( "role", customer.getRole() );
        session.setAttribute("navn", customer.getName());
        session.setAttribute("email", email);  // ellers skal man skrive  user.email på jsp siderne og det er sgu lidt mærkeligt at man har adgang til private felter. Men måske er det meget fedt , jeg ved det ikke

        if (customer.getRole().equals("admin")) {
            return "adminstart";
        } else {
            return "start";
        }
    }

}
