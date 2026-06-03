package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.Vector;

public class Player extends Entity {
    private static final double ROTATION_ANGLE = 150d;
    private static final double MOVEMENT_SPEED = 2d;
    private static final double MAX_MOVEMENT_SPEED = 255d;

    public double getRotationAngle() {
        return ROTATION_ANGLE;
    }

    public double getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    @Override
    public void setVelocity(Vector vel){
        if (vel.magnitude() > MAX_MOVEMENT_SPEED){
            double s = MAX_MOVEMENT_SPEED / vel.magnitude();
            super.setVelocity(vel.scale(s));
            return;
        }
        super.setVelocity(vel);
    }
}
