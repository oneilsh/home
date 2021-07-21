import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class MyPanel extends EventPanel
{
	private Quad objects[];
	private int howmanyObjects;
	private double xLow, xHigh, yHigh;
	private int backgroundColor;
	private int width;
	private int pixels[];
	private double eye[], sun[], negsun[];
	private double sunSize;

	public MyPanel()
	{
	sunSize = .98;
	backgroundColor = 0xffbbbbff;
	howmanyObjects = 0;
	width = 399;
	pixels = new int[width*width];
	xLow = -5;
	yHigh = xHigh = 5;
	sun = new double[3];
	sun[0] = 1.5;
	sun[1] = -2;
	sun[2] = 3;
	double sunDistance = Math.sqrt(sun[0]*sun[0] + sun[1]*sun[1] + sun[2]*sun[2]);
	sun[0] /= sunDistance;
	sun[1] /= sunDistance;
	sun[2] /= sunDistance;
	negsun = new double[3];
	negsun[0] = -sun[0];
	negsun[1] = -sun[1];
	negsun[2] = -sun[2];
	eye = new double[3];
	eye[0] = 0;
	eye[1] = 0;
	eye[2] = -60;

	sceneReflectSpheres();

	fillPixels();
	}

	private void sceneBars()
		{
		howmanyObjects = 1;
		objects = new Quad[howmanyObjects];

		objects[0] = new Quad(2,2,0,  0,0,0,  0,0,0,  -1);
		objects[0].setSunSpot(true);
		objects[0].setShadeColor(new Color(0,200,200));
		objects[0].setBrightColor(new Color(0,55,55));
		objects[0].translate(1,-1,0);
		objects[0] = new Quad(2,2,0,  0,0,0,  0,0,0,  -1);
		objects[0].setSunSpot(true);
		objects[0].setShadeColor(new Color(0,200,200));
		objects[0].setBrightColor(new Color(0,55,55));
		objects[0].translate(1,-1,0);
		objects[0] = new Quad(2,2,0,  0,0,0,  0,0,0,  -1);
		objects[0].setSunSpot(true);
		objects[0].setShadeColor(new Color(0,200,200));
		objects[0].setBrightColor(new Color(0,55,55));
		objects[0].translate(1,-1,0);
		//objects[0].rotateY(5);
		//objects[0].setReflect(.9);
		}

	private void sceneReflectingBalls()
		{
		howmanyObjects = 3;
		objects = new Quad[howmanyObjects];

		objects[0] = new Quad(.5,.5,.5,  0,0,0,  0,0,0,  -1);
		objects[0].setSunSpot(true);
		objects[0].setShadeColor(new Color(0,200,200));
		objects[0].setBrightColor(new Color(0,55,55));
		objects[0].translate(2,0,0);
		objects[0].setReflect(.9);

		objects[1] = new Quad(.5,.5,.5,  0,0,0,  0,0,0,  -1);
		objects[1].setSunSpot(true);
		objects[1].setShadeColor(new Color(0,200,200));
		objects[1].setBrightColor(new Color(0,55,55));
		objects[1].translate(-2,0,0);
		objects[1].setReflect(.8);

		objects[2] = new Quad( 1,1,1,  0,0,0,  0,0,0,  -1);
		objects[2].setSunSpot(true);
		objects[2].setShadeColor(new Color(0,0,200));
		objects[2].setBrightColor(new Color(0,0,55));
		objects[2].translate(-1.5,3,-2);
		}

	private void sceneReflectSpheres()
		{
		howmanyObjects = 6;
		objects = new Quad[howmanyObjects];

		objects[0] = new Quad(.04,.04,16,  0,0,0,  0,0,0,  -1);
		objects[0].setSunSpot(true);
		objects[0].setShadeColor(new Color(0,105,105));
		objects[0].setBrightColor(new Color(0,150,150));
		objects[0].translate(0,0,-2);
		objects[0].setReflect(.2);

		objects[1] = new Quad( .75, .75, .75,  0,0,0,  0,0,0,  -1);
		objects[1].setSunSpot(true);
		objects[1].setShadeColor(new Color(105,0,0));
		objects[1].setBrightColor(new Color(150,0,0));
		objects[1].translate(3,3,-4);


		objects[2] = new Quad( .5,.5,.5,  0,0,0,  0,0,0,  -1);
		objects[2].setSunSpot(true);
		objects[2].setShadeColor(new Color(0,0,105));
		objects[2].setBrightColor(new Color(0,0,150));
		objects[2].translate(3,-3,-4);


		objects[3] = new Quad(1 ,1, 1,  0,0,0,  0,0,0,  -1);
		objects[3].setSunSpot(true);
		objects[3].setShadeColor(new Color(0,105,0));
		objects[3].setBrightColor(new Color(0,150,0));
		objects[3].translate(-3,3,-4);

		objects[4] = new Quad(2 ,2, .5,  0,0,0,  0,0,0,  -1);
		objects[4].setSunSpot(true);
		objects[4].setShadeColor(new Color(105,105,0));
		objects[4].setBrightColor(new Color(150,150,0));
		objects[4].translate(-3,-2,-4);

		objects[5] = new Quad(.8 ,.8, .8,  0,0,0,  0,0,0,  -1);
		objects[5].setSunSpot(true);
		objects[5].setShadeColor(new Color(125,125,125));
		objects[5].setBrightColor(new Color(130,130,130));
		objects[5].translate(0,0,-4);
		objects[5].setReflect(.8);

		for(int i = 0; i < howmanyObjects; i++)
			{
			objects[i].rotateZ(75);
			objects[i].rotateX(180);
			objects[i].translate(0,-3,0);
			}
		}

	private void sceneTowerandSpheres()
		{
		howmanyObjects = 5;
		objects = new Quad[howmanyObjects];

		objects[0] = new Quad(1 ,-1, 1,  0,0,0,  0,0,0,  -1);
		objects[0].setSunSpot(true);
		objects[0].setShadeColor(new Color(125,0,125));
		objects[0].setBrightColor(new Color(130,0,130));
		objects[0].translate(0,0,-2);
		//objects[0].setReflect(3.5);

		objects[1] = new Quad(1 ,1, 1,  0,0,0,  0,0,0,  -1);
		objects[1].setSunSpot(true);
		objects[1].setShadeColor(new Color(125,125,0));
		objects[1].setBrightColor(new Color(130,130,0));
		objects[1].translate(-3,1.5,-5);
		//objects[1].setReflect(.5);

		objects[2] = new Quad(1 ,1, 1,  0,0,0,  0,0,0,  -1);
		objects[2].setSunSpot(true);
		objects[2].setShadeColor(new Color(125,125,0));
		objects[2].setBrightColor(new Color(130,130,0));
		objects[2].translate(3,-1.5,-5);
		//objects[2].setReflect(.35);

		objects[3] = new Quad(1.5 ,1.5, 1.5,  0,0,0,  0,0,0,  -1);
		objects[3].setSunSpot(true);
		objects[3].setShadeColor(new Color(125,125,0));
		objects[3].setBrightColor(new Color(130,130,0));
		objects[3].translate(3,1,-1);

		objects[4] = new Quad(2 ,2, 2,  0,0,0,  0,0,0,  -1);
		objects[4].setSunSpot(true);
		objects[4].setShadeColor(new Color(125,125,0));
		objects[4].setBrightColor(new Color(130,130,0));
		objects[4].translate(-3,-1,-6);
		}

	private void fillPixels()
		{
		double dir[] = new double[3];
		dir[2] = -eye[2];
		double dx = (xHigh - xLow) / (width -1);
		double y = yHigh;
		for(int v = 0; v < width; v++)
			{
			dir[1] = y - eye[1] + 0.01;
			double x = xLow;
			for(int h = 0; h < width; h++)
				{
				dir[0] = x - eye[0] + 0.02;
				setPixel(pixels, h, v, findColor(eye, dir));
				x+=dx;
				}
			y-= dx;
			}
		}

	public int findColor(double eye[],double dir[])
		{
		int objectNumber = -1;
		double smallT = 1.0e20;
		for(int n = 0; n < howmanyObjects; n++)
			{
			double t = objects[n].getSmallT(eye, dir);
			if(t < smallT && t >= .0001)
				{
				smallT = t;
				objectNumber = n;
				}
			}
		if(objectNumber < 0)
			return backgroundColor;
		else
			{
			int shadered = objects[objectNumber].getShadeColor().getRed();
			int shadegreen = objects[objectNumber].getShadeColor().getGreen();
			int shadeblue = objects[objectNumber].getShadeColor().getBlue();
			int brightred = objects[objectNumber].getBrightColor().getRed();
			int brightgreen = objects[objectNumber].getBrightColor().getGreen();
			int brightblue = objects[objectNumber].getBrightColor().getBlue();
			double normal[] = objects[objectNumber].getNormal(smallT, dir, eye);
			double sunSpotValue = roundsunspot(normal, dir, negsun);
			if(objects[objectNumber].getReflect() == 0)
				{
				if(!checkforShadow(eye, negsun, dir, smallT))
					{
					if(objects[objectNumber].getSunSpot())
						{
						if(sunSpotValue < sunSize)
							{
							double intensity = dot(sun, normal);
							if(intensity < 0) intensity = 0;
							int red = (int)(brightred*intensity + shadered);
							int green = (int)(brightgreen*intensity + shadegreen);
							int blue = (int)(brightblue*intensity + shadeblue);
							int wholecolor = (255 << 24) + (red << 16) + (green << 8) + blue;
							return wholecolor;
							}
						else
							{
							double intensity = dot(sun, normal);
							if(intensity < 0) intensity = 0;
							int red = (int)(brightred*intensity + shadered);
							int green = (int)(brightgreen*intensity + shadegreen);
							int blue = (int)(brightblue*intensity + shadeblue);
							int wholecolor = (255-(int)(255*(sunSpotValue-sunSize)*(1/(1-sunSize))) << 24) + (red << 16) + (green << 8) + blue;
							return wholecolor;
							}
						}
					else
						{
						double intensity = dot(sun, normal);
						if(intensity < 0) intensity = 0;
						int red = (int)(brightred*intensity + shadered);
						int green = (int)(brightgreen*intensity + shadegreen);
						int blue = (int)(brightblue*intensity + shadeblue);
						int wholecolor = (255 << 24) + (red << 16) + (green << 8) + blue;
						return wholecolor;
						}
					}
				else
					{
					int red = shadered;
					int green = shadegreen;
					int blue = shadeblue;
					int wholecolor = (255 << 24) + (red << 16) + (green << 8) + blue;
					return wholecolor;
					}
				}
			else
				{
				if(sunSpotValue < sunSize)
					{
					double reflect = objects[objectNumber].getReflect();
					//double intensity = dot(sun, normal);
					//if(intensity < 0) intensity = 0;
					double neweye[] = getPoint(eye, dir, smallT);
					double newdir[] = getReflectedRay(normal, dir);
					int reflectColor = findColor(neweye, newdir);
					int reflectRed = (reflectColor >> 16) & 255;
					int reflectGreen = (reflectColor >> 8) & 255;
					int reflectBlue = reflectColor & 255;
					//int reflectRed = (0x00110000 | reflectColor) >> 16;
					//int reflectGreen = (0x00001100 | reflectColor) >> 8;
					//int reflectBlue = (0x00000011 | reflectColor);
					int red = (int)((1-reflect)*brightred + reflect*reflectRed);
					int green = (int)((1-reflect)*brightgreen + reflect*reflectGreen);
					int blue = (int)((1-reflect)*brightblue + reflect*reflectBlue);
					int wholecolor = (255 << 24) + (red << 16) + (green << 8) + blue;
					return wholecolor;
					}
				else
					{
					double reflect = objects[objectNumber].getReflect();
					//double intensity = dot(sun, normal);
					//if(intensity < 0) intensity = 0;
					double neweye[] = getPoint(eye, dir, smallT);
					double newdir[] = getReflectedRay(normal, dir);
					int reflectColor = findColor(neweye, newdir);
					int reflectRed = (reflectColor >> 16) & 255;
					int reflectGreen = (reflectColor >> 8) & 255;
					int reflectBlue = reflectColor & 255;
					//int reflectRed = (0x00110000 | reflectColor) >> 16;
					//int reflectGreen = (0x00001100 | reflectColor) >> 8;
					//int reflectBlue = (0x00000011 | reflectColor);
					int red = (int)((1-reflect)*brightred + reflect*reflectRed);
					int green = (int)((1-reflect)*brightgreen + reflect*reflectGreen);
					int blue = (int)((1-reflect)*brightblue + reflect*reflectBlue);
					int wholecolor = (255-(int)(255*(sunSpotValue-sunSize)*(1/(1-sunSize))) << 24) + (red << 16) + (green << 8) + blue;
					return wholecolor;
					}
				}

			}
		}

	public boolean sunspot(double normal[], double dir[], double sundir[])
		{
		double reflectedRay[] = new double[3];
		reflectedRay = getReflectedRay(normal, dir);
		if(Math.abs(reflectedRay[0]-sundir[0]) < .15 && Math.abs(reflectedRay[1]-sundir[1]) < .15 && Math.abs(reflectedRay[2]-sundir[2]) < .15)
			return true;
		return false;
		}

	public double[] getPoint(double eye[], double dir[], double t)
		{
		double point[] = new double[3];
		point[0] = eye[0] +t*dir[0];
		point[1] = eye[1] +t*dir[1];
		point[2] = eye[2] +t*dir[2];
		return point;
		}

	public double[] getReflectedRay(double normal[], double dir[])
		{
		double component[] = new double[3];
		double reflectedRay[] = new double[3];
		double dot = dot(dir, normal);
		component[0] = -dot*normal[0];
		component[1] = -dot*normal[1];
		component[2] = -dot*normal[2];
		reflectedRay[0] = dir[0]+2*component[0];
		reflectedRay[1] = dir[1]+2*component[1];
		reflectedRay[2] = dir[2]+2*component[2];
		double rayDistance = Math.sqrt(reflectedRay[0]*reflectedRay[0] + reflectedRay[1]*reflectedRay[1] + reflectedRay[2]*reflectedRay[2]);
		reflectedRay[0] /= rayDistance;
		reflectedRay[1] /= rayDistance;
		reflectedRay[2] /= rayDistance;
		return reflectedRay;
		}

	public double roundsunspot(double normal[], double dir[], double sundir[])
		{
		//if(dot(getReflectedRay(normal, dir), sundir) > .98)
			//return true;
		//return false;
		return dot(getReflectedRay(normal, dir), sundir);
		}

	public boolean checkforShadow(double oldeye[], double newdir[], double olddir[],double oldt)
		{
		double neweye[] = getPoint(oldeye, olddir, oldt);
		int objectNumber = -1;
		double smallT = 1.0e20;
		for(int n = 0; n < howmanyObjects; n++)
			{
			double newt = objects[n].getSmallT(neweye, newdir);
			if(newt < smallT && newt >= 0.0001)
				{
				smallT = newt;
				objectNumber = n;
				}
			}
		if(objectNumber < 0)
			return false;
		return true;
		}

	public int getReflectColor(double oldeye[], double normal1[], double olddir[], double oldt)
		{
		return 0;
		}

	public double magnitude(double dir[])
		{
		double mag = Math.sqrt(dir[0]*dir[0] + dir[1]*dir[1] + dir[2]*dir[2]);
		return mag;
		}

	public double dot(double dir[], double normal[])
		{
		double dot = dir[0]*normal[0] + dir[1]*normal[1] + dir[2]*normal[2];
		return dot;
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


}