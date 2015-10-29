package Things;
import simthingy.Animator;
import simthingy.Properties;

public class Accelerometer extends Thing
{
	Connector myConnector;
	public double maxAccel;
	public double alpha =.3;// alpha range =[0,1] less alpha is smoother
	String myLabel;
	public Accelerometer (Connector c,String s){
		myLabel = s;
		myConnector = c;
	}
	public void force(){}
	public void move() {}
	
	public void  showThing (Animator a){

	double acc = (Math.sqrt(	myConnector.xforceprev * myConnector.xforceprev +
						myConnector.yforceprev * myConnector.yforceprev)/myConnector.mass)
				 /Properties.gravity;
	
	if(acc > maxAccel) {maxAccel = alpha *acc + (1-alpha)* maxAccel;}
	a.offscreenGraphics.drawString("" +maxAccel+  "   " + acc ,
								   a.rescaleX(myConnector.xpos),
								   a.rescaleY(myConnector.ypos));
	
	}
}
