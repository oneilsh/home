import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class MyPanel extends EventPanel
{
	private int pixels[];
	private double zbuffer[][];
	private double sunX, sunY, sunZ, xlow, xhigh, ylow, yhigh;
	private int width;

	public MyPanel()
	{
	xlow = -.75;
	xhigh = 2;
	ylow = -1.375;
	yhigh = 1.375;
	width = 300;
	pixels = new int[width*width];
	zbuffer = new double[width][width];
	sunX = 1;
	sunY = 1;
	sunZ = 2;
	double sunDistance = Math.sqrt(sunX*sunX + sunY*sunY + sunZ*sunZ);
	sunX /= sunDistance;
	sunY /= sunDistance;
	sunZ /= sunDistance;

	for(int h = 0; h < width; h++)
		{
		for(int v = 0; v < width; v++)
			{
			zbuffer[h][v] = -10000;
			}
		}

	makeFractal(xlow, xhigh, ylow, yhigh);
	makeSphere(50, 220, 70, 200, 8);
	makeSphere(40, 250, 250, 200, 8);
	makeSphere(25, 200, 60, 200, 16);
	makeSphere(65, 150, 200, 0, 0);
	makeSphere(30, 110, 160, 20 , 0);
	makeSphere(50, 50, 70, 50, 16);
	}
/*
	public void mousePressed(MouseEvent e)
		{

		double x = e.getX(), y = e.getY();

		double dx = (xhigh - xlow)*.25;
		double dy = (yhigh - ylow)*.25;
		xlow = (x/400)*dx;
		xhigh = (x/400)*dx;
		ylow = (y/400)*dy;
		yhigh = (y/400)*dy;
		makeFractal(xlow, xhigh, ylow, yhigh);
		repaint();
		}

*/
	private void setPixel(int pixels[], int h, int v, int color)
		{
		pixels[h +width*v]= color;
		}

	public void paint(Graphics g)
		{
		Image image = createImage(new MemoryImageSource(width, width, pixels, 0, width));
		g.drawImage(image, 0, 0, null);
		}

	private void makeFractal(double xlow, double xhigh, double ylow, double yhigh)
		{
		double dx = (xhigh - xlow)/300;
		double dy = (yhigh - ylow)/300;
		double a = xlow;
		for(int h = 0; h < 300; h++)
			{
			double b = ylow;
			for(int v = 0; v <300; v++)
				{
				int count = 0;
				double aa = a;
				double bb = b;
				while(4 > aa*aa + bb*bb && count < 50)
					{
					count++;
					double aaa = aa*aa -bb*bb -a;
					bb = 2*aa*bb-b;
					aa = aaa;
					}
				int green = (int)(5*count+5);
				if (count == 50)
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

	private void makeSphere(int rad, int ccx, int ccy, int ccz, int colorpush)
		{
		int radius = rad;
		int cx = ccx, cy = ccy;
		for(int v = cy - radius; v <= cy +radius; v++)
			{
			int dx = (int)(Math.sqrt(radius*radius - (cy-v)*(cy-v)));
			for(int h = cx - dx; h <= cx +dx; h++)
				{
				double x = cx-h;
				double z = (int)(Math.sqrt(radius*radius - x*x - (cy-v)*(cy-v)));
				double intensity = (sunX*x +sunY*(cy-v) + sunZ*z)/radius;
				if(z+ccz > zbuffer[h][v])
					{
					zbuffer[h][v] = z +ccz;
					if(intensity < 0) intensity = 0;
					int green = (int)((200*intensity+55)*1 + getGreen(pixels[h+width*v])*0);
					setPixel(pixels, h, v, 0xff000000 | (green << colorpush));
					zbuffer[h][v] = z;
					}
				}
			}

		}

}