import au.com.buenosystems.marsrover.MarsRoverCommandValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsRoverCommandValidatorTests {

    private MarsRoverCommandValidator marsRoverCommandValidator;

    @BeforeAll
    public void initialise() {
        marsRoverCommandValidator = new MarsRoverCommandValidator();
    }

    @Test
    public void testIsValidStringInvalidDoesNotReturnTrue() {
        String command = "LRMxXMFFLR";
        assertNotEquals(true, marsRoverCommandValidator.isValid(command));
    }

    @Test
    public void testIsValidStringInvalidReturnsFalse() {
        String command = "LRMxXMFFLR";
        assertFalse(marsRoverCommandValidator.isValid(command));
    }

    @Test
    public void testIsValidStringValidDoesNotReturnFalse() {
        String command = "RMLRLMRMLMM";
        assertNotEquals(false, marsRoverCommandValidator.isValid(command));
    }

    @Test
    public void testIsValidStringValidReturnsTrue() {
        String command = "RMLRLMRMLMM";
        assertTrue(marsRoverCommandValidator.isValid(command));
    }

    @Test
    public void testIsValidStringEmptyDoesNotReturnTrue() {
        String command = "";
        assertNotEquals(true, marsRoverCommandValidator.isValid(command));
    }

    @Test
    public void testIsValidUppercaseXReturnsTrue() {
        String command = "X";
        assertTrue(marsRoverCommandValidator.isValid(command));
    }

    @Test
    public void testIsValidUppercaseXDoesNotReturnFalse() {
        String command = "X";
        assertNotEquals(false, marsRoverCommandValidator.isValid(command));
    }

    @Test
    public void testIsValidLowercaseXReturnsTrue() {
        String command = "x";
        assertTrue(marsRoverCommandValidator.isValid(command));
    }

    @Test
    public void testIsValidLowercaseXDoesNotReturnFalse() {
        String command = "x";
        assertNotEquals(false, marsRoverCommandValidator.isValid(command));
    }
}
