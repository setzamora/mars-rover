import au.com.buenosystems.marsrover.command.RoverCommand;
import au.com.buenosystems.marsrover.command.RoverCommandStats;
import au.com.buenosystems.marsrover.helper.ProgramHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramTests {

    @Test
    public void testIsRoverOperationalReturnsTrue() {
        assertTrue(RoverCommand.isOperational("LMRMRLMRLMRLMR"));
    }

    @Test
    public void testIsRoverOperationalReturnsFalse() {
        assertFalse(RoverCommand.isOperational("X"));
        assertFalse(RoverCommand.isOperational("x"));
    }

    @Test
    public void testIsRoverOperationalDoesNotReturnTrue() {
        assertTrue(!RoverCommand.isOperational("X"));
        assertTrue(!RoverCommand.isOperational("x"));
    }

    @Test
    public void testIsRoverOperationalDoesNotReturnFalse() {
        assertFalse(!RoverCommand.isOperational("MMLRMLRMLRMRLMxX"));
    }

    @Test
    public void testIsArgumentSpecifiedReturnsTrue() {
        String[] args = { "C:\\Some\\Arbitrary\\Path\\Terrain.txt" };
        assertTrue(ProgramHelper.isArgumentSpecified(args));
    }

    @Test
    public void testIsArgumentSpecifiedEmptyReturnsFalse() {
        String[] args = { "" };
        assertFalse(ProgramHelper.isArgumentSpecified(args));
    }

    @Test
    public void testIsArgumentSpecifiedNullReturnsFalse() {
        String[] args = null;
        assertFalse(ProgramHelper.isArgumentSpecified(args));
    }

    @Test
    public void testDisplayCommandStatsSucceeds() {
        try {
            RoverCommandStats.display();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetTerrainPathReturnsCustomPath() {
        String[] args = { "C:\\Some\\Arbitrary\\Path\\Terrain.txt" };
        try {
            assertEquals("C:\\Some\\Arbitrary\\Path\\Terrain.txt", ProgramHelper.getTerrainPath(args).toString());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetTerrainPathDoesNotReturnCustomPath() {
        String[] args = { "" };
        try {
            assertNotEquals("C:\\Some\\Arbitrary\\Path\\Terrain.txt", ProgramHelper.getTerrainPath(args).toString());
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
