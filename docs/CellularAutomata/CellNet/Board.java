//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Board
	{
	public Cell array[][];
	public boolean torus;
	PrintWriter outfile, saveoutfile;
	BufferedReader infile;


	public Board()
		{
		try
			{
			outfile = new PrintWriter(new FileOutputStream("results.txt"));
			}
		catch(Throwable error)
			{
			System.out.println("Error: " +error);
			}
		torus = true;
		array = new Cell[102][102];
		initBlank();
		}

	public void initBlank()
		{
		for(int x = 0; x < 102; x++)
			for(int y = 0; y < 102; y++)
				{
				array[x][y] = new BlankCell();
				}
		}

	public void initBrain()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new BrainCell();
				}
		}

	public void initLife()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new LifeCell();
				}
		}
	public void initLife2()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new Life2Cell();
				}
		}
	public void initRace()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new RaceCell();
				}
		}
	public void initRace2()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new Race2Cell();
				}
		}
	public void initRace3()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new Race3Cell();
				}
		}
	public void initRace4()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new Race4Cell();
				}
		}
	public void initRand()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new RandCell();
				}
		}
	public void initRand2()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new Rand2Cell();
				}
		}
	public void initRand3()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new Rand3Cell();
				}
		}
	public void initRand4()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new Rand4Cell();
				}
		}
	public void initAge()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new AgeCell();
				}
		}
	public void initFiveRace()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y] = new FiveRace();
				}
		}
	public void stateChange(int x, int y)
		{
		array[x][y].switchState();
		}

	public void addBlank(int x, int y)
		{
		array[x][y] = new BlankCell();
		}

	public void addLifeCell(int x, int y)
		{
		array[x][y] = new LifeCell();
		}

	public void addBrainCell(int x, int y)
		{
		array[x][y] = new BrainCell();
		}

	public void switchtorus()
		{
		torus = !torus;
		}

	public void computeNext()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				int whites = 0;
				int blacks = 0;
				int reds = 0;
				int nones = 0;
				int greens = 0;
				int blues = 0;
				int xm,ym,xp,yp;

				if(x-1 < 1 && torus)
					xm = 100;
				else
					xm = x-1;

				if(y-1 < 1 && torus)
					ym = 100;
				else
					ym = y-1;

				if(y+1 > 100 && torus)
					yp = 1;
				else
					yp = y+1;

				if(x+1 > 100 && torus)
					xp = 1;
				else
					xp = x+1;

				if(array[xm][ym].getState() == Color.black)
					blacks++;
				else if(array[xm][ym].getState() == Color.red)
					reds++;
				else if(array[xm][ym].getState() == Color.green)
					greens++;
				else if(array[xm][ym].getState() == Color.blue)
					blues++;
				else if(array[xm][ym].getState() == Color.white)//array[x-1][y-1].getBackgroundColor())
					whites++;
				else if(array[xm][ym].getState() == Color.gray)
					nones++;

				if(array[xm][y].getState() == Color.black)
					blacks++;
				else if(array[xm][y].getState() == Color.red)
					reds++;
				else if(array[xm][y].getState() == Color.green)
					greens++;
				else if(array[xm][y].getState() == Color.blue)
					blues++;
				else if(array[xm][y].getState() == Color.white)//array[x-1][y].getBackgroundColor())
					whites++;
				else if(array[xm][y].getState() == Color.gray)
					nones++;


				if(array[xm][yp].getState() == Color.black)
					blacks++;
				else if(array[xm][yp].getState() == Color.red)
					reds++;
				else if(array[xm][yp].getState() == Color.green)
					greens++;
				else if(array[xm][yp].getState() == Color.blue)
					blues++;
				else if(array[xm][yp].getState() == Color.white)//array[x-1][y+1].getBackgroundColor())
					whites++;
				else if(array[xm][yp].getState() == Color.gray)
					nones++;


				if(array[x][ym].getState() == Color.black)
					blacks++;
				else if(array[x][ym].getState() == Color.red)
					reds++;
				else if(array[x][ym].getState() == Color.green)
					greens++;
				else if(array[x][ym].getState() == Color.blue)
					blues++;
				else if(array[x][ym].getState() == Color.white)//array[x][y-1].getBackgroundColor())
					whites++;
				else if(array[x][ym].getState() == Color.gray)
					nones++;

				if(array[x][yp].getState() == Color.black)
					blacks++;
				else if(array[x][yp].getState() == Color.red)
					reds++;
				else if(array[x][yp].getState() == Color.green)
					greens++;
				else if(array[x][yp].getState() == Color.blue)
					blues++;
				else if(array[x][yp].getState() == Color.white)//array[x][y+1].getBackgroundColor())
					whites++;
				else if(array[x][yp].getState() == Color.gray)
					nones++;

				if(array[xp][ym].getState() == Color.black)
					blacks++;
				else if(array[xp][ym].getState() == Color.red)
					reds++;
				else if(array[xp][ym].getState() == Color.green)
					greens++;
				else if(array[xp][ym].getState() == Color.blue)
					blues++;
				else if(array[xp][ym].getState() == Color.white)//array[x+1][y-1].getBackgroundColor())
					whites++;
				else if(array[xp][ym].getState() == Color.gray)
					nones++;

				if(array[xp][y].getState() == Color.black)
					blacks++;
				else if(array[xp][y].getState() == Color.red)
					reds++;
				else if(array[xp][y].getState() == Color.green)
					greens++;
				else if(array[xp][y].getState() == Color.blue)
					blues++;
				else if(array[xp][y].getState() == Color.white)//array[x+1][y].getBackgroundColor())
					whites++;
				else if(array[xp][y].getState() == Color.gray)
					nones++;

				if(array[xp][yp].getState() == Color.black)
					blacks++;
				else if(array[xp][yp].getState() == Color.red)
					reds++;
				else if(array[xp][yp].getState() == Color.green)
					greens++;
				else if(array[xp][yp].getState() == Color.blue)
					blues++;
				else if(array[xp][yp].getState() == Color.white)//array[x+1][y+1].getBackgroundColor())
					whites++;
				else if(array[xp][yp].getState() == Color.gray)
					nones++;

				array[x][y].workNow(blacks, reds, greens, blues, nones, whites);
				}
		}

	public void updateNow()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y].setToNextState();
				}
		}

	public void closeFile()
		{
		outfile.close();
		}

	public void getNumbers()
		{
		int blacks = 0;
		int reds = 0;
		int greens = 0;
		int blues = 0;
		int whites = 0;
		int grays = 0;
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				if(array[x][y].getState() == Color.black)
					blacks++;
				if(array[x][y].getState() == Color.red)
					reds++;
				if(array[x][y].getState() == Color.green)
					greens++;
				if(array[x][y].getState() == Color.blue)
					blues++;
				if(array[x][y].getState() == Color.white)
					whites++;
				if(array[x][y].getState() == Color.gray)
					grays++;
				}
		int total = blacks+reds+greens+blues+whites;
		try
			{
			//outfile.println(""+whites+","+blues+","+blacks+","+reds+","+greens+","+total);
			outfile.println(""+whites+" "+blues+" "+blacks+" "+reds+" "+greens);
			}
		catch(Throwable error)
			{
			System.out.println("Error Opening Output File: "+error);
			}
		//write to file the generation, then blacks, reds, greens, blues, and whites
		}

	public void act1()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y].act1();
				}
		}

	public void act2()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y].act2();
				}
		}
		
	public void openBoard()
		{
		try
			{
 		   infile = new BufferedReader (new FileReader ("savedboard.txt"));
 		   }
		catch(Throwable error)
			{
			System.out.println("Error reading board file: "+error);
			}
		int x;
		int y;
		int stateInt;
		int nextStateInt;
		try
			{
			String line = infile.readLine();
			while(line != null)
				{
				//System.out.println(line);
				java.util.StringTokenizer st = new java.util.StringTokenizer( line, " " );
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				stateInt = Integer.parseInt(st.nextToken());
				nextStateInt = Integer.parseInt(st.nextToken());
				
				if(stateInt == 1)
					array[x][y].setState(Color.white);
				else if(stateInt == 2)
					array[x][y].setState(Color.blue);
				else if(stateInt == 3)
					array[x][y].setState(Color.black);				
				else if(stateInt == 4)
					array[x][y].setState(Color.red);				
				else if(stateInt == 5)
					array[x][y].setState(Color.green);
				else if(stateInt == 6)
					array[x][y].setState(Color.gray);
				
				if(nextStateInt == 1)
					array[x][y].setNextState(Color.white);
				else if(nextStateInt == 2)
					array[x][y].setNextState(Color.blue);
				else if(nextStateInt == 3)
					array[x][y].setNextState(Color.black);
				else if(nextStateInt == 4)
					array[x][y].setNextState(Color.red);
				else if(nextStateInt == 5)
					array[x][y].setNextState(Color.green);
				else if(nextStateInt == 6)
					array[x][y].setNextState(Color.gray);				
					
				line = infile.readLine();
				}
			infile.close();
			}
		catch(Throwable error)
			{
			System.out.println("Error opening board file for read: "+error);
			}
		}
		
	public void saveBoard()
		{
		try
			{
			saveoutfile = new PrintWriter(new FileOutputStream("savedboard.txt"));
			}		
		catch(Throwable error)
			{
			System.out.println("Error opening boardfile for saving: "+error);
			}
		try
			{
			int nextStateInt = 0;
			int stateInt = 0;
			for(int x = 1; x < 101; x++)
				{
				for(int y = 1; y < 101; y++)
					{
					//outfile.println(""+whites+","+blues+","+blacks+","+reds+","+greens+","+total);
					if(array[x][y].getState() == Color.white)
						stateInt = 1;
					else if(array[x][y].getState() == Color.blue)
						stateInt = 2;
					else if(array[x][y].getState() == Color.black)
						stateInt = 3;
					else if(array[x][y].getState() == Color.red)
						stateInt = 4;
					else if(array[x][y].getState() == Color.green)
						stateInt = 5;
					else if(array[x][y].getState() == Color.gray)
						stateInt = 6;
						
					if(array[x][y].getNextState() == Color.white)
						nextStateInt = 1;
					else if(array[x][y].getNextState() == Color.blue)
						nextStateInt = 2;
					else if(array[x][y].getNextState() == Color.black)
						nextStateInt = 3;
					else if(array[x][y].getNextState() == Color.red)
						nextStateInt = 4;
					else if(array[x][y].getNextState() == Color.green)
						nextStateInt = 5;
					else if(array[x][y].getNextState() == Color.gray)
						nextStateInt = 6;	
						
					saveoutfile.println(""+x+" "+y+" "+stateInt+" "+nextStateInt);
					}
				}
			saveoutfile.close();
			System.out.println("done");
			}
		catch(Throwable error)
			{
			System.out.println("Error Opening SavedBoard File To Write: "+error);
			}
		}

	public void randomizeLowHigh(int prob)
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y].randomizeLowHigh(prob);
				}
		}

	public void clear()
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				array[x][y].clear();
				}
		}

	public void paint(Graphics g)
		{
		for(int x = 1; x < 101; x++)
			for(int y = 1; y < 101; y++)
				{
				//g.setColor(array[x][y].getBackgroundColor());
				//g.fillRect(x*6+10, y*6+10, 6, 6);
				g.setColor(array[x][y].getState());
				g.fillRect(x*6+11, y*6+11, 4, 4);
				}
		}

	}
