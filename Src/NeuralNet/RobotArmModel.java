package NeuralNet;




import java.util.Vector;

import simthingy.Animator;

import Models.Model;
import Things.AirResistance;
import Things.Anchor;
import Things.Connector;
import Things.Gravity;
import Things.Molassis;
import Things.Rod;
import Things.Shape;
import Things.Thing;
public class RobotArmModel extends Model{


  public void reset(){
    thingList.removeAllElements();
    build();
  }
  public void build()
  {

        Connector basePoint = new Anchor (0,0,0,0);
        thingList.addElement(basePoint);
        Connector b1 = new Anchor (.10,.15,0,0);
        thingList.addElement(b1);
        Connector b2 = new Anchor (-1,1.5,0,0);
        thingList.addElement(b2);

        Connector elbow = new Connector (1,0,0,0,100);
        thingList.addElement(elbow);

        Connector musclePt1 = new Connector (.5,.1,0,0,100);
        thingList.addElement(musclePt1);
        Connector musclePt2 = new Connector (.5,-.1,0,0,100);
        thingList.addElement(musclePt2);

        Connector hand = new Connector (1.5,0,0,0,100);
        thingList.addElement(hand);
        Thing arm1 = new Rod (basePoint,elbow);
        thingList.addElement(arm1);
        Thing arm2 = new Rod (elbow,hand);
        thingList.addElement(arm2);

        Connector musclePt3 = new Connector (1.1,.1,0,0,100);
        thingList.addElement(musclePt3);
        Connector musclePt4 = new Connector (1.1,-.1,0,0,100);
        thingList.addElement(musclePt4);

        Vector armpts = new Vector();
        //	armpts.addElement(bungeeAxle);
                armpts.addElement(basePoint);
                armpts.addElement(musclePt1);
                armpts.addElement(elbow);
                armpts.addElement(musclePt2);
                 armpts.addElement(basePoint);
        Thing armShape1 = new Shape(armpts);
        thingList.addElement(armShape1);

        Vector armpts1 = new Vector();
       //	armpts.addElement(bungeeAxle);
               armpts1.addElement(elbow);
               armpts1.addElement(musclePt3);
               armpts1.addElement(hand);
               armpts1.addElement(musclePt4);
                armpts1.addElement(elbow);
       Thing armShape2 = new Shape(armpts1);
       thingList.addElement(armShape2);

       // BRAINS!!!  BRAINS!!!
       Brain br = new Brain(3);
       Head h = new Head(br,b2);
       thingList.addElement(h);
       Node mu1 = new Node(-1);
       Node mu2 = new Node(-2);
       br.addNode(mu1);
       br.addNode(mu2);
       Thing muscle1 = new Muscle(b1, musclePt2,mu1);
       thingList.addElement(muscle1);
       Thing muscle2 = new Muscle(musclePt4, musclePt1,mu2);
       thingList.addElement(muscle2);

       Connector food = new Anchor(1.35,.2,0,0);
       Node sensorNode = new Node(10);
       FoodSensor fs = new FoodSensor(food,b2,sensorNode);
       Node armSensorNode = new Node(11);
       FoodSensor as = new FoodSensor(hand,b2,armSensorNode);
       br.addNode(sensorNode);
       br.addNode(armSensorNode);
       RewardSensor rs = new RewardSensor(food,hand,br);
       thingList.addElement(rs);
       thingList.addElement(fs);
        thingList.addElement(as);
       thingList.addElement(food);


       // body forces
        Thing grav = new Gravity(thingList);
        thingList.addElement(grav);
        Thing viscosity = new AirResistance(thingList);

        thingList.addElement(viscosity);
        Thing molassis= new Molassis(thingList);
        thingList.addElement(molassis);

        Animator an = new Animator(this,-2,2,-1);
  }

  public RobotArmModel() {
  }
}
