package Things;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Grapher extends Frame 
{
	public Grapher()
	{
		resize(700,500);
		show();
	}
	public boolean handleEvent(Event e){
		if (e.id == Event.WINDOW_DESTROY) {
			hide();
			System.exit(0);
		}
		return super.handleEvent(e);													  
	}
	public void paint(Graphics g){
		Dimension d = size();
		if ((offscreenImage == null) || (d.width != offscreenSize.width)||
         (d.height != offscreenSize.height)) {
			offscreenImage = createImage( d.width, d.height );
			offscreenSize = d;
			offscreenGraphics = offscreenImage.getGraphics();
		}
		double xScale = (d.width-30)/ (maxX - minX);
		double yScale = (d.height-50)/ (maxY - minY);
		double xOffset = d.width/ minX;
		double yOffset = d.height / minY;
		if(axesYes)drawAxes();
		offscreenGraphics.drawString(title,d.width/2,d.height-10);
		int endpoint = xPoints.size();
		for (int i = 0; i < endpoint; i++){
			double xval = ((Double)xPoints.elementAt(i)).doubleValue();
			double yval = ((Double)yPoints.elementAt(i)).doubleValue();
			offscreenGraphics.drawOval(	(int) ( xScale * (xval - minX)+10 ),
						(int) (-1 *yScale * (yval - minY))+d.height-20,5,5);
		}
		g.drawImage( offscreenImage, 0, 0, this );
	}
	public void addPoint(double x, double y){
		if (x > maxX) maxX = x;
		if (y > maxY) maxY = y;
		if (x < minX) minX = x;
		if (y < minY) minY = y;
		
		xPoints.addElement(new Double(x) );
		yPoints.addElement(new Double(y) );
		
	}
	public void addLine(double x1, double y1, double x2,double y2){
		if (x1 > maxX) maxX = x1;		if (y1 > maxY) maxY = y1;
		if (x1 < minX) minX = x1;		if (y1 < maxY) minY = y1;
		if (x2 > maxX) maxX = x2;		if (y2 > maxY) maxY = y2;
		if (x2 < minX) minX = x2;		if (y2 < maxY) minY = y2;
	}
	public void clear(){
		xPoints.removeAllElements();
		yPoints.removeAllElements();
		
	}
	
	public void drawAxes(){}
	public String title = "No Title";
	public boolean axesYes;
	private Vector lines = new Vector(); 
	private Vector xPoints = new Vector();
	private Vector yPoints = new Vector();
	// CAREFULL HERE Vector is not type specific
	
	private double maxX;
	private double maxY;
	private double minX;
	private double minY;
	private Graphics offscreenGraphics;
	private Image offscreenImage;
	private Dimension offscreenSize;
	
	
}	
		
