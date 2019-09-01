package au.com.buenosystems.marsrover.rover;

public interface Direction {
    Direction rotateLeft();
    Direction rotateRight();

    int getStepX();
    int getStepY();
}
