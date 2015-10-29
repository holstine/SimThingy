package NeuralNet;


import Things.Connector;
import Things.Thing;

import java.awt.Color;

import simthingy.Animator;



public class Head extends Thing {
  // head is the physical manifestation of the brain;
  // just so it gets updated

  Brain     myBrain;
  Connector myConnector;


  public Head(Brain b,Connector c) {
    myBrain = b;
    myConnector = c;
    xpos = c.xpos;
    ypos = c.ypos;

  }
  public void force() {

  }
  public void move() {

    myBrain.update();
    xpos = myConnector.xpos;
    ypos = myConnector.ypos;
  }
  public void showThing(Animator a) {
    double size = 0.7;

    Color pc = a.offscreenGraphics.getColor();
    a.offscreenGraphics.setColor(Color.cyan);


    //  Each Node
    for( int i =0; i < myBrain.numNodes(); i++){
    //  if ( Math.abs(myBrain.node(i).getValue() ) >.0){

        a.offscreenGraphics.setColor(Color.cyan);
        if (myBrain.node(i).getValue()>0.01) a.offscreenGraphics.setColor(Color.blue);
        if (myBrain.node(i).getValue()<-0.01) a.offscreenGraphics.setColor(Color.red);
        if (myBrain.node(i).getValue()==0.0) a.offscreenGraphics.setColor(Color.yellow);

        double ix = xpos + size* Math.sin(2*Math.PI * i/myBrain.numNodes());
        double iy = ypos + size* Math.cos(2*Math.PI * i/myBrain.numNodes());
        double ovalSize = .31;
        int ovalSizeX = a.rescaleX(ix + ovalSize *Math.abs(myBrain.node(i).getValue() ) ) -
            a.rescaleX(ix);
        int ovalSizeY = -a.rescaleY(iy + ovalSize *Math.abs(myBrain.node(i).getValue() ) ) -
            -a.rescaleY(iy);

        a.offscreenGraphics.fillOval(a.rescaleX(ix) - ovalSizeX/2,
                                     a.rescaleY(iy) - ovalSizeX/2,
                                     ovalSizeX,
                                     ovalSizeY
                                 );
    //  }// end if node active

      for(int k =0; k <myBrain.numNodes() ; k++){

        for(int j =0; j <myBrain.numNodes() ; j++){
          if ( myBrain.getWeight(j,k) !=0.0) {

            double kx = xpos + size* Math.sin(2*Math.PI * (k+.01)/myBrain.numNodes());
            double ky = ypos + size* Math.cos(2*Math.PI * (k+.01)/myBrain.numNodes());
            double jx = xpos + size* Math.sin(2*Math.PI * (j-.01)/myBrain.numNodes());
            double jy = ypos + size* Math.cos(2*Math.PI * (j-.01)/myBrain.numNodes());
            a.offscreenGraphics.setColor(Color.DARK_GRAY);

            if (myBrain.getWeight(j,k)>0.5) {
              a.offscreenGraphics.setColor(Color.blue);
            }
            if (myBrain.getWeight(j,k)<-0.5) {
              a.offscreenGraphics.setColor(Color.red);
            }


            a.offscreenGraphics.drawLine(a.rescaleX(kx),
                                         a.rescaleY(ky),
                                         a.rescaleX(jx),
                                         a.rescaleY(jy)  );


          }// end if
        }// for j
      }// for k
    }

     a.offscreenGraphics.setColor(pc);

  }

}
