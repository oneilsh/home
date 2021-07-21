//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class ObjectList
	{
	public Obj array[];
	public BoardCanvas bcanvas;
	int count;

	public ObjectList(BoardCanvas thebcanvas)
		{
		array = new Obj[1000];
		bcanvas = thebcanvas;
		count = 0;
		}

	public void clear()
		{
		count = 0;
		}

	public void deletelast()
		{
		if(count > 0)
			count--;
		}

	public void togglesenseview()
		{
		for(int i = 0; i < count; i++)
			array[i].togglesenseview();
		}

	public void add(int type, int xcoord, int ycoord)
		{
		if(type == 0)
			array[count] = new BlueLight(xcoord, ycoord);
		else if(type == 1)
			array[count] = new SimpleCar(xcoord, ycoord);
		else if(type == 2)
			array[count] = new FlockCar1(xcoord, ycoord);
		else if(type == 3)
			array[count] = new BlueWallFollower(xcoord, ycoord);
		else if(type == 4)
			array[count] = new LightOnCar(xcoord, ycoord);
		else if(type == 5)
			array[count] = new BlueGreenCar(xcoord, ycoord);
		else if(type == 6)
			array[count] = new RedAvoider(xcoord, ycoord);
		else if(type == 7)
			array[count] = new RedLight(xcoord, ycoord);
		else if(type == 8)
			array[count] = new GreenFollower(xcoord, ycoord);
		count++;
		}

	public void paint(Graphics g)
		{
		for(int i = 0; i < count; i++)
			array[i].paint(g);
		}

	public void computenext()
		{
		for(int i = 0; i < count; i++)
			//for(int j = 0; j < count; j++)
			//	if(j != i)
					array[i].go(array, count, i);
		}
	
	public void update()
		{
		for(int i = 0; i < count; i++)
			array[i].update();
		}

	}
