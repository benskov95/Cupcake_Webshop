package PresentationLayer;

import DBAccess.UserMapper;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Start extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        User kunde = UserMapper.login(email, password);
        int saldo = 500;

        request.setAttribute("saldo", saldo);
        request.setAttribute("email", email);

        return "start";
    }
}
