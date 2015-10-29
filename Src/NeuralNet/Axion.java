package NeuralNet;

public class Axion {
  public double myWeight ;
  public Node myFrom;
  public Node myTo;

  public Axion(Node from, Node to, double weight) {
    myWeight = weight;
    myFrom = from;
    myTo = to;
  }

  public void strengthen(double weight){
    myWeight *= weight;
  }
  void  fire(double value){
    myTo.add (value , myWeight);

  }

}
