package NeuralNet;


import simthingy.Animator;
import Things.Connector;
import Things.Thing;


public class FoodSensor extends Thing {
  Connector myFood;
  Connector myEye;
  Node myNode;
  double myWeight = 1.0;

  public FoodSensor(Connector food, Connector eye, Node n) {
    myFood = food;
    myEye = eye;
    myNode = n;

  }
  public void force() {
  // no force
  }
  public void move() {
   // no movement
   double deltaX = myFood.xpos - myEye.xpos;
   double deltaY = myFood.ypos - myEye.ypos;
   myNode.add( Math.atan(deltaY/deltaX),myWeight );

  }

  public void showThing(Animator a) {
   // might draw lines later
  }

}
