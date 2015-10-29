package simthingy;


import java.io.*;


public class Log {

    FileWriter out ;

    //**************************************************************
    public Log(String logfile) {
        try {
            File outputFile = new File(logfile);
            out = new FileWriter(outputFile);

        } catch (IOException ex) {System.out.println("error opening log file"); }
    }
    //**************************************************************
    public void write (String s){
        try {
            out.write(s);
            out.flush();
        } catch (IOException ex) { System.out.println("yarg");}
    }
    //**************************************************************
    public void close(){
        try {
            out.flush();
            out.close();
        } catch (IOException ex) { }
    }
    //**************************************************************
}
