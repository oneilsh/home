//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class Rand4Cell extends Cell
	{
	public int statecounter, age;
	public Color bg, black, red;

	public Rand4Cell()
		{
		super();
		black = Color.black;
		red = Color.red;
		bg = Color.gray;//new Color(100,100,75);
		super.setBackgroundColor(bg);
		super.setState(bg);
		super.setNextState(bg);
		statecounter = 0;
		age = 0;
		}

	public void randomizeLowHigh(int prob)
		{
		int fire = (int)(100*Math.random());
		if(fire < prob)
			super.setState(bg);
		else
			super.setState(black);
		}

	public void switchState()
		{
		if(statecounter < 2)
			statecounter++;
		else
			statecounter = 0;
		if(statecounter == 0)
			super.setState(bg);
		else if(statecounter == 1)
			super.setState(black);
		else if(statecounter == 2)
			super.setState(red);
		}

	public void changecolor()
		{
		if(super.getState() == Color.black)
			super.setNextState(Color.red);
		else if(super.getState() == Color.red)
			super.setNextState(Color.green);
		else if(super.getState() == Color.green)
			super.setNextState(Color.blue);
		else if(super.getState() == Color.blue)
			super.setNextState(Color.red);
		}

	public void workNow(int blacks, int reds, int greens, int blues, int nones, int whites)
		{
		int random = (int)(100*Math.random());
		if(super.getState() != Color.gray)
			{
			if(random > 10*(8-nones)+20 || (8-nones) == 0)
				{
				super.setNextState(bg);
				age = 0;
				}
			else
				{
				if(age < 12 && age != 0)
					super.setNextState(Color.red);
				else if(age > 40)
					super.setNextState(Color.blue);
				else if(age != 0)
					super.setNextState(Color.green);
				//if((age%140 == 0 && age != 0) || age == 15)
				//	changecolor();
				age++;
				}
			}
		else
			{
			if(random < 10*(8-nones) && (8-nones) != 0)
				{
				super.setNextState(black);//new Color(255, 100, 100));
				age = 0;
				}
			}
		}

	public void clear()
		{
		super.setState(bg);
		super.setNextState(bg);
		age = 0;
		}
	}
