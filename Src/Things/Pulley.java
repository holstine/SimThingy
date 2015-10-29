package Things;

import simthingy.Animator;
import simthingy.Properties;


public class Pulley extends Thing
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Connector myEnd1 ;
	public Connector myEnd2 ;
	public Connector myPulley ;
	
	private double mylength;
	private double ropeforce;
	private double myNewLength;
	private double springK = Properties.springK;
	private double alpha = Properties.dampingConst;
	
// ****************************************************************
	public Pulley (Thing c1, Thing c2,Thing c3)
	{
		myEnd1 =  (Connector) c1 ;
		myPulley = (Connector)c2;
		myEnd2 =   (Connector)c3 ;
		
		double dx1 = myEnd1.xpos -myPulley.xpos;
		double dy1 = myEnd1.ypos -myPulley.ypos;
		
		double dx2 = myEnd2.xpos -myPulley.xpos;
		double dy2 = myEnd2.ypos -myPulley.ypos;
		
		mylength = Math.sqrt(dx1*dx1 + dy1*dy1) + Math.sqrt(dx2*dx2 +dy2*dy2);
		myNewLength = mylength;
	}
	
	
	public void force()
	{
		double dx1 = myEnd1.xpos -myPulley.xpos;
		double dy1 = myEnd1.ypos -myPulley.ypos;
		
		double dx2 = myEnd2.xpos -myPulley.xpos;
		double dy2 = myEnd2.ypos -myPulley.ypos;
		
		double prevLength = myNewLength;
		double ropelength1 = Math.sqrt(dx1*dx1 + dy1*dy1);
		double ropelength2 = Math.sqrt(dx2*dx2 + dy2*dy2);
		myNewLength =  ropelength1 + ropelength2;
		double delta = myNewLength - mylength;
		if ( delta > 0)
		{		
			double expVel= (myNewLength-prevLength)/Properties.deltaT; 
			// rope expansion velocity
			ropeforce = (springK * delta - Math.abs(expVel)*expVel*alpha);
			double xforce1 = -dx1 / ropelength1 * ropeforce;
			double yforce1 = -dy1 / ropelength1 * ropeforce;
			
			double xforce2 = -dx2 / ropelength2 * ropeforce;
			double yforce2 = -dy2 / ropelength2 * ropeforce;
			
			myEnd1.addXForce( xforce1);
			myEnd1.addYForce( yforce1);
			
			myEnd2.addXForce(  xforce2);
			myEnd2.addYForce(  yforce2);
			
			myPulley.addXForce( - (xforce1 + xforce2) );
			myPulley.addYForce( - (yforce1 + yforce2) );
		}
	}
	public void move(){}	
	
	public void showThing( Animator a){
		a.offscreenGraphics.drawLine(  a.rescaleX(myEnd1.xpos) ,a.rescaleY(myEnd1.ypos),
									   a.rescaleX(myPulley.xpos) ,a.rescaleY(myPulley.ypos));
		
		a.offscreenGraphics.drawLine(  a.rescaleX(myEnd2.xpos) ,a.rescaleY(myEnd2.ypos),
									   a.rescaleX(myPulley.xpos) ,a.rescaleY(myPulley.ypos));
	}
}