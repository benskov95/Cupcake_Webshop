package PresentationLayer;

import DBAccess.CustomerMapper;
import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AdminStart extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        //todo: Lav tilbageknap i adminheaderen, så admin kan komme tilbage til adminsiden. Og refactor jsp siderne, så alt er på engelsk.
        return "adminstart";
    }
}
