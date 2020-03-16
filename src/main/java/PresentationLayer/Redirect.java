package PresentationLayer;

import DBAccess.CustomerMapper;
import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Redirect extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String email = request.getParameter("email");
        String navn = "";
        // Sørger for, kundenavnet kommer med når Redirect objektet bruges.
        ArrayList<Customer> customers = CustomerMapper.getAllCustomers();

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                navn = customer.getName();
            }
        }

        request.setAttribute("navn", navn);
        request.setAttribute("email", email);
        return request.getParameter("destination");
    }
}
