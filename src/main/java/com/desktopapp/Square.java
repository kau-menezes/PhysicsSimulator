package com.desktopapp;

import java.util.ArrayList;

public class Square {

    ArrayList<Spring> springs = new ArrayList<>();
    ArrayList<Mass> balls = new ArrayList<>();

    
    public Square(Double k, Double side, Double mass, Double x, Double y, String color){

        balls.add(new Mass(0.95, mass, 0d, 0d, x, y, color, 10d));
        balls.add(new Mass(0.95, mass, 0d, 0d, x - side, y, color, 10d));
        balls.add(new Mass(0.95, mass, 0d, 0d, x, y - side, color, 10d));
        balls.add(new Mass(0.95, mass, 0d, 0d, x - side, y - side, color, 10d));

        for(int i = 0; i < 3; i++) {
            for(int j = i + 1; j < 4; j++) {
                springs.add(new Spring(balls.get(i), balls.get(j), k, y));
            }
        }

    }
}
