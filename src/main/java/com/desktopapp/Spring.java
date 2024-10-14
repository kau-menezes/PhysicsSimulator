package com.desktopapp;

import java.lang.Math;

import javafx.scene.paint.Color;

public class Spring {

    Mass mass1;
    Mass mass2;
    Double k;
    Double len;

    public Spring(Mass mass1, Mass mass2, Double k, Double len){
        
        this.mass1 = mass1;
        this.mass2 = mass2;
        this.k = k;
        this.len = len;
    }

    public Double SpringForce() {

        Double x = Math.sqrt(Math.pow((this.mass1.xPosition - this.mass2.xPosition), 2) + Math.pow((this.mass1.yPosition - this.mass2.yPosition), 2));

        var force = (this.len - x) * this.k;

        return force;

    }
    
}
