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

        ArrayList<Spring> molinhas = new ArrayList<>();

        listinha.add(bolinha1);
        listinha.add(bolinha2);
        listinha.add(bolinha3);

        Spring molinha1 = new Spring(bolinha1, bolinha2, 4.0d, 150d);
        Spring molinha2 = new Spring(bolinha1, bolinha3, 4.0d, 150d);
        molinhas.add(molinha1);
        molinhas.add(molinha2);
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                // renderSpring(molinhas);
                // renderBall(listinha);

                Square quadardo = new Square(4d, 100d, 6.5, 200d, 200d, "#252525");

                renderSpring(quadardo.springs);
                renderBall(quadardo.balls);
            }
            
        }, 100, 31);

    }
}


