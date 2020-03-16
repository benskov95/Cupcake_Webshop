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
        return "index";
    }
}
