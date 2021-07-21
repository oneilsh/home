//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Manager extends EventPanel implements MouseListener, ActionListener
	{
	public ObjectList list1;
	public BoardCanvas bcanvas;
	public Button addtype, step;
	public TextField spacing;
	public int adding;
	public Label addtypeLabel;
	public boolean dragging;
	public int beginX, beginY, endX, endY;

	public Manager(ObjectList thelist1, BoardCanvas thebcanvas, Button theaddtype, Button thestep, Label theaddtypeLabel, TextField theSpacing)
		{
		list1 = thelist1;
		bcanvas = thebcanvas;
		addtype = theaddtype;
		spacing = theSpacing;
		step = thestep;
		adding = 0;
		addtypeLabel = theaddtypeLabel;
		}

		public void mouseEntered(MouseEvent e)
			{
			//System.out.println("Mouse Entered!!");
			}

		public void mouseExited(MouseEvent e)
			{
			//System.out.println("Mouse Exited");
			}

		public void mouseDragged(MouseEvent e)
			{
			}

		/*public void mouseClicked(MouseEvent e)
			{
			}*/

		public void mouseReleased(MouseEvent e)	
			{
			endX = e.getX();
			endY = e.getY();
			
			if(beginX == endX && beginY == endY)
				{
				list1.add(adding, beginX, beginY);
				}
			else if(0 < endX && endX < 800 && 0 < endY && endY < 640)
				{
				int numpoints = (int)(distance(beginX, beginY, endX, endY)/convert(spacing.getText()));
				System.out.println("that distance was "+numpoints);
				double rise = (endY-beginY)/numpoints;
				double run = (endX-beginX)/numpoints;
				for(int i = 0; i < numpoints; i++)
					{
					list1.add(adding, beginX, beginY);
					beginX += run;
					beginY += rise; 
					}
				}
			System.out.println("Mouse Released at "+endX+","+endY);
			bcanvas.repaint();
			}

		public void mousePressed(MouseEvent e)
			{
			beginX = e.getX();
			beginY = e.getY();
			System.out.println("Mouse Pressed at "+beginX+","+beginY);
			}

	public double distance(double x1, double y1, double x2, double y2)
		{
		//System.out.println("y1: " + y1+ " y2: "+ y2+ " x1 " +x1+" x2: "+x2);
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		}

	public void actionPerformed(ActionEvent e)
		{
		if(e.getSource() == addtype)
			{
			if(adding == 8)
				adding = 0;
			else
				adding++;
			if(adding == 0)
				addtypeLabel.setText(new BlueLight(0,0).getDesc());
			else if(adding == 1)
				addtypeLabel.setText(new SimpleCar(0,0).getDesc());
			else if(adding == 2)
				addtypeLabel.setText(new FlockCar1(0,0).getDesc());
			else if(adding == 3)
				addtypeLabel.setText(new BlueWallFollower(0,0).getDesc());
			else if(adding == 4)
				addtypeLabel.setText(new LightOnCar(0,0).getDesc());
			else if(adding == 5)
				addtypeLabel.setText(new BlueGreenCar(0,0).getDesc());
			else if(adding == 6)
				addtypeLabel.setText(new RedAvoider(0,0).getDesc());
			else if(adding == 7)
				addtypeLabel.setText(new RedLight(0,0).getDesc());
			else if(adding == 8)
				addtypeLabel.setText(new GreenFollower(0,0).getDesc());
			}

		else if(e.getSource() == step)
			{
			//list1.go();
//			list1.repaint();
			bcanvas.toggle();
			}
		}

	private int convert(String s)
		{
		try
			{
			return (new Integer(s)).intValue();
			}
		catch (Throwable t)
			{
			return 15;
			}
		}
	}
