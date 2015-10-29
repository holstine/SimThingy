package simthingy;
public class Variable {
  double myBegin;
  double myEnd;
  double myStep;

  double myCurrent;
  public String variableName = "VariableName";

 //**************************************************************
  public Variable( String name,double begin, double end ,double step) {
    myBegin = begin;
    myEnd = end;
    myStep = step;
    variableName = name;

    myCurrent = myBegin;

  }
 //**************************************************************
 public void reset() {
   myCurrent = myBegin;
 }
 //**************************************************************
 public double get() {
   return myCurrent;
 }
 //**************************************************************
  public double next(){
    myCurrent += myStep;

    return myCurrent;
  }
 //**************************************************************
  public boolean done(){
    if ( (myCurrent > myEnd) && (myStep >0) ) return true;
    if ( (myCurrent < myEnd) && (myStep <0) ) return true;
    return false;
  }
}
