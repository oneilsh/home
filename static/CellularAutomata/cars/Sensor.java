import java.awt.*;
import java.awt.event.*;

public class Sensor
	{
	public double Offset, Width, Range, LEdge, REdge, dir;
	public double x, y;
	public Color Sees[];
	

	public Sensor()
		{
		Offset = Width = Range = LEdge = REdge = 0;
		}
	
	public Sensor(double theOffset, double theWidth, double theRange, Color[] theSees)
		{
		Offset = theOffset;
		Width = theWidth;
		Range = theRange;
		Sees = new Color[theSees.length];
		for(int i = 0; i < Sees.length; i++)
			Sees[i] = theSees[i];

		REdge = Offset-Width/2;
		LEdge = Offset+Width/2;

		LEdge = dirnorm(LEdge);
		REdge = dirnorm(REdge);
		}	
		
	boolean test(Obj other)
		{
		double directoffset = direction(other.getx(), other.gety(), x, y)-dir;
		directoffset = dirnorm(directoffset);
		double distance = distance(other.getx(), other.gety(), x, y);
		for(int i = 0; i < Sees.length; i++)
			{
			if(other.getLightColor() == Sees[i])
				{
				if(distance < Range && ((LEdge > directoffset && directoffset >= 0) || (360 > directoffset && directoffset > REdge)))
					return true;;
				}
			}
		return false;
		}
	
	public void updateXYDir(double theX, double theY, double theDir)
		{
		dir = theDir;
		x = theX;
		y = theY;
		}
	
	public void paint(Graphics g, Color theColor)
		{
		double rad1 = (dir+Offset-Width/2)*Math.PI/180;//Right Sensor Left Line
		double rad2 = (dir+Offset+Width/2)*Math.PI/180;//Right Sensor Right Line
		double arc1 = dirnorm(dir+Offset-Width/2);
		double arc2 = dirnorm(dir+Offset+Width/2);
		g.setColor(theColor);
		g.drawLine((int)(x+3), (int)(y+3), (int)(x+Range*Math.cos(rad1)+3), (int)(y-Range*Math.sin(rad1)+3));//Right Sensor
		g.drawLine((int)(x+3), (int)(y+3), (int)(x+Range*Math.cos(rad2)+3), (int)(y-Range*Math.sin(rad2)+3));//Right Sensor
		//System.out.println("Dir is: "+dir+"   Offset is: "+Offset+"   Width is: "+Width+"   Arc1 is : "+arc1+"   Arc2 is: "+arc2);
		g.drawArc((int)(x-Range), (int)(y-Range), (int)(Range*2), (int)(Range*2), (int)(0), (int)(359));
		}
		
		
		
	public double getOffset() {return Offset;}
	public void setOffset(double theOffset) {Offset = theOffset;}
	public double getWidth() {return Width;}
	public void setWidth(double theWidth) {Width = theWidth;}
	public double getRange() {return Range;}
	public void setRange(double theRange) {Range = theRange;}
	public double getLEdge() {return LEdge;}
	public void setLEdge(double theLEdge) {LEdge = theLEdge;}
	public double getREdge() {return REdge;}
	public void setREdge(double theREdge) {REdge = theREdge;}
	public Color[] getSees() {return (Color[])Sees.clone();}
	public void setSees(Color[] theSees) 
		{
		Sees = new Color[theSees.length];
		for(int i = 0; i < theSees.length; i++)
			Sees[i] = theSees[i];
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
	}
