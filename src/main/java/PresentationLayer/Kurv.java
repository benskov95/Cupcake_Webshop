package PresentationLayer;

import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Kurv extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String email = request.getParameter("email");
        int saldo = Integer.parseInt(request.getParameter("saldo"));

        request.setAttribute("email", email);
        request.setAttribute("saldo", saldo);
        return "kurv";
    }
}
