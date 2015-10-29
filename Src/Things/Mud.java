package Things;


import java.util.*;

import simthingy.Animator;


public class Mud extends Thing
{
	public double damp = .01;
	public double myGroundLevel;
	public Vector myThingList;
// ********************************************************
	public Mud (double groundLevel, Vector thingList){
		myGroundLevel = groundLevel;
		myThingList = thingList;
	}
	
	public void force (){
	
		for ( int i = 0 ; i < myThingList.size(); i ++)
		{
			Thing t=	((Thing) (myThingList.elementAt(i)));	
			if (t.ypos < myGroundLevel) {
				t.yvel =t.yvel * 1/(1+damp); 
				t.xvel= t.xvel * 1/(1+damp);
			}
		}
		
			
	}
	public void move(){
		// ground don't move!
	}
	public void showThing(Animator a){
		a.offscreenGraphics.drawLine(  a.rescaleX(a.minX) ,a.rescaleY(myGroundLevel),
									   a.rescaleX(a.maxX) ,a.rescaleY(myGroundLevel));
	}
}
