import java.awt.*;
import java.awt.event.*;

public class StatsCanvas extends Canvas
	{
	private BoardList list1, list2, list3, list4;	
	public int showtype;
	
	public StatsCanvas(BoardList lst1, BoardList lst2, BoardList lst3, BoardList lst4)
		{
		showtype = 1;
		list1 = lst1;
		list2 = lst2;
		list3 = lst3;
		list4 = lst4;
		}
		
	public void paint(Graphics g)
		{
		int total = list1.getHowMany();
		g.drawString("Total = "+total, 10, 10);
		if(showtype == 1)
			{
			g.drawString(list1.showing+" of "+list1.getHowMany(), 10, 22);
			}
		if(showtype == 2)
			{
			g.drawString(list2.showing+" of "+list2.getHowMany(), 10, 22);
			}		
		if(showtype == 3)
			{
			g.drawString(list3.showing+" of "+list3.getHowMany(), 10, 22);
			}	
		if(showtype == 4)
			{
			g.drawString(list4.showing+" of "+list4.getHowMany(), 10, 22);
			}
		}
	}