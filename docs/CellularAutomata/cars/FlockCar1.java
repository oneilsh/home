import java.awt.*;
import java.awt.event.*;

public class FlockCar1 extends Obj
	{
	//public int x, y;
	//public double dir;
	//public Color LightColor;
	public int debug;
	public Sensor leftSensor, rightSensor, closeSensor, farSensor, blueLeftSensor, blueRightSensor;
	public double size = .6;
	public boolean side = false; //if side is true, it will try to stay on the right


	public FlockCar1(int thex, int they)
		{
		super(thex, they);
		LightColor = Color.green;
		desc = "Green car which attempts to flock with other Green cars.";
		Color[] colors = {Color.green};
		leftSensor = new Sensor(-25, 80, 200*size, colors);
		rightSensor = new Sensor(25, 80, 200*size, colors);
		closeSensor = new Sensor(0, 130, 50*size, colors);
		double rand = Math.random();
		if(rand > .5) side = true;
		else side = true;
		farSensor = new Sensor(0, 30, 100*size, colors);
		
		Color[] colors2 = {Color.blue};
		blueRightSensor = new Sensor(-12.5, 45, 350*size, colors2);
		blueLeftSensor = new Sensor(12.5, 45, 350*size, colors2);
		leftSensor.updateXYDir(x, y, dir);
		rightSensor.updateXYDir(x, y, dir);
		closeSensor.updateXYDir(x, y, dir);
		farSensor.updateXYDir(x, y, dir);
		blueLeftSensor.updateXYDir(x, y, dir);
		blueRightSensor.updateXYDir(x, y, dir);
		}

	public void go(Obj array[], int count, int i)
		{
		boolean leftsensor = false;
		boolean rightsensor = false;
		boolean closesensor = false;
		boolean farsensor = false;
		boolean blueleftsensor = false;
		boolean bluerightsensor = false;
		for(int j = 0; j < count; j++)
			{
			if(j != i)
				{
				if(leftSensor.test(array[j]))leftsensor = true;
				if(rightSensor.test(array[j]))rightsensor = true;
				if(closeSensor.test(array[j]))closesensor = true;
				if(farSensor.test(array[j]))farsensor = true;
				if(blueLeftSensor.test(array[j]))blueleftsensor = true;
				if(blueRightSensor.test(array[j]))bluerightsensor = true;
				}
			}


		if(closesensor)
			{
			if(leftsensor && rightsensor)
				super.moveforward(1.5*size);
			}
		else if(!closesensor && farsensor) // within its correct range, it is either a right follower (side) or a left
			{
			if(leftsensor && rightsensor)
				{
				if(side)super.turn(-5);
				else super.turn(5);
				}
			else if(leftsensor && !rightsensor)
				{
				if(side) super.moveforward(3*size);
				else super.turn(5);
				}
			else if(!leftsensor && rightsensor)
				{
				if(side) super.turn(-5);
				else super.moveforward(3*size);
				}
			else if(!leftsensor && !rightsensor)
				{
				if(side) {super.moveforward(3*size); super.turn(5);}
				else {super.moveforward(3*size); super.turn(-5);}
				}
			}
		else if(!closesensor && !farsensor)
			{
			if(leftsensor && !rightsensor)
				super.turn(-5);
			else if(!leftsensor && rightsensor)
				super.turn(5);
			else if(leftsensor && rightsensor)
				super.moveforward(4.5*size);
			else if(!leftsensor && !rightsensor)
				{
				if(blueleftsensor && bluerightsensor)
					super.moveforward(3*size);
				else if(blueleftsensor && !bluerightsensor)
					super.turn(5);
				else if(!blueleftsensor && bluerightsensor)
					super.turn(-5);
				else {super.moveforward(3*size); super.turn(4);}
				}
			}
		leftSensor.updateXYDir(x, y, dir);
		rightSensor.updateXYDir(x, y, dir);
		closeSensor.updateXYDir(x, y, dir);
		farSensor.updateXYDir(x, y, dir);
		blueLeftSensor.updateXYDir(x, y, dir);
		blueRightSensor.updateXYDir(x, y, dir);
		}

	public void paint(Graphics g)
		{
		g.setColor(LightColor);
		g.fillOval((int)x, (int)y, 7, 7);
		g.setColor(new Color(200, 200, 200));
		g.fillOval((int)x, (int)y, 5, 5);
		if(side) g.setColor(Color.black);
		else g.setColor(Color.red);

		double rad = dir*Math.PI/180;
		//System.out.println("rads: "+rad+" cos(rads) = "+Math.cos(rad));
		g.drawLine((int)(x+3), (int)(y+3), (int)(x+20*Math.cos(rad)*size+3), (int)(y-20*Math.sin(rad)*size+3));
		if(senseview)
			{
			leftSensor.paint(g, Color.yellow);
			rightSensor.paint(g, Color.orange);
			closeSensor.paint(g, Color.red);
			farSensor.paint(g, Color.pink);
			blueLeftSensor.paint(g, Color.blue);
			blueRightSensor.paint(g, Color.blue);
			}
		}


	}
