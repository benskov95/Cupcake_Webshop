package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NyKunde extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String navn = request.getParameter("navn");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        LogicFacade.createUser(navn, email, password);
        request.setAttribute("nykunde", "Så er din konto oprettet!\nNu skal du bare logge ind for at bestille.");
        return "index";
    }
}
