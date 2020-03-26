package PresentationLayer;

import FunctionLayer.LoginSampleException;

import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The purpose of Command is to create a link
 * between the jsp pages and their respective
 * information and functions. Each string and
 * object added in the HashMap in initCommands
 * represent an individual jsp page and/or
 * actions and functions related to those jsp
 * pages.
 *
 * This information is relayed to the FrontController
 * and used to ensure that the jsp pages always
 * have their matching class (and therefore
 * functionality).
 *
 * All classes listed in the initCommands method
 * below extend Command, and all actions and
 * calculations happen through Command's
 * execute method, whose content is different
 * for each class that extends Command.
 *
 * @author benjamin
 */

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
            throws LoginSampleException, SQLException, ClassNotFoundException;

}
