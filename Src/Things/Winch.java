package Things;
import simthingy.Animator;
import simthingy.Properties;

public class Winch extends Rope
{
	public Winch (Connector c1, Connector c2)
	{
		super(c1,c2);
	}
	
	public void force (){

		if (  Properties.winch )
		{
			mylength += Properties.winchSpeed * Properties.deltaT;
			if (mylength < 0 ){
				mylength = 0;
				Properties.winch = false;
				Properties.winchSpeed = -Properties.winchSpeed;
			}
		}
		super.force();
	}
	public void showThing( Animator a){
		super.showThing(a);	
	}
}


