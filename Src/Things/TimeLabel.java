package Things;
import simthingy.Animator;
import simthingy.Properties;

public class TimeLabel extends Thing
{
	Connector myConnector;

	String myLabel;
	public TimeLabel (Connector c,String s){
		myLabel = s;
		myConnector = c;
	}
	public void force(){}
	public void move() {}
	
	public void  showThing (Animator a){

	
	a.offscreenGraphics.drawString(myLabel + Properties.time,
								   a.rescaleX(myConnector.xpos),
								   a.rescaleY(myConnector.ypos));
	
	}
}
