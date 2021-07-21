//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

//Race 3 - Blue is very fast growing food, red and green eat it to survive
public class Race3Cell extends Cell
	{
	public Color bg, green, red, blue;
	public int statecounter, redcrowd, greencrowd, bluecrowd;

	public Race3Cell()
		{
		super();
		green = Color.green;
		red = Color.red;
		blue = Color.blue;
		bg = Color.gray;//new Color(30, 30, 30);
		super.setBackgroundColor(bg);
		super.setState(bg);
		super.setNextState(bg);
		statecounter = 0;
		redcrowd = 4;
		greencrowd = 4;
		bluecrowd = 6;
		}

	public void clear()
		{
		super.setState(bg);
		super.setNextState(bg);
		}

	public void randomizeLowHigh(int prob)
		{
		int fire = (int)(4*Math.random());
		if(fire == 0)
			super.setState(red);
		else if(fire == 1)
			super.setState(green);
		else if(fire == 2)
			super.setState(blue);
		else
			super.setState(bg);
		}

	public void switchState()
		{
		if(statecounter < 3)
			statecounter++;
		else
			statecounter = 0;
		if(statecounter == 0)
			super.setState(bg);
		else if(statecounter == 1)
			super.setState(green);
		else if(statecounter == 2)
			super.setState(red);
		else if(statecounter == 3)
			super.setState(blue);
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
			if(blues > 1)
				super.setNextState(blue);
			else
				{
				if(reds > greens && reds == 3 && blues >= 1)
					super.setNextState(red);
				else if(greens > reds && greens == 3 && blues >= 1)
					super.setNextState(green);
				}
			}
		else if(super.getState() == red)
			{
			if(reds <= greens || blues <2)//reds+greens >= redcrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == green)
			{
			if(greens <= reds || blues <2)//reds+greens >= greencrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == blue)
			{
			if(reds > 2 && reds > greens)
				super.setNextState(red);
			else if(greens > 2 && greens > reds)
				super.setNextState(green);
			}
		}
	}