import java.awt.*;
import java.awt.event.*;

public class BlueWallFollower extends Obj
	{
	//public int x, y;
	//public double dir;
	//public Color LightColor;
	public int debug;
	public double LOffset, ROffset, LWidth, RWidth, LRange, RRange, LLEdge, LREdge, RLEdge, RREdge;

	public BlueWallFollower()
		{
		super();
		LightColor = Color.brown;
		LOffset = -12.5;
		ROffset = 12.5;
		LWidth = 45;
		RWidth = 45;
		LRange = 150;
		RRange = 150;
		debug = 0;
		LREdge = LOffset-LWidth/2;
		LLEdge = LOffset+LWidth/2;
		RREdge = ROffset-RWidth/2;
		RLEdge = ROffset+RWidth/2;
		LLEdge = dirnorm(LLEdge);
		LREdge = dirnorm(LREdge);
		RLEdge = dirnorm(RLEdge);
		RREdge = dirnorm(RREdge);
		}

	public BlueWallFollower(int thex, int they)
		{
		super(thex, they);
		LightColor = Color.brown;
		LOffset = -12.5;
		ROffset = 12.5;
		LWidth = 45;
		RWidth = 45;
		LRange = 150;
		RRange = 150;
		debug = 0;
		LREdge = LOffset-LWidth/2;
		LLEdge = LOffset+LWidth/2;
		RREdge = ROffset-RWidth/2;
		RLEdge = ROffset+RWidth/2;
		LLEdge = dirnorm(LLEdge);
		LREdge = dirnorm(LREdge);
		RLEdge = dirnorm(RLEdge);
		RREdge = dirnorm(RREdge);
		}

	public void go(Obj array[], int count, int i)
		{
		boolean leftsensor = false;
		boolean rightsensor = false;
		for(int j = 0; j < count; j++)
			{
			if(j != i)
				{
				double directoffset = direction(array[j].getx(), array[j].gety(), x, y)-dir;
				directoffset = dirnorm(directoffset);
			/*	while(directoffset < 0)
					directoffset = directoffset+360;
				while(directoffset > 360)
					directoffset = directoffset-360;   */
				double distance = distance(array[j].getx(), array[j].gety(), x, y);
				//System.out.println("Direction of other: "+direction(array[j].getx(), array[j].gety(), x, y)+" My Dir: "+dir);
				//if(array[j].getLightColor() == Color.green)
				//	System.out.println("Direction Offset: " +directoffset);
				if(array[j].getLightColor() == Color.blue)
					{
					if(distance < LRange && distance > 20 && ((LLEdge > directoffset && directoffset >= 0) || (360 > directoffset && directoffset > LREdge)))
						leftsensor = true;
					if(distance < RRange && distance > 20 && ((RLEdge > directoffset && directoffset >= 0) || (360 > directoffset && directoffset > RREdge)))
						rightsensor = true;
					}
				}
			}

		if(leftsensor && !rightsensor)
			super.turn(-5);
		else if(!leftsensor && rightsensor)
			super.moveforward(5);
		else if(leftsensor && rightsensor)
			super.turn(-5);
		else if(!leftsensor && !rightsensor)
			{
			super.moveforward(5);
			//System.out.println("doing nothing "+debug);
			//debug++;
			//double rand = Math.random();
			//if(rand < .35)
			//	super.turn(8);
			//else if(rand >.4)
			//	super.turn(-8);
			//else
			//	super.moveforward(5);
			}
		}

	public void paint(Graphics g)
		{
		g.setColor(LightColor);
		g.fillOval(x, y, 7, 7);
		g.setColor(new Color(200, 200, 200));
		g.fillOval(x, y, 5, 5);
		g.setColor(Color.black);

		double rad = dir*Math.PI/180;
		//System.out.println("rads: "+rad+" cos(rads) = "+Math.cos(rad));
		g.drawLine(x+3, y+3, (int)(x+20*Math.cos(rad)+3), (int)(y-20*Math.sin(rad)+3));
		double rad1 = (dir+ROffset-RWidth/2)*Math.PI/180;//Right Sensor Left Line
		double rad2 = (dir+ROffset+RWidth/2)*Math.PI/180;//Right Sensor Right Line
		double rad3 = (dir+LOffset-LWidth/2)*Math.PI/180;//Left Sensor Left Line
		double rad4 = (dir+LOffset+LWidth/2)*Math.PI/180;//Left Sensor Right Line
		g.setColor(Color.yellow);
		g.drawLine(x+3, y+3, (int)(x+RRange*Math.cos(rad1)+3), (int)(y-RRange*Math.sin(rad1)+3));//Right Sensor
		g.drawLine(x+3, y+3, (int)(x+RRange*Math.cos(rad2)+3), (int)(y-RRange*Math.sin(rad2)+3));//Right Sensor
		g.setColor(Color.orange);
		g.drawLine(x+3, y+3, (int)(x+LRange*Math.cos(rad3)+3), (int)(y-LRange*Math.sin(rad3)+3));//Left Sensor
		g.drawLine(x+3, y+3, (int)(x+LRange*Math.cos(rad4)+3), (int)(y-LRange*Math.sin(rad4)+3));//Left Sensor
		}


	}