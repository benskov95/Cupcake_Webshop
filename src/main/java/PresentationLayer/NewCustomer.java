package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewCustomer extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String navn = request.getParameter("navn");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        if (!email.contains("@")) {
            request.setAttribute("emailFejl", "Du skal bruge en email (@) for at oprette en konto.");
            return "newcustomer";
        }

        LogicFacade.createUser(navn, email, password);
        request.setAttribute("nykunde", "Så er din konto oprettet!\nNu skal du bare logge ind for at bestille.");
        return "index";
    }
}
