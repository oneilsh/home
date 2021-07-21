import java.awt.*;
import java.awt.event.*;

public class TStack
	{
	public double data[][][];
	public int top;

	public TStack()
		{
		data = new double[20][4][4];
		top = -1;
		}

	public void push(double matrix[][])
		{
		if(top<20)
			{
			top++;
			for(int x = 0; x < 4; x ++)
				for(int y = 0; y < 4; y++)
					{
					data[top][x][y] = matrix[x][y];
					}
			}
		}

	public double[][] pop()
		{
		if(top < 0)
			{
			double m[][] = new double[4][4];
			for(int x = 0; x < 3; x++)
				{
				m[x][x] = 1;
				}
			System.out.println("Popped a default.");
			return m;
			}
		return data[top--];
		}

	public void clear()
		{
		top = -1;
		}

	public double[][] peek()
		{
		if(top < 0)
			{
			double m[][] = new double[4][4];
			for(int x = 0; x < 3; x++)
				{
				m[x][x] = 1;
				}
			System.out.println("Peeked a default.");
			return m;
			}
		return data[top];
		}

	public void clearstack()
		{
		top = -1;
		}

	public boolean empty()
		{
		return(top<0);
		}
	}