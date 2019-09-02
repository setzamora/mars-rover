package au.com.buenosystems.marsrover;

import au.com.buenosystems.marsrover.command.RoverController;
import au.com.buenosystems.marsrover.command.RoverCommandValidator;
import au.com.buenosystems.marsrover.rover.Direction;
import au.com.buenosystems.marsrover.rover.Rover;
import au.com.buenosystems.marsrover.terrain.Position;
import au.com.buenosystems.marsrover.terrain.Surface;

public final class MarsRover implements Rover {

    private Position position;
    private Direction direction;

    @Override
    public void initialise(final Position position, final Direction direction) {
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
        if (position.getSurface() == Surface.ROCK) {
            throw new UnsupportedOperationException(String.format("Unfriendly Mars Rover surface: %s",
                    position.getSurface()));
        }
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

    @Override
    public RoverController getRoverController() { return new MarsRoverController(this); }
}
