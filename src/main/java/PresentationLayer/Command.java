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
        commands.put("nykunde", new NyKunde());
        commands.put("kurv", new Kurv());
        commands.put("checkout", new Checkout());
        commands.put("adminstart", new AdminStart());
        commands.put("kunder", new Kunder());
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
