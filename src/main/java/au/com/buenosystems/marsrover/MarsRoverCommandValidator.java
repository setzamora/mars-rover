package au.com.buenosystems.marsrover;

import au.com.buenosystems.marsrover.command.RoverCommandValidator;

public class MarsRoverCommandValidator implements RoverCommandValidator {

    @Override
    public boolean isValid(String command) {
        if (isMultiple(command)) {
            return command.matches("[LRM]*");
        }
        if (isSingle(command)) {
            return command.matches("[LRMXx]*");
        }
        return false;
    }

    private boolean isMultiple(String command) {
        return command != null && command.length() > 1;
    }

    private boolean isSingle(String command) {
        return command != null && command.length() == 1;
    }
}
