package Models;
import java.util.*;

import Things.AirResistance;
import Things.Anchor;
import Things.Bumper;
import Things.Connector;
import Things.Gravity;
import Things.Mud;
import Things.Pulley;
import Things.Rod;
import Things.Rope;
import Things.Slider;
import Things.Thing;

public class TestModel extends Model
{
	
	
	public TestModel (){
		build();
	}
	
	public void reset(){
		thingList.removeAllElements();
		build();
	}
	public void build()
	{
		
		Connector anchor = new Anchor  (/*x*/ 0,   /*y*/  1,	/*xv*/0,/*yv*/0	);
		
		Connector anchora = new Anchor  (/*x*/ -2,   /*y*/  2,	/*xv*/0,/*yv*/0	);
		Connector anchorb = new Anchor  (/*x*/ 2,   /*y*/  -2,	/*xv*/0,/*yv*/0	);
		Connector mass1 = new Connector(/*x*/0,   /*y*/  0,	    /*xv*/0,/*yv*/0,	/*M*/1);
		Connector mass2 = new Connector(/*x*/-1,   /*y*/ 3,	/*xv*/-2,/*yv*/0,	/*M*/10);
		Connector mass3 = new Connector(/*x*/-.9, /*y*/  0,	    /*xv*/0,/*yv*/0,    /*M*/1);
		Connector mass4 = new Connector(/*x*/-1.1, /*y*/  0,	/*xv*/0,/*yv*/0,	/*M*/1);
		Connector counterMass = new Connector(/*x*/-1, /*y*/  0,	/*xv*/0,/*yv*/0,	/*M*/20);
		
		Thing  rod1 = new Rod(mass1, mass2);
		Rod  rod2 = new Rod(mass2, mass3);
		Rod  rod3 = new Rod(mass1, mass3);
		Rod  rod4 = new Rod(mass2, mass4);
		Rod  rod5 = new Rod(mass1, mass4);
		Rod  rod6 = new Rod(mass3, mass4);
		Thing bumper1 = new Bumper(anchora,mass1,1);
		Thing bumper2 = new Bumper(anchorb,mass1,1);
		Pulley pulley = new Pulley(anchora,mass1, anchorb);
		Slider slider = new Slider(anchora,anchorb,mass1 );
		Rope r1 = new Rope(anchor, mass1,2);				
		
		thingList.addElement(anchora);
		thingList.addElement(anchorb);
		
		thingList.addElement(mass1);
		thingList.addElement(mass2);	
	//	thingList.addElement(mass3);
	//	thingList.addElement(mass4);
	//	thingList.addElement(counterMass);
		
		thingList.addElement(rod1);
	//	thingList.addElement(rod2);
	//	thingList.addElement(rod3);
	//	thingList.addElement(rod4);
	//	thingList.addElement(rod5);
	//	thingList.addElement(rod6);	
		thingList.addElement(slider);
	//	thingList.addElement(r1);
		thingList.addElement(bumper1);
		thingList.addElement(bumper2);	
		
		
		// body forces
		
		Thing ground = new Mud(-2,thingList);
		thingList.addElement(ground);
		Thing gravity = new Gravity(thingList);
		thingList.addElement(gravity);
		
		Thing viscosity = new AirResistance(thingList);
	  	thingList.addElement(viscosity);
	}
	
}
