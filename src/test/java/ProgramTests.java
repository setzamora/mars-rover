import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramTests {

    @Test
    public void testIsRoverOperationalReturnsTrue() {
        assertTrue(Program.isRoverOperational("LMRMRLMRLMRLMR"));
    }

    @Test
    public void testIsRoverOperationalReturnsFalse() {
        assertFalse(Program.isRoverOperational("X"));
        assertFalse(Program.isRoverOperational("x"));
    }

    @Test
    public void testIsRoverOperationalDoesNotReturnTrue() {
        assertTrue(!Program.isRoverOperational("X"));
        assertTrue(!Program.isRoverOperational("x"));
    }

    @Test
    public void testIsRoverOperationalDoesNotReturnFalse() {
        assertFalse(!Program.isRoverOperational("MMLRMLRMLRMRLMxX"));
    }

    @Test
    public void testIsTerrainPathSpecifiedReturnsTrue() {
        String[] args = { "C:\\Some\\Arbitrary\\Path\\Terrain.txt" };
        assertTrue(Program.isTerrainPathSpecified(args));
    }

    @Test
    public void testIsTerrainPathSpecifiedEmptyReturnsFalse() {
        String[] args = { "" };
        assertFalse(Program.isTerrainPathSpecified(args));
    }

    @Test
    public void testIsTerrainPathSpecifiedNullReturnsFalse() {
        String[] args = null;
        assertFalse(Program.isTerrainPathSpecified(args));
    }

    @Test
    public void testDisplayCommandStatsSucceeds() {
        try {
            Program.displayCommandStats();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetTerrainPathReturnsCustomPath() {
        String[] args = { "C:\\Some\\Arbitrary\\Path\\Terrain.txt" };
        try {
            assertEquals("C:\\Some\\Arbitrary\\Path\\Terrain.txt", Program.getTerrainPath(args).toString());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetTerrainPathDoesNotReturnCustomPath() {
        String[] args = { "" };
        try {
            assertNotEquals("C:\\Some\\Arbitrary\\Path\\Terrain.txt", Program.getTerrainPath(args).toString());
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
