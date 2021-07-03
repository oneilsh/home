import java.awt.*;
import java.awt.event.*;

public class Node
	{
	private Board board;
	private int number, type;
	private Node link;
	
	public Node(int i, Board b, int t, Node n)
		{
		type = t;
		number = i;
		board = b;
		link = n;
		}
		
	public void paint(Graphics g, int showing, int showtype)
		{
		Color color = Color.black;
		if(number == showing)
			{
			if(type == 2)
				color = Color.green;
			if(type == 3)
				color = Color.blue;
			if(type == 4)
				color = Color.red;
			board.paint(g, color);
			//g.drawString(""+number, 20, 20);
			//g.drawString(""+board.toString(), 100, 20);
			}
		else if(link != null)
			link.paint(g, showing, showtype);
		}
		

	public Node getLink()
		{
		return link;
		}
		
	public int getNumber()
		{
		return number;
		}
	
	public Board getBoard()
		{
		return board;
		}
		
	public void addToEnd(int i, Board b, int t)
		{
		if(link != null)
			link.addToEnd(i, b, t);
		else
			link = new Node(i, b, t, null);
		}
		
	public int getHowMany()
		{
		int me = 1;
		if(link == null)
			return me;
		else
			return link.getHowMany() + me;
		}
	}