package simthingy;


import java.util.*;

import Models.Model;


public class IterativeUniverse extends Universe {

    List variables = new ArrayList();

    Log log = new Log("log.txt");
    //********************************************************************
    public IterativeUniverse( Model m) {
       variables = m.variables;
       myModel = m;
    }

    //********************************************************************
    public void recursiveGo(List var,int  level) {
        if(var.size() ==level){
           // bottom level... all variabls set
            myModel.reset();
            super.go();
            log.write(myModel.systemState() );
            return;
        }

        Variable v = (Variable) (var.get(level) );

        for (v.reset() ; ! v.done(); v.next()){
            recursiveGo(var,level+1);
        }
        //v.reset();
        System.out.println("   ");
        log.write("\r\n");// just for gnuplot
    }

    //********************************************************************
  public void go(){

        recursiveGo(variables,0);
        log.close();
  }
}
