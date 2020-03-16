package PresentationLayer;

import DBAccess.BottomMapper;
import DBAccess.CustomerMapper;
import DBAccess.ToppingMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Start extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String navn = LogicFacade.login(email, password).getName();
        int saldo = LogicFacade.login(email, password).getCredit();

        HttpSession session = request.getSession();
        ArrayList<BottomAndTop> bottomsAndToppings = BottomMapper.getAllBottomsAndToppings();

        request.setAttribute("saldo", saldo);
        request.setAttribute("email", email);
        request.setAttribute("navn", navn);
        session.setAttribute("bottomsAndToppings", bottomsAndToppings);

        return "start";
    }
}
