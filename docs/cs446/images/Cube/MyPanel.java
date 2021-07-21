import java.awt.*;
import java.awt.event.*;

public class MyPanel extends EventPanel implements ActionListener
{
	private double[][] transform;
	private Button xup, xdown, yup, ydown, zup, zdown, clear;
	private int xrotate, yrotate, zrotate, hstart, hend, vstart, vend;
	private boolean clearbool, dragging;

	public MyPanel()
		{
		hstart = hend = vstart = vend = 0;
		clearbool = false;
		xrotate = yrotate = zrotate = 0;
		setLayout(null);
		transform = Transform.identity();

		clear = new Button("Clear");
		clear.setLocation(10, 10);
		clear.setSize(40, 25);
		add(clear);

		xup = new Button("xup");
		xup.setLocation(300, 20);
		xup.setSize(40, 25);
		add(xup);

		xdown = new Button("xdown");
		xdown.setLocation(350, 20);
		xdown.setSize(40, 25);
		add(xdown);

		yup = new Button("yup");
		yup.setLocation(300, 50);
		yup.setSize(40, 25);
		add(yup);

		ydown = new Button("ydown");
		ydown.setLocation(350, 50);
		ydown.setSize(40, 25);
		add(ydown);

		zup = new Button("zup");
		zup.setLocation(300, 80);
		zup.setSize(40, 25);
		add(zup);

		zdown = new Button("zdown");
		zdown.setLocation(350, 80);
		zdown.setSize(40, 25);
		add(zdown);

		clear.addActionListener(this);
		xup.addActionListener(this);
		xdown.addActionListener(this);
		yup.addActionListener(this);
		ydown.addActionListener(this);
		zup.addActionListener(this);
		zdown.addActionListener(this);
		}

	public void mousePressed(MouseEvent e)
			{
			int x = e.getX(), y = e.getY();
			dragging = true;
			hstart = x;
			vstart = y;
			repaint();
			}

		public void mouseDragged(MouseEvent e)
			{
			if(dragging)
				{
				hend = e.getX();
				vend = e.getY();
				yrotate = hend - hstart;
				xrotate = vend - vstart;
				repaint();
				}
			}

		public void mouseReleased(MouseEvent e)
			{
			hend = e.getX();
			vend = e.getY();
			yrotate = hend - hstart;
			xrotate = vend - vstart;
			dragging = false;
			repaint();
		}

	public void actionPerformed(ActionEvent e)
		{
		if(e.getSource() == clear)
			{
			clearbool = !clearbool;
			}
		if(e.getSource() == xup)
			{
			xrotate += 10;
			}

		if(e.getSource() == xdown)
			{
			xrotate -= 10;
			}

		if(e.getSource() == yup)
			{
			yrotate += 10;
			}

		if(e.getSource() == ydown)
			{
			yrotate -= 10;
			}

		if(e.getSource() == zup)
			{
			zrotate += 10;
			}

		if(e.getSource() == zdown)
			{
			zrotate -= 10;
			}
		repaint();
		}

	public void paint(Graphics g)
		{
		ScaledGraphics sg = new ScaledGraphics(g, 400);
		sg.setWindow(0,1,0,1);
		sg.setViewPort(0,1,0,1);
		sg.pushIdentity();
		sg.pushIdentity();
		sg.modify(Transform.translate(-.5,-.5,-.5));
		sg.modify(Transform.yRotate(yrotate));
		sg.modify(Transform.xRotate(xrotate));
		sg.modify(Transform.zRotate(zrotate));
		sg.modify(Transform.stretch(.5,.5,.5));
		//sg.modify(Transform.translate(.6,.6,.6)); get rid of next
		sg.modify(Transform.translate(.5,.5,.5));
		drawCube(sg);
		sg.clear();
/*
		sg.setWindow(0,1,0,1);
		sg.setViewPort(wx1,wx2,wy1,wy2);
		sg.pushIdentity();
		sg.pushIdentity();
		sg.modify(Transform.translate(-.5,-.5,-.5));
		sg.modify(Transform.yRotate(yrotate));
		sg.modify(Transform.xRotate(xrotate));
		sg.modify(Transform.zRotate(zrotate));
		sg.modify(Transform.stretch(.3,.3,.3));
		//sg.modify(Transform.translate(.2,.2,.2));
		drawCube(sg);
*/
		}

	public void drawCube(ScaledGraphics sg)
		{
		drawSide1(sg);

		sg.pushIdentity();
		sg.modify(Transform.yRotate(-90));
		sg.modify(Transform.translate(0,0,1));
		sg.merge();
		drawSide2(sg);
		sg.pop();

		sg.pushIdentity();
		sg.modify(Transform.yRotate(90));
		sg.modify(Transform.translate(1,0,0));
		sg.merge();
		drawSide3(sg);
		sg.pop();

		sg.pushIdentity();
		sg.modify(Transform.xRotate(90));
		sg.modify(Transform.translate(0,0,1));
		sg.merge();
		drawSide4(sg);
		sg.pop();

		sg.pushIdentity();
		sg.modify(Transform.xRotate(-90));
		sg.modify(Transform.translate(0,1,0));
		sg.merge();
		drawSide5(sg);
		sg.pop();

		sg.pushIdentity();
		sg.modify(Transform.yRotate(180));
		sg.modify(Transform.translate(1,0,1));
		sg.merge();
		drawSide6(sg);

		}

