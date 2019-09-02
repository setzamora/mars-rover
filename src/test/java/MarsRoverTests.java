import au.com.buenosystems.marsrover.MarsPlateau;
import au.com.buenosystems.marsrover.MarsRover;
import au.com.buenosystems.marsrover.MarsRoverController;
import au.com.buenosystems.marsrover.MarsRoverDirection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsRoverTests {

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

    @Test
    public void testFacingNorthRotateLeftReturnsWest() {
        marsRover.initialise(marsPlateau.getPositions()[0][0], MarsRoverDirection.North);

        marsRover.rotateLeft();
        assertEquals(MarsRoverDirection.West, marsRover.getDirection());
    }

    @Test
    public void testFacingNorthRotateRightReturnsEast() {
        marsRover.initialise(marsPlateau.getPositions()[0][0], MarsRoverDirection.North);

        marsRover.rotateRight();
        assertEquals(MarsRoverDirection.East, marsRover.getDirection());
    }

    @Test
    public void testFacingEastRotateLeftReturnsNorth() {
        marsRover.initialise(marsPlateau.getPositions()[0][0], MarsRoverDirection.East);

        marsRover.rotateLeft();
        assertEquals(MarsRoverDirection.North, marsRover.getDirection());
    }

    @Test
    public void testFacingEastRotateRightReturnsSouth() {
        marsRover.initialise(marsPlateau.getPositions()[0][0], MarsRoverDirection.East);

        marsRover.rotateRight();
        assertEquals(MarsRoverDirection.South, marsRover.getDirection());
    }

    @Test
    public void testFacingSouthRotateLeftReturnsEast() {
        marsRover.initialise(marsPlateau.getPositions()[0][0], MarsRoverDirection.South);

        marsRover.rotateLeft();
        assertEquals(MarsRoverDirection.East, marsRover.getDirection());
    }

    @Test
    public void testFacingSouthRotateRightReturnsWest() {
        marsRover.initialise(marsPlateau.getPositions()[0][0], MarsRoverDirection.South);

        marsRover.rotateRight();
        assertEquals(MarsRoverDirection.West, marsRover.getDirection());
    }

    @Test
    public void testFacingWestRotateLeftReturnsSouth() {
        marsRover.initialise(marsPlateau.getPositions()[0][0], MarsRoverDirection.West);

        marsRover.rotateLeft();
        assertEquals(MarsRoverDirection.South, marsRover.getDirection());
    }

    @Test
    public void testFacingWestRotateRightReturnsNorth() {
        marsRover.initialise(marsPlateau.getPositions()[0][0], MarsRoverDirection.West);

        marsRover.rotateRight();
        assertEquals(MarsRoverDirection.North, marsRover.getDirection());
    }

    @Test
    public void testGetRoverControllerReturnsMarsRoverController() {
        assertTrue(marsRover.getRoverController() instanceof MarsRoverController);
    }
}
