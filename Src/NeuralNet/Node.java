package NeuralNet;

import java.util.Vector;



public class Node {

  Vector axionList;
  double defaultWeight = 1.0;
  double value;
  int nodeIndex;

  public Node(int i) {
    value = 0.0;
    axionList = new Vector();
    nodeIndex = i;
  }

  public void addAxion(Node n) {
     boolean axionExisted = false;
     for (int i = 0; i < axionList.size(); i++) {
      Axion tmpAxion = ( (Axion) (axionList.elementAt(i)));

      if (tmpAxion.myTo == n) {
        tmpAxion.myWeight += defaultWeight;
         axionExisted = true;
      }

    } // end for
    if ( ! axionExisted ){
      axionList.addElement(new Axion(this, n, defaultWeight));
    }

  } // end add axion

  public void addAxion(Node n,double weight){
    boolean axionExisted = false;
    for (int i = 0; i < axionList.size(); i++) {
      Axion tmpAxion = ( (Axion) (axionList.elementAt(i)));
      if (tmpAxion.myTo == n) {
        tmpAxion.myWeight += weight;
        axionExisted = true;
      }
    } // end for
    if ( ! axionExisted ){
      axionList.addElement(new Axion(this, n, weight));
    }
  }


  public void update(){
    for(int i=0; i< axionList.size(); i++){
      ((Axion)(axionList.elementAt(i))).fire(value);

    }// end for each axion

  } // end update
  public void add(double val,double weight){
    value = 2.0/Math.PI* Math.atan(val*weight+value);
    // between -1 and 1 sigmoid ish
  }
  public void teach(double weight){
    for(int i =0; i< axionList.size();i++){
      ((Axion)(axionList.elementAt(i))).strengthen(weight);
    }
  }
  public double getValue(){return value;}
}// end Node
