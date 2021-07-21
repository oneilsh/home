//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class BrainCell extends Cell
	{
	public Color bg, black, red;
	public boolean previousfire;
	public int statecounter;

	public BrainCell()
		{
		super();
		black = Color.black;
		red = Color.red;
		bg = Color.gray;//
		super.setBackgroundColor(bg);
		super.setState(bg);
		super.setNextState(bg);
		previousfire = false;
		statecounter = 0;
		}

	public void clear()
		{
		super.setState(bg);
		super.setNextState(bg);
		previousfire = false;
		}

	public void randomizeLowHigh(int prob)
		{
		int fire = (int)(2*Math.random());
		if(fire == 0)
			super.setState(bg);
		else
			super.setState(black);
		previousfire = false;
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

/*	public Color getState()
		{
		if(previousfire)
			return Color.red;
		else
			return super.getState();
		}
*/
	public void workNow(int blacks, int reds, int greens, int blues, int nones, int whites)
		{
		//System.out.println(""+low+" : "+high);
		if(super.getState() == black)
			{
			super.setNextState(red);
			previousfire = true;
			}
		else
			{
			super.setNextState(bg);
			if(blacks == 2 && !previousfire)
				{
				super.setNextState(black);
				}
			previousfire = false;
			}
		}
	}