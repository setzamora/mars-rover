import au.com.buenosystems.marsrover.MarsRover;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsRoverCommandValidatorTests {

    private MarsRover marsRover;

    @BeforeAll
    public void initialise() {
        marsRover = new MarsRover();
    }

    @Test
    public void testIsValidStringInvalidDoesNotReturnTrue() {
        String command = "LRMxXMFFLR";
        assertNotEquals(true, marsRover.getRoverCommandValidator().isValid(command));
    }

    @Test
    public void testIsValidStringInvalidReturnsFalse() {
        String command = "LRMxXMFFLR";
        assertFalse(marsRover.getRoverCommandValidator().isValid(command));
    }

    @Test
    public void testIsValidStringValidDoesNotReturnFalse() {
        String command = "RMLRLMRMLMM";
        assertNotEquals(false, marsRover.getRoverCommandValidator().isValid(command));
    }

    @Test
    public void testIsValidStringValidReturnsTrue() {
        String command = "RMLRLMRMLMM";
        assertTrue(marsRover.getRoverCommandValidator().isValid(command));
    }

    @Test
    public void testIsValidStringEmptyDoesNotReturnTrue() {
        String command = "";
        assertNotEquals(true, marsRover.getRoverCommandValidator().isValid(command));
    }

    @Test
    public void testIsValidUppercaseXReturnsTrue() {
        String command = "X";
        assertTrue(marsRover.getRoverCommandValidator().isValid(command));
    }

    @Test
    public void testIsValidUppercaseXDoesNotReturnFalse() {
        String command = "X";
        assertNotEquals(false, marsRover.getRoverCommandValidator().isValid(command));
    }

    @Test
    public void testIsValidLowercaseXReturnsTrue() {
        String command = "x";
        assertTrue(marsRover.getRoverCommandValidator().isValid(command));
    }

    @Test
    public void testIsValidLowercaseXDoesNotReturnFalse() {
        String command = "x";
        assertNotEquals(false, marsRover.getRoverCommandValidator().isValid(command));
    }
}
