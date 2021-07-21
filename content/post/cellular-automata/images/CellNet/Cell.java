//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class Cell
	{
	public boolean firing, nextfire;
	public Color backgroundColor, state, nextState;

	public Cell()
		{
		backgroundColor = Color.black;
		state = backgroundColor;
		nextState = state;
		}

	public Cell(boolean fire)
		{
		backgroundColor = Color.black;
		state = backgroundColor;
		nextState = state;
		}

	public void setBackgroundColor(Color theColor)
		{
		backgroundColor = theColor;
		}

	public Color getBackgroundColor()
		{
		return backgroundColor;
		}

	public void setState(Color theColor)
		{
		state = theColor;
		}

	public Color getState()
		{
		return state;
		}

	public void setNextState(Color nextstate)
		{
		nextState = nextstate;
		}

	public Color getNextState()
		{
		return nextState;
		}

	public void setToNextState()
		{
		state = nextState;
		}

	public void switchState()
		{
		}

	public void workNow(int blacks, int reds, int greens, int blues, int nones, int whites)
		{
		}

	public void act1()
		{
		}

	public void act2()
		{
		}

	public void randomizeLowHigh(int prob)
		{
		}

	public void clear()
		{
		}
	}