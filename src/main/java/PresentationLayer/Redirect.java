package PresentationLayer;

import DBAccess.CustomerMapper;
import DBAccess.OrderMapper;
import FunctionLayer.Cupcake;
import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;

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

        if (destination.equals("newcustomer")) {
            return destination;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        ArrayList<Order> customerOrders = OrderMapper.getCustomerOrders(customer.getId());
        String kundebesked = "";

        if (customerOrders.size() > 0) {
            kundebesked = "Her kan du se alle dine ordrer.";
        } else {
            kundebesked = "Du har ingen registrerede ordrer på nuværende tidspunkt.";
        }

        if (destination.equals("fjerncupcake")) {
           FjernCupcake fjernCupcake = new FjernCupcake();
           fjernCupcake.execute(request, response);

            destination = "cart";
        }

        if (destination.equals("deleteorder")) {
            DeleteOrder deleteOrder = new DeleteOrder();
            String delete = deleteOrder.execute(request, response);
            ArrayList<Order> orders = OrderMapper.getAllOrders();

            request.setAttribute("delete", delete);
            session.setAttribute("orders", orders);
            destination = "orderline";
        }

        if (destination.equals("findcustomer")) {
            FindCustomer findCustomer = new FindCustomer();
            String result = findCustomer.execute(request, response);

            request.setAttribute("result", result);
            destination = "customers";
        }

        if (destination.equals("addmoney")) {
            String confirm = request.getParameter("money");
            String msg = "";

            if (confirm.equalsIgnoreCase("cupcakes")) {
                customer.restoreCredit();
                CustomerMapper.updateCustomer(customer);
                msg = "Korrekt! Din saldo er nu opdateret. Køb løs!";
            } else {
                msg = "Forkert. Svaret er ellers åbenlyst.";
            }

            request.setAttribute("confirm", msg);
            destination = "mineordrer";
        }

        if (destination.equals("start") || destination.equals("index")) {
            boolean hasPaid = (boolean) session.getAttribute("hasPaid");

            if (hasPaid) {
                cupcakes.clear();
            }
            if (destination.equals("index")) {
                cupcakes.clear();
            }
        }

        session.setAttribute("customerOrders", customerOrders);
        session.setAttribute("kundebesked", kundebesked);

        return destination;
    }
}
