package au.com.buenosystems.marsrover.terrain;

import java.util.Objects;

public final class Position {
    private Coordinates coordinates;
    private Surface surface;

    public Position(Coordinates coordinates, Surface surface) {
        this.coordinates = coordinates;
        this.surface = surface;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Surface getSurface() {
        return surface;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return coordinates.equals(position.coordinates) &&
                surface == position.surface;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, surface);
    }
}
