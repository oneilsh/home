import java.awt.*;
import java.awt.event.*;

public class MyPanel extends EventPanel implements ActionListener, Runnable
	{
	private int board[][], changeboard[][], probInt;
	private Label probLabel, raceLabel, crowdLabel, speedLabel, sizeLabel;
	private TextField probField, raceRedField, raceGreenField, crowdRedField, crowdGreenField, speedField;
	private Button go, pause, clear, size1, size2, size3, changeadd;
	private Image offScreen;
	private Graphics osg;
	private int width, height, speed, size;
	private int redrace, greenrace, redcrowd,greencrowd;
	private Thread runner;
	private boolean busyPainting, going, whoseturn, addcolor;

	public MyPanel()
		{
		setLayout(null);

		probLabel = new Label("Prob:");
		probLabel.setLocation(300, 60);
		probLabel.setSize(35, 20);
		add(probLabel);

		probField = new TextField("0");
		probField.setLocation(350, 60);
		probField.setSize(35, 25);
		add(probField);

		go = new Button("Randomize");
		go.setLocation(300, 20);
		go.setSize(90, 25);
		add(go);

		pause = new Button("Pause/Go");
		pause.setLocation(20, 310);
		pause.setSize(90, 25);
		add(pause);

		sizeLabel = new Label("Size:");
		sizeLabel.setLocation(210, 310);
		sizeLabel.setSize(35, 20);
		add(sizeLabel);

		size1 = new Button("1");
		size1.setLocation(210, 340);
		size1.setSize(25, 25);
		add(size1);


		size2 = new Button("2");
		size2.setLocation(240, 340);
		size2.setSize(25, 25);
		add(size2);

		size3 = new Button("3");
		size3.setLocation(270, 340);
		size3.setSize(25, 25);
		add(size3);

		changeadd = new Button("Add Color:");
		changeadd.setLocation(300, 310);
		changeadd.setSize(90, 25);
		add(changeadd);

		clear = new Button("Clear");
		clear.setLocation(20, 340);
		clear.setSize(90, 25);
		add(clear);

		raceLabel = new Label("How Racist:");
		raceLabel.setLocation(300, 135);
		raceLabel.setSize(90, 20);
		add(raceLabel);

		raceRedField = new TextField("0");
		raceRedField.setLocation(300, 165);
		raceRedField.setSize(40, 25);
		raceRedField.setBackground(new Color(255, 100, 100));
		add(raceRedField);

		raceGreenField = new TextField("0");
		raceGreenField.setLocation(345, 165);
		raceGreenField.setSize(40, 25);
		raceGreenField.setBackground(new Color(100, 255, 100));
		add(raceGreenField);

		crowdLabel = new Label("Crowdability:");
		crowdLabel.setLocation(300, 210);
		crowdLabel.setSize(90, 20);
		add(crowdLabel);

		crowdRedField = new TextField("5");
		crowdRedField.setLocation(300, 240);
		crowdRedField.setSize(40, 25);
		crowdRedField.setBackground(new Color(255, 100, 100));
		add(crowdRedField);

		crowdGreenField = new TextField("5");
		crowdGreenField.setLocation(345, 240);
		crowdGreenField.setSize(40, 25);
		crowdGreenField.setBackground(new Color(100, 255, 100));
		add(crowdGreenField);

		speedLabel = new Label("Speed:");
		speedLabel.setLocation(120, 310);
		speedLabel.setSize(85, 20);
		add(speedLabel);

		speedField = new TextField("200");
		speedField.setLocation(120, 340);
		speedField.setSize(85, 25);
		add(speedField);

		size = 250;
		probInt = 0;
		speed = 200;
		redrace = 0;
		greenrace = 0;
		redcrowd= 5;
		greencrowd = 5;
		whoseturn = true;
		addcolor = true;

		board = new int[252][252];
		changeboard = new int[size+2][size+2];
		go.addActionListener(this);
		probField.addActionListener(this);
		pause.addActionListener(this);
		speedField.addActionListener(this);
		clear.addActionListener(this);
		raceRedField.addActionListener(this);
		raceGreenField.addActionListener(this);
		crowdRedField.addActionListener(this);
		crowdGreenField.addActionListener(this);
		size1.addActionListener(this);
		size2.addActionListener(this);
		size3.addActionListener(this);
		changeadd.addActionListener(this);

		busyPainting = true;
		going = true;
		randomizeboard();
		runner = new Thread(this);
		runner.start();
		}

	public void update(Graphics g)
		{
		paint(g);
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
			if(going)
				life();
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

	public void mousePressed(MouseEvent e)
		{
		int xCoord = e.getX();
		int yCoord = e.getY();

		if (0 < xCoord && xCoord < 250 && 25 < yCoord && yCoord < 275)
			{
			if(addcolor)
				{
				if(board[(int)(xCoord)/(250/size)][(int)((yCoord-25)/(250/size))] == 1 || board[(int)(xCoord)/(250/size)][(int)((yCoord-25)/(250/size))] == 2)
					board[(int)(xCoord)/(250/size)][(int)((yCoord-25)/(250/size))] = 0;
				else
					board[(int)(xCoord)/(250/size)][(int)((yCoord-25)/(250/size))] = 1;
				}
			else
				{
				if(board[(int)(xCoord)/(250/size)][(int)((yCoord-25)/(250/size))] == 1 || board[(int)(xCoord)/(250/size)][(int)((yCoord-25)/(250/size))] == 2)
					board[(int)(xCoord)/(250/size)][(int)((yCoord-25)/(250/size))] = 0;
				else
					board[(int)(xCoord)/(250/size)][(int)((yCoord-25)/(250/size))] = 2;
				}
			}
		repaint();
		}


	public void actionPerformed(ActionEvent e)
		{
		if(e.getSource() == changeadd)
			addcolor = !addcolor;
		else if(e.getSource() == size1)
			{
			size = 50;
			//randomizeboard();
			}
		else if(e.getSource() == size2)
			{
			size = 125;
			//randomizeboard();
			}
		else if(e.getSource() == size3)
			{
			size = 250;
			//randomizeboard();
			}
		else if(e.getSource() == raceRedField || e.getSource() == raceGreenField || e.getSource() == crowdRedField || e.getSource() == crowdGreenField)
			{
			redrace = convert(raceRedField.getText());
			greenrace = convert(raceGreenField.getText());
			redcrowd = convert(crowdRedField.getText());
			greencrowd = convert(crowdGreenField.getText());
			}
		else if(e.getSource() == clear)
			{
			for(int x=1; x < size; x++)
				for(int y=1; y < size; y++)
					{
					board[x][y] = 0;
					}
			}

		else if(e.getSource() == speedField)
			{
			speed = convert(speedField.getText());
			}
		else if(e.getSource() == pause)
			{
			if(going)
				going = false;
			else
				{
				going = true;
				}
			}
		else if(e.getSource() == go)
			{
			randomizeboard();
			}
		else if(e.getSource() == probField)
			probInt = convert(probField.getText());
		}

	public void randomizeboard()
		{
		for(int x=1; x < size; x++)
			for(int y=1; y < size; y++)
				{
				board[x][y] = (int)(3*Math.random());
				}
		}

	public void life()
		{
	//	while(true)
			{
			for(int x = 1; x<size; x++)
				for(int y = 1; y < size; y++)
					{
					changeboard[x][y] = 0;
					}
			for(int x = 1; x<size; x++)
				for(int y = 1; y<size; y++)
					{
					if(board[x][y] == 1)
						if(dies(x,y))
							changeboard[x][y] = 1;
					if(board[x][y] == 2)
						if(dies(x,y))
							changeboard[x][y] = 1;
					if(board[x][y] == 0)
						if(creates(x,y) == 1)
							changeboard[x][y] = 2;
					if(board[x][y] == 0)
						if(creates(x,y) == 2)
							changeboard[x][y] = 3;
					}

			if(((int)(100*Math.random())) <= probInt)
				{
				changeboard[(int)(size*Math.random())][(int)(size*Math.random())] = (int)(3*Math.random()+1);
				}

			for(int x = 1; x<size; x++)
				for(int y = 1; y < size; y++)
					{
					if(changeboard[x][y] == 1)
						board[x][y] = 0;
					if(changeboard[x][y] == 2)
						board[x][y] = 1;
					if(changeboard[x][y] == 3)
						board[x][y] = 2;
					}
		//	repaint();
			}
		}

	public boolean dies(int x, int y)
		{
		int red = 0;
		int green = 0;
		if(board[x][y+1] == 1)
			red += 1;
		if(board[x+1][y] == 1)
			red += 1;
		if(board[x][y-1] == 1)
			red += 1;
		if(board[x-1][y] == 1)
			red += 1;
		if(board[x+1][y+1] == 1)
			red += 1;
		if(board[x-1][y-1] == 1)
			red += 1;
		if(board[x+1][y-1] == 1)
			red += 1;
		if(board[x-1][y+1] == 1)
			red += 1;

		if(board[x][y+1] == 2)
			green += 1;
		if(board[x+1][y] == 2)
			green += 1;
		if(board[x][y-1] == 2)
			green += 1;
		if(board[x-1][y] == 2)
			green += 1;
		if(board[x+1][y+1] == 2)
			green += 1;
		if(board[x-1][y-1] == 2)
			green += 1;
		if(board[x+1][y-1] == 2)
			green += 1;
		if(board[x-1][y+1] == 2)
			green += 1;

		int total = red + green;

		if(board[x][y] == 1)
			{
			if(red > green && total < redcrowd)
				return false;
			}
		if(board[x][y] == 2)
			{
			if(red < green && total < greencrowd)
				return false;
			}
		return true;
		}

	public int creates(int x, int y)
		{
		int red = 0;
		int green = 0;

		if(board[x][y+1] == 1)
			red += 1;
		if(board[x+1][y] == 1)
			red += 1;
		if(board[x][y-1] == 1)
			red += 1;
		if(board[x-1][y] == 1)
			red += 1;
		if(board[x+1][y+1] == 1)
			red += 1;
		if(board[x-1][y-1] == 1)
			red += 1;
		if(board[x+1][y-1] == 1)
			red += 1;
		if(board[x-1][y+1] == 1)
			red += 1;

		if(board[x][y+1] == 2)
			green += 1;
		if(board[x+1][y] == 2)
			green += 1;
		if(board[x][y-1] == 2)
			green += 1;
		if(board[x-1][y] == 2)
			green += 1;
		if(board[x+1][y+1] == 2)
			green += 1;
		if(board[x-1][y-1] == 2)
			green += 1;
		if(board[x+1][y-1] == 2)
			green += 1;
		if(board[x-1][y+1] == 2)
			green += 1;

		int total = red + green;
		if(whoseturn)
			{
			if(red > green+redrace && red == 3)
				{
				whoseturn = !whoseturn;
				return 1;
				}
			}
		else
			{
			if(red + greenrace< green && green == 3)
				{
				whoseturn = !whoseturn;
				return 2;
				}
			}
		whoseturn = !whoseturn;
		return 0;
		}

	public void lifepaint(Graphics g)
		{
		for(int x = 0; x < size+1; x++)
			for(int y = 0; y < size+1; y++)
				{
				if(board[x][y] == 1)
					{
					g.setColor(Color.red);
					g.fillRect(x*(250/size), y*(250/size)+25, (250/size), (250/size));
					}
				if(board[x][y] == 2)
					{
					g.setColor(Color.green);
					g.fillRect(x*(250/size), y*(250/size)+25, (250/size), (250/size));
					}
				}
		g.setColor(Color.black);
		g.drawString(""+probInt+"% Prob. of New", 300, 110);
		g.drawString(""+redrace, 300, 205);
		g.drawString(""+greenrace, 350, 205);
		g.drawString(""+redcrowd, 300, 280);
		g.drawString(""+greencrowd, 350, 280);
		if(addcolor)
			{
			g.drawString("Red", 320, 360);
			}
		else
			g.drawString("Green", 320, 360);

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

