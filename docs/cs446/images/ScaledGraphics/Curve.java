import java.awt.*;
import java.awt.event.*;

public class Curve
	{
	private double x1, x2, y1, y2;


	public Curve( double cx1, double cx2, double cy1, double cy2)
		{

		x1 = cx1;
		x2 = cx2;
		y1 = cy1;
		y2 = cy2;

		}

	public void paint(ScaledGraphics sg)
		{
		sg.setColor(Color.blue);
		sg.setViewPort(x1,x2,y1,y2);
		sg.setWindow(-1.5,1.5,-1.5,1.5);
		//sg.drawLine(-1,-1,1,-1);
		//sg.drawLine(1,-1,1,1);
		//sg.drawLine(1,1,-1,1);
		//sg.drawLine(-1,1,-1,-1);
		double t = 0;
		double dt = 2*Math.PI/1000;
		double x = Math.cos(t)+ .2*Math.cos(9*t);
		double y = Math.sin(t)+ .2*Math.sin(9*t);
		for(int n = 0; n < 1000; n++)
			{
			t += dt;
			double xx = Math.cos(t)+ .2*Math.cos(9*t);
			double yy = Math.sin(t)+ .2*Math.sin(9*t);
			sg.drawLine(x,y,xx,yy);
			x = xx;
			y = yy;
		}
		}


	private int convert(String s)
			{
			try
				{
				return (new Integer(s)).intValue();
				}
			catch (Throwable t)
				{
				return 50;
				}
			}

	private double convertdouble(String s)
			{
			try
				{
				return (new Double(s)).doubleValue();
				}
			catch (Throwable t)
				{
				return .7;
				}
			}
	}