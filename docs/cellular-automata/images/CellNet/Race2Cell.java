//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

//Race2 - Green beats red, red beats blue, blue beats green
public class Race2Cell extends Cell
	{
	public Color bg, green, red, blue;
	public int statecounter, redcrowd, greencrowd, bluecrowd;

	public Race2Cell()
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
		redcrowd = 6;
		greencrowd = 6;
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
			if(reds > greens && reds == 3)
				super.setNextState(red);
			if(greens > blues && greens == 3)
				super.setNextState(green);
			if(blues > reds && blues == 3)
				super.setNextState(blue);
			}
		else if(super.getState() == red)
			{
			if(reds <= greens || reds+greens >= redcrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == green)
			{
			if(greens <= blues || greens+blues >= greencrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == blue)
			{
			if(blues <= reds || blues+reds >= bluecrowd)
				super.setNextState(bg);
			}
		}
	}