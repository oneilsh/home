//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class Rand2Cell extends Cell
	{
	public int statecounter;
	public Color bg, black, red;

	public Rand2Cell()
		{
		super();
		black = Color.black;
		red = Color.red;
		bg = Color.gray;//new Color(100,100,75);
		super.setBackgroundColor(bg);
		super.setState(bg);
		super.setNextState(bg);
		statecounter = 0;
		}

	public void randomizeLowHigh(int prob)
		{
		int fire = (int)(3*Math.random());
		if(fire == 0)
			super.setState(bg);
		else if(fire == 1)
			super.setState(black);
		else
			super.setState(red);
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
		if(super.getState() == black)
			{
			if(random > 12*blacks+20 || blacks == 0)
				super.setNextState(bg);
			}
		else if(super.getState() == red)
			{
			if(random > 12*reds+20 || reds == 0)
				super.setNextState(bg);
			}
		else
			{
			if(random < 10*(blacks+reds)-0 && blacks+reds != 0)
				if(blacks > reds)
					super.setNextState(black);
				else if(reds > blacks)
					super.setNextState(red);
			}
		}

	public void clear()
		{
		super.setState(bg);
		super.setNextState(bg);
		}
	}