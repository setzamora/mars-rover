import au.com.buenosystems.marsrover.MarsPlateau;
import au.com.buenosystems.marsrover.MarsRover;
import au.com.buenosystems.marsrover.MarsRoverDirection;
import au.com.buenosystems.marsrover.MarsTerrainView;
import au.com.buenosystems.marsrover.event.Event;
import au.com.buenosystems.marsrover.event.EventStore;
import au.com.buenosystems.marsrover.rover.Direction;
import au.com.buenosystems.marsrover.rover.Rover;
import au.com.buenosystems.marsrover.terrain.Position;
import au.com.buenosystems.marsrover.terrain.Surface;
import au.com.buenosystems.marsrover.terrain.Terrain;
import au.com.buenosystems.marsrover.view.TerrainView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String... args) {
        try {
            Path path;
            if (isTerrainPathSpecified(args)) {
                path = Paths.get(args[0]);
            } else {
                final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                final URI uri = classLoader.getResource("plateau.txt").toURI();
                path = Paths.get(uri);
            }

            final Terrain marsPlateau = new MarsPlateau();
            marsPlateau.initialise(path);

            final Rover marsRover = new MarsRover();
            marsRover.initialise(marsPlateau.getPositions()[0][5], MarsRoverDirection.East);

            final TerrainView marsTerrainView = new MarsTerrainView();
            marsTerrainView.setRover(marsRover);
            marsTerrainView.setTerrain(marsPlateau);

            final Scanner scanner = new Scanner(System.in);
            String roverCommands = null;

            System.out.println(String.format("\nMars Rover v1.0 running, %s configuration is:", marsPlateau.getName().toLowerCase()));
            while (isRoverOperational(roverCommands)) {

                if (marsRover.getRoverCommandValidator().isValid(roverCommands)) {
                    for (char roverCommand : roverCommands.toCharArray()) {
                        processRoverCommand(marsPlateau, marsRover, roverCommand);
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
            int allCommandsCount = EventStore.getInstance().size();
            int failedCommandsCount = EventStore.getInstance()
                    .stream()
                    .filter(e -> Objects.equals(Event.ROVER_COMMAND_FAILED, e.getName()))
                    .collect(Collectors.toList()).size();
            System.out.println(String.format("\nSent %d command(s) / %d failed.\n", allCommandsCount, failedCommandsCount));
            System.out.println("Mars Rover v1.0 closed.");
        }
    }

    private static boolean isRoverOperational(String roverCommands) {
        return roverCommands == null
                || !roverCommands.equalsIgnoreCase("x");
    }

    private static boolean isTerrainPathSpecified(final String[] args) {
        return args != null && args.length > 0 && !args[0].isEmpty();
    }

    private static void processRoverCommand(final Terrain marsPlateau, final Rover marsRover, final char roverCommand) {
        switch (roverCommand) {
            case 'L':
                marsRover.rotateLeft();
                EventStore.getInstance().add(Event.create(Event.ROVER_ROTATED_LEFT, "Mars Rover Rotated Left"));
                break;
            case 'R':
                marsRover.rotateRight();
                EventStore.getInstance().add(Event.create(Event.ROVER_ROTATED_RIGHT, "Mars Rover Rotated Right"));
                break;
            case 'M':
                try {
                    final Position position = marsRover.getPosition();
                    final Direction direction = marsRover.getDirection();

                    final int newX = position.getCoordinates().getX() + direction.getStepX();
                    final int newY = position.getCoordinates().getY() + direction.getStepY();

                    final Position newPosition = marsPlateau.getPositions()[newX][newY];
                    if (newPosition.getSurface() == Surface.PLAIN) {
                        marsRover.move(newPosition);
                        EventStore.getInstance().add(Event.create(Event.ROVER_MOVED, "Mars Rover Moved"));
                    } else {
                        EventStore.getInstance().add(Event.create(Event.ROVER_COMMAND_FAILED, String.format("Mars Rover Command Failed: %s", Surface.ROCK)));
                    }
                } catch (Exception e) {
                    EventStore.getInstance().add(Event.create(Event.ROVER_COMMAND_FAILED, String.format("Mars Rover Command Failed: %s", e.getMessage())));
                }
                break;
            default:
                break;
        }
    }
}
