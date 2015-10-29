package Models;

import java.io.*;
import java.util.*;
import javax.swing.*;

import simthingy.Animator;
import simthingy.Properties;
import simthingy.Refdouble;
import simthingy.Variable;

import Things.Anchor;
import Things.Bungee;
import Things.Connector;
import Things.Gravity;
import Things.Pulley;
import Things.Rod;
import Things.Rope;
import Things.Shape;
import Things.Slider;
import Things.Sling;

public class FileModel
    extends Model {

    public static final int MAX_FILE_SIZE = 100000;
    private boolean EOF = false;
    private boolean init = false;
    private boolean initState = false;

    private String myFileName;

    StreamTokenizer st;
    BufferedReader r;
    JFileChooser chooser = new JFileChooser();
    public FileModel(String fileName) { // constructor
        myFileName = fileName;
        answer = new Refdouble();
        init();
        System.out.println("end init");
    } // filemodel constructor

    // ***********************************************
    public void init() {
        try {

            FileReader fr = new FileReader(myFileName);
            r = new BufferedReader(fr);
            r.mark(MAX_FILE_SIZE); // sets the reset positon to the begining of the file
            while (!r.ready()) {
                System.out.println("not ready");
                Thread.sleep(10);
            }
            String s = new String();
            while (s != null) {
                System.out.println(s);
                s = r.readLine();
            }
            r.reset();
            st = new StreamTokenizer(r);
        }
        catch (Exception e) {
            System.err.println("Error reading from file");
            e.printStackTrace();
            System.exit( -1);
        }
        init = true;
        build();
        init = false;
    }

    // ***********************************************
    public void build() {

        try {
            r.reset();
            EOF = false;
            while (!EOF) {

                readLine();
            }
        }
        catch (Exception e) {
            System.err.println("cant build");
            e.printStackTrace();
            System.exit( -1);
        }
    }

    // ***********************************************
    String readString() throws Exception {
        st.nextToken();
        if (st.ttype == StreamTokenizer.TT_EOF) {
            return "EOF";
        }

        String s ;
        if (st.ttype != StreamTokenizer.TT_WORD) {
            s = "" ;
 //          throw new IOException("no word in readString");
       }else{

            s = new String(st.sval);
       }

        return s;
    }

    // ***********************************************
    double readDouble() throws Exception {
        st.nextToken();
        if (st.ttype != StreamTokenizer.TT_NUMBER) {
            throw new IOException("no number in readDouble ");
        }
        double d = st.nval;
        return d;
    }

    // ***********************************************
    void readLine() {
        try{
            String type = readString();
            //System.out.println("token = " + type);
            if (type.equals("init")) {
                initState = true;
                return;
            } // *********
            if (type.equals("endinit")) {
                initState = false;
                r.mark(1000);
                if (init ==true) EOF = true;
                return;
            } // *********
            if (init != initState) {
                return;
            }

            if (type.equals("EOF")) {
                EOF = true;
                return;
            } // *********

            if (type.equals("connector")) {
                String name = readString();
                double x = readVariable();
                double y = readVariable();
                double xv = readVariable();
                double yv = readVariable();
                double m = readVariable();
                add(new Connector(x, y, xv, yv, m), name);
                return;
            } // *********
            if (type.equals("anchor")) {
                String name = readString();
                double x = readVariable();
                double y = readVariable();
                double xv = readVariable();
                double yv = readVariable();
                add(new Anchor(x, y, xv, yv), name);
                return;
            } // *********

            if (type.equals("variable")) {
                String name = readString();
                double beg = readVariable();
                double end = readVariable();
                double step = readVariable();
                addVariable(new Variable(name, beg, end, step));
                return;
            } // *********

            if (type.equals("property")) {
                String name = readString();
                if (name.equals("deltat")) {
                    Properties.deltaT = readVariable();
                }
                if (name.equals("springk")) {
                    Properties.springK = readVariable();
                }
                if (name.equals("torquek")) {
                    Properties.torqueK = readVariable();
                }
                if (name.equals("dampingconst")) {
                    Properties.dampingConst = readVariable();
                }
                if (name.equals("torquedampingconst")) {
                    Properties.torqueDampingConst = readVariable();
                }
                if (name.equals("connectoralpha")) {
                    Properties.connectorAlpha = readVariable();
                }
                if (name.equals("bungeedouble")) {
                  Properties.bungeeDoubleForce = readVariable();
              }
              if (name.equals("finaltime")) {
                Properties.finalTime = readVariable();
            }

                return;
            } // *********
            if (type.equals("animator")) {

                double xmin = readVariable();
                double xmax = readVariable();
                double floor = readVariable();

                new Animator(this, xmin, xmax, floor, true);
                return;
            } // *********
            if (type.equals("shape")) {
                String name = readString();
                String element = readString();
                Vector shape = new Vector();
                while (!element.equals("endshape")) {
                    shape.addElement(get(element));
                    element = readString();
                }
                add(new Shape(shape), name);
                return;
            } // *********
            if (type.equals("rotconnector")) {
                String name = readString();
                String rotAbout = readString();
                double angle = readVariable();
                double length = readVariable();
                double mass = readVariable();
                add(Connector.rotate( (Connector) get(rotAbout),
                                     angle,
                                     length, mass),
                    name);

                return;
            } // *********
            if (type.equals("gravity")) {
                double g = readVariable();
                add(new Gravity(thingList, g));
                return;
            } // *********
            if (type.equals("rope")) {
                String pt1 = readString();
                String pt2 = readString();
                double length = readVariable();
                
                add(new Rope(get(pt1), get(pt2),length));
                return;
            } // *********
            if (type.equals("rod")) {
            String pt1 = readString();
            String pt2 = readString();
            add(new Rod(get(pt1), get(pt2)));
            return;
        } // *********

            if (type.equals("bungee")) {
                String pt1 = readString();
                String pt2 = readString();
                double unstretchedLength = readVariable();
                add(new Bungee(get(pt1), get(pt2), unstretchedLength));
                return;
            } // *********
            if (type.equals("slider")) {
                String pt1 = readString();
                String pt2 = readString();
                String pt3 = readString();
                add(new Slider(get(pt1), get(pt2), get(pt3) ));
                return;
            } // *********

            if (type.equals("sling")) {
                String pt1 = readString();
                String pt2 = readString();
                add(new Sling(get(pt1), get(pt2), answer));
                return;
            } // *********
            if (type.equals("pulley")) {
                String pt1 = readString();
                String pt2 = readString();
                String pt3 = readString();
                add(new Pulley(get(pt1), get(pt2), get(pt3)));
                return;
            } // *********
            System.out.println("type not recognized: " + type);
        }catch (Exception e){
            System.out.println();
            e.printStackTrace();
            System.exit( -1);
        }
    }

    // ***********************************************
    double readVariable() throws Exception {
        st.nextToken();
        double value = 0;
        if (st.ttype == StreamTokenizer.TT_WORD) {
            String s = new String(st.sval);
            value = getVariable(s).get();
            return value;
        }
        if (st.ttype == StreamTokenizer.TT_NUMBER) {
            value = (st.nval);
            return value;
        }
        throw new Exception("variable not read");
    }

    // ***********************************************
    //**************************************************************
         public boolean isDone() {
             if (/* (answer.d != 0.0)|| */(Properties.time >Properties.finalTime) ) {
                try {
                   // an.repaint();
              //      Thread.sleep(10);
                }
                catch (Exception ex) { }
                 printState();
                 //System.out.println("time:"+Properties.time);
                 return true;
             }
             return false;

     }
} //FileModel class
