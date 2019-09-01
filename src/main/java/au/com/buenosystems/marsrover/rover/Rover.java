package au.com.buenosystems.marsrover.rover;

import au.com.buenosystems.marsrover.command.RoverCommandValidator;
import au.com.buenosystems.marsrover.terrain.Position;

public interface Rover {
    void initialise(Position position, Direction direction);
    void rotateLeft();
    void rotateRight();
    void move(Position position);

    Position getPosition();
    Direction getDirection();
    RoverCommandValidator getRoverCommandValidator();
}
