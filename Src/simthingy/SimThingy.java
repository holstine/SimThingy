package simthingy;
import Models.FileModel;
import Models.Model;

public class SimThingy {

	//*********************************************************************************
	public static void  main (String[] args){


		Model m = new FileModel("treb.txt");
		//Model m = new Bungepult();
		//Model m = new SimpleBungModel();
		//Model m = new SimpleTrebModel();
                //Model m = new ShapeModel();
                //Model m = new MovingAxleModel();
                //Model m = new RobotArmModel();
               // Animator an = new Animator(m,-9,8,-4);
                //PropertiesModifier.create();
               // ButtonBar.create(m);

	 	//Animator a2 = new Animator(m,-20,300,-20);
         // put the animator in the model

		Universe u = new IterativeUniverse(m);

		u.go();

	}
	//*********************************************************************************
}
