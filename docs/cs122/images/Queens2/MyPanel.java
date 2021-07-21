import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MyPanel extends Panel
	{
	private Label rowsLabel;
	private TextField rowsField;
	private BoardList list1, list2, list3, list4;
	private ListCanvas listCanvas;
	private Manager manager;
	private Button go, shownext, showprev, all, unique, symetric, elegant;
	private StatsCanvas statsCanvas;
	
	public MyPanel()
		{
		setLayout(null);
		
		rowsLabel = new Label("Rows:");
		rowsLabel.setLocation(300, 10);
		rowsLabel.setSize(90, 20);
		add(rowsLabel);
		
		rowsField = new TextField();
		rowsField.setLocation(300, 35);
		rowsField.setSize(90, 25);
		add(rowsField);
		
		go = new Button("Go");
		go.setLocation(300, 70);
		go.setSize(90,25);
		add(go);
		
		shownext = new Button("Show Next");
		shownext.setLocation(300, 95);
		shownext.setSize(90,25);
		add(shownext);	
			
		showprev = new Button("Show Prev");
		showprev.setLocation(300, 120);
		showprev.setSize(90,25);
		add(showprev);
		
		all = new Button("A");
		all.setLocation(300, 145);
		all.setSize(20, 25);
		add(all);
		all.setBackground(new Color(100, 100, 100));
				
		unique = new Button("U");
		unique.setLocation(322, 145);
		unique.setSize(20, 25);
		add(unique);
		unique.setBackground(new Color(100, 255, 100));	
			
		symetric = new Button("S");
		symetric.setLocation(344, 145);
		symetric.setSize(20, 25);
		add(symetric);
		symetric.setBackground(new Color(100, 100, 255));
				
		elegant = new Button("E");
		elegant.setLocation(366, 145);
		elegant.setSize(20, 25);
		add(elegant);
		elegant.setBackground(new Color(255, 100, 100));
		
		list1 = new BoardList();
		list2 = new BoardList();
		list3 = new BoardList();
		list4 = new BoardList();
		
		listCanvas = new ListCanvas(list1, list2, list3, list4);
		listCanvas.setLocation(10, 10);
		listCanvas.setSize(280, 280);
		add(listCanvas);
		listCanvas.setBackground(new Color(200, 200, 255));
		
		statsCanvas = new StatsCanvas(list1, list2, list3, list4);
		statsCanvas.setLocation(300, 180);
		statsCanvas.setSize(90, 110);
		add(statsCanvas);
		statsCanvas.setBackground(new Color(200, 200, 255));

		manager = new Manager(rowsField, list1, list2, list3, list4, listCanvas, statsCanvas, shownext, showprev, all, unique, symetric, elegant);
		
		rowsField.addActionListener(manager);
		go.addActionListener(manager);
		shownext.addActionListener(manager);
		showprev.addActionListener(manager);
		all.addActionListener(manager);
		unique.addActionListener(manager);
		symetric.addActionListener(manager);
		elegant.addActionListener(manager);
		}
	}