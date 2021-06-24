import java.awt.*;
import java.awt.event.*;

public class ListCanvas extends Canvas
	{
	private BoardList list1, list2, list3, list4;
	public int godirection;
	public int showtype;
	
	public ListCanvas(BoardList lst1, BoardList lst2, BoardList lst3, BoardList lst4)
		{
		list1 = lst1;
		list2 = lst2;
		list3 = lst3;
		list4 = lst4;
		showtype = 1;
		godirection = 1;
		}
		
	public void paint(Graphics g)
		{
		if(godirection == 1)
			{
			if(showtype == 1)
				list1.showNext(g);
			else if(showtype == 2)
				list2.showNext(g);
			else if(showtype == 3)
				list3.showNext(g);
			else if(showtype == 4)
				list4.showNext(g);
			}
		else if(godirection == -1)
			{
			if(showtype == 1)
				list1.showPrev(g);
			else if(showtype == 2)
				list2.showPrev(g);
			else if(showtype == 3)
				list3.showPrev(g);
			else if(showtype == 4)
				list4.showPrev(g);
			}
		}
		
	public void resetShowing()
		{
		list1.resetShowing();
		list2.resetShowing();
		list3.resetShowing();
		list4.resetShowing();
		}
		
	}