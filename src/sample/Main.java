package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application {
    ArrayList<Body> BODIES = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("N-Body Simulator");
        primaryStage.setScene(new Scene(root, 800, 600, Color.BLACK));

        Color colors[] = new Color[] {
                Color.RED,
                Color.BLUE,
                Color.AQUA,
                Color.YELLOW,
                Color.GREEN,
                Color.PURPLE,
                Color.BISQUE,
                Color.CRIMSON,
                Color.ORANGE,
                Color.HONEYDEW};
        for(int i=0; i<10; i++) {
            Body b = new Body();
            int m = (int)(Math.random()*20);
            b.setRadius(m);
            b.setFill(colors[i]);
            b.setMass(m*100);
            b.setTranslateX((int)(Math.random()*800));
            b.setTranslateY((int) (Math.random() * 600));
            b.setVelocity(0,0);
            root.getChildren().add(b);
            BODIES.add(b);
        }
        primaryStage.show();
        Timeline timeline = new Timeline(60.0d, new KeyFrame(Duration.INDEFINITE));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                CalcDeltas(BODIES);
                for(Body b : BODIES) {
                    b.setTranslateX(b.getTranslateX()+b.getVelocityX());
                    b.setTranslateY(b.getTranslateY() + b.getVelocityY());
                }
            }
        };
        timeline.play();
        timer.start();
    }

    public static void CalcDeltas(ArrayList<Body> bodies) {
        for(Body a : bodies) {
            for(Body b : bodies) {
                if(a.equals(b)) {
                    continue;
                }
                double[] delta = getG(a, b);

                if(a.getTranslateX()>b.getTranslateX())
                    delta[0] *= -1;

                if(a.getTranslateY()>b.getTranslateY())
                    delta[1] *= -1;

                a.setVelocity(a.getVelocityX()+delta[0], a.getVelocityY()+delta[1]);
            }
        }
    }

    public static double[] getG(Body a, Body b) {
        double diffX = Math.abs(a.getTranslateX()-b.getTranslateX());
        double diffY = Math.abs(a.getTranslateY()-b.getTranslateY());
        double radius = Math.sqrt(Math.pow(diffX, 2)+Math.pow(diffY, 2));
        double f = (0.000002 * a.getMass() * b.getMass()) / (Math.pow(radius, 2));
        return new double[] {diffX*f, diffY*f};

    }


    public static void main(String[] args) {
        launch(args);
    }
}
