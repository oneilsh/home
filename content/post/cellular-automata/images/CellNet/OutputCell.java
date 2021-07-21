//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class OutputCell extends Cell
	{
	public int low, high;

	public OutputCell()
		{
		super();
		setLowHigh();
		super.setBackgroundColor(Color.gray);
		}

	public OutputCell(boolean fire, int thelow, int thehigh)
		{
		super(fire);
		low = thelow;
		high = thehigh;
		super.setBackgroundColor(Color.gray);
		}

	public void setLowHigh()
		{
		low = 3;
		high = 6;
		}

	public void workNow(int neighbors)
		{
		if(neighbors >= low && neighbors <= high)
			{
			super.setNextState(Color.black);
			}
		else
			{
			super.setNextState(Color.gray);
			}
		}
	}