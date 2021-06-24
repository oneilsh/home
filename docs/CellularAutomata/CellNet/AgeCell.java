//Shawn O'neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class AgeCell extends Cell
	{
	public int statecounter, age;
	public Color bg, black, red;

	public AgeCell()
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
		int fire = (int)(2*Math.random());
		if(fire == 0)
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

	public void workNow(int blacks, int reds, int greens, int blues, int nones, int whites)
		{
		int random = (int)(100*Math.random());
		if(super.getState() != bg)
			{
			if(random > (10*(8-nones)+22)-(int)(.5*age) || (8-nones) == 0)// || age > 9)
				super.setNextState(bg);
			else
				{
				age++;
				if(age < 4)
					super.setNextState(Color.red);
				else if(age > 7)
					super.setNextState(Color.blue);
				else
					super.setNextState(Color.green);
				//super.setNextState(new Color(150-(15*age), 80/*70-(7*age)*/,(15*age)));
				}
			}
		else
			{
			if(random < 10*(8-nones)-0 && (8-nones) != 0)
				{
				super.setNextState(new Color(255, 150, 150));
				age = 0;
				}
			}
		}

	public void clear()
		{
		super.setState(bg);
		super.setNextState(bg);
		}
	}