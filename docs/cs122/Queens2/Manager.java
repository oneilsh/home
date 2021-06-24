import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Manager implements ActionListener
	{
	private int count1, count2, count3, count4, size, showing, showtype;
	private BoardList list1, list2, list3, list4;
	private TextField rowsField;
	private ListCanvas listCanvas;
	private StatsCanvas statsCanvas;
	private Button shownext, showprev, all, unique, symetric, elegant;
	
	public Manager(TextField rf, BoardList lst1, BoardList lst2, BoardList lst3, BoardList lst4, ListCanvas can, StatsCanvas sc, Button sn, Button sp, Button al, Button uq, Button sy, Button el)
		{
		showtype = 1;
		list1 = lst1;
		list2=lst2;
		list3 = lst3;
		list4 = lst4;
		shownext = sn;
		showprev = sp;
		statsCanvas = sc;
		count1 = 0;
		count2 = 0;
		count3 = 0;
		count4 = 0;
		size = 0;
		rowsField = rf;
		listCanvas = can;
		all = al;
		unique = uq;
		symetric = sy;
		elegant = el;
		}
		
	public void actionPerformed(ActionEvent e)
		{
		if(e.getSource() == shownext)
			{
			if(showtype == 1)
				{
				if(list1.showing < list1.getHowMany())
					{
					listCanvas.showtype = showtype;
					listCanvas.godirection = 1;
					listCanvas.repaint();
					statsCanvas.repaint();
					}
				}
			else if(showtype == 2)
				{
				if(list2.showing < list2.getHowMany())
					{
					listCanvas.showtype = showtype;
					listCanvas.godirection = 1;
					listCanvas.repaint();
					statsCanvas.repaint();
					}
				}
			else if(showtype == 3)
				{
				if(list3.showing < list3.getHowMany())
					{
					listCanvas.showtype = showtype;
					listCanvas.godirection = 1;
					listCanvas.repaint();
					statsCanvas.repaint();
					}
				}
			else if(showtype == 4)
				{
				if(list4.showing < list4.getHowMany())
					{
					listCanvas.showtype = showtype;
					listCanvas.godirection = 1;
					listCanvas.repaint();
					statsCanvas.repaint();
					}
				}
			}
		
		else if(e.getSource() == showprev)
			{
			if(showtype == 1)
				{
				if(list1.showing > 1)
					{
					listCanvas.showtype = showtype;
					listCanvas.godirection = -1;
					listCanvas.repaint();
					statsCanvas.repaint();
					}
				}
			else if(showtype == 2)
				{
				if(list2.showing > 1)
					{
					listCanvas.showtype = showtype;
					listCanvas.godirection = -1;
					listCanvas.repaint();
					statsCanvas.repaint();
					}
				}
			else if(showtype == 3)
				{
				if(list3.showing > 1)
					{
					listCanvas.showtype = showtype;
					listCanvas.godirection = -1;
					listCanvas.repaint();
					statsCanvas.repaint();
					}
				}
			else if(showtype == 4)
				{
				if(list4.showing > 1)
					{
					listCanvas.showtype = showtype;
					listCanvas.godirection = -1;
					listCanvas.repaint();
					statsCanvas.repaint();
					}
				}
			}
		
		else if(e.getSource() == all)
			{
			showtype = 1;
			count1 = 0;
			count2 = 0;
			count3 = 0;
			count4 = 0;
			list1.head = null;
			list2.head = null;
			list3.head = null;
			list4.head = null;
			size = convert(rowsField.getText());
			makeFuller(new Board(size));
			statsCanvas.showtype = showtype;
			listCanvas.showtype = showtype;
			listCanvas.godirection = 1;
			listCanvas.resetShowing();
			listCanvas.repaint();
			statsCanvas.repaint();
			}
			
		else if(e.getSource() == unique)
			{
			showtype = 2;
			count1 = 0;
			count2 = 0;
			count3 = 0;
			count4 = 0;
			list1.head = null;
			list2.head = null;
			list3.head = null;
			list4.head = null;
			size = convert(rowsField.getText());
			makeFuller(new Board(size));
			statsCanvas.showtype = showtype;
			listCanvas.showtype = showtype;
			listCanvas.godirection = 1;
			listCanvas.resetShowing();
			listCanvas.repaint();
			statsCanvas.repaint();
			}
			
			
		else if(e.getSource() == symetric)
			{
			showtype = 3;
			count1 = 0;
			count2 = 0;
			count3 = 0;
			count4 = 0;
			list1.head = null;
			list2.head = null;
			list3.head = null;
			list4.head = null;
			size = convert(rowsField.getText());
			makeFuller(new Board(size));
			statsCanvas.showtype = showtype;
			listCanvas.showtype = showtype;
			listCanvas.godirection = 1;
			listCanvas.resetShowing();
			listCanvas.repaint();
			statsCanvas.repaint();
			}
			
			
		else if(e.getSource() == elegant)
			{
			showtype = 4;
			count1 = 0;
			count2 = 0;
			count3 = 0;
			count4 = 0;
			list1.head = null;
			list2.head = null;
			list3.head = null;
			list4.head = null;
			size = convert(rowsField.getText());
			makeFuller(new Board(size));
			statsCanvas.showtype = showtype;
			listCanvas.showtype = showtype;
			listCanvas.godirection = 1;
			listCanvas.resetShowing();
			listCanvas.repaint();
			statsCanvas.repaint();
			}
			
		else
			{
			count1 = 0;
			count2 = 0;
			count3 = 0;
			count4 = 0;
			list1.head = null;
			list2.head = null;
			list3.head = null;
			list4.head = null;
			size = convert(rowsField.getText());
			makeFuller(new Board(size));
			statsCanvas.showtype = showtype;
			listCanvas.showtype = showtype;
			listCanvas.godirection = 1;
			listCanvas.resetShowing();
			listCanvas.repaint();
			statsCanvas.repaint();
			}
		}
	
	public void makeFuller(Board b)
		{
			if(b == null)
				return;
			if(b.full())
				{
				int type = 2;
				if(b.toString().compareTo(b.reverse().upsideDown().toString()) == 0)
					type = 3;
				if(b.toString().compareTo(b.inverse().reverse().toString()) == 0)
					type = 4;
				if(b.toString().compareTo(b.reverse().toString()) > 0)
					type = 1;
				if(b.toString().compareTo(b.upsideDown().toString()) > 0)
					type = 1;
				if(b.toString().compareTo(b.inverse().toString()) > 0)
					type = 1;
				if(b.toString().compareTo(b.reverse().upsideDown().toString()) > 0)
					type = 1;		
				if(b.toString().compareTo(b.inverse().reverse().toString()) > 0)
					type = 1;
				if(b.toString().compareTo(b.inverse().upsideDown().toString()) > 0)
					type = 1;
				if(b.toString().compareTo(b.inverse().reverse().upsideDown().toString()) > 0)
					type = 1;
				
				//count++;
				if(type == 1)
					{
					count1++;
					list1.addToEnd(count1, b, type);
					}
				else if(type == 2)
					{
					count1++;
					count2++;
					list1.addToEnd(count1, b, type);
					list2.addToEnd(count2, b, type);
					}
				else if(type == 3)
					{
					count1++;
					count2++;
					count3++;
					list1.addToEnd(count1, b, type);
					list2.addToEnd(count2, b, type);
					list3.addToEnd(count3, b, type);
					}
				else if(type == 4)
					{
					count1++;
					count2++;
					count3++;
					count4++;
					list1.addToEnd(count1, b, type);
					list2.addToEnd(count2, b, type);
					list3.addToEnd(count3, b, type);
					list4.addToEnd(count4, b, type);
					}
				}
			else
				for(int r = 0; r<size; r++)
					makeFuller(b.makeAFullerBoard(r));
		}
		
	private int convert(String s)
		{
		try
			{
			return (new Integer(s)).intValue();
			}
		catch (Throwable t)
			{
			return -9999;
			}
		}
	}
