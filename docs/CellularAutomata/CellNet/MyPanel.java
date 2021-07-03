//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MyPanel extends EventPanel
	{
	private Board board1;
	private BoardCanvas bcanvas;
	private Button change, iterate, act1, act2, random, clear, pause, reset, fill, torus, CLR, openBoard, saveBoard;
	private TextField speedField, genField, randField;
	private Label changeLabel, genchangeLabel, genLabel, fillLabel, topolLabel;
	private Manager manager;

	public MyPanel()
		{
		setLayout(null);

		board1 = new Board();

		change = new Button("Add");
		change.setLocation(40, 640);
		change.setSize(60, 25);
		add(change);

		changeLabel = new Label("State Change");
		changeLabel.setLocation(60, 670);
		changeLabel.setSize(80, 25);
		add(changeLabel);

		iterate = new Button("Step");
		iterate.setLocation(100, 640);
		iterate.setSize(60, 25);
		add(iterate);

		act1 = new Button("Act1");
		act1.setLocation(160, 640);
		act1.setSize(60, 25);
		add(act1);

		act2 = new Button("Act2");
		act2.setLocation(220, 640);
		act2.setSize(60, 25);
		add(act2);

		clear = new Button("Clear");
		clear.setLocation(340, 640);
		clear.setSize(60, 25);
		add(clear);

		pause = new Button("Pause");
		pause.setLocation(400, 640);
		pause.setSize(60, 25);
		add(pause);

		speedField = new TextField("20");
		speedField.setLocation(460, 640);
		speedField.setSize(60, 25);
		add(speedField);

		genField = new TextField("1");
		genField.setLocation(640, 10);
		genField.setSize(60, 25);
		add(genField);

		genchangeLabel = new Label("1");
		genchangeLabel.setLocation(640, 40);
		genchangeLabel.setSize(60, 25);
		add(genchangeLabel);

		genLabel = new Label("0");
		genLabel.setLocation(640, 90);
		genLabel.setSize(60,25);
		add(genLabel);

		reset = new Button("Reset");
		reset.setLocation(640, 120);
		reset.setSize(60,25);
		add(reset);

		fill = new Button("Fill");
		fill.setLocation(640, 200);
		fill.setSize(60, 25);
		add(fill);

		fillLabel = new Label("Blank");
		fillLabel.setLocation(640, 230);
		fillLabel.setSize(60, 25);
		add(fillLabel);

		torus = new Button("Topol");
		torus.setLocation(640, 270);
		torus.setSize(60, 25);
		add(torus);

		topolLabel = new Label("Torus");
		topolLabel.setLocation(640, 300);
		topolLabel.setSize(60,25);
		add(topolLabel);

		random = new Button("Rand");
		random.setLocation(640, 350);
		random.setSize(60, 25);
		add(random);

		randField = new TextField("50");
		randField.setLocation(640, 380);
		randField.setSize(60,25);
		add(randField);
		
		CLR = new Button("CLR");
		CLR.setLocation(640, 410);
		CLR.setSize(60, 25);
		add(CLR);
	
		openBoard = new Button("Open");
		openBoard.setLocation(640, 440);
		openBoard.setSize(60, 25);
		add(openBoard);

		saveBoard = new Button("Save");
		saveBoard.setLocation(640, 470);
		saveBoard.setSize(60, 25);
		add(saveBoard);

		bcanvas = new BoardCanvas(board1, manager, pause, speedField, genField, genchangeLabel, genLabel, reset, iterate);
		bcanvas.setLocation(10, 10);
		bcanvas.setSize(620, 620);
		add(bcanvas);
		bcanvas.setBackground(Color.gray);

		manager = new Manager(board1, change, iterate, changeLabel, bcanvas, act1, act2, random, clear, fill, fillLabel, torus, topolLabel, randField, CLR, openBoard, saveBoard);

		change.addActionListener(manager);
		iterate.addActionListener(manager);
		act1.addActionListener(manager);
		act2.addActionListener(manager);
		CLR.addActionListener(manager);
		openBoard.addActionListener(manager);
		saveBoard.addActionListener(manager);
		random.addActionListener(manager);
		clear.addActionListener(manager);
		torus.addActionListener(manager);

		iterate.addActionListener(bcanvas);
		reset.addActionListener(bcanvas);
		genField.addActionListener(bcanvas);
		pause.addActionListener(bcanvas);
		speedField.addActionListener(bcanvas);

		fill.addActionListener(manager);

		bcanvas.addMouseListener(manager);
		}

	public void paint(Graphics g)
		{
		board1.paint(g);
		}


	}
