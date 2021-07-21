import java.awt.*;
import java.awt.event.*;


public class ThingList
	{
	public static final int MAX = 50;

	private Tree treelist[];
	private House houselist[];
	private Curve curvelist[];
	private int howManyTrees, howManyHouses, howManyCurves;

	public ThingList()
		{
		treelist = new Tree[MAX];
		houselist = new House[MAX];
		curvelist = new Curve[MAX];
		howManyTrees = 0;
		howManyHouses = 0;
		howManyCurves = 0;

		}

	public void paint(Graphics g)
		{
		ScaledGraphics sg = new ScaledGraphics(g, 400);
		Tree tree1 = new Tree (.01, .21, .78, .98);
		House house1 = new House(.41, .59, .85, .93);
		Curve curve1 = new Curve(.78, .98, .78, .98);
		tree1.paint(sg);
		house1.paint(sg);
		curve1.paint(sg);
		for (int x=0; x<howManyTrees; x++)
			treelist[x].paint(sg);
		for (int y=0; y<howManyHouses; y++)
			houselist[y].paint(sg);
		for (int z=0; z<howManyCurves; z++)
			curvelist[z].paint(sg);
		}


	public void addTree(Tree aTree)
		{
		if(howManyTrees<MAX)
			{
			treelist[howManyTrees] = aTree;
			howManyTrees++;
			}
		}

	public void addHouse(House aHouse)
		{
		if(howManyHouses<MAX)
			{
			houselist[howManyHouses] = aHouse;
			howManyHouses++;
			}
		}

	public void addCurve(Curve aCurve)
		{
		if(howManyCurves<MAX)
			{
			curvelist[howManyCurves] = aCurve;
			howManyCurves++;
			}
		}

	/*public void delete()
		{
		if(howMany > 0)
			{
			for(int x=0; x<howMany; x++)
				{
				if(list[x].marked())
					{
					for(int a=x; a<howMany; a++)
						{
						list[a] = list[a+1];
						}
					howMany--;
					}
				}
			}
		}


	public void bigger()
		{
		for(int x=0; x<howMany; x++)
			{
			list[x].bigger();
			}
		}
		*/


	}
