package sample;

import javafx.scene.shape.Circle;

import java.util.Map;

/**
 * Created by bjt on 6/12/15.
 */
public class Body extends Circle {
    private int mass;
    private double[] velocity = new double[2];
    private static int X = 0;
    private static int Y = 1;
    //private static int Z = 2;

    public void setVelocity(double x, double y) {
        velocity[X] = x;
        velocity[Y] = y;
        //velocity[Z] = z;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getMass() {
        return this.mass;
    }

    public double getVelocityX() {
        return velocity[X];
    }

    public double getVelocityY() {
        return velocity[Y];
    }

    /*public double getVelocityZ() {
        return velocity[Z];
    }*/

}
