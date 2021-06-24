//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class Nueron extends Cell
	{
	public int low, high;

	public Nueron()
		{
		super();
		randomizeLowHigh();
		super.setBackgroundColor(Color.red);
		}

	public Nueron(boolean fire)
		{
		super(fire);
		randomizeLowHigh();
		super.setBackgroundColor(Color.red);
		}

	public void randomizeLowHigh()
		{
		low = (int)(4*Math.random()+1);
		high = (int)(4*Math.random()+5);
		}

	public void reward()
		{
		if(super.getState() == Color.black)
			{
			if(low > 1)	low--;
			if(high < 8) high++;
			}
		else
			{
			if(low < high)	low++;
			if(low < high) high--;
			}
		}

	public void punish()
		{
		if(super.getState() == Color.black)
			{
			if(low < high)	low++;
			if(low < high) high--;
			}
		else
			{
			if(low > 1)	low--;
			if(high < 8) high++;
			}
		}

	public void clear()
		{
		super.setState(Color.red);
		}

	public void workNow(int neighbors)
		{
		System.out.println(""+low+" : "+high);
		if(neighbors >= low && neighbors <= high)
			{
			super.setNextState(Color.black);
			}
		else
			{
			super.setNextState(Color.red);
			}
		}
	}