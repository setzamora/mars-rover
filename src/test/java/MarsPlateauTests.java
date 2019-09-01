import au.com.buenosystems.marsrover.MarsPlateau;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import au.com.buenosystems.marsrover.terrain.Position;
import au.com.buenosystems.marsrover.terrain.Surface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsPlateauTests {

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
    public void get7and0ReturnsRock() {
        Position position = marsPlateau.getPositions()[7][0];
        assertEquals(Surface.ROCK, position.getSurface());
    }

    @Test
    public void get1and3ReturnsPlain() {
        Position position = marsPlateau.getPositions()[1][3];
        assertEquals(Surface.PLAIN, position.getSurface());
    }

    @Test
    public void get2and4ReturnsRock() {
        Position position = marsPlateau.getPositions()[2][4];
        assertEquals(Surface.ROCK, position.getSurface());
    }

    @Test
    public void get5and0ReturnsRock() {
        Position position = marsPlateau.getPositions()[5][0];
        assertEquals(Surface.ROCK, position.getSurface());
    }

    @Test
    public void get9and0ReturnsPlain() {
        Position position = marsPlateau.getPositions()[9][0];
        assertEquals(Surface.PLAIN, position.getSurface());
    }
}
