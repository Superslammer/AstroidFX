package dk.sdu.cbse.common.data;

import javafx.scene.input.KeyCode;

public class GameData {
    private static final double WIDTH = 800;
    private static final double HEIGHT = 800;

    private double deltaT = 0;

    private boolean left = false;
    private boolean right = false;
    private boolean forwards = false;
    private boolean backwards = false;
    private boolean shoot = false;

    public double getWidth() {
        return WIDTH;
    }

    public double getHeight() {
        return HEIGHT;
    }

    public void pressKey(KeyCode code){
        if(code == KeyCode.LEFT || code == KeyCode.A){
            left = true;
        }
        else if(code == KeyCode.RIGHT || code == KeyCode.D){
            right = true;
        }
        else if(code == KeyCode.UP || code == KeyCode.W){
            forwards = true;
        }
        else if(code == KeyCode.DOWN || code == KeyCode.S){
            backwards = true;
        }
        else if(code == KeyCode.SPACE){
            shoot = true;
        }
    }

    public void releaseKey(KeyCode code){
        if(code == KeyCode.LEFT || code == KeyCode.A){
            left = false;
        }
        else if(code == KeyCode.RIGHT || code == KeyCode.D){
            right = false;
        }
        else if(code == KeyCode.UP || code == KeyCode.W){
            forwards = false;
        }
        else if(code == KeyCode.DOWN || code == KeyCode.S){
            backwards = false;
        }
        else if(code == KeyCode.SPACE){
            shoot = false;
        }
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isForwards() {
        return forwards;
    }

    public boolean isBackwards() {
        return backwards;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setDeltaT(double dt){
        deltaT = dt;
    }

    public double getDeltaT(){
        return deltaT;
    }
}
