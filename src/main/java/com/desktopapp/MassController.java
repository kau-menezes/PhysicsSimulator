package com.desktopapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MassController implements Initializable {
    
    protected static Scene CreateScene() throws Exception {
        
        URL sceneUrl = MassController.class.getResource("MassSimulator.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        
        Timer timer = new Timer(); 
        MassController controller = loader.getController();
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                controller.canvas.setWidth(scene.getWidth());
                controller.canvas.setHeight(scene.getHeight());
            }
            
        }, 0, 55);
        
        return scene;
    }

    @FXML
    private Canvas canvas;
    
    @FXML
    private VBox vbox;
    
    Timer timer = new Timer();
    GraphicsContext g;


    protected void renderBall(ArrayList<Mass> bolinhas) {

        for (Mass ball : bolinhas) {
            
            g.setFill(Color.web(ball.color));

            g.fillOval(ball.xPosition, ball.yPosition, ball.diameter, ball.diameter);

            ball.Movement(200.0, canvas.getWidth(), canvas.getHeight());
            
        }

    }


    protected void renderSpring(ArrayList<Spring> molinhas) {
        

        for (Spring spring : molinhas) {
            
            ArrayList<Double> forces = spring.SpringForce();

            spring.mass1.xVelocity -= forces.get(0) / spring.mass1.mass;
            spring.mass1.yVelocity -= forces.get(1) / spring.mass1.mass;

            spring.mass2.xVelocity += forces.get(0) / spring.mass2.mass;
            spring.mass2.yVelocity += forces.get(1) / spring.mass2.mass;
                            
            var force = forces.get(2) / 200;

            if (Math.abs(force) > 1) force = 1;

            Color new_color = new Color
            (Math.abs(force), 
            0, 
            0, 
            1f);

            Color new_color2 = new Color
            (0, 
            Math.abs(force), 
            0, 
            1f);

            if (force < 0) {
                g.setStroke(new_color2);

            } else {
                g.setStroke(new_color);
            }

            g.strokeLine(
                spring.mass1.xPosition + spring.mass1.diameter/2,
                spring.mass1.yPosition + spring.mass1.diameter/2,
                spring.mass2.xPosition + spring.mass2.diameter/2,
                spring.mass2.yPosition + spring.mass2.diameter/2
            );
            
        }

    }
     
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        this.g = canvas.getGraphicsContext2D();

        ArrayList<Mass> listinha = new ArrayList<>();
        Mass bolinha1 = new Mass(0.95, 6.5, -50.0, 0.0, 300d, 300d, "#5e25a8", 25d);
        Mass bolinha2 = new Mass(0.95, 6.5,  50.0, -9.0,300d, 190d, "#e6d410", 25d);
        Mass bolinha3 = new Mass(0.95, 6.5,  30.0, 0.0,60d, 200d, "#387594", 25d);
        Mass bolinha4 = new Mass(0.95, 6.5,  -20.0, 0.0,60d, 250d, "#3164b5", 25d);
        Mass bolinha5 = new Mass(0.95, 6.5,  -10.0, 0.0,60d, 220d, "#ad289e", 25d);

        ArrayList<Spring> molinhas = new ArrayList<>();

        listinha.add(bolinha1);
        listinha.add(bolinha2);
        listinha.add(bolinha3);
        listinha.add(bolinha4);
        listinha.add(bolinha5);

        Spring molinha1 = new Spring(bolinha1, bolinha2, 4.0d, 150d);
        Spring molinha2 = new Spring(bolinha1, bolinha3, 4.0d, 150d);
        Spring molinha3 = new Spring(bolinha2, bolinha4, 4.0d, 150d);
        Spring molinha4 = new Spring(bolinha5, bolinha1, 4.0d, 150d);
        Spring molinha5 = new Spring(bolinha5, bolinha3, 4.0d, 150d);
        Spring molinha6 = new Spring(bolinha5, bolinha4, 4.0d, 150d);
        Spring molinha7 = new Spring(bolinha2, bolinha3, 4.0d, 150d);

        molinhas.add(molinha1);
        molinhas.add(molinha2);
        molinhas.add(molinha3);
        molinhas.add(molinha4);
        molinhas.add(molinha5);
        molinhas.add(molinha6);
        molinhas.add(molinha7);

        Square quadardo = new Square(4d, 100d, 6.5, 200d, 200d, "#252525");
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                renderSpring(quadardo.springs);
                renderBall(quadardo.balls);

                // renderSpring(molinhas);
                // renderBall(listinha);
            }
            
        }, 100, 31);

    }
}


