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
        
        return scene;
    }


    
    @FXML
    private Canvas canvas;
    
    @FXML
    private VBox vbox;
    
    Timer timer = new Timer();
    GraphicsContext g;


    
    protected void renderBall(ArrayList<Mass> bolinhas) {

        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Mass ball : bolinhas) {
            
            g.setFill(Color.web(ball.color));

            g.fillOval(ball.xPosition, ball.yPosition, ball.diameter, ball.diameter);

            ball.FreeFall(200.0, canvas.getWidth(), canvas.getHeight());
            
        }

        vbox.requestLayout();
    }





    protected void renderSpring(ArrayList<Spring> molinhas) {

        for (Spring spring : molinhas) {
            
            
            Double force = spring.SpringForce();
            
            System.out.println(force);

            // Color newColor = new Color(
            //     (force) / 1000,
            //     0f,
            //     0f,
            //     1
            // );
                
            // g.setFill(newColor);
                
                
                
                
            g.strokeLine(
                spring.mass1.xPosition + spring.mass1.diameter/2,
                spring.mass1.yPosition + spring.mass1.diameter/2,
                spring.mass2.xPosition + spring.mass2.diameter/2,
                spring.mass2.yPosition + spring.mass2.diameter/2
            );
            
        }

        vbox.requestLayout();
    }


    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.g = canvas.getGraphicsContext2D();
        ArrayList<Mass> listinha = new ArrayList<>();
        Mass bolinha1 = new Mass(0.95, 4.0, -75.0, 0.0, 300d, 50d, "#5e25a8", 65d);
        Mass bolinha2 = new Mass(0.95, 4.0, 56.0, 0.0, 450d, 100d, "#e6d410", 25d);

        ArrayList<Spring> molinhas = new ArrayList<>();

        listinha.add(bolinha1);
        listinha.add(bolinha2);

        Spring molinha1 = new Spring(bolinha1, bolinha2, 2.0d, 300d);
        molinhas.add(molinha1);
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                renderBall(listinha);
                renderSpring(molinhas);
            }
            
        }, 0, 33);
        

    
    }
}


