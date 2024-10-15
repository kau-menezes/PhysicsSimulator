package com.desktopapp;

import java.lang.Math;
import java.util.ArrayList;

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

    public ArrayList<Double> SpringForce() {

        Double x = Math.sqrt(Math.pow((this.mass1.xPosition - this.mass2.xPosition), 2) + Math.pow((this.mass1.yPosition - this.mass2.yPosition), 2));
        
        var force = (this.len - x) * ( this.k);

        ArrayList<Double> forcas = new ArrayList<>();

        var fx = force * ((this.mass2.xPosition - this.mass1.xPosition) / x);
        var fy =  force * ((this.mass2.yPosition - this.mass1.yPosition) / x);

        forcas.add(fx);
        forcas.add(fy);
        forcas.add(force);

        return forcas;
    }
    
}
