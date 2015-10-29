package simthingy;
import java.util.*;

import Models.Model;
import Things.Thing;

public class Universe {

    public Vector thingList = new Vector(3);
    Model myModel;
    int count = 0;
    //public Vector connectorList = new Vector(3);

    // ********************************************************************
    public Universe() { }
    // ********************************************************************
    public Universe(Model m) {
        myModel = m;
    }

    //********************************************************************
    public void redFlag(){
             if (Properties.redFlag) {
                 //	Properties.deltaT /=4;
                 Properties.goFlag = false;
             }
             else {
                 //		Properties.deltaT =
                 //                    (Properties.timeAlpha*Properties.deltaT +
                 //                     (1-Properties.timeAlpha)*Properties.maxDeltaT);
             } // end else (redflag )
             Properties.redFlag = false;

    }

    //********************************************************************
    public void processorRest(){
        // let the system have the processors once in a while to avoid skipping
        try {
            count++;
            if ( (count) % 500 == 5) {
                //     count = 0;
                Thread.sleep(10);
            }

        } catch (Exception ex) { }
    }
    //********************************************************************
    public void timeModel(){
        Properties.time += Properties.deltaT;
    }
    //********************************************************************
     public void forceModel() {
         for (int i = 0; i < myModel.thingList.size(); i++) {
             ( (Thing) (myModel.thingList.elementAt(i))).force();
         }
     }

    //********************************************************************
     public void moveModel() {

         for (int i = 0; i < myModel.thingList.size(); i++) {
             ( (Thing) (myModel.thingList.elementAt(i))).move();
         }

     }

    //********************************************************************
    public void waitForGo(){

        while (!Properties.goFlag) {
            timeModel();
            try {
                Thread.sleep(50); // wait for the go signal!
            }
            catch (Exception ex) {}
        }
    }
    //********************************************************************
    public boolean isDone(){
       return  myModel.isDone();
    }
    //********************************************************************

     public void go() {

         Properties.time = 0;
         while (! isDone()) {
             waitForGo();
             processorRest();
             timeModel();
             moveModel();
             redFlag();
             forceModel();
         } // end while
     } // end go

} // end universe
