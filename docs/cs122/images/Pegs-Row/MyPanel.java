

import java.awt.*;
import java.awt.event.*;

public class MyPanel extends Panel implements ActionListener
	{
	private Board list[];
	private int howMany;
	public int showing, size;
	private Button shownext, showprev, size5, size6;
	private Label sizeLabel, showLabel;
	
	public MyPanel()
		{
		setLayout(null);
				
		showLabel = new Label("Show Step:");
		showLabel.setLocation(300, 10);
		showLabel.setSize(90, 20);
		add(showLabel);
		
		shownext = new Button("Next");
		shownext.setLocation(300, 40);
		shownext.setSize(90, 25);
		add(shownext);
		
		showprev = new Button("Prev");
		showprev.setLocation(300, 70);
		showprev.setSize(90, 25);
		add(showprev);
		
		sizeLabel = new Label("Board Size:");
		sizeLabel.setLocation(300, 100);
		sizeLabel.setSize(90, 20);
		add(sizeLabel);
				
		size5 = new Button("5");
		size5.setLocation(300, 130);
		size5.setSize(40, 25);
		add(size5);	
			
		size6 = new Button("6");
		size6.setLocation(350, 130);
		size6.setSize(40, 25);
		add(size6);
	
		shownext.addActionListener(this);
		showprev.addActionListener(this);
		size5.addActionListener(this);
		size6.addActionListener(this);
		
		size = 5;
		howMany = 0;
		showing = 0;
		list = new Board[7*7];
		try
			{
			search(new Board(size));
			}
		catch(FoundItException e)
			{
			repaint();
			}
		}
		
	public void paint(Graphics g)
		{
		list[showing].paint(g);
		}
		
	public void actionPerformed(ActionEvent e)
		{
		if(e.getSource() == shownext)
			{
			if(showing < howMany-1)
				showing++;
			repaint();
			}
		if(e.getSource() == showprev)
			{
			if(showing > 0)
				showing--;
			repaint();
			}
		if(e.getSource() == size5)
			{
			size = 5;
			try
				{
				howMany = 0;
				showing = 0;
				search(new Board(size));
				}
			catch(FoundItException x)
				{
				repaint();
				}
			
			}
			
		if(e.getSource() == size6)
			{
			size = 6;
			try
				{
				howMany = 0;
				showing = 0;
				search(new Board(size));
				}
			catch(FoundItException x)
				{
				repaint();
				}
			
			}
		}

	private void search(Board b) throws FoundItException
		{
		if(b == null)
			return;
		list[howMany++] = b;
		if(b.solved())
			{
			throw new FoundItException();
			}
		else
			{
			for(int x=0; x<size; x++)
				{
				for(int y=0; y<size; y++)
					{
					search(b.jump(x,y, 1, -1));
					search(b.jump(x,y, -1, 1));
					search(b.jump(x,y, 1, 0));
					search(b.jump(x,y, -1, 0));
					search(b.jump(x,y, 0, 1));
					search(b.jump(x,y, 0, -1));
					}
				}
			}
		howMany--;
		}

	public void keyPressed(KeyEvent e)
		{
		repaint();
		}
}

class FoundItException extends Exception
	{
	}