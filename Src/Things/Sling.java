package Things;
import simthingy.Animator;
import simthingy.Properties;
import simthingy.Refdouble;

public class Sling extends Rope
{
	boolean myConnected = true;
	double releaseX;
	double releaseY;
	double myRange;
	double epsilon = .15;
        Refdouble myAnswer;
	public Sling(Thing c1, Thing c2){
		super(c1,c2);
	}
	public Sling(Thing c1, Thing c2,Refdouble answer){
		super(c1,c2);
                myAnswer = answer;
                answer.d =0;
	}
	public void force(){

		if  (myConnected == true)
		  {
			if(
				(  (myEnd1.yvel/myEnd1.xvel) -1 <  epsilon) &&
				(  (myEnd1.yvel/myEnd1.xvel) -1 > -epsilon) &&
				(   myEnd1.yvel > 0)  &&
				(   myEnd1.xvel > 0)
			 ){ myConnected =false;}

			releaseX = myEnd1.xpos;
			releaseY = myEnd1.ypos;
			myRange = (myEnd1.xvel * myEnd1.xvel + myEnd1.yvel * myEnd1.yvel)/Properties.gravity;
			super.force();

		}else {
                        myAnswer.d = myRange;
			return;
		}

	}
	public void showThing( Animator a){
		if (myConnected)super.showThing(a);
		else{
				a.offscreenGraphics.drawString("Range = " + myRange,
									a.rescaleX(releaseX),
									a.rescaleY(releaseY));
		}
	}
}
