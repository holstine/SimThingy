package NeuralNet;



import java.awt.Color;

import simthingy.Animator;
import Things.Connector;
import Things.Rod;

public class Muscle extends Rod
{
  Node myNode;
  double myOriginalLength;
        public Muscle (Connector c1, Connector c2, Node n)
        {
                super(c1,c2);
                myNode = n;
                myOriginalLength = super.mylength;
        }

        public void force (){


          mylength = (myNode.getValue() +1.0) * myOriginalLength;
          if (mylength < 0 ){
            mylength = 0;

          } // end if
          super.force();
        }
        public void showThing( Animator a){
           a.offscreenGraphics.setColor(Color.green);
           super.showThing(a);
           a.offscreenGraphics.setColor(Color.white);
        }
}
