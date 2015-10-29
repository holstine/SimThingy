package Things;

import java.util.*;

import simthingy.Animator;
import simthingy.Properties;
public class AirResistance extends Thing
{
	public double decayConst = 100.0718* Properties.deltaT;
	public double linearDecay = 00.0718* Properties.deltaT;
	public Vector myThingList;
// ********************************************************
	public AirResistance ( Vector thingList){

		myThingList = thingList;
	}

	public void force (){

		for ( int i = 0 ; i < myThingList.size(); i ++)
		{
			Thing t=	((Thing) (myThingList.elementAt(i)));
			t.addXForce( - Math.abs( t.xvel)*t.xvel * decayConst);
			t.addYForce( - Math.abs( t.yvel)*t.yvel * decayConst);
			// add a little bit of linear
			t.addXForce( - t.xvel * linearDecay);
			t.addYForce( - t.yvel * linearDecay);
		}


	}
	public void move(){
	}
	public void showThing(Animator a){

	}
}


