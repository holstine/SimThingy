package Things;

import java.util.*;

import simthingy.Animator;
import simthingy.Properties;


public class Shape
    extends Thing {
  public Vector myConnectors = new Vector();
  int numberOfPoints;
  private double[] myOriginalLength;
  private double[] myCrossProduct;
  private double[] myPrevCrossProduct;
  private double[] myPrevLength;
  private int[] myFormFactor;

  private double springK = Properties.springK;
  private double torqueK = Properties.torqueK; // Properties.springK;
  private double alpha = Properties.dampingConst;
  private double torqueAlpha = Properties.torqueDampingConst; //Properties.dampingConst;

  public Shape(Vector c) {
    myConnectors = c;
    numberOfPoints = myConnectors.size();
    myOriginalLength = new double[numberOfPoints];
    myCrossProduct = new double[numberOfPoints];
    myPrevCrossProduct = new double[numberOfPoints];
    myPrevLength = new double[numberOfPoints];
    myFormFactor = new int[numberOfPoints];

    for (int i = 0; i < numberOfPoints; i++) {
      Connector ca = ( (Connector) (myConnectors.elementAt(i % numberOfPoints)));
      Connector cb = ( (Connector) (myConnectors.elementAt( (i + 1) %
          numberOfPoints)));
      Connector cc = ( (Connector) (myConnectors.elementAt( (i + 2) %
          numberOfPoints)));
      double dxa = ca.xpos - cb.xpos;
      double dya = ca.ypos - cb.ypos;
      double dxc = cb.xpos - cc.xpos;
      double dyc = cb.ypos - cc.ypos;

      // length is between a and b
      myOriginalLength[i] = Math.sqrt( (dxa * dxa) + (dya * dya));
      myPrevLength[i] = myOriginalLength[i];

      // cross produduct is on the angle a b c  ( b being the pivot)
      myCrossProduct[i] = - ( (dxa * dyc) - (dxc * dya));
      myPrevCrossProduct[i] = myCrossProduct[i];
      // need to know obtuse or acute because there are two solutions to crossproduct
      // for set lengths
      // obtuse is positive and accute is negitive
      if ( (dxa * dxc + dya * dyc) > 0) {
        myFormFactor[i] = 1;
      }
      else {
        myFormFactor[i] = -1;
      }
    }
  }

  public void force() {

    for (int i = 0; i < numberOfPoints - 1; i++) {
      Connector ca = ( (Connector) (myConnectors.elementAt(i % numberOfPoints)));
      Connector cb = ( (Connector) (myConnectors.elementAt( (i + 1) %
          numberOfPoints)));
      Connector cc = ( (Connector) (myConnectors.elementAt( (i + 2) %
          numberOfPoints)));
      double dxAB = ca.xpos - cb.xpos;
      double dyAB = ca.ypos - cb.ypos;
      double dxCB = cc.xpos - cb.xpos;
      double dyCB = cc.ypos - cb.ypos;

      // length calculations (force is only between a and b)
      double lengthAB = Math.sqrt( (dxAB * dxAB) + (dyAB * dyAB));
      double lengthBC = Math.sqrt( (dxCB * dxCB) + (dyCB * dyCB));
      double delta = lengthAB - myOriginalLength[i];
      double expVel = (lengthAB - myPrevLength[i]) / Properties.deltaT;
      // rod expansion velocity

      double rodforce = (springK * delta - Math.abs(expVel) * expVel * alpha);
      ca.addXForce( -1 * dxAB / lengthAB * rodforce);
      ca.addYForce( -1 * dyAB / lengthAB * rodforce);

      cb.addXForce(dxAB / lengthAB * rodforce);
      cb.addYForce(dyAB / lengthAB * rodforce);
      // end length calculations

      // deflection calculations
      double crossproduct = (dxAB * dyCB) - (dxCB * dyAB);
      double deflection = crossproduct - myCrossProduct[i];
      double deflectVel = (crossproduct - myPrevCrossProduct[i]) /
          Properties.deltaT;
      double torque = (torqueK * deflection -
                       Math.abs(deflectVel) * deflectVel * torqueAlpha)
          * myFormFactor[i]; // need the formfactor to tell accute from obtuse

      if ( (i) < numberOfPoints - 2) {
        // forces are in the neg reciprocal direction
        // if  torque is positive then the angle is obtuse
        double forceAx = torque / lengthAB * (dyAB / lengthAB);
        double forceAy = torque / lengthAB * ( -dxAB / lengthAB);

        double forceCx = torque / lengthBC * ( -dyCB / lengthBC);
        double forceCy = torque / lengthBC * (dxCB / lengthBC);

        ca.addXForce(forceAx);
        ca.addYForce(forceAy);

        cc.addXForce(forceCx);
        cc.addYForce(forceCy);

        // equal but opposite reaction

        cb.addXForce( - (forceAx + forceCx));
        cb.addYForce( - (forceAy + forceCy));

      }

      myPrevLength[i] = lengthAB;
      myPrevCrossProduct[i] = crossproduct;
    }
  }

  public void move() {}

  public void showThing(Animator a) {
    for (int i = 0; i < numberOfPoints - 1; i++) {
      a.offscreenGraphics.drawLine(
          a.rescaleX( ( (Connector) (myConnectors.elementAt(i % numberOfPoints))).
                     xpos),
          a.rescaleY( ( (Connector) (myConnectors.elementAt(i % numberOfPoints))).
                     ypos),
          a.rescaleX( ( (Connector) (myConnectors.elementAt( (i + 1) %
          numberOfPoints))).xpos),
          a.rescaleY( ( (Connector) (myConnectors.elementAt( (i + 1) %
          numberOfPoints))).ypos)
          );
      //a.offscreenGraphics.drawString(String.valueOf((double)(myPrevLength[i])),120*i,50);
    }
  }
}
