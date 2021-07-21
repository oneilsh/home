//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

//FiveRace - white beats blue beats black beats red beats green beats white
public class FiveRace extends Cell
	{
	public Color bg, white, blue, black, red, green;
	public int statecounter, whitecrowd, bluecrowd, blackcrowd, redcrowd, greencrowd;

	public FiveRace()
		{
		super();
		green = Color.green;
		red = Color.red;
		blue = Color.blue;
		white = Color.white;
		black = Color.black;
		bg = Color.gray;//new Color(30, 30, 30);
		super.setBackgroundColor(bg);
		super.setState(bg);
		super.setNextState(bg);
		statecounter = 0;
		redcrowd = 6;
		greencrowd = 6;
		bluecrowd = 6;
		whitecrowd = 6;
		blackcrowd = 6;
		}

	public void clear()
		{
		super.setState(bg);
		super.setNextState(bg);
		}

	public void randomizeLowHigh(int prob)
		{
		int fire = (int)(6*Math.random());
		if(fire == 0)
			super.setState(white);
		else if(fire == 1)
			super.setState(blue);
		else if(fire == 2)
			super.setState(black);
		else if(fire == 3)
			super.setState(red);	
		else if(fire == 4)
			super.setState(green);
		else
			super.setState(bg);
		}

	public void switchState()
		{
		if(statecounter < 5)
			statecounter++;
		else
			statecounter = 0;
		if(statecounter == 0)
			super.setState(bg);
		else if(statecounter == 1)
			super.setState(white);
		else if(statecounter == 2)
			super.setState(blue);
		else if(statecounter == 3)
			super.setState(black);	
		else if(statecounter == 4)
			super.setState(red);	
		else if(statecounter == 5)
			super.setState(green);
		}

	public void act1()
		{
		//redcrowd++;
		//greencrowd--;
		}

	public void act2()
		{
		//redcrowd--;
		//greencrowd++;
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
			if(blues > whites && blues == 3)// tail > head && tail == 3
				super.setNextState(blue);
			if(whites > blacks && whites == 3)
				super.setNextState(white);
			if(blacks > reds + blues && blacks == 3)
				super.setNextState(black);	
			if(reds > greens && reds == 3)
				super.setNextState(red);	
			if(greens > blacks && greens == 3)
				super.setNextState(green);
	
			/*if(whites > blues && whites == 3)// tail > head && tail == 3
				super.setNextState(white);
			if(blues > blacks && blues == 3)
				super.setNextState(blue);
			if(blacks > reds && blacks == 3)
				super.setNextState(black);	
			if(reds > greens && reds == 3)
				super.setNextState(red);	
			if(greens > whites && greens == 3)
				super.setNextState(green);*/
			}
		else if(super.getState() == blue)
			{
			if(blues <= whites || blues+whites >= bluecrowd) //tail <= head || tail+head >=tailcrowd
				super.setNextState(bg);
			}
		else if(super.getState() == white)
			{
			if(whites <= blacks || whites+blacks >= whitecrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == black)
			{
			if(blacks <= reds+blues || blacks+reds+blues >= blackcrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == red)
			{
			if(reds <= greens || reds+greens >= redcrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == green)
			{
			if(greens <= blacks || greens+blacks >= greencrowd)
				super.setNextState(bg);
			}
			
		/*else if(super.getState() == white)
			{
			if(whites <= blues || whites+blues >= whitecrowd) //tail <= head || tail+head >=tailcrowd
				super.setNextState(bg);
			}
		else if(super.getState() == blue)
			{
			if(blues <= blacks || blues+blacks >= bluecrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == black)
			{
			if(blacks <= reds || blacks+reds >= blackcrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == red)
			{
			if(reds <= greens || reds+greens >= redcrowd)
				super.setNextState(bg);
			}
		else if(super.getState() == green)
			{
			if(greens <= whites || greens+whites >= greencrowd)
				super.setNextState(bg);
			}*/
		}
	}