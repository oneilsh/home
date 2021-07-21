//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class Obj
	{
	public double x, y, nextx, nexty;
	public boolean senseview;
	public double dir, nextdir;
	public Color LightColor;
	public String desc = "Generic Object Description.";

	public Obj()
		{
		x = y = nextx = nexty = 50;
		dir = nextdir = 0;
		senseview = false;
		LightColor = Color.black;
		}

	public Obj(double thex, double they)
		{
		x = nextx = thex;
		y = nexty = they;
		dir = nextdir = 45;
		senseview = false;
		LightColor = Color.black;
		}

	public void go(Obj array[], int count, int i)
		{
		}
	
	public void update()
		{
		x = nextx; 
		y = nexty;
		dir = nextdir;
		}
	
	public String getDesc()
		{
		return desc;
		}

	public void paint(Graphics g)
		{
		g.setColor(LightColor);
		g.fillOval((int)x, (int)y, 5, 5);
		}

	public void togglesenseview()
		{
		senseview = !senseview;
		}

	public double getx()
		{
		return x;
		}

	public double gety()
		{
		return y;
		}

	public double getdir()
		{
		return dir;
		}

	public Color getLightColor()
		{
		return LightColor;
		}

	public void setLightColor(Color theColor)
		{
		LightColor = theColor;
		}

	public void turn(double degrees)
		{
		nextdir = dir + degrees;
		nextdir = dirnorm(nextdir);
		}

	public double distance(double x1, double y1, double x2, double y2)
		{
		//System.out.println("y1: " + y1+ " y2: "+ y2+ " x1 " +x1+" x2: "+x2);
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		}

	public double dirnorm(double a)
		{
		while(a < 0)
			a = a + 360;
		while(a > 360)
			a = a - 360;
		return a;
		}

	public double direction(double x, double y, double a, double b)
		{
		double ret = -99999;
		//System.out.println("x: " + x+ " y: "+ y+ " a: " +a+" b: "+b);
		if(a == x)
			{
			if(y<b)
				ret = 90;
			else
				ret = 270;
			}
		else if(y == b)
			{
			if(x<a)
				ret = 180;
			else
				ret = 0;
			}
		else
			{
			if(x<a && y<b)//second quad
				{
				//System.out.println("incidence from x axis in 2nd: "+180/Math.PI*Math.atan((b-y)/(a-x)));
				ret = (90+90-180/Math.PI*Math.atan((b-y)/(a-x)));
				}
			else if(x>a && y <b)//first quad
				{
				//System.out.println("incidence from x axis in 1st: "+(-180/Math.PI*Math.atan((b-y)/(a-x))));
				ret = (-180/Math.PI*Math.atan((b-y)/(a-x)));
				}
			else if(x>a && y>b)//fourth quad
				{
				//System.out.println("incidence from x axis in 4th: "+(180/Math.PI*Math.atan((b-y)/(a-x))));
				ret = (270+90-180/Math.PI*Math.atan((b-y)/(a-x)));
				}
			else if(x<a && y>b)//third quad
				{
				//System.out.println("incidence from x axis in 3rd: "+(-180/Math.PI*Math.atan((b-y)/(a-x))));
				ret = (180-180/Math.PI*Math.atan((b-y)/(a-x)));
				}
			}
		return ret;
		}

	public void moveforward(double dist)
		{
		double rad = dir*Math.PI/180;
		nexty = (y-dist*Math.sin(rad));
		nextx = (x+dist*Math.cos(rad));
		if(x < 0)							//The first set of lines here effectively makes the board a torus, but
			//x = 790 + x;					//the sensors wont wrap around, so its flawed
			nextx = 0;
		else if(x > 790)
			//x = x - 790;
			nextx = 790;
		if(y < 0)
			//y = 630 + y;
			nexty = 0;
		else if(y > 630)
			//y = 630 - y;
			nexty = 630;
		}


	}
