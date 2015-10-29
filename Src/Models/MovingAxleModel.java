package Models;
import java.util.*;

import Things.AirResistance;
import Things.Anchor;
import Things.Connector;
import Things.Gravity;
import Things.Label;
import Things.Mud;
import Things.Shape;
import Things.Slider;
import Things.Sling;
import Things.Thing;

public class MovingAxleModel extends Model
{

	//Vector thingList = new Vector(3);

//	public MovingAxleModel (){
//		build();
//	}

	public static double rackheight = 3;
	public static double armslope = 1;
	public static double weightheight = 5;
	public static double armsize = 20;
	public static double ropesize = 5;
	public static double rollerpos = -(weightheight - rackheight)/armslope;
	public static double tipxpos = rollerpos * weightheight/rackheight;

	public void reset(){
		thingList.removeAllElements();
		build();
	}
	public void build()
	{

		Connector topPoint = new Anchor  (/*x*/ 0,   /*y*/  weightheight +1,	/*xv*/0,/*yv*/0	);
		Connector bottomPoint = new Anchor  (/*x*/ 0,   /*y*/  -5,	/*xv*/0,/*yv*/0	);
		Connector aftRack = new Anchor  (/*x*/ -9,   /*y*/  rackheight,	/*xv*/0,/*yv*/0	);
		Connector foreRack = new Anchor  (/*x*/ 4,   /*y*/  rackheight,	/*xv*/0,/*yv*/0	);
		Connector counterWeight = new Connector(/*x*/0,   /*y*/  weightheight,  /*xv*/0,/*yv*/0,/*M*/100);
		Connector roller = new Connector(/*x*/rollerpos,   /*y*/ rackheight,	/*xv*/0,/*yv*/0,	/*M*/1);
		Connector tip = new Connector(/*x*/tipxpos - armslope*4, /*y*/ -4,	    /*xv*/0,/*yv*/0,    /*M*/.1);
		Connector projectile = new Connector(/*x*/tipxpos+ ropesize, /*y*/-2,	/*xv*/0,/*yv*/0,	/*M*/5);

		Slider track1 = new Slider(topPoint,bottomPoint,counterWeight );
		Slider track2 = new Slider(bottomPoint,topPoint,counterWeight );
		Slider horzTrack1 = new Slider(foreRack,aftRack,roller );
		Slider horzTrack2 = new Slider(aftRack,foreRack,roller );


		Vector armpts = new Vector();

		armpts.addElement(tip);
		armpts.addElement(roller);
		armpts.addElement(counterWeight);

		Thing arm = new Shape(armpts);

		Thing sling = new Sling(projectile,tip );


		Thing label = new Label(projectile,"Pumpkin");
		//Pulley pulley = new Pulley(anchora,mass1, anchorb);

		//Thing rangelabel = new Label(topPoint, ""+projectile.velocity()*projectile.velocity()/
		//										  Properties.gravity);

		thingList.addElement(topPoint);
		thingList.addElement(bottomPoint);

		thingList.addElement(foreRack);
		thingList.addElement(aftRack);
		thingList.addElement(counterWeight);
		thingList.addElement(roller);
		thingList.addElement(tip);
		thingList.addElement(projectile);

		thingList.addElement(track1);
		thingList.addElement(track2);
	//	thingList.addElement(horzTrack1);
		thingList.addElement(horzTrack2);
		thingList.addElement(arm);
		thingList.addElement(sling);

		thingList.addElement(label);
	//	thingList.addElement(rangelabel);



		// body forces

		Thing ground = new Mud(-2,thingList);
	//	thingList.addElement(ground);
		Thing gravity = new Gravity(thingList);
		thingList.addElement(gravity);

		Thing viscosity = new AirResistance(thingList);
	  	thingList.addElement(viscosity);
	}

}
