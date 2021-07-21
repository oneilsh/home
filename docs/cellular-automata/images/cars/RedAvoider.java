import java.awt.*;
import java.awt.event.*;

public class RedAvoider extends Obj
	{
	//public int x, y;
	//public double dir;
	//public Color LightColor;
	public Sensor blueLeft, blueRight, redLeft, redRight;
	public double size = 1.0;

	public RedAvoider(int thex, int they)
		{
		super(thex, they);
		LightColor = Color.orange;
		desc = "Orange car which is attracted to Blue but avoids Red.";
		Color[] colorsblue = {Color.blue};
		Color[] colorsred = {Color.red};
		blueLeft = new Sensor(-8, 25, 400*size, colorsblue);
		blueRight = new Sensor(8, 25, 400*size, colorsblue);

		redLeft = new Sensor(-20, 65, 75*size, colorsred);
		redRight = new Sensor(20, 65, 75*size, colorsred);
		blueLeft.updateXYDir(x,y,dir);
		blueRight.updateXYDir(x,y,dir);
		redLeft.updateXYDir(x,y,dir);
		redRight.updateXYDir(x,y,dir);
		}

	public void go(Obj array[], int count, int i)
		{
		boolean blueleft = false;
		boolean blueright = false;
		boolean redleft = false;
		boolean redright = false;
		for(int j = 0; j < count; j++)
			{
			if(j != i)
				{
				if(redLeft.test(array[j])) redleft = true;
				if(redRight.test(array[j])) redright = true;
				if(blueLeft.test(array[j])) blueleft = true;
				if(blueRight.test(array[j])) blueright = true;
				
				}
			}
			
		if(!redright && !redleft)
			{
			if(blueleft && !blueright)
				super.turn(-5);
			else if(!blueleft && blueright)
				super.turn(5);
			else if(blueleft && blueright)
				super.moveforward(5);
			else if(!blueleft && !blueright)
				{
				//System.out.println("doing nothing "+debug);
				//debug++;
				double rand = Math.random();
				if(rand < .2)
					super.turn(8);
				else if(rand >.5)
					super.turn(-8);
				else
					super.moveforward(5);
				}
			}
		else
			{
			if(redleft && !redright)
				super.turn(25);
			else if(!redleft && redright)
				super.turn(-25);
			else if(redleft && redright)
				{
				super.turn(35);
				super.moveforward(-1);
				}
			else if(!redleft && !redright)
				{
				//System.out.println("doing nothing "+debug);
				//debug++;
				//double rand = Math.random();
				//if(rand < .1)
				//	super.turn(8);				//this is irrelevant, probably bad coding
				//else if(rand >.7)
				//	super.turn(-8);
				//else
				//	super.moveforward(5);
				}
			}
		blueLeft.updateXYDir(x,y,dir);
		blueRight.updateXYDir(x,y,dir);
		redLeft.updateXYDir(x,y,dir);
		redRight.updateXYDir(x,y,dir);
		}

	public void paint(Graphics g)
		{
		g.setColor(LightColor);
		g.fillOval((int)x, (int)y, 7, 7);
		g.setColor(new Color(200, 200, 200));
		g.fillOval((int)x, (int)y, 5, 5);
		g.setColor(Color.black);

		double rad = dir*Math.PI/180;
		//System.out.println("rads: "+rad+" cos(rads) = "+Math.cos(rad));
		g.drawLine((int)(x+3), (int)(y+3), (int)(x+20*Math.cos(rad)*size+3), (int)(y-20*Math.sin(rad)*size+3));
		if(senseview)
			{
			redLeft.paint(g, Color.pink);
			redRight.paint(g, Color.red);
			blueLeft.paint(g, Color.blue);
			blueRight.paint(g, Color.blue);
			}
		}


	}
