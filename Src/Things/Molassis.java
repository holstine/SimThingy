package Things;

import java.util.*;

import simthingy.Animator;
import simthingy.Properties;

public class Molassis extends Thing
{
	public double decayConst = .00010;
	public Vector myThingList;
// ********************************************************
	public Molassis ( Vector thingList){

		myThingList = thingList;
	}

	public void force (){

		for ( int i = 0 ; i < myThingList.size(); i ++)
		{
			Thing t=	((Thing) (myThingList.elementAt(i)));
			t.xvel = t.xvel/ (1+decayConst*Properties.deltaT);
			t.yvel = t.yvel/ (1+decayConst*Properties.deltaT);
		}


	}
	public void move(){
	}
	public void showThing(Animator a){

	}
}



