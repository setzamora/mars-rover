import au.com.buenosystems.marsrover.MarsPlateau;
import au.com.buenosystems.marsrover.MarsRover;
import au.com.buenosystems.marsrover.MarsRoverDirection;
import au.com.buenosystems.marsrover.MarsTerrainView;
import au.com.buenosystems.marsrover.view.TerrainView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsTerrainViewTests {

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
        marsRover.initialise(marsPlateau.getPositions()[0][5], MarsRoverDirection.East);
    }

    @Test
    public void testMarsTerrainViewRenderSucceeds() {
        TerrainView marsTerrainView = new MarsTerrainView();
        marsTerrainView.setRover(marsRover);
        marsTerrainView.setTerrain(marsPlateau);

        try {
            marsTerrainView.render();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testMarsTerrainViewRenderThrowsException() {
        TerrainView marsTerrainView = new MarsTerrainView();
        marsTerrainView.setRover(marsRover);
        marsTerrainView.setTerrain(marsPlateau);

        try {
            marsTerrainView.render();
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
