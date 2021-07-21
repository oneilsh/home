//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BoardCanvas extends Canvas implements Runnable, ActionListener
	{
	private Manager manager;
	private Board board1;
	private Image offScreen;
	private Button pause, reset, iterate;
	private TextField speedField, genField;
	private Label genLabel, genchangeLabel;
	private Graphics osg;
	private int width, height, speed, gen, genchange;
	private Thread runner;
	private boolean busyPainting, going;

	public BoardCanvas(Board theboard, Manager themanager, Button thepause, TextField thespeedField, TextField thegenField, Label thegenchangeLabel, Label thegenLabel, Button thereset, Button theiterate)
		{
		gen = 0;
		genchange = 1;
		pause = thepause;
		speedField = thespeedField;
		going = false;
		speed = 20;
		manager = themanager;
		board1 = theboard;
		genField = thegenField;
		genchangeLabel = thegenchangeLabel;
		genLabel = thegenLabel;
		reset = thereset;
		iterate = theiterate;
//		manager = theman;

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
		if(e.getSource() == pause)
			{
			going = !going;
			}
		else if(e.getSource() == speedField)
			{
			speed = convert(speedField.getText());
			}
		else if(e.getSource() == genField)
			{
			genchange = convert(genField.getText());
			genchangeLabel.setText(""+genchange);
			}
		else if(e.getSource() == reset)
			{
			gen = 0;
			genLabel.setText("0");
			}

		else if(e.getSource() == iterate)
			{
			for(int i = 0; i < genchange; i++)
				{
				board1.computeNext();
				board1.updateNow();
				gen++;
				genLabel.setText(""+gen);
				}
			repaint();
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
				for(int i = 0; i < genchange; i++)
					{
					board1.getNumbers();
					board1.computeNext();
					board1.updateNow();
					gen++;
					genLabel.setText(""+gen);
					}
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

	public void lifepaint(Graphics g)
		{
		board1.paint(g);
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