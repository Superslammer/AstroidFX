package dk.sdu.cbse.common.bullet;

import dk.sdu.cbse.common.data.Entity;

public class Bullet extends Entity {
    private static final int COOLDOWN_TICKS = 30;
    private int currentCooldown = 0;

    public void startCooldown() {
        currentCooldown = COOLDOWN_TICKS;
    }

    public void tickCooldown() {
        currentCooldown--;
    }

    public int getCooldown() {
        return currentCooldown;
    }
}