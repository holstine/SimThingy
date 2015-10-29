package simthingy;


import java.awt.*;
import java.util.*;
import java.io.*;
import java.net.*;

import Models.Model;
import Things.Thing;

// ---------------------------------------------------------------------------
public class Animator
    extends Frame
    implements Runnable {
  private Vector thingList;
  public Graphics offscreenGraphics;
  private Image offscreenImage;
  private Dimension offscreenSize;
  private Model myModel;

  public double maxX = 4;
  public double minX = -3; // meters
  public double maxY = 4;
  public double minY = -3; // meters

  int x;

  //Button go = new Button("Go!");

  boolean updateable = true;
  private Thread animate_thread = null;

  public Animator(Model m) {

    getThings(m);
    myModel = m;
    init();
    start();

  }

  public Animator(Model m, double min, double max, double miny) {

    getThings(m);
    myModel = m;
    init();
    start();

    maxX = max;
    minX = min;
    minY = miny;
    //	maxY = maxy;

  }
  public Animator(Model m, double min, double max, double miny, boolean up) {

    getThings(m);
    myModel = m;
    init();
    start();
    updateable = up;

    maxX = max;
    minX = min;
    minY = miny;
  }

// ---------------------------------------------------------------------------
  // functions
  public boolean handleEvent(Event e) {
    if (e.id == Event.WINDOW_DESTROY) {
      hide();
      System.exit(0);
    }
    return super.handleEvent(e);
  }

  public boolean action(Event evt, Object arg) {

    if (arg.equals("Go")) {
      Properties.goFlag = true;
    }
    else if (arg.equals("Reset")) {
      myModel.reset();
    }
    else if (arg.equals("Pause")) {
      Properties.goFlag = false;
    }
    else if (arg.equals("Trigger")) {
      Properties.trigger = ! (Properties.trigger);
    }
    else if (arg.equals("Winch")) {
      Properties.winch = ! (Properties.winch);
    }
    else if (arg.equals("Winch Reverse")) {
      Properties.winchSpeed = - (Properties.winchSpeed);
    }
    else {
      return super.action(evt, arg);
    }
    return true;
  }

  // ------------------------------------------------------------------------
  // Initialize some variables.
  //
  public void init() {

    Panel p = new Panel();
    p.add(new Button("Pause"));
    p.add(new Button("Reset"));
    p.add(new Button("Go"));
    p.add(new Button("Trigger"));
    p.add(new Button("Winch"));
    p.add(new Button("Winch Reverse"));
    add("South", p);

    resize(700, 700);
    show();
  }

  // ------------------------------------------------------------------------

  // ------------------------------------------------------------------------
  // At start, create a new thread for animation.
  //
  public void start() {
    if (animate_thread == null) {
      animate_thread = new Thread(this); // Calls _this_ run method,

      // when start() is called.
      animate_thread.start();
    }
  }

  // ------------------------------------------------------------------------

  // ------------------------------------------------------------------------
  // Move sprite, repaint, and sleep.
  //
  public void run() {
    while (true) {
        if (updateable && myModel.repaint){
            repaint();
        }
      try {
        Thread.sleep(100);
        // don't need to repaint all the time
      }
      catch (Exception ex) {
      }
    }
  }

  // ------------------------------------------------------------------------

  // ------------------------------------------------------------------------
  // Call paint.
  // Necessary to prevent "flicker".
  //   -- From screen erase in default implementation.
  //
  public void update(Graphics g) {
    paint(g);
  }

  // ------------------------------------------------------------------------

  // ------------------------------------------------------------------------
  // This is where all the work is done.
  //
  public void paint(Graphics g) {

    // Check offscreen image - create if necessary.
    //
    Dimension d = size();
    if ( (offscreenImage == null) || (d.width != offscreenSize.width) ||
        (d.height != offscreenSize.height)) {

      offscreenImage = createImage(d.width, d.height);
      offscreenSize = d;
      maxY = ( (double) (d.height) / (double) (d.width) * (maxX - minX)) + minY;
      offscreenGraphics = offscreenImage.getGraphics();
    }
    Color c = offscreenGraphics.getColor();

    //offscreenGraphics.drawOval(x - 5, 95, x + 5, 100 + x);
    offscreenGraphics.setColor(Color.white);
    offscreenGraphics.fillRect(0, 0, d.width, d.height);
    offscreenGraphics.setColor(c);

    for (int i = 0; i < thingList.size(); i++) {
      ( (Thing) (thingList.elementAt(i))).showThing(this);
    }

    //
    g.drawImage(offscreenImage, 0, 0, this);
  }

  // ------------------------------------------------------------------------
  public void getThings(Model m) {
    thingList = m.thingList;
  }

  // ------------------------------------------------------------------------
  public int rescaleX(double x) {
    double xScale = (offscreenSize.width - 30) / (maxX - minX);
    return (int) (xScale * (x - minX) + 10);
  }

  public int rescaleY(double y) {
    double yScale = (offscreenSize.height - 50) / (maxY - minY);
    return (int) ( -1 * yScale * (y - minY)) + offscreenSize.height - 50;
  }
} // end of animator

// ---------------------------------------------------------------------------
