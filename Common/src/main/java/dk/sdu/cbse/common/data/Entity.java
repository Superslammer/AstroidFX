package dk.sdu.cbse.common.data;

import javafx.scene.shape.Polygon;

import java.util.UUID;

public class Entity {
    private final UUID ID = UUID.randomUUID();

    protected double x = 0;
    protected double y = 0;
    protected double angle = 0;
    private Vector velocity = new Vector();
    private Polygon sprite = new Polygon();

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

    public Polygon getSprite() {
        return sprite;
    }

    public void setSprite(Polygon boundingBox) {
        this.sprite = boundingBox;
    }

    public void setPosition(Vector pos){
        x = pos.getX();
        y = pos.getY();
    }

    public Vector getPosition(){
        return new Vector(x, y);
    }
}
