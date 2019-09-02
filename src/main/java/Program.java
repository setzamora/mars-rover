import au.com.buenosystems.marsrover.*;
import au.com.buenosystems.marsrover.command.RoverController;
import au.com.buenosystems.marsrover.event.Event;
import au.com.buenosystems.marsrover.event.EventStore;
import au.com.buenosystems.marsrover.rover.Rover;
import au.com.buenosystems.marsrover.terrain.Terrain;
import au.com.buenosystems.marsrover.view.TerrainView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String... args) {
        try {
            final Path path = getTerrainPath(args);

            final Terrain marsPlateau = new MarsPlateau();
            marsPlateau.initialise(path);

            final Rover marsRover = new MarsRover();
            marsRover.initialise(marsPlateau.getPositions()[0][5], MarsRoverDirection.East);

            final TerrainView marsTerrainView = new MarsTerrainView();
            marsTerrainView.setRover(marsRover);
            marsTerrainView.setTerrain(marsPlateau);

            final RoverController marsRoverController = marsRover.getRoverController();
            marsRoverController.initialise(marsPlateau);

            final Scanner scanner = new Scanner(System.in);
            String roverCommands = null;

            System.out.println(String.format("\nMars Rover v1.0 running, %s configuration is:", marsPlateau.getName().toLowerCase()));
            while (isRoverOperational(roverCommands)) {

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
            displayCommandStats();
            System.out.println("Mars Rover v1.0 closed.");
        }
    }

    public static Path getTerrainPath(String[] args) throws URISyntaxException {
        Path path;
        if (isTerrainPathSpecified(args)) {
            path = Paths.get(args[0]);
        } else {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            final URI uri = classLoader.getResource("plateau.txt").toURI();
            path = Paths.get(uri);
        }
        return path;
    }

    public static void displayCommandStats() {
        int allCommandsCount = EventStore.getInstance().size();
        int failedCommandsCount = EventStore.getInstance()
                .stream()
                .filter(e -> Objects.equals(Event.ROVER_COMMAND_FAILED, e.getName()))
                .collect(Collectors.toList()).size();
        System.out.println(String.format("\nSent %d command(s) / %d failed.\n", allCommandsCount, failedCommandsCount));
    }

    public static boolean isRoverOperational(String roverCommands) {
        return roverCommands == null
                || !roverCommands.equalsIgnoreCase("x");
    }

    public static boolean isTerrainPathSpecified(final String[] args) {
        return args != null && args.length > 0 && !args[0].isEmpty() && isPathValid(args[0]);
    }

    public static boolean isPathValid(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException e) {
            return false;
        }
        return true;
    }
}
