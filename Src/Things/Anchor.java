package Things;
import simthingy.Properties;

public class Anchor extends Connector
{
	public Anchor(double x,double y , double xv, double yv)
	{		super( x, y ,  xv,  yv);	}
		
		
		
	public void move(){
		double dt = Properties.deltaT;
		xprev = xpos ; yprev =ypos;
		xvprev = xvel; yvprev =yvel;
	//	xforceprev = xforce; yforceprev =yforce;
	
		xpos = xprev + xvel * dt ;
		ypos = yprev + yvel * dt ;
	}
}
