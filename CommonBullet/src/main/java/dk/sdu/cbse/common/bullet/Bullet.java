package dk.sdu.cbse.common.bullet;

import dk.sdu.cbse.common.data.Entity;

public class Bullet extends Entity {
    private double iTime = 0.009d;

    public double getITime() {
        return iTime;
    }

    public void subITime(double iTime) {
        this.iTime -= iTime;
    }

}