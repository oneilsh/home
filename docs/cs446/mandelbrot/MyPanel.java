import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class MyPanel extends EventPanel
{
	private int pixels[];
	private double zbuffer[][];
	private double xlow, xhigh, ylow, yhigh;
	private int width, timeszoomed;

	public MyPanel()
	{
	timeszoomed = 1;
	xlow = -.75;
	xhigh = 2;
	ylow = -1.375;
	yhigh = 1.375;
	width = 600;
	pixels = new int[width*width];
	zbuffer = new double[width][width];

	for(int h = 0; h < width; h++)
		{
		for(int v = 0; v < width; v++)
			{
			zbuffer[h][v] = -10000;
			}
		}

	makeFractal(xlow, xhigh, ylow, yhigh, width);
	}

	public void mousePressed(MouseEvent e)
		{
		timeszoomed++;
		double x = e.getX(), y = e.getY();

		double dx = (xhigh - xlow);
		double dy = (yhigh - ylow);
		double newxcenter = (x/width)*dx + xlow;
		double newycenter = (y/width)*dy + ylow;
		xlow = newxcenter - (dx/8);
		xhigh = newxcenter + (dx/8);
		ylow = newycenter - (dy/8);
		yhigh = newycenter + (dy/8);
		makeFractal(xlow, xhigh, ylow, yhigh, width);
		repaint();
		}

	public void keyPressed(KeyEvent e)

		{

		char key = e.getKeyChar();

		if (key == 'O' || key == 'o')
			{
			timeszoomed--;
			double dx = (xhigh - xlow);
			double dy = (yhigh - ylow);
			double newxcenter = (.5)*dx + xlow;
			double newycenter = (.5)*dy + ylow;
			xlow = newxcenter - (dx*2);
			xhigh = newxcenter + (dx*2);
			ylow = newycenter - (dy*2);
			yhigh = newycenter + (dy*2);
			makeFractal(xlow, xhigh, ylow, yhigh, width);
			repaint();
			}
		}

	private void setPixel(int pixels[], int h, int v, int color)
		{
		pixels[h +width*v]= color;
		}

	public void paint(Graphics g)
		{
		Image image = createImage(new MemoryImageSource(width, width, pixels, 0, width));
		g.drawImage(image, 0, 0, null);
		}

	private void makeFractal(double xlow, double xhigh, double ylow, double yhigh, double width)
		{
		double maxcount = 50+timeszoomed*3;
		if(maxcount > 90)
			maxcount = 90;
		System.out.println(""+maxcount);
		double dx = (xhigh - xlow)/width;
		double dy = (yhigh - ylow)/width;
		double a = xlow;
		for(int h = 0; h < width; h++)
			{
			double b = ylow;
			for(int v = 0; v <width; v++)
				{
				double count = 0;
				double aa = a;
				double bb = b;
				while(4 > aa*aa + bb*bb && count < maxcount)
					{
					count++;
					double aaa = aa*aa -bb*bb -a;
					bb = 2*aa*bb-b;
					aa = aaa;
					}
				int green = (int)(255*(count/maxcount));
				if (count == maxcount)
					{
					green = 0;
					setPixel(pixels, h, v, 0xff000000 | (green << 8) | (green));

					}
				else
					{
					zbuffer[h][v] = 80;
					setPixel(pixels, h, v, 0xff000000 | (green << 8) | (green));
					}
				b+=dy;
				}
			a+= dx;
			}
		}

	private int colorInt(int red, int green, int blue)
		{
		return (0xff00000 | (red << 16) | (green << 8) | (blue));
		}

	private int getBlue(int colorInt)
		{
		return 0x000000ff & colorInt;
		}

	private int getRed(int colorInt)
		{
		return 0x00ff0000 & colorInt;
		}

	private int getGreen(int colorInt)
		{
		return (0x0000ff00 & colorInt) >> 8;
		}
}