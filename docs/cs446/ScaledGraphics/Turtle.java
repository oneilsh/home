import java.awt.*;
import java.awt.event.*;

public class Turtle
	{
	private double turtlex, turtley, turtleAngle;
	private double xComponent, yComponent;
	private ScaledGraphics sg;

	public Turtle(ScaledGraphics sgg)
		{
		sg = sgg;
		turtley = 0;
		turtlex = 0;
		turtleAngle = 0;
		xComponent = 1;
		yComponent = 0;
		}

	public void moveTo(double x, double y)
		{
		turtlex = x;
		turtley = y;
		}

	public void drawTo(double x, double y)
		{
		sg.drawLine(turtlex, turtley, x, y);
		moveTo(x,y);
		}

	public void move(double distance)
		{
		turtlex += distance*xComponent;
		turtley += distance*yComponent;
		}

	public void draw(double distance)
		{
		double x = turtlex;
		double y = turtley;
		turtlex += distance*xComponent;
		turtley += distance*yComponent;
		sg.drawLine(turtlex, turtley, x, y);
		}

	public void turnTo(double angle)
		{
		turtleAngle = (Math.PI/180)*angle;
		xComponent = Math.cos(turtleAngle);
		yComponent = Math.sin(turtleAngle);
		}

	public void turn(double angle)
		{
		turtleAngle += (Math.PI/180)*angle;
		while(turtleAngle > Math.PI*2)
			turtleAngle = turtleAngle - Math.PI*2;
		while(turtleAngle < 0)
			turtleAngle = turtleAngle + Math.PI*2;
		xComponent = Math.cos(turtleAngle);
		yComponent = Math.sin(turtleAngle);
		}
	}