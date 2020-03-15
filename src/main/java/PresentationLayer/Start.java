package PresentationLayer;

import DBAccess.UserMapper;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Start extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        UserMapper.login(email, password);
        ArrayList<String> test = new ArrayList<>();
        int saldo = 500;

        test.add("Virker");
        test.add("Det");
        test.add("Mon");
        test.add("Klaus?");


        request.setAttribute("saldo", saldo);
        request.setAttribute("email", email);
        session.setAttribute("test", test);

        return "start";
    }
}
