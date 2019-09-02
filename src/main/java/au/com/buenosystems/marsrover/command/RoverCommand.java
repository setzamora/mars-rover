package au.com.buenosystems.marsrover.command;

public class RoverCommand {
    public static boolean isOperational(String roverCommands) {
        return roverCommands == null
                || !roverCommands.equalsIgnoreCase("x");
    }
}
