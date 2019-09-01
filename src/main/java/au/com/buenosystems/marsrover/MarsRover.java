package au.com.buenosystems.marsrover;

import au.com.buenosystems.marsrover.command.RoverCommandValidator;
import au.com.buenosystems.marsrover.rover.Direction;
import au.com.buenosystems.marsrover.rover.Rover;
import au.com.buenosystems.marsrover.terrain.Position;

public final class MarsRover implements Rover {

    private Position position;
    private Direction direction;

    @Override
    public void initialise(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    @Override
    public void rotateLeft() {
        direction = direction.rotateLeft();
    }

    @Override
    public void rotateRight() {
        direction = direction.rotateRight();
    }

    @Override
    public void move(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public RoverCommandValidator getRoverCommandValidator() {
        return new MarsRoverCommandValidator();
    }
}
