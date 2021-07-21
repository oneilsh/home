//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class LifeCell extends Cell
	{
	public int statecounter;
	public Color bg, black, red;
	public boolean done;

	public LifeCell()
		{
		super();
		black = Color.black;
		red = Color.red;
		bg = Color.gray;//new Color(100,100,75);
		super.setBackgroundColor(bg);
		super.setState(bg);
		super.setNextState(bg);
		statecounter = 0;
		done = false;
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
		if(super.getState() == Color.black || super.getState() == red)
			{
			//if((reds+blacks) == 1)//(blacks+reds != 2 && blacks+reds != 3)
			//	super.setNextState(bg);
			//else if(reds >0 || super.getState() == red)
			//	super.setNextState(red);
			//else
			//	super.setNextState(black);
			super.setNextState(super.getState());
			}
		else
			{
			int fire = (int)(8*Math.random());
			if((blacks+reds == 1) && fire == 1)// 3)
				{
				if(reds > 0)
					super.setNextState(red);
				else
					super.setNextState(black);
				}
			}
		}

	public void clear()
		{
		super.setState(bg);
		super.setNextState(bg);
		done = false;
		}
	}