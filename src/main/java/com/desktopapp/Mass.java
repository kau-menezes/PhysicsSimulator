package com.desktopapp;

public class Mass {

  Double mass;

  Double yPosition;
  Double xPosition;

  Double yVelocity;
  Double xVelocity;

  Double k;

  String color;

  Double diameter;
    
    public Mass(Double k, Double mass, Double xVelocity, Double yVelocity, Double xPosition, Double yPosition, String color, Double diameter ) {
      this.mass = mass;  
      this.k = k;
      this.xPosition = xPosition;
      this.yPosition = yPosition;
      this.xVelocity = xVelocity;
      this.yVelocity = yVelocity;
      this.color = color;
      this.diameter = diameter;
    }

    public void Movement(Double gravity, Double MaxWidth, Double MaxHeight) {
       
      this.yVelocity += gravity * 0.04f;

      this.yPosition += this.yVelocity * 0.04f;
      this.xPosition += this.xVelocity * 0.04f;

      if(this.yPosition >= (MaxHeight - this.diameter)) { 
        
        var passou = this.yPosition - (MaxHeight - this.diameter);
        
        this.yPosition -= passou;
        this.yVelocity *= - k;
      }
      
      if (this.yPosition < 0) { 
        
        var passou = (this.yPosition - this.diameter) * -1 ;
        
        this.yPosition += passou;
        this.yVelocity *= - k;
      }

      if(this.xPosition >= (MaxWidth - this.diameter) || this.xPosition <= 0)  { 
        
        var passou = (double) 0;

        if(xVelocity > 0) {
          passou = this.xPosition - (MaxWidth - this.diameter);

        } else {
          passou = this.xPosition ;
        }
        
        this.xPosition -= passou;
        this.xVelocity *= - k;
      }

      
    }

}
