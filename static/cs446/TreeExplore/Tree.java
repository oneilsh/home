//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class Tree
	{
	private double x1, x2, y1, y2;

	private double angle1, angle2, angle3, angle4, branches;
	private double length1, length2, length3, length4;

	public Tree( double ang1, double ang2, double ang3, double ang4, double len1, double len2, double len3, double len4)
		{

		double x1 = 0;
		double x2 = 1;
		double y1 = 0;
		double y2 = 1;
		branches = 2;
		angle1 = ang1;
		angle2 = ang2;
		angle3 = ang3;
		angle4 = ang4;
		length1 = len1;
		length2 = len2;
		length3 = len3;
		length4 = len4;
		}

	public void paint(ScaledGraphics sg)
		{
		//System.out.println("Tree Painting");
		sg.setColor(Color.green);
		//sg.setViewPort(x1, x2, y1, y2);
		sg.setWindow(-30,30,-30,30);
		//sg.drawLine(-15,-4.999,15,-4.999);
		//sg.drawLine(15,-5,15,25);
		//sg.drawLine(15,25,-15,25);
		//sg.drawLine(-15,25,-15,-5);
		Turtle t = new Turtle(sg);
		t.moveTo(0,0);
		t.turnTo(90);
		drawTwig(t,8);
		}

	public void setBranches(int i)
		{
		branches = i;
		}

	public void setStats(double ang1, double ang2, double ang3, double ang4, double len1, double len2, double len3, double len4)
		{
		angle1 = ang1;
		angle2 = ang2;
		angle3 = ang3;
		angle4 = ang4;
		length1 = len1;
		length2 = len2;
		length3 = len3;
		length4 = len4;
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
		if(branches == 1)
			{
			if(length > .2)
				{
				t.draw(length);
				t.turn(angle1);
				drawTwig(t, length1*length);
				t.turn(-1*(angle1));
				t.move(-length);
				}
			}

		else if(branches == 2)
			{
			if(length > .2)
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

		else if(branches == 3)
			{
			if(length > .2)
				{
				t.draw(length);
				t.turn(angle1);
				drawTwig(t, length1*length);
				t.turn(angle2);
				drawTwig(t, length2*length);
				t.turn(angle3);
				drawTwig(t, length3*length);
				t.turn(-1*(angle1 +angle2+angle3));
				t.move(-length);
				}
			}

		else if(branches == 4)
			{
			if(length > .2)
				{
				t.draw(length);
				t.turn(angle1);
				drawTwig(t, length1*length);
				t.turn(angle2);
				drawTwig(t, length2*length);
				t.turn(angle3);
				drawTwig(t, length3*length);
				t.turn(angle4);
				drawTwig(t, length4*length);
				t.turn(-1*(angle1 +angle2+angle3+angle4));
				t.move(-length);
				}
			}
		}
	}