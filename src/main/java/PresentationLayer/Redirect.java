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
        String customerMessage = "";

        if (customerOrders.size() > 0) {
            customerMessage = "Her kan du se alle dine ordrer.";
        } else {
            customerMessage = "Du har ingen registrerede ordrer på nuværende tidspunkt.";
        }

        if (destination.equals("removecupcake")) {
           RemoveCupcake removeCupcake = new RemoveCupcake();
           removeCupcake.execute(request, response);

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
            destination = "myorders";
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
        session.setAttribute("customerMessage", customerMessage);

        return destination;
    }
}
