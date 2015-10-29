package Models;

import java.util.*;

import simthingy.Properties;

import Things.AirResistance;
import Things.Anchor;
import Things.Bumper;
import Things.Bungee;
import Things.Connector;
import Things.Gravity;
import Things.Label;
import Things.Molassis;
import Things.Mud;
import Things.Pulley;
import Things.Rod;
import Things.Rope;
import Things.Shape;
import Things.Slider;
import Things.Thing;
import Things.TimeLabel;
import Things.Trigger;
import Things.Winch;

public class Bungepult extends Model
{

	//Vector thingList = new Vector(3);

//	public Bungepult (){
//		build();
//	}


	public static double tipPosX = -8;
	public static double tipPosY =  5;

	public static double mainX = 0;
	public static double mainY = 7.5;
	public static double ratio = .85;

	public static double projX =  -1;
	public static double projY =  2;
	public static double bungeeBottom =  6.0;

	public void reset(){
		thingList.removeAllElements();
		build();
	}
	public void build()
	{


		Properties.time = 0;

		double midX = (ratio * mainX) + ((1-ratio) * tipPosX);
		double midY = (ratio * mainY) + ((1-ratio) * tipPosY);

		Connector BasePoint = new Anchor (0,0,							0,0);
		Connector back = new Anchor(-7.5,0,								0,0);
		Connector triggerPt = new Anchor(-7.5,3,								0,0);
		Connector mainAxle = new Anchor		(mainX,   mainY,			0,0	);
		Connector strapShackle = new Connector (-1,0	,				0,0 );
		Connector ropePt = new Connector (.2 ,bungeeBottom -1	,		0,0,   .1 );
		Connector winchPt = new Connector (-2 ,0	,					0,0,   .1 );
		Connector bungeeShackle = new Connector  ( .22,bungeeBottom,	0,0,	5	);
		Connector uprightAxle = new Anchor  ( 0,     2  ,				0,0	);
		Connector winchBlock= new Anchor (-8,0,							0,0);

		Connector bungeeAxle = new Connector(    .5,     9.0,			0,0,	 10/32.2);
		Connector finger = new Connector	( tipPosX -.5,  tipPosY+.2,	    0,0,	 .01);
		Connector tip = new Connector		( tipPosX,  tipPosY,	    0,0,	 10/32.2);
		Connector midarm = new Connector		( midX,  midY,			0,0,     10/32.2);

		Connector platform1 =  new Anchor(    projX -1, projY,			0,0);
		Connector platform2 =  new Anchor(    projX +1,projY,			0,0);

		Connector tipRing = new Connector(	tipPosX-.25,	tipPosY+.1,			0,0,	  .1);
		Connector projectile = new Connector(	projX,	projY,			0,0,	  8/32.2);
		Connector counterWeight =  new Connector(    1.0,     8.0,		0,0, 	 90/32.2);
		Connector marker =  new Anchor(    200.0,     1.0,				0,0);
		Connector marker2 =  new Anchor(    280.0,     1.0,				0,0);
		Thing label = new Label(marker,"200 ft");


		Vector armpts = new Vector();
	//	armpts.addElement(bungeeAxle);
		armpts.addElement(finger);
		armpts.addElement(tip);
		armpts.addElement(midarm);
		armpts.addElement(mainAxle);
		armpts.addElement(bungeeAxle);
		armpts.addElement(counterWeight);
	 //	armpts.addElement(mainAxle);

	 	armpts.addElement(tip);

		Thing arm = new Shape(armpts);

		Thing platform = new Slider(platform1,platform2,projectile);
		Thing shackleSlider = new Slider(mainAxle,BasePoint,bungeeShackle);
		Thing fingSlider = new Slider(finger,tip,tipRing);
		Thing bungee = new Bungee(bungeeShackle,bungeeAxle,2.5);
		Thing suport = new Rod(midarm,bungeeAxle);
		Thing suport2 = new Rod(mainAxle,bungeeAxle);
		Thing suport3 = new Rod(counterWeight,mainAxle);
		Thing sling = new Rope(projectile,tipRing );
		Thing fingerPlate = new Bumper(tipRing,tip,.1 );
		Thing upright = new Rod(BasePoint, mainAxle);
		Thing rearUpright = new Rod(back, triggerPt);
		Thing bottom = new Rod(BasePoint,back);
		Thing pul1 = new Pulley(uprightAxle,bungeeShackle,ropePt);
		Thing pul2 = new Pulley(ropePt,BasePoint,winchPt);
		Thing winch = new Winch(winchBlock,winchPt);
	//	Thing label = new Label(projectile,"Pumpkin");

	//	Thing accel = new Accelerometer(projectile,":accel");
		Thing tLbl = new TimeLabel(BasePoint," Time  = ");
		Thing trigger = new Trigger(tip,triggerPt);

// ************************************************************

		thingList.addElement(mainAxle);
		thingList.addElement(bungeeAxle);
		thingList.addElement(counterWeight);
		thingList.addElement(uprightAxle);
		thingList.addElement(midarm);

	 	thingList.addElement(suport);
	  	thingList.addElement(suport2);
		thingList.addElement(suport3);

		thingList.addElement(bungeeShackle);

		thingList.addElement(finger);
		thingList.addElement(tip);
		thingList.addElement(projectile);
	  	thingList.addElement(bungee);
		thingList.addElement(arm);
		thingList.addElement(sling);

		thingList.addElement(back);
		thingList.addElement(rearUpright);
		thingList.addElement(triggerPt);
		thingList.addElement(bottom);
		thingList.addElement(BasePoint);
		thingList.addElement(ropePt);
		thingList.addElement(pul1);
		thingList.addElement(pul2);
		thingList.addElement(trigger);
		thingList.addElement(tipRing);
		thingList.addElement(fingerPlate);
	 	thingList.addElement(fingSlider);
	//	thingList.addElement(label);

	 	thingList.addElement(tLbl);
		thingList.addElement(upright);
		thingList.addElement(marker);
		thingList.addElement(marker2);
		thingList.addElement(label);

		thingList.addElement(platform1);
		thingList.addElement(platform2);
		thingList.addElement(platform);

		thingList.addElement(winchPt);
		thingList.addElement(winchBlock);
		thingList.addElement(winch);
		thingList.addElement(shackleSlider);
		// body forces

		Thing ground = new Mud(-.1,thingList);
		thingList.addElement(ground);
		Thing gravity = new Gravity(thingList);
		thingList.addElement(gravity);

		Thing viscosity = new AirResistance(thingList);

	  	thingList.addElement(viscosity);

		Vector m1 = new Vector();
			m1.addElement(bungeeShackle);
			m1.addElement(ropePt);
			m1.addElement(counterWeight);
			m1.addElement(midarm);
		Thing mol = new Molassis(m1);
		thingList.addElement(mol);
	}

}
