package PresentationLayer;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        try {
            int orderId = Integer.parseInt(request.getParameter("deleteOrder"));
            int result = LogicFacade.deleteOrderLine(orderId);
            LogicFacade.deleteOrder(orderId);


            if (result == 0) {
                return "Der er ingen ordrer med dette ID. Prøv igen.";
            } else {
                return "Ordren blev slettet.";
            }

        } catch (Exception e) {
            return "Du skal skrive et tal.";
        }
    }
}
