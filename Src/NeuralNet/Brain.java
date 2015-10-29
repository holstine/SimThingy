package NeuralNet;

import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Brain  {
  Vector nodeList;
  int numNodes;
  double mutateWeight = .1;

  public Brain(int n) {

    // n is the number of nodes in the brain
    nodeList = new Vector(n);
    numNodes = n;

    for ( int i =0; i< n; i++){
      nodeList.addElement(new Node(i) );
    }

  } // end constructor

  public void addNode(Node n){
  nodeList.addElement(n);
  numNodes++;

  }
  public void update(){

    for ( int i =0; i<numNodes; i++){


       ( (Node)(nodeList.elementAt(i)) ).update();

     }// end for each node
   if (Math.random() < .0001){  mutate();}
   if (Math.random() < .001){  forget();}
 //  forget();

   }// end update
   public int numNodes() {return numNodes;}
   public Node node(int i){return ( (Node) (nodeList.elementAt(i)) );}


   public void mutate(){

    int toNode = (int)(Math.random() * numNodes);
    //   int fromNode = (int)(Math.random() * numNodes  );

    int fromNode = toNode + (int)(Math.random() * 3)+3;
    if (fromNode == toNode) fromNode = toNode  +1;
    if (fromNode >= numNodes) fromNode -=numNodes;


    double w = (Math.random() -.5) * 2.0;

   // if ( w > 1000) System.out.print(w +" \n");

    node(toNode).addAxion( node(fromNode), w);

   } // end mutate


   public void forget(){
     for (int i =0; i< numNodes; i++){

       double w = .9990; // teaching coefficent
       node(i).teach(w );

     }
   }

   public void reward(){
     for (int i =0; i< numNodes; i++){

       node(i).teach(Math.abs(node(i).getValue()) * 1.01);
  //     System.out.println("yeah");
     }
   }

public double getWeight(int j, int k){
     Node nj =( (Node)(nodeList.elementAt(j)) );
     Node nk =( (Node)(nodeList.elementAt(k)) );

     for (int m = 0; m < nj.axionList.size(); m++) {
       Axion tmpAxion = ( (Axion) (nj.axionList.elementAt(m)));
       if (tmpAxion.myTo == nk) {
//         System.out.print(tmpAxion.myWeight+ " \n");
         return tmpAxion.myWeight;
       }

     } // end for m
     return 0.0;

   }// end getWeight



} // end Brain
