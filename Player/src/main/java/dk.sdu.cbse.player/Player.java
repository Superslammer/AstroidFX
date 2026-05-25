package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;

public class Player extends Entity {
    private static final double ROTATION_ANGLE = 12d;
    private static final double MOVEMENT_SPEED = 2d;

    public double getRotationAngle() {
        return ROTATION_ANGLE;
    }

    public double getMovementSpeed() {
        return MOVEMENT_SPEED;
    }
}
