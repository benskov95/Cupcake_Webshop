package PresentationLayer;

import DBAccess.CustomerMapper;
import DBAccess.OrderMapper;
import FunctionLayer.Customer;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class FindCustomer extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        HttpSession session = request.getSession();

        try {
            int id = Integer.parseInt(request.getParameter("findcustomer"));
            ArrayList<Customer> customers = LogicFacade.findCustomer(id);

            for (Customer customer : customers) {
                customer.setNumberOfOrders(LogicFacade.countOrdersById(customer.getId()));
            }

            if (customers.size() == 0) {
                return "Der findes ingen kunder med dette ID i databasen.";
            }

            session.setAttribute("customers", customers);

        } catch (Exception e) {
            return "Du skal skrive et tal.";
        }
        return "";
    }
}
