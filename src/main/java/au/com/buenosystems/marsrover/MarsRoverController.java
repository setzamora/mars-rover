package au.com.buenosystems.marsrover;

import au.com.buenosystems.marsrover.command.RoverController;
import au.com.buenosystems.marsrover.event.Event;
import au.com.buenosystems.marsrover.rover.Direction;
import au.com.buenosystems.marsrover.rover.Rover;
import au.com.buenosystems.marsrover.terrain.Position;
import au.com.buenosystems.marsrover.terrain.Terrain;

public class MarsRoverController implements RoverController {

    private Terrain terrain;
    private Rover rover;

    public MarsRoverController(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void initialise(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public void processCommand(final char command) {
        switch (command) {
            case 'L':
                rover.rotateLeft();
                Event.fire(Event.ROVER_ROTATED_LEFT, "Mars Rover Rotated Left");
                break;
            case 'R':
                rover.rotateRight();
                Event.fire(Event.ROVER_ROTATED_RIGHT, "Mars Rover Rotated Right");
                break;
            case 'M':
                try {
                    final Position position = rover.getPosition();
                    final Direction direction = rover.getDirection();

                    final int newX = position.getCoordinates().getX() + direction.getStepX();
                    final int newY = position.getCoordinates().getY() + direction.getStepY();

                    final Position newPosition = terrain.getPositions()[newX][newY];
                    rover.move(newPosition);
                    Event.fire(Event.ROVER_MOVED, "Mars Rover Moved");
                } catch (Exception e) {
                    Event.fire(Event.ROVER_COMMAND_FAILED, String.format("Mars Rover Command Failed: %s",
                            e.getMessage()));
                }
                break;
            default:
                Event.fire(Event.ROVER_COMMAND_FAILED, "Mars Rover Command Failed: Unknown Command");
                break;
        }
    }
}
