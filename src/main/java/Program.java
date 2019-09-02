import au.com.buenosystems.marsrover.*;
import au.com.buenosystems.marsrover.command.RoverCommand;
import au.com.buenosystems.marsrover.command.RoverCommandStats;
import au.com.buenosystems.marsrover.command.RoverController;
import au.com.buenosystems.marsrover.helper.ProgramHelper;
import au.com.buenosystems.marsrover.rover.Rover;
import au.com.buenosystems.marsrover.terrain.Terrain;
import au.com.buenosystems.marsrover.view.TerrainView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    public static void main(String... args) {
        try {
            final Path path = ProgramHelper.getTerrainPath(args);

            final Terrain marsPlateau = new MarsPlateau();
            marsPlateau.initialise(path);

            final Rover marsRover = new MarsRover();
            marsRover.initialise(marsPlateau.getPositions()[0][5], MarsRoverDirection.East);

            final TerrainView marsTerrainView = new MarsTerrainView();
            marsTerrainView.setRover(marsRover);
            marsTerrainView.setTerrain(marsPlateau);

            final RoverController marsRoverController = marsRover.getRoverController();
            marsRoverController.initialise(marsPlateau);

            System.out.println(String.format("\nMars Rover v1.0 running, %s configuration is:", marsPlateau.getName().toLowerCase()));

            String roverCommands = null;
            final Scanner scanner = new Scanner(System.in);
            while (RoverCommand.isOperational(roverCommands)) {

                if (marsRover.getRoverCommandValidator().isValid(roverCommands)) {
                    for (char roverCommand : roverCommands.toCharArray()) {
                        marsRoverController.processCommand(roverCommand);
                    }
                }

                marsTerrainView.render();

                System.out.println("\nWaiting for commands.");
                System.out.print("> ");
                roverCommands = scanner.next();
            }
        } catch (URISyntaxException | IOException e) {
            System.err.println(String.format("Something went wrong: %s", e.getMessage()));
        } finally {
            RoverCommandStats.display();
            System.out.println("Mars Rover v1.0 closed.");
        }
    }
}
