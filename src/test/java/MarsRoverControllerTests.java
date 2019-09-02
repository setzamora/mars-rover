import au.com.buenosystems.marsrover.MarsPlateau;
import au.com.buenosystems.marsrover.MarsRover;
import au.com.buenosystems.marsrover.MarsRoverDirection;
import au.com.buenosystems.marsrover.command.RoverController;
import au.com.buenosystems.marsrover.terrain.Coordinates;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsRoverControllerTests {

    private MarsRover marsRover;
    private MarsPlateau marsPlateau;

    @BeforeAll
    public void initialise() {
        try {
            ClassLoader classLoader =Thread.currentThread().getContextClassLoader();
            URI uri = classLoader.getResource("plateau.txt").toURI();

            marsPlateau = new MarsPlateau();
            marsPlateau.initialise(Paths.get(uri));

            for (int i = 0; i < 6; i++) {
                assertEquals(10, marsPlateau.getPositions().length);
                assertEquals(6, marsPlateau.getPositions()[i].length);
            }
        } catch (URISyntaxException | IOException e) {
            fail(e.getMessage());
        }

        marsRover = new MarsRover();
    }

    /*   10 x 6 Plateau
     *   Y
     *   ----------------
     *   5 | ooooooRRRR
     *   4 | ooRooooooo
     *   3 | ooooooRRoo
     *   2 | ooRooooooo
     *   1 | oooooRoooo
     *   0 | oooooRRRoo
     *   ----------------
     *       0123456789 X
     */

    @Test
    public void testRoverCommandsMMMMRMLL_LandsAt4_4AndFacesNorth() {
        marsRover.initialise(marsPlateau.getPositions()[0][5], MarsRoverDirection.East);

        final RoverController marsRoverController = marsRover.getRoverController();
        marsRoverController.initialise(marsPlateau);

        String roverCommands = "MMMMRMLL";
        for (char roverCommand : roverCommands.toCharArray()) {
            marsRoverController.processCommand(roverCommand);
        }

        assertEquals(new Coordinates(4,4), marsRover.getPosition().getCoordinates());
        assertEquals(MarsRoverDirection.North, marsRover.getDirection());
    }

    @Test
    public void testRoverCommandsRRMMMMMR_LandsAt0_0AndFacesWest() {
        marsRover.initialise(marsPlateau.getPositions()[0][5], MarsRoverDirection.North);

        final RoverController marsRoverController = marsRover.getRoverController();
        marsRoverController.initialise(marsPlateau);

        String roverCommands = "RRMMMMMR";
        for (char roverCommand : roverCommands.toCharArray()) {
            marsRoverController.processCommand(roverCommand);
        }

        assertEquals(new Coordinates(0,0), marsRover.getPosition().getCoordinates());
        assertEquals(MarsRoverDirection.West, marsRover.getDirection());
    }
}
