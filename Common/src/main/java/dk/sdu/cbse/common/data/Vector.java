package dk.sdu.cbse.common.data;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector(){}

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

    public double magnitude(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector scale(double s){
        this.x = x * s;
        this.y = y * s;
        return this;
    }

    public Vector add(Vector other){
        x = x + other.x;
        y = y + other.y;
        return this;
    }

    public Vector sub(Vector other){
        other.setX(-other.getX());
        other.setY(-other.getY());

        return this.add(other);
    }

    public Vector normalize(){
        double s = 1/magnitude();
        return scale(s);
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
