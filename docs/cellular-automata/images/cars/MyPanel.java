//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MyPanel extends EventPanel
	{
	public ObjectList list1;
	public BoardCanvas bcanvas;
	public Button addtype, step, clear, delete, togglesenseview;
	public Label addtypeLabel;
	public Manager manager;
	public TextField spacing;


	public MyPanel()
		{
		setLayout(null);

		list1 = new ObjectList(bcanvas);

		addtype = new Button("Add: ");
		addtype.setLocation(10, 660);
		addtype.setSize(60, 25);
		add(addtype);

		addtypeLabel = new Label(new BlueLight(0,0).getDesc());
		addtypeLabel.setLocation(60,660);
		addtypeLabel.setSize(500,25);
		add(addtypeLabel);

		step = new Button("Stop/Go");
		step.setLocation(600, 660);
		step.setSize(80,25);
		add(step);

		clear = new Button("Clear");
		clear.setLocation(670, 660);
		clear.setSize(80,25);
		add(clear);

		delete = new Button("Del Last");
		delete.setLocation(740, 660);
		delete.setSize(80,25);
		add(delete);

		togglesenseview = new Button("Sensors View");
		togglesenseview.setLocation(810, 20);
		togglesenseview.setSize(110,30);
		add(togglesenseview);
		
		spacing = new TextField("25");
		spacing.setLocation(810, 55);
		spacing.setSize(90,30);
		add(spacing);


		bcanvas = new BoardCanvas(list1, manager, addtype, step, clear, delete, togglesenseview);
		bcanvas.setLocation(0,0);
		bcanvas.setSize(800, 640);
		bcanvas.setBackground(new Color(180,180,180));
		add(bcanvas);

		manager = new Manager(list1, bcanvas, addtype, step, addtypeLabel, spacing);


		addtype.addActionListener(manager);
		step.addActionListener(bcanvas);
		clear.addActionListener(bcanvas);
		delete.addActionListener(bcanvas);
		togglesenseview.addActionListener(bcanvas);

		bcanvas.addMouseListener(manager);
		}

	public void paint(Graphics g)
		{
		list1.paint(g);
		}


	}