	public void drawSide1(ScaledGraphics sg)
		{
		if(sg.isVisible(0,0,0,1,0,0,1,1,0) || clearbool)
			{
			sg.setColor(Color.black);
			sg.pushIdentity();
			sg.modify(Transform.stretch(.5,.5,1));
			sg.modify(Transform.translate(.45,.45,0));
			sg.merge();
			Tree tree = new Tree();
			tree.paint(sg);
			sg.pop();
			sg.pushIdentity();
			sg.modify(Transform.stretch(.5,.5,1));
			sg.modify(Transform.translate(.05,.05,0));
			sg.merge();
			Tree tree2 = new Tree();
			tree2.paint(sg);
			sg.pop();
			sg.drawLine(0,0,1,0);
			sg.drawLine(1,0,1,1);
			sg.drawLine(1,1,0,1);
			sg.drawLine(0,1,0,0);
			}
		}

	public void drawSide2(ScaledGraphics sg)
		{
		if(sg.isVisible(0,0,0,1,0,0,1,1,0) || clearbool)
			{
			sg.setColor(Color.red);
			sg.pushIdentity();
			sg.modify(Transform.stretch(.15,.15,0));
			sg.modify(Transform.translate(.75,.75,0));
			sg.merge();
			Curve curve = new Curve();
			curve.paint(sg);
			sg.pop();

			sg.pushIdentity();
			sg.modify(Transform.stretch(.15,.15,0));
			sg.modify(Transform.translate(.25,.25,0));
			sg.merge();
			Curve curve2 = new Curve();
			curve2.paint(sg);
			sg.pop();
			sg.setColor(Color.black);
			sg.drawLine(0,0,1,0);
			sg.drawLine(1,0,1,1);
			sg.drawLine(1,1,0,1);
			sg.drawLine(0,1,0,0);
			}
		}

	public void drawSide3(ScaledGraphics sg)
		{
		if(sg.isVisible(0,0,0,1,0,0,1,1,0) || clearbool)
			{
			sg.setColor(Color.green);
			House house = new House();
			house.paint(sg);
			sg.setColor(Color.black);
			sg.drawLine(0,0,1,0);
			sg.drawLine(1,0,1,1);
			sg.drawLine(1,1,0,1);
			sg.drawLine(0,1,0,0);
			}
		}

	public void drawSide4(ScaledGraphics sg)
		{
		if(sg.isVisible(0,0,0,1,0,0,1,1,0) || clearbool)
			{
			sg.setColor(Color.blue);
			Tree tree = new Tree();
			tree.paint(sg);
			sg.setColor(Color.black);
			sg.drawLine(0,0,1,0);
			sg.drawLine(1,0,1,1);
			sg.drawLine(1,1,0,1);
			sg.drawLine(0,1,0,0);
			}
		}

	public void drawSide5(ScaledGraphics sg)
		{
		if(sg.isVisible(0,0,0,1,0,0,1,1,0) || clearbool)
			{
			sg.setColor(Color.yellow);
			sg.pushIdentity();
			sg.modify(Transform.stretch(.5,.5,1));
			sg.modify(Transform.translate(.45,.45,0));
			sg.merge();
			House house = new House();
			house.paint(sg);
			sg.pop();
			sg.pushIdentity();
			sg.modify(Transform.stretch(.5,.5,1));
			sg.modify(Transform.translate(.05,.05,0));
			sg.merge();
			House house2 = new House();
			house2.paint(sg);
			sg.pop();
			sg.setColor(Color.black);
			sg.drawLine(0,0,1,0);
			sg.drawLine(1,0,1,1);
			sg.drawLine(1,1,0,1);
			sg.drawLine(0,1,0,0);
			}
		}

	public void drawSide6(ScaledGraphics sg)
		{
		if(sg.isVisible(0,0,0,1,0,0,1,1,0) || clearbool)
			{
			sg.setColor(Color.gray);
			sg.pushIdentity();
			sg.modify(Transform.stretch(.36,.36,0));
			sg.modify(Transform.translate(.5,.5,0));
			sg.merge();
			Curve curve = new Curve();
			curve.paint(sg);
			sg.pop();
			sg.setColor(Color.black);
			sg.drawLine(0,0,1,0);
			sg.drawLine(1,0,1,1);
			sg.drawLine(1,1,0,1);
			sg.drawLine(0,1,0,0);
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



