import java.awt.*;
import java.awt.event.*;

public class RedLight extends Obj
	{
	//public int x, y, dir;
	//public Color LightColor;
	public Sensor sensor1, sensor2;
	public double size = 1.0;

	public RedLight(int thex, int they)
		{
		super(thex, they);
		LightColor = Color.red;
		desc = "Red light which turns on when White is near, off when Orange is near.";
		Color[] colors1 = {Color.orange};
		Color[] colors2 = {Color.white};
		sensor1 = new Sensor(0, 365, 40*size, colors1);
		sensor2 = new Sensor(0, 365, 40*size, colors2);
		sensor1.updateXYDir(x,y,dir);
		sensor2.updateXYDir(x,y,dir);
		}

	public void go(Obj array[], int count, int i)
		{
		for(int j = 0; j < count; j++)
			{
				if(j != i)
				{
				if(sensor1.test(array[j])) LightColor = Color.black;
				if(sensor2.test(array[j])) LightColor = Color.red;
				}
			}
		}

	public void paint(Graphics g)
		{
		g.setColor(LightColor);
		g.fillOval((int)x, (int)y, 7, 7);
		g.setColor(new Color(200, 255, 200));
		g.fillOval((int)x, (int)y, 5, 5);
		if(senseview)
			{
			sensor1.paint(g, Color.red);
			}
		}


	}
