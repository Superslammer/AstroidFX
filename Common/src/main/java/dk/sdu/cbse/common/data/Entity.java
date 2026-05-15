package dk.sdu.cbse.common.data;

import java.util.UUID;

public class Entity {
    private final UUID ID = UUID.randomUUID();

    private double x;
    private double y;
    private double angle;
    private Vector velocity;
    private double[][] boundingBox;

    public String getID() {
        return ID.toString();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = ((angle % 360) + 360) % 360;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public double[][] getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(double[][] boundingBox) {
        this.boundingBox = boundingBox;
    }
}
