package dk.sdu.cbse.common.enemy;

import dk.sdu.cbse.common.data.Entity;

public class Enemy extends Entity {
    private static final double BOUNCE_ANGLE = 10d;
    private static final double BOUNCE_COOLDOWN_TIME = 1d;
    private static final double MOVEMENT_SPEED = 200d;
    private double timeoutTimer= 1d;
    private double currentBounceCooldown = 0;
    private boolean isBounceUp = true;

    public double getBounceAngle() {
        return BOUNCE_ANGLE;
    }

    public double getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    public void subtractFromTimeout(double deltaT) {
        timeoutTimer -= deltaT;
    }

    public double getTimeout(){
        return timeoutTimer;
    }

    public void processBounce(double dTime){
        currentBounceCooldown -= dTime;
        if (currentBounceCooldown > 0){
            return;
        }

        if (isBounceUp){
            angle -= BOUNCE_ANGLE*2;
            isBounceUp = false;
        }
        else {
            angle += BOUNCE_ANGLE*2;
            isBounceUp = true;
        }

        currentBounceCooldown = BOUNCE_COOLDOWN_TIME;
    }
}
