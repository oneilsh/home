import java.awt.*;
import java.awt.event.*;

public class Board
	{
	private int howMany, size;
	private int rows[];
	
	public Board(int s)
		{
		size = s;
		howMany = 0;
		rows = new int[size];
		}
		
	public boolean full()
		{
		return howMany == size;
		}
		
	public void paint(Graphics g, Color color)
		{
		for(int l = 1; l < size+2; l++)
			{
			g.drawLine(20*l, 20, 20*l, 20 + 20*size);	
			g.drawLine(20, 20*l, 20 + 20*size, 20*l);
			}
		g.setColor(color);
		for(int c = 0; c < howMany; c++)
			g.fillOval(20*c+2+20, 20*rows[c]+2+20, 15, 15);
		}
		
	public Board makeAFullerBoard(int row)
		{
		if(full())
			return null;
		if(ewMatch(row))
			return null;
		if(diagMatch(row))
			return null;
		Board b = new Board(size);
		b.howMany = howMany +1;
		for(int c = 0; c < howMany; c++)
			b.rows[c] = rows[c];
		b.rows[howMany] = row;
		return b;
		}
		
	public Board reverse()
		{
		if(!full()) return null;
		Board b = new Board(size);
		b.howMany = howMany;
		for(int c = 0, cc = howMany-1; c<howMany; c++, cc--)
			b.rows[cc] = rows[c];
		return b; 
		}
		
	public Board upsideDown()
		{
		if(!full()) return null;
		Board b = new Board(size);
		b.howMany = howMany;
		for(int c = 0; c<howMany; c++)
			b.rows[c] = howMany - 1 - rows[c];
		return b; 
		}
		
				
	public Board inverse()
		{
		if(!full()) return null;
		Board b = new Board(size);
		b.howMany = howMany;
		for(int c = 0; c<howMany; c++)
			b.rows[rows[c]] = c;
		return b; 
		}
		
	private boolean ewMatch(int r)
		{
		for(int c = 0; c < howMany; c++)
			if(r == rows[c])
				return true;
		return false;
		}
	
	public String toString()
		{
		String s = "";
		for(int c = 0; c < howMany; c++)
			s += rows[c];
		return s;
		}
	
	private boolean diagMatch(int r)
		{
		for(int c = 0; c < howMany; c++)
			if(howMany - c == Math.abs(r - rows[c]))
				return true;
		return false;
		}
	}