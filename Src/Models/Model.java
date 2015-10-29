package Models;

import java.util.*;

import simthingy.Refdouble;
import simthingy.Variable;

import Things.Thing;

public class Model {

    public Vector thingList = new Vector(3);
    public List variables = new ArrayList();
    public boolean repaint = true;

    public Refdouble answer = new Refdouble();

    //**************************************************************

    //**************************************************************
     public void reset() {
         thingList.removeAllElements();
         repaint = false;
         build();
         repaint = true;
     }

    //**************************************************************
     public Thing add(Thing t) {
         thingList.addElement(t);
         return t;
     }

    //**************************************************************
     public Thing add(Thing t, String name) {
         thingList.addElement(t);
         t.name = name;
         return t;
     }

    //**************************************************************
     public Thing get(String name) {
         for (int i = 0; i < thingList.size(); i++) {
             Thing t = ( (Thing) (thingList.elementAt(i)));
             if (name.equals(t.name)) {
                 return t;
             }
         } //end for i
         System.out.print(name + " Not found!");
         return null;
     }

//**************************************************************
     public Variable addVariable(Variable t) {
         if (getVariable(t.variableName) == null) {
             variables.add(t);
         }
         return t;
     }

    //**************************************************************
     public Variable getVariable(String name) {
         for (int i = 0; i < variables.size(); i++) {
             Variable t = (Variable) (variables.get(i));
             if (name.equals(t.variableName)) {
                 return t;
             }
         } //end for i
         return null;
     }

    //**************************************************************
     public void build() {}
    //**************************************************************
     public void init(){}
    //**************************************************************
     public boolean isDone() {
         if (answer.d != 0.0) {
             printState();
             return true;

         }
         return false;
     }

    //**************************************************************
     public void printState() {
         System.out.print(systemState());
     }

    //**************************************************************
     public String systemState() {
         String s = new String();
         for (Iterator iter = variables.iterator(); iter.hasNext(); ) {
             Variable item = (Variable) iter.next();
             s += item.variableName + " \t" + item.get() + " \t";
         }
         s += answer.d ;
         s += "\r\n";
         return s;
     }

     //**************************************************************

}
