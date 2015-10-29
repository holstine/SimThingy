package Models;


import java.util.*;

import simthingy.Animator;
import simthingy.Properties;
import simthingy.Variable;

import Things.AirResistance;
import Things.Anchor;
import Things.Bungee;
import Things.Connector;
import Things.Gravity;
import Things.Shape;
import Things.Sling;

public class SimpleBungModel
    extends Model {



    public SimpleBungModel() {
        Animator an = new Animator(this,-9,8,-4);
    }

    public void build() {
        // lets do all this in meters
        answer.d =0.0;
        Properties. deltaT = .0002; // seconds
        Properties. springK = 10000000;
        Properties. torqueK = 500000;
	Properties.bungeeDoubleForce = 5000;
        Properties. dampingConst = -10.1;
        Properties. torqueDampingConst = -.05;
        Properties. gravity =9.8; // M/s2
        Properties. connectorAlpha = 1.5;

        addVariable( new Variable("armLength",.1,4,.1) );
        double al = getVariable("armLength").get();

        addVariable( new Variable("slingRatio",0,2,.05) );  // experiment shows optimal ratio about 2 armlength to 1 sling length
        double slingLength = getVariable("slingRatio").get() * al;
        double slingAngle = -10;


        //addVariable( new Variable("armWeight",1,20,1) );
        double aw = 4;// getVariable("armWeight").get();


        //addVariable( new Variable("weightPoint",2,3,2) );
        //double wp = getVariable("weightPoint").get();

        double armAngle = 30 +180; // degrees
        double armRatio = 1.;

        add(new Anchor(0, 2, 0, 0), "Pivot");
        add(new Anchor(0, -1, 0, 0), "Anchor");

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



       // add(new Connector( -(al -sl), 1.8, 0, 0, 4), "pumpkin");

      //  add(new Connector(1, 2.0-wp, 0, 0, 1000), "weight");
        Vector arm = new Vector();

        arm.addElement(get("armTip"));
        arm.addElement(get("Pivot"));
        arm.addElement(get("armBack"));
        add(new Shape(arm), "arm");

        add(new Sling(get("pumpkin"), get("armTip"), answer));
        add(new Bungee(get("armBack"), get("Anchor"),1.0));

        add(new Gravity(thingList, 9.8));
        add(new AirResistance(thingList));



    }
    //**************************************************************
     public boolean isDone() {
         if ( (answer.d != 0.0)|| (Properties.time >1) ) {
             printState();
             //System.out.println("time:"+Properties.time);
             return true;
         }
         return false;

     }
}
