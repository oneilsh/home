//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class BlankCell extends Cell
	{
	public Color bg, black;
	public int statecounter;

	public BlankCell()
		{
		super();
		black = Color.black;
		bg = Color.gray;
		statecounter = 0;
		super.setBackgroundColor(bg);
		super.setState(bg);
		super.setNextState(bg);
		}

	public BlankCell(boolean fire)
		{
		super(fire);
		super.setBackgroundColor(bg);
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
			super.setState(Color.black);
		else if(statecounter == 2)
			super.setState(Color.red);
		}

	public void randomizeLowHigh(int prob)
		{
		int x = (int)(100*Math.random());
		super.setState(bg);
		if(x < prob)
			{
			int fire = (int)(2*Math.random());
			if(fire == 0)
				super.setState(bg);
			else
				super.setState(black);
			}
		}
	}