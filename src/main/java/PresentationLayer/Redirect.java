package PresentationLayer;

import DBAccess.CustomerMapper;
import DBAccess.OrderMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Redirect class is crucial to the
 * functionality of this program, as it
 * is used heavily and on almost every page.
 *
 * Its purpose is to ensure that the
 * customer gets sent to the correct
 * jsp page when certain conditions are
 * met or to ensure that certain
 * variables or elements are modified
 * in various circumstances.
 *
 * An example of this can be found in
 * the final if statement, where
 * the cart (cupcakes) is cleared
 * if the customer has successfully
 * made a purchase or if they
 * return to the index page (log out.
 *
 * This ensures that once an order
 * is completed, the cart no longer
 * contains the cupcakes that were
 * previously added.
 *
 * @author benjamin
 */

public class Redirect extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        String destination = request.getParameter("destination");
        ArrayList<Cupcake> cupcakes = (ArrayList<Cupcake>) session.getAttribute("cupcakes");

        if (destination.equals("newcustomer")) {
            return destination;
        }

        if (destination.equals("cart")) {
            boolean hasPaid = (boolean) session.getAttribute("hasPaid");

            if (hasPaid) {
                cupcakes.clear();
                session.setAttribute("totalPrice", 0);
            }
        }

        Customer customer = (Customer) session.getAttribute("customer");
        ArrayList<Order> customerOrders = LogicFacade.getCustomerOrders(customer.getId());
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
            ArrayList<Order> orders = LogicFacade.getAllOrders();

            request.setAttribute("delete", delete);
            session.setAttribute("orders", orders);
            destination = "orderline";
        }

        if (destination.equals("getCustomerOrders")) {

            try {
                int id = Integer.parseInt(request.getParameter("getCustomerOrders"));
                ArrayList<Order> getCustomerOrders = LogicFacade.getSpecificOrders(id);

                if (getCustomerOrders.size() == 0) {
                    request.setAttribute("result", "Denne kunde har ikke lavet nogen ordrer endnu.");

                }
                session.setAttribute("orders", getCustomerOrders);
            } catch (Exception e) {
                request.setAttribute("result", "Du skal skrive et tal.");
            }
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
                LogicFacade.updateCustomer(customer);
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
                session.setAttribute("totalPrice", 0);
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
