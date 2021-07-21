//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class RaceCell extends Cell
	{
	public Color bg, green, red;
	public int statecounter, redcrowd, greencrowd;

	public RaceCell()
		{
		super();
		green = Color.green;
		red = Color.red;
		bg = Color.gray;//new Color(50, 50, 85);
		super.setBackgroundColor(bg);
		super.setState(bg);
		super.setNextState(bg);
		statecounter = 0;
		redcrowd = 5;
		greencrowd = 5;
		}

	public void clear()
		{
		super.setState(bg);
		super.setNextState(bg);
		}

	public void randomizeLowHigh(int prob)
		{
		int fire = (int)(3*Math.random());
		if(fire == 0)
			super.setState(red);
		else if(fire == 1)
			super.setState(green);
		else
			super.setState(bg);
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
			super.setState(green);
		else if(statecounter == 2)
			super.setState(red);
		}

	public void act1()
		{
		redcrowd++;
		greencrowd--;
		}

	public void act2()
		{
		redcrowd--;
		greencrowd++;
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
		if(super.getState() == bg)
			{
			if(greens > 3 && reds > 1)
				super.setNextState(red);
			else if(reds < 5)
				super.setNextState(green);
			}
		else if(super.getState() == red)
			{
			if(greens < 3 )
				super.setNextState(bg);
			}
		else if(super.getState() == green)
			{
			if(reds > 0)
				super.setNextState(bg);
			}
		}
	}
