package Things;

import java.awt.*;
import simthingy.Animator;
import simthingy.Properties;


public class Connector extends Thing
{
	public Connector(double x,double y ){
		xpos = x; ypos =y; xvel =0;yvel = 0; mass = MINMASS;
                xprev = xpos; yprev = ypos;
                xvprev = xvel; yvprev = yvel;
                xprevprev =xprev; yprevprev = yprev;
                xvprevprev =xvprev; yvprevprev = yvprev;
	}
	public Connector(double x,double y , double xv, double yv){
		xpos = x; ypos =y; xvel =xv;yvel = yv; mass = MINMASS;
                xprev = xpos; yprev = ypos;
                xvprev = xvel; yvprev = yvel;
                xprevprev =xprev; yprevprev = yprev;
                xvprevprev =xvprev; yvprevprev = yvprev;
	}
	public Connector(double x,double y , double xv, double yv,double m){
		xpos = x; ypos =y; xvel =xv;yvel = yv; mass = m;
                xprev = xpos; yprev = ypos;
                xvprev = xvel; yvprev = yvel;
                xprevprev =xprev; yprevprev = yprev;
                xvprevprev =xvprev; yvprevprev = yvprev;
	}

	public double alpha = Properties.connectorAlpha;

	public double xprev; public double yprev;
	public double xvprev; public double yvprev;
	public double xforceprev; public double yforceprev;

	public double xprevprev; public double yprevprev;
	public double xvprevprev; public double yvprevprev;
	public double xforceprevprev; public double yforceprevprev;

        public double xforce;
        public double yforce;
	public static double MINMASS = .001;

	public double velocity() {return Math.sqrt(xvel *xvel +yvel*yvel);}
	public boolean redFlag = false;

	//*********************************************************************
        public static Connector translate(double x, double y,double m){
           // only to be used to place point durring construction phase
           Connector c = new Connector(0,0,0,0,m);
            c.xpos += x; c.ypos +=y;
            c.xprev = c.xpos; c.yprev = c.ypos;
            c.xprevprev =c.xprev; c.yprevprev = c.yprev;

            return c;
        }
	//*********************************************************************
        public void rotate (double x, double y, double t){
           // only to be used to place point durring construction phase
           // rotates the connector about the point (x,y) counter clockwise by t degrees
           // theta is counter-clockwise rotation
            t = Math.PI/180.0 * t;  // deg to rad
            xpos = x + ( (xpos - x) *Math.cos(t)   + (ypos - y) *- Math.sin(t) );
            ypos = y + ( (xpos - x) * Math.sin(t) + (ypos - y) *  Math.cos(t) );

            xprev = xpos; yprev = ypos;
            xprevprev =xprev; yprevprev = yprev;
        }
	//*********************************************************************
        public static Connector rotate (Connector c, double t, double l,double m){
            // only to be used to create a point durring construction phase
            // rotate t degrees about connector c at length l
            t = Math.PI/180.0 * t;  // deg to rad
            double xpos = c.xpos + l *Math.cos(t);
            double ypos = c.ypos + l *Math.sin(t);

            Connector cc  = new Connector(xpos,ypos,0,0,m);

            return cc;
        }
	//*********************************************************************
	public void move(){
          double dt = Properties.deltaT;
          // modified euler
          xforce = alpha *xForce() + (1-alpha)*xforceprev;  // linear filter on force
          yforce = alpha *yForce() + (1-alpha)*yforceprev;  // translates to material flexibility

          xpos = xprev + xvel * dt + .5 * xforce/mass * dt * dt;
          ypos = yprev + yvel * dt + .5 * yforce/mass * dt * dt;

          xvel =xvprev + xforce/mass * dt;
          yvel =yvprev + yforce/mass * dt;


          redFlag();

          /*/ runge kuttta
         double xk1 = xforceprevprev ;
         double xk2 = xforceprev ;
         double xk3 = xforceprev ;
         double xk4 = xForce() ;
         double yk1 = yforceprevprev ;
         double yk2 = yforceprev ;
         double yk3 = yforceprev ;
         double yk4 = yForce() ;
          //xforce = (xforceprevprev + 4.*xforceprev +xForce() )/6.;
          //yforce = (yforceprevprev + 4.*yforceprev +yForce() )/6.;

          xpos = xprev + xvel * dt + (-xk1 + xk2 +xk3)/(mass*2) * dt * dt;
          ypos = yprev + yvel * dt + (-yk1 + yk2 +yk3)/(mass*2) * dt * dt;


          xvel =xvprev + (-xk1 +2*xk2 + 2*xk3 +xk4)/(4*mass) * dt;
          yvel =yvprev + (-yk1 +2*yk2 + 2*yk3 +yk4)/(4*mass) * dt;
          // */

          xprevprev = xprev ; yprevprev =yprev;
          xvprevprev = xvprev; yvprevprev = yvprev;
          xforceprevprev = xforceprev; yforceprevprev = yforceprev;

          xprev = xpos ; yprev =ypos;
          xvprev = xvel; yvprev = yvel;
          xforceprev = xForce(); yforceprev =yForce();

          zeroForce();

	}

	public void force(){
	}

        public void showThing(Animator a){
          Color c = a.offscreenGraphics.getColor();
          if (redFlag) {a.offscreenGraphics.setColor(Color.red);}
          a.offscreenGraphics.drawOval(  a.rescaleX(xpos)-2 ,a.rescaleY(ypos)-2,4,4);
          a.offscreenGraphics.setColor(c);
        }

	public boolean redFlag(){
		if( Math.sqrt(xForce() *xForce() +yForce() * yForce())*Properties.deltaT* Properties.deltaT >Properties.redFlagConst) {
			redFlag = true;
			Properties.redFlag = false;
		//	Properties.deltaT /=64;
		//	Properties.goFlag = false;
                System.out.print("Red Flag \n");
			return true;
		}else {
			redFlag = false;

			return false;
		}
	}
}
