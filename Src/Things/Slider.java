package Things;

import java.util.*;

import simthingy.Animator;
import simthingy.Properties;

public class Slider extends Thing
{
	Connector myEnd1;
	Connector myEnd2;
	Connector mySlider;

	private double mySliderLength;
	private double myCrossProduct;
	private double myPrevCrossProduct;
	private double myPrevLength;
	private int myFormFactor;
	private double epsilon = .00001 * mySliderLength;

	private double springK = Properties.springK;
	private double torqueK = Properties.torqueK;// Properties.springK;
	private double alpha = Properties.dampingConst;
	private double torqueAlpha = Properties.torqueDampingConst; //Properties.dampingConst;

	public Slider (Thing c1, Thing c2,Thing c3)
	{
		myEnd1 = (Connector)c1;
		myEnd2 = (Connector)c2;
                mySlider = (Connector)c3;

		double dx = myEnd1.xpos - myEnd2.xpos;
		double dy = myEnd1.ypos - myEnd2.ypos;

		mySliderLength = Math.sqrt( (dx*dx) +(dy*dy) );
		epsilon = .00001 * mySliderLength;



	}


	public void force()
	{
			double dx = myEnd1.xpos - myEnd2.xpos;
			double dy = myEnd1.ypos - myEnd2.ypos;

			mySliderLength = Math.sqrt( (dx*dx) +(dy*dy) );
			double dxAB = myEnd1.xpos - mySlider.xpos;
			double dyAB = myEnd1.ypos - mySlider.ypos;
			double dxCB = myEnd2.xpos - mySlider.xpos;
			double dyCB = myEnd2.ypos - mySlider.ypos;


			double lengthAB = Math.sqrt( (dxAB*dxAB) +(dyAB*dyAB) );
			double lengthBC = Math.sqrt( (dxCB*dxCB) +(dyCB*dyCB) );

			if (( lengthBC < epsilon )|| (lengthAB < epsilon)) return;
			if (( lengthBC > mySliderLength )|| (lengthAB > mySliderLength)) return;

			// deflection calculations
			double crossproduct = (dxAB * dyCB)  - (dxCB * dyAB);
			if (crossproduct >0) crossproduct = 0;

			double deflection = crossproduct ;
			double deflectVel = (crossproduct -myPrevCrossProduct)/Properties.deltaT;
			double torque = (torqueK * deflection -Math.abs(deflectVel)*deflectVel*alpha);
			// need the formfactor to tell accute from obtuse

			// forces are in the neg reciprocal direction
				// if torque is positive then the angle is obtuse
			double forceAx = torque/lengthAB * (    dyAB/lengthAB);
			double forceAy = torque/lengthAB * (   -dxAB/lengthAB);

			double forceCx = torque/lengthBC * ( -dyCB/lengthBC);
			double forceCy = torque/lengthBC * (  dxCB/lengthBC);

			myEnd1.addXForce(  forceAx);
		  	myEnd1.addYForce(  forceAy);

		 	myEnd2.addXForce(  forceCx);
		 	myEnd2.addYForce(  forceCy);

			// equal but opposite reaction

			mySlider.addXForce( -( forceAx + forceCx));
			mySlider.addYForce( -( forceAy + forceCy));



			myPrevLength = lengthAB;
			myPrevCrossProduct = crossproduct;

	}

	public void move(){}


	public void showThing( Animator a){

		a.offscreenGraphics.drawLine(
			a.rescaleX( myEnd1.xpos ) ,
			a.rescaleY( myEnd1.ypos ) ,
			a.rescaleX(myEnd2.xpos) ,
			a.rescaleY(myEnd2.ypos)
		);

	}
}
