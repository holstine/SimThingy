package Things;

import java.awt.*;
import java.util.*;
import java.io.*;

import simthingy.Animator;
import simthingy.Properties;

public class Bumper extends Thing
{
	public Connector myEnd1 ;
	public Connector myEnd2 ;
	private double mylength;
	private double ropeforce;
	private double myNewLength;
	private double springK = Properties.springK/10;
	private double alpha = Properties.dampingConst/100;
	public Bumper (Connector c1, Connector c2)
	{
		myEnd1 = c1 ;
		myEnd2 = c2 ;
		double dx = c1.xpos -c2.xpos;
		double dy = c1.ypos -c2.ypos;
		mylength = Math.sqrt(dx*dx + dy*dy);
		myNewLength = mylength;
	}
	public Bumper (Connector c1, Connector c2,double length)
	{
		myEnd1 = c1 ;
		myEnd2 = c2 ;
		myNewLength = mylength = length;
	}
	
	
	public void force()
	{
		double dx = myEnd1.xpos -myEnd2.xpos;
		double dy = myEnd1.ypos -myEnd2.ypos;
		double prevLength = myNewLength;
		myNewLength = Math.sqrt(dx*dx + dy*dy);
		double delta = myNewLength - mylength;
		if ( delta < 0)
		{		
			double expVel= (myNewLength-prevLength)/Properties.deltaT; 
			// Bumper expansion velocity
			ropeforce = (springK * delta - Math.abs(expVel)*expVel*alpha);
			myEnd1.addXForce( -dx / myNewLength * ropeforce);
			myEnd1.addYForce( -dy / myNewLength * ropeforce);
			
			myEnd2.addXForce(  dx / myNewLength * ropeforce);
			myEnd2.addYForce(  dy / myNewLength * ropeforce);
		}
	}
	public void move(){}	
	
	public void showThing( Animator a){
		if (mylength > myNewLength){
			a.offscreenGraphics.setColor(Color.blue);
		
			a.offscreenGraphics.drawLine(  a.rescaleX(myEnd1.xpos) ,a.rescaleY(myEnd1.ypos),
									   a.rescaleX(myEnd2.xpos) ,a.rescaleY(myEnd2.ypos));
			a.offscreenGraphics.setColor(Color.black);
		}
	}
}