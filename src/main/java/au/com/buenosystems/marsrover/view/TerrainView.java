package au.com.buenosystems.marsrover.view;

import au.com.buenosystems.marsrover.rover.Rover;
import au.com.buenosystems.marsrover.terrain.Terrain;

public abstract class TerrainView {

    protected Rover rover;
    protected Terrain terrain;

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public abstract void render();
}
