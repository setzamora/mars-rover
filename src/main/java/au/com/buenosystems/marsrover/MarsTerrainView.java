package au.com.buenosystems.marsrover;

import au.com.buenosystems.marsrover.terrain.Position;
import au.com.buenosystems.marsrover.terrain.Surface;
import au.com.buenosystems.marsrover.view.TerrainView;

public class MarsTerrainView extends TerrainView {

    @Override
    public void render() {
        if (terrain == null) {
            System.out.println("Invalid terrain / no terrain set");
            return;
        }

        System.out.println();
        for (int y = terrain.getPositions()[0].length -1; y >= 0; y--) {
            StringBuffer lineBuffer = new StringBuffer();
            for (int x = 0; x < terrain.getPositions().length; x++) {
                Position position = terrain.getPositions()[x][y];
                if (position.equals(rover.getPosition())) {
                    lineBuffer.append('X');
                } else {
                    lineBuffer.append(position.getSurface() == Surface.PLAIN ? 'o' : 'R');
                }
            }
            System.out.println(lineBuffer.toString());
        }
        System.out.println();
        System.out.println(String.format("Rover is facing: %s", rover.getDirection()));
    }
}
