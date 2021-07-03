import java.awt.*;
import java.awt.event.*;

public class House
	{
	private double x1, x2, y1, y2;


	public House(double cx1, double cx2, double cy1, double cy2)
		{

		x1 = cx1;
		x2 = cx2;
		y1 = cy1;
		y2 = cy2;

		}

	public void paint(ScaledGraphics sg)
		{
		sg.setColor(Color.black);
		sg.setViewPort(x1,x2,y1,y2);
		sg.setWindow(5,45,5,35);
		sg.drawLine(5,5,45,5);
		sg.drawLine(5,5,5,20);
		sg.drawLine(45,5,45,20);
		sg.drawLine(45,20,5,20);
		sg.drawLine(25,35,45,20);
		sg.drawLine(25,35,5,20);
		sg.drawLine(10,10,10,15);
		sg.drawLine(10,15,20,15);
		sg.drawLine(20,15,20,10);
		sg.drawLine(20,10,10,10);
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