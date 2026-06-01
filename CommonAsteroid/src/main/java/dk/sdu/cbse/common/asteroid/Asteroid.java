package dk.sdu.cbse.common.asteroid;

import dk.sdu.cbse.common.data.Entity;

public class Asteroid extends Entity {
    private double rotationSpeed;
    private double timeoutTimer = 1d;

    public void subtractFromTimeout(double deltaT) {
        timeoutTimer -= deltaT;
    }

    public double getTimeout(){
        return timeoutTimer;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
}