package NeuralNet;


import Things.Connector;
import Things.Thing;

import java.awt.Color;

import simthingy.Animator;



public class RewardSensor extends Thing {
  Connector myFood;
  Connector myMouth;
  Connector myConnector;
  double dist;

  double foodSize = .15;
  Brain myBrain;
  public RewardSensor(Connector food, Connector mouth, Brain b) {
    myFood = food;
    myMouth = mouth;
    myBrain = b;
    myConnector = myFood;
  }
  public void force() {

  }
  public void move() {
    double prevDist = dist;
    dist = Math.sqrt(  (myFood.xpos - myMouth.xpos)*(myFood.xpos - myMouth.xpos)
                       + (myFood.ypos - myMouth.ypos)*(myFood.ypos - myMouth.ypos));
    if (dist < foodSize) myBrain.reward();
    if (dist -prevDist < - .05) myBrain.reward();
}
  public void showThing(Animator a) {

    Color pc = a.offscreenGraphics.getColor();

    a.offscreenGraphics.setColor(Color.cyan);
    a.offscreenGraphics.draw3DRect(
      a.rescaleX(myConnector.xpos),
      a.rescaleY(myConnector.ypos),10,(int)(50*dist),true);
    a.offscreenGraphics.setColor(Color.yellow);
  a.offscreenGraphics.draw3DRect(
    a.rescaleX(myConnector.xpos),
    a.rescaleY(myConnector.ypos),10,(int)(50*foodSize),true);

    a.offscreenGraphics.setColor(pc);
  }

}
