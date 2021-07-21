import java.awt.*;
import java.awt.event.*;

public class Curve
	{
	private double x1, x2, y1, y2;


	public Curve()
		{

		}

	public void paint(ScaledGraphics sg)
		{
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