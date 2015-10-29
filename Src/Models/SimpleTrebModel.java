package Models;


import java.util.*;

import simthingy.Animator;
import simthingy.Properties;
import simthingy.Variable;

import Things.AirResistance;
import Things.Anchor;
import Things.Connector;
import Things.Gravity;
import Things.Rope;
import Things.Shape;
import Things.Sling;

public class SimpleTrebModel
    extends Model {

        Animator an ;


    public SimpleTrebModel() {
        init();
    }

    public void init(){

        answer.d =0.0;
        an = new Animator(this,-9,18,-4, true);
        addVariable( new Variable("armLength",4,7,.1) );
        addVariable( new Variable("slingRatio",.5,1,.01) );
        // experiment shows optimal ratio about 2 armlength to 1 sling length
        //addVariable( new Variable("armWeight",1,20,1) );
        //addVariable( new Variable("weightPoint",2,3,2) );
        Properties. deltaT = .0002; // seconds
        Properties. springK = 10000000;
        Properties. torqueK = 500000;
        Properties.bungeeDoubleForce = 5000;
        Properties. dampingConst = -10.1;
        Properties. torqueDampingConst = -.05;
        Properties. gravity =9.8; // M/s2
        Properties. connectorAlpha = 1.5;
    }
    public void build() {
        // lets do all this in meters
        answer.d =0.0;


        double al = getVariable("armLength").get();

        double slingLength = getVariable("slingRatio").get() * al;
        double slingAngle = 30;

        double aw = 4;// getVariable("armWeight").get();

        double wp = 2;// getVariable("weightPoint").get();

        double armAngle = 30 +180; // degrees
        double armRatio = 1.;

        add(new Anchor(0, 2, 0, 0),
            "Pivot");
        add(new Anchor(0, -1, 0, 0),
            "Anchor");

        add( Connector.rotate( (Connector) get("Pivot") ,
                               armAngle,
                               al,aw),
             "armTip");

        add( Connector.rotate((Connector) get("Pivot") ,
                              armAngle +180.0 ,
                              1/armRatio,1),
             "armBack");

        add( Connector.rotate((Connector) get("armTip") ,
                              - slingAngle  ,
                              slingLength,4),
             "pumpkin");


        add(new Connector(1, 2.0-wp, 0, 0, 500),
            "weight");


        Vector arm = new Vector();
        arm.addElement(get("armTip"));
        arm.addElement(get("Pivot"));
        arm.addElement(get("armBack"));
        add(new Shape(arm), "arm");

        add(new Sling(get("pumpkin"), get("armTip"), answer));
        //add(new Bungee(get("armBack"), get("Anchor"),1.0));
        add(new Rope(get("armBack"), get("weight")));

        add(new Gravity(thingList, 9.8));
        add(new AirResistance(thingList));

    }
    //**************************************************************
     public boolean isDone() {
         if ( (answer.d != 0.0)|| (Properties.time >1) ) {
            try {
                an.repaint();
          //      Thread.sleep(10);
            }
            catch (Exception ex) { }
             printState();
             //System.out.println("time:"+Properties.time);
             return true;
         }
         return false;

     }
}
