import java.awt.*;
import java.awt.event.*;

public class Tree
	{
	private double x1, x2, y1, y2;

	private int angle1, angle2;
	private double length1, length2;

	public Tree()
		{

		angle1 = -35;
		angle2 = 65;
		length1 = .65;
		length2 = .7;
		}

	public void paint(ScaledGraphics sg)
		{
		Turtle t = new Turtle(sg);
		t.moveTo(.5,.2);
		t.turnTo(90);
		drawTwig(t,.26666);
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

	public void drawTwig(Turtle t, double length)
		{
		if(length > .006666666)
			{
			t.draw(length);
			t.turn(angle1);
			drawTwig(t, length1*length);
			t.turn(angle2);
			drawTwig(t, length2*length);
			t.turn(-1*(angle1 +angle2));
			t.move(-length);
			}
		}
	}