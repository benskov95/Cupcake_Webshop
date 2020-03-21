package PresentationLayer;

import FunctionLayer.LoginSampleException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("redirect", new Redirect());
        commands.put("newcustomer", new NewCustomer());
        commands.put("cart", new Cart());
        commands.put("checkout", new Checkout());
        commands.put("adminstart", new AdminStart());
        commands.put("customers", new Customers());
        commands.put("orderline", new OrderLine());
        commands.put("removecupcake", new RemoveCupcake());
        commands.put("deleteorder", new DeleteOrder());
        commands.put("findcustomer", new FindCustomer());
    }

    static Command from( HttpServletRequest request ) {
        String targetName = request.getParameter( "target" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand() );   // unknowncommand er default.
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws LoginSampleException;

}
