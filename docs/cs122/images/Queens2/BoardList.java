import java.awt.*;
import java.awt.event.*;

public class BoardList
	{
	public Node head;
	public int showing, showtype;
	
	public BoardList()
		{
		showtype = 1;
		showing = 0;
		head = null;
		}
	
	public void paint(Graphics g)
		{
		if (head == null)
			g.drawString("No Solutions.", 20, 20);
		else
			head.paint(g, showing, showtype);
		}
		
	public void resetShowing()
		{
		showing = 0;
		}
		
	public void showNext(Graphics g)
		{
		if (head == null)
			{
			g.drawString("No Solutions.", 20, 20);
			return;
			}
		else
			{
			showing++;
			head.paint(g, showing, showtype);
			}
		}
		
	public void showPrev(Graphics g)
		{
		if (head == null)
			g.drawString("No Solutions.", 20, 20);
		else
			{
			showing--;
			head.paint(g, showing, showtype);
			}
		}
		
	public void addToEnd(int i, Board b, int t)
		{
		if(head == null)
			head = new Node(i, b, t, null);
		else
			head.addToEnd(i, b, t);
		}
		
	public int getHowMany()
		{
		return head.getHowMany();
		}
	
	public int getShowType()
		{
		return showtype;
		}

	}
