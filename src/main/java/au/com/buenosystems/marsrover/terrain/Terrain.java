package au.com.buenosystems.marsrover.terrain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Terrain {

    protected Position[][] positions;
    protected Deque<String> data;
    protected int lengthX;
    protected int lengthY;

    public void initialise(final Path path) throws IOException {
        initialiseData(path);
        initialisePositions();
    }

    private void initialisePositions() {
        int currentY = 0;
        for (final String line : data) {
            int currentX = 0;
            for (final char datum : line.toCharArray()) {
                final Coordinates coordinates = new Coordinates(currentX, currentY);
                final Surface surface = datum == 'R' ? Surface.ROCK : Surface.PLAIN;
                final Position position = new Position(coordinates, surface);
                positions[currentX][currentY] = position;
                currentX++;
            }
            currentY++;
        }
    }

    private void initialiseData(Path path) throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path.toFile()));
            String line = reader.readLine();
            while (line != null) {
                if (data == null) {
                    data = new ArrayDeque<>();
                    lengthX = line.length();
                }
                lengthY++;
                data.push(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw e;
        }

        if (positions == null) {
            positions = new Position[lengthX][lengthY];
        }
    }

    public Position[][] getPositions() {
        return positions;
    }

    public boolean isWithinBounds(Coordinates coordinates) {
        if (isWithinXBounds(coordinates)
            && isWithinYBounds(coordinates)) {
            return true;
        }
        return false;
    }

    public boolean isWithinBounds(Position position) {
        return isWithinBounds(position.getCoordinates());
    }

    private boolean isWithinXBounds(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < lengthX;
    }

    private  boolean isWithinYBounds(Coordinates coordinates) {
        return coordinates.getY() >= 0 && coordinates.getY() < lengthY;
    }

    public abstract String getName();
}
