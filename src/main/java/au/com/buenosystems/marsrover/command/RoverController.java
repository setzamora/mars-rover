package au.com.buenosystems.marsrover.command;

import au.com.buenosystems.marsrover.rover.Rover;
import au.com.buenosystems.marsrover.terrain.Terrain;

public interface RoverController {
    void initialise(final Terrain terrain);
    void processCommand(final char command);
}
