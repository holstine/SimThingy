package simthingy;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.*;
import java.text.*;

public class PropertiesModifier
    extends JPanel
    implements PropertyChangeListener {

  public String PropertyString = "deltaT";
  public double defaultProperty = .000001;
  public JFormattedTextField f1;

  public PropertiesModifier() { //

    DecimalFormat df = (DecimalFormat)(DecimalFormat.getNumberInstance() );
    df.setMinimumFractionDigits(9);



   JLabel l1 = new JLabel(PropertyString);
   f1 = new JFormattedTextField(df);
   defaultProperty = Properties.deltaT;
   f1.setValue(new Double(defaultProperty));
   f1.setColumns(20);
   f1.addPropertyChangeListener("value",this);
   add(l1);
   add(f1);
  }


  public void propertyChange(PropertyChangeEvent e) {
    Object source = e.getSource();

        if (source == f1) {Properties.deltaT =
              ((Number)f1.getValue()).doubleValue() ;

        }
  } // end propertyChange
  private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        //JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Properties");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new PropertiesModifier();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // call this to make a Properties Pane
    public static void create(){
     //Schedule a job for the event-dispatching thread:
         //creating and showing this Thing's GUI.
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 createAndShowGUI();
             }
         });
   }

}// end class PropertiesModifier
