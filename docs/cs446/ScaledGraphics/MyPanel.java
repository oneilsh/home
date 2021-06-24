import java.awt.*;
import java.awt.event.*;

public class MyPanel extends EventPanel implements ActionListener
{
	private double hstart, vstart, hend, vend, selection;
	private boolean dragging;
	private ThingList list;


	public MyPanel()
		{
		setLayout(null);
		dragging = false;
		selection = 0;
		list = new ThingList();
		}

	public void actionPerformed(ActionEvent e)
		{
		}

	public void mousePressed(MouseEvent e)
		{

		int x = e.getX(), y = e.getY();
		if (y > 100)
			{
			dragging = true;
			hstart = x;
			vstart = y;
			hend = x;
			vend = y;
			}

		else
			{
			if(x < 133)
				selection = 0;
			if(x < 266 && 133 <= x)
				selection = 1;
			if(x <= 400 && 266 <= x)
				selection = 2;
			}
		repaint();
		}

	public void mouseDragged(MouseEvent e)
		{
		if(dragging)
			{
			hend = e.getX();
			vend = e.getY();
			repaint();
			}
		}

	public void mouseReleased(MouseEvent e)
		{
		hend = e.getX();
		vend = e.getY();
		if(dragging)
			{
			if(selection == 0)
				list.addTree(new Tree(hstart/400 , hend/400, (400-vend)/400, (400-vstart)/400));
			if(selection == 1)
				list.addHouse(new House(hstart/400 , hend/400, (400-vend)/400, (400-vstart)/400));
			if(selection == 2)
				list.addCurve(new Curve(hstart/400 , hend/400, (400-vend)/400, (400-vstart)/400));
			}
		dragging = false;
		repaint();
		}

	public void paint(Graphics g)
		{
		g.setColor(Color.white);
		g.fillRect(0, 0, 400, 400);
		g.setColor(Color.black);
		g.drawLine(0, 100, 400, 100);
		if(dragging)
			{
			g.drawLine((int)hstart, (int)vstart, (int)hend, (int)vstart);
			g.drawLine((int)hend,(int)vstart,(int)hend,(int)vend);
			g.drawLine((int)hend,(int)vend,(int)hstart,(int)vend);
			g.drawLine((int)hstart,(int)vend,(int)hstart,(int)vstart);
			}
		g.setColor(Color.gray);
		if(selection == 0)
			{
			g.fillRect(0,10,80,80);
			}
		if(selection == 1)
			{
			g.fillRect(160,10,80,80);
			}
		if(selection == 2)
			{
			g.fillRect(310, 10,80, 80);
			}
		g.setColor(Color.black);
		list.paint(g);
		}

	private int convert(String s)
			{
			try
				{
				return (new Integer(s)).intValue();
				}
			catch (Throwable t)
				{
				return 50;
				}
			}

	private double convertdouble(String s)
			{
			try
				{
				return (new Double(s)).doubleValue();
				}
			catch (Throwable t)
				{
				return .7;
				}
			}

}



