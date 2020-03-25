package PresentationLayer;

import DBAccess.CustomerMapper;
import DBAccess.OrderMapper;
import FunctionLayer.Customer;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customers extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        ArrayList<Customer> customers = LogicFacade.getAllCustomers();
        ArrayList<Integer> customerOrderNumbers = new ArrayList<>();

        for (Customer customer : customers) {
            customer.setNumberOfOrders(LogicFacade.countOrdersById(customer.getId()));
        }

        session.setAttribute("numberOfOrders", customerOrderNumbers);
        session.setAttribute("customers", customers);

        return "customers";
    }
}
