package Things;

import java.awt.*;
import java.util.*;
import java.io.*;

import simthingy.Animator;
import simthingy.Properties;


public class Rod extends Thing
{

	public Rod (Thing c1, Thing c2)
	{
		myEnd1 = (Connector)c1 ;
		myEnd2 = (Connector)c2 ;
		double dx = c1.xpos -c2.xpos;
		double dy = c1.ypos -c2.ypos;
		mylength = Math.sqrt(dx*dx + dy*dy);
		myNewLength = mylength;
	}


	public void force()
	{
		double dx = myEnd1.xpos -myEnd2.xpos;
		double dy = myEnd1.ypos -myEnd2.ypos;
		double prevLength = myNewLength;
		myNewLength = Math.sqrt(dx*dx + dy*dy);
		double delta = myNewLength - mylength;

			double expVel= (myNewLength-prevLength)/Properties.deltaT;
			// rod expansion velocity

			rodforce = (springK * delta - Math.abs(expVel)*expVel*alpha);
			myEnd1.addXForce( -1*dx / myNewLength * rodforce);
			myEnd1.addYForce( -1*dy / myNewLength * rodforce);

			myEnd2.addXForce(  dx / myNewLength * rodforce);
			myEnd2.addYForce(  dy / myNewLength * rodforce);

	}
	public void move(){}
	public Connector myEnd1 ;
	public Connector myEnd2 ;
	public double mylength;
	private double rodforce;
	private double myNewLength;
	private double springK = Properties.springK;
	private double alpha = Properties.dampingConst;
	public void showThing( Animator a){
          a.offscreenGraphics.drawLine(  a.rescaleX(myEnd1.xpos) ,a.rescaleY(myEnd1.ypos),
                                         a.rescaleX(myEnd2.xpos) ,a.rescaleY(myEnd2.ypos));
	//	String s = "helloworld"+ myNewLength;
	//	a.offscreenGraphics.drawString(s,75,100);
	}
}
