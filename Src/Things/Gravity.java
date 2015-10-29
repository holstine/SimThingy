package Things;

import java.util.*;

import simthingy.Animator;
import simthingy.Properties;

public class Gravity extends Thing
{
	public Vector myThingList;
// ********************************************************
	public Gravity ( Vector thingList){
		myThingList = thingList;
	}
	public Gravity ( Vector thingList, double gravity){
		myThingList = thingList;
                Properties.gravity = gravity;
	}

	public void force (){

		for ( int i = 0 ; i < myThingList.size(); i ++)
		{
			Thing t=	((Thing) (myThingList.elementAt(i)));
			t.addYForce( - (t.mass*Properties.gravity) );
		}


	}
	public void move(){
		// ground don't move!
	}
	public void showThing(Animator a){
		// can't see gravity
	}

}
