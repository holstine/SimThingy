package Things;

import java.awt.*;
import java.util.*;
import java.io.*;

import simthingy.Animator;
import simthingy.Properties;


public abstract  class Thing implements Serializable
{
  public String name = "thing";
  public abstract void force();
  public abstract void showThing(Animator a);
  public abstract void move();

  public void addXForce(double f){

    if (f *Properties.deltaT *Properties.deltaT > Properties.redFlagConst){
      //		Properties.goFlag = false;
      Properties.redFlag  = true;
    }else{
      xforce += f;
    }
  }
  public void addYForce(double f){
    if (f *Properties.deltaT *Properties.deltaT > Properties.redFlagConst){
      //		Properties.goFlag = false;
      Properties.redFlag  = true;
    }else{
      yforce += f;
    }
  }
  public double xForce(){return xforce;}
  public double yForce(){return yforce;}
  public void zeroForce(){xforce = 0;yforce = 0;}


  public double xpos;
  public double ypos;
  public double xvel;
  public double yvel;
  public double xforce;
  public double yforce;
  public double mass;


}
