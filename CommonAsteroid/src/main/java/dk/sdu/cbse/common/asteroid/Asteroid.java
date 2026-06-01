package dk.sdu.cbse.common.asteroid;

import dk.sdu.cbse.common.data.Entity;

public class Asteroid extends Entity {
    private double rotationSpeed;
    private double timeoutTimer = 1d;
    private int life = 3;

    public void setLife(int life){
        this.life = life;
    }

    public int getLife() {
        return life;
    }

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