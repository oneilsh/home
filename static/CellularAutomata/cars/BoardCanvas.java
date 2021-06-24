//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BoardCanvas extends Canvas implements Runnable, ActionListener
	{
	private ObjectList list1;
	private Button addtype, step, clear, delete, togglesenseview;
	private Manager manager;
	private int speed, adding, width, height;
	private boolean going, busyPainting;
	private Graphics osg;
	private Image offScreen;

	//private int width, height, speed, gen, genchange;
	private Thread runner;

	public BoardCanvas(ObjectList thelist1, Manager themanager, Button theaddtype, Button thestep, Button theclear, Button thedelete, Button thetogglesenseview)
		{
		adding = 0;
		going = false;
		speed = 20;
		manager = themanager;
		list1 = thelist1;
		addtype = theaddtype;
		step = thestep;
		clear = theclear;
		delete = thedelete;
		togglesenseview = thetogglesenseview;

		busyPainting = true;
		runner = new Thread(this);
		runner.start();
		}

	public void update(Graphics g)
		{
		paint(g);
		}

	public void actionPerformed(ActionEvent e)
		{
		if(e.getSource() == addtype)
			{
			if(adding == 1)
				adding = 0;
			else
				adding++;
			}

		else if(e.getSource() == step)
			{
			//list1.go();
			//repaint();
			toggle();
			}
		else if(e.getSource() == clear)
			{
			list1.clear();
			}

		else if(e.getSource() == delete)
			{
			list1.deletelast();
			}

		else if(e.getSource() == togglesenseview)
			{
			//System.out.println("bcanvas go the toggle command, passing on to list");
			list1.togglesenseview();
			}
		}

	public void paint(Graphics g)
		{
		if(offScreen == null)
			{
			width = getSize().width;
			height = getSize().height;
			offScreen = createImage(width, height);
			osg = offScreen.getGraphics();
			}
		osg.setColor(getBackground());
		osg.fillRect(0, 0, width, height);

		lifepaint(osg);

		g.drawImage(offScreen, 0, 0, null);
		notifyRunner();
		}

	public void run()
		{
		while(busyPainting)
			{
			waitForPainting();
			}
		while(true)
			{
		//	System.out.println("working");
			if(going)
				{
				list1.computenext();
				list1.update();
				}
			repaintAndWait();
			delayForUser();
			}
		}

	private synchronized void notifyRunner()
		{
		busyPainting = false;
		notifyAll();
		}

	private synchronized void repaintAndWait()
		{
		busyPainting = true;
		repaint();
		while(busyPainting)
			{
			try{wait(100);}
			catch (InterruptedException exp){}
			}
		}

	private synchronized void waitForPainting()
		{
		while(busyPainting)
			{
			try{wait(100);}
			catch(InterruptedException exp){}
			}
		}

	private void delayForUser()
		{
		try{Thread.currentThread().sleep(speed);}
		catch(InterruptedException exp) {}
		}

	public void toggle()
		{
		going = !going;
		}

	public void lifepaint(Graphics g)
		{
		if(going)
			{
			list1.computenext();
			list1.update();
			}
		list1.paint(g);
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

	}
