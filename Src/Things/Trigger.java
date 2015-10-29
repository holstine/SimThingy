package Things;
import simthingy.Animator;
import simthingy.Properties;


public class Trigger extends Rope
{
	public Trigger (Connector c1, Connector c2)
	{
		super(c1,c2);
	}
	
	public void force (){

		if ( ! Properties.trigger )
		{
			// if trigger = false then do force
			super.force();
		}
	}
	public void showThing( Animator a){
		if ( ! Properties.trigger) super.showThing(a);	
	}
}
