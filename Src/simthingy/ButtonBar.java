package simthingy;
import javax.swing.*;

import Models.Model;
import Things.Thing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.*;
import java.text.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
// ****************************************************************************
public class ButtonBar
    extends JPanel
    implements ActionListener {

  JFileChooser chooser = new JFileChooser();

  public static Model myModel;

// ****************************************************************************
  public ButtonBar() {

    JButton loadButton = new JButton("load");
    loadButton.addActionListener(this);
    add(loadButton);

    JButton saveButton = new JButton("save");
    saveButton.addActionListener(this);
    add(saveButton);

    JButton clearButton = new JButton("clear");
    clearButton.addActionListener(this);
    add(clearButton);

  }

// ****************************************************************************
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == "save") {
      int returnVal = chooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        System.out.println("You chose to save to this file: " +
                           chooser.getSelectedFile().getName());
        save(chooser.getSelectedFile());
      }
    } // end if save button pressed

    if (e.getActionCommand() == "load") {
      int returnVal = chooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        System.out.println("You chose to load this file: " +
                           chooser.getSelectedFile().getName());
        load(chooser.getSelectedFile());
      }
    } // end if load
    if (e.getActionCommand() == "clear"){
   //   myModel.thingList.removeAllElements();
      myModel.thingList.clear();
      myModel.thingList.setSize(0);
   //   myModel.build();
    }
  } // end action performed

// ****************************************************************************
  private static int save(File f) {
    try {
      FileOutputStream fos = new FileOutputStream(f);
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      for (int i = 0; i < myModel.thingList.size(); i++) {
        oos.writeObject(myModel.thingList.elementAt(i));
      }

      oos.close();
      fos.close();
    }
    catch (IOException i) {
      System.out.print("aww nuts");
    }

    return 1;
  }

// ****************************************************************************
  private static int load(File f) {
    try {
      FileInputStream fos = new FileInputStream(f);
      ObjectInputStream oos = new ObjectInputStream(fos);

      for (int i = 0; i < myModel.thingList.size(); i++) {
        myModel.add( (Thing) (oos.readObject()));
      }

      oos.close();
      fos.close();
    }
    catch (IOException j) {
      System.out.print("aww nuts");
    }
    catch (ClassNotFoundException c) {
    }
    return 1;

  }

// ****************************************************************************
  private static void createAndShowGUI() {
    //Make sure we have nice window decorations.
    //JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    JFrame frame = new JFrame("SimThingy");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create and set up the content pane.
    JComponent newContentPane = new ButtonBar();
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

// ****************************************************************************
  public static void create(Model m) {
  // call this to make a Properties Pane
    myModel = m;
    //Schedule a job for the event-dispatching thread:
    //creating and showing this Thing's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }

}
