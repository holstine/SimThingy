package Things;

import java.awt.*;
import java.util.*;
import java.io.*;

import simthingy.Animator;
import simthingy.Properties;


public class Pivot extends Thing
{
	public Connector myEnd1 ;
	public Connector myEnd2 ;
	public Connector myEnd3 ;
	
	private double mylengthA;
	private double mylengthB;
	private double myCrossProduct;
	private double myPrevCrossProduct;
	private double rodforce;
	private double myNewLengthA;
	private double myNewLengthB;
	
	private double springK = 1000;
	private double alpha = -.000001;
	
	public Pivot (Connector c1, Connector c2,Connector c3)
	{
		myEnd1 = c1 ; // end 1 is the pivot point
		myEnd2 = c2 ;
		myEnd3 = c3 ;
		double dxA = c1.xpos -c2.xpos;
		double dyA = c1.ypos -c2.ypos;
		double dxB = c1.xpos -c3.xpos;
		double dyB = c1.ypos -c3.ypos;
		mylengthA = Math.sqrt(dxA*dxA + dyA*dyA);
		mylengthB = Math.sqrt(dxB*dxB + dyB*dyB);
		myCrossProduct = (dxA * dyB) -(dyA *dxB);
	}
	
	
	public void force()
	{
		double dx = myEnd1.xpos -myEnd2.xpos;
		double dy = myEnd1.ypos -myEnd2.ypos;
		double prevLength = myNewLengthA;
		double myNewLength = Math.sqrt(dx*dx + dy*dy);
		double delta = myNewLengthA - mylengthA;
				
		double expVel= (myNewLengthA-prevLength)/Properties.deltaT; 
		// rod expansion velocity
		rodforce = (springK * delta - expVel *expVel*alpha);
		myEnd1.xforce += -1*dx / myNewLength * rodforce;
		myEnd1.yforce += -1*dy / myNewLength * rodforce;
		
		myEnd2.xforce += dx / myNewLength * rodforce;
		myEnd2.yforce += dy / myNewLength * rodforce;
		
	}
	
	public void move(){}
	
	
	public void showThing( Animator a){
		a.offscreenGraphics.drawLine(  a.rescaleX(myEnd1.xpos) ,a.rescaleY(myEnd1.ypos),
									   a.rescaleX(myEnd2.xpos) ,a.rescaleY(myEnd2.ypos));
	}
}