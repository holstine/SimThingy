package Things;

import simthingy.Animator;

public class Label extends Thing
{
	Connector myConnector;
	String myLabel;
	public Label (Connector c,String s){
		myLabel = s;
		myConnector = c;
	}
	public void force(){}
	public void move() {}
	
	public void  showThing (Animator a){

	
	a.offscreenGraphics.drawString(myLabel,
								   a.rescaleX(myConnector.xpos),
								   a.rescaleY(myConnector.ypos));
	
	}
}
