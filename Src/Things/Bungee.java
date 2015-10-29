package Things;

import java.awt.*;
import java.util.*;
import java.io.*;

import simthingy.Animator;
import simthingy.Properties;

public class Bungee extends Thing
{
    public Connector myEnd1 ;
    public Connector myEnd2 ;
    public double myLength;
    private double ropeforce;
    private double myNewLength;
    private double forceToDouble = Properties.bungeeDoubleForce;
    private double springK;
    private double alpha = Properties.dampingConst;


	public Bungee (Thing c1, Thing c2)
	{
		myEnd1 = (Connector)c1 ;
		myEnd2 = (Connector)c2 ;
		double dx = c1.xpos -c2.xpos;
		double dy = c1.ypos -c2.ypos;
		myLength = Math.sqrt(dx*dx + dy*dy);
		myNewLength = myLength;
		springK = forceToDouble * myLength;
	}
	public Bungee (Thing c1, Thing c2,double length)
	{
		myEnd1 = (Connector)c1 ;
		myEnd2 = (Connector)c2 ;
		myLength = length;
		myNewLength = myLength;
		springK = forceToDouble / myLength;
	}
        public Bungee (Thing c1, Thing c2,double length,double doubleforce) {
            forceToDouble = doubleforce;
            myEnd1 = (Connector)c1 ;
            myEnd2 = (Connector)c2 ;
            myLength = length;
            myNewLength = myLength;
            springK = forceToDouble / myLength;
        }


	public void force()
	{
		double dx = myEnd1.xpos -myEnd2.xpos;
		double dy = myEnd1.ypos -myEnd2.ypos;
		double prevLength = myNewLength;
		myNewLength = Math.sqrt(dx*dx + dy*dy);
		double delta = myNewLength - myLength;
		if ( delta > 0)
		{
			double expVel= 0;//(myNewLength-prevLength)/Properties.deltaT;
			// rope expansion velocity
			ropeforce = (springK * delta - Math.abs(expVel)*expVel*alpha);
			myEnd1.addXForce( -1*dx / myNewLength * ropeforce);
			myEnd1.addYForce( -1*dy / myNewLength * ropeforce);

			myEnd2.addXForce( dx / myNewLength * ropeforce);
			myEnd2.addYForce( dy / myNewLength * ropeforce);
		}
	}
	public void move(){}

	public void showThing( Animator a){
            if ( (myNewLength -myLength) > 0) {a.offscreenGraphics.setColor(Color.blue);}
            a.offscreenGraphics.drawLine(  a.rescaleX(myEnd1.xpos) ,a.rescaleY(myEnd1.ypos),
                                           a.rescaleX(myEnd2.xpos) ,a.rescaleY(myEnd2.ypos));
            a.offscreenGraphics.setColor(Color.black);
	}
}
