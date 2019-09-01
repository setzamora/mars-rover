package au.com.buenosystems.marsrover;

import au.com.buenosystems.marsrover.rover.Direction;

public enum MarsRoverDirection implements Direction {

    North(0,1) {
        @Override
        public MarsRoverDirection rotateLeft() {
            return West;
        }

        @Override
        public MarsRoverDirection rotateRight() {
            return East;
        }
    },

    South(0,-1) {
        @Override
        public MarsRoverDirection rotateLeft() {
            return East;
        }
        @Override
        public MarsRoverDirection rotateRight() {
            return West;
        }
    },

    East(1,0) {
        @Override
        public MarsRoverDirection rotateLeft() {
            return North;
        }

        @Override
        public MarsRoverDirection rotateRight() {
            return South;
        }
    },

    West(-1,0) {
        @Override
        public MarsRoverDirection rotateLeft() {
            return South;
        }

        @Override
        public MarsRoverDirection rotateRight() {
            return North;
        }
    };

    private int stepX;
    private int stepY;

    MarsRoverDirection(final int stepX, final int stepY) {
        this.stepX = stepX;
        this.stepY = stepY;
    }

    @Override
    public int getStepX() {
        return stepX;
    }

    @Override
    public int getStepY() {
        return stepY;
    }
}
