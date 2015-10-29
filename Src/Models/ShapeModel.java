package Models;
import java.util.*;

import Things.AirResistance;
import Things.Anchor;
import Things.Connector;
import Things.Gravity;
import Things.Rope;
import Things.Shape;

public class ShapeModel
    extends Model {


  public void build() {

    add(new Connector( -2, 0, 0, 0, 100000), "a");
    add(new Connector( -1, 0, 0, 0, 1), "b");
    add(new Anchor(0, 0, 0, 0), "c");
    add(new Connector(1, 0, 0, 0, 1), "d");
    add(new Connector(2, 0, 0, 0, 1), "e");
    add(new Connector(3, 0, 0, 0, 1), "f");
    add(new Connector(4, 0, 0, 0, 5), "g");
    add(new Connector(5, 0, 0, 0, 5), "h");
    add(new Connector(6, 0, 0, 0, 5), "i");

    add(new Connector(7, 0, 0, 0, .7), "1");
    add(new Connector(8, 0, 0, 0, .6), "2");
    add(new Connector(9, 0, 0, 0, .5), "3");
    add(new Connector(10, 0, 0, 0, .4), "4");
    add(new Connector(11, 0, 0, 0, .3), "5");
    add(new Connector(12, 0, 0, 0, .2), "6");
    add(new Connector(13, 0, 0, 0, .1), "7");

    add(new Rope(get("i"), get("1")));
    add(new Rope(get("1"), get("2")));
    add(new Rope(get("2"), get("3")));
    add(new Rope(get("3"), get("4")));
    add(new Rope(get("4"), get("5")));
    add(new Rope(get("5"), get("6")));
    add(new Rope(get("6"), get("7")));

    Vector polygon = new Vector();
    polygon.addElement(get("a"));
    polygon.addElement(get("b"));
    polygon.addElement(get("c"));
    polygon.addElement(get("d"));
    polygon.addElement(get("e"));
    polygon.addElement(get("f"));
    polygon.addElement(get("g"));
    polygon.addElement(get("h"));
    polygon.addElement(get("i"));

    add(new Shape(polygon), "shape");

  // body forces

  //  add(new Mud( -1.1, thingList));
    add(new Gravity(thingList));
    add( new AirResistance(thingList));

}
}
