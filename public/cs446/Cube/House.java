import java.awt.*;
import java.awt.event.*;

public class House
	{
	private double x1, x2, y1, y2;


	public House()
		{

		}

	public void paint(ScaledGraphics sg)
		{
		sg.drawLine(.1,.1,.9,.1);
		sg.drawLine(.9,.1,.9,.6);
		sg.drawLine(.9,.6,.1,.6);
		sg.drawLine(.1,.6,.1,.1);
		sg.drawLine(.1,.6,.5,.9);
		sg.drawLine(.5,.9,.9,.6);
		sg.drawLine(.2,.1,.2,.3);
		sg.drawLine(.2,.3,.3,.3);
		sg.drawLine(.3,.3,.3,.1);
		sg.drawLine(.6,.3,.8,.3);
		sg.drawLine(.8,.3,.8,.4);
		sg.drawLine(.8,.4,.6,.4);
		sg.drawLine(.6,.4,.6,.3);
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