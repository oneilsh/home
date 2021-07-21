//Shawn O'Neil - CS422 

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class tape
	{
	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
	
	//handles input
	public static void main(String[] args) throws Exception
		{
		
		while(true)
			{
			String tapes = in.readLine();
			if(tapes == null) break;
			StringTokenizer st = new StringTokenizer(tapes);
			int tapesct = 0;
			int[] tlengths = new int[10];
			while(st.hasMoreTokens())
				{
				tlengths[tapesct++] = Integer.parseInt(st.nextToken());
				}
			int[] secs = new int[100];
			for(int i = 0; i < secs.length; i++) secs[i] = -1;
   		int sct = 0;
			while(true)
				{
				String s = in.readLine();
    			if(s.equals ("%")) break;
    			
    			st = new StringTokenizer(s);
    			String t = st.nextToken();
    	    	int minsec = Integer.parseInt (t.substring(0,t.length()-1));
    	    	t = st.nextToken();
				minsec = 60*minsec + Integer.parseInt (t.substring(0,t.length()-1));
				secs[sct++] = minsec;
				}
			printStuff(tlengths, secs, tapesct, sct);
			}
		}
	
	//takes in the arrays and does all the array type stuff
	public static void printStuff(int tlengths[], int secs[], int tapesct, int sct)
		{
		//this stuff handles the case where no songs are given
		boolean gotinput = false;
		for(int i = 0; i < secs.length; i++)
			if(secs[i] != -1) gotinput = true;
		if(!gotinput)
			{
			System.out.println("Side A");
			System.out.println("Side B");
			System.out.println("%");
			return;
			}
		
		//make the table
		int totalsecs = 0;
		for(int i = 0; i < sct; i++) totalsecs += secs[i];
		boolean[][] table = new boolean[sct][(int)(totalsecs/2)+1];
		for(int i = 0; i < table[0].length; i++) //how many "cents" i am trying to make
			{
			for(int j = 0; j < sct; j++) //number of "coins"
				{
				if(i == 0) {table[j][i] = true; continue;}
				if(j != 0)
					if(table[j-1][i] == true) {table[j][i] = true; continue;}

				int newcents = i - secs[j];
				int newj = j-1;
				if(newcents == 0) {table[j][i] = true; continue;}
				if(newcents < 0 || newj < 0) {table[j][i] = false; continue;}
				if(table[newj][newcents]) {table[j][i] = true; continue;}
				table[j][i] = false;
				}
			}
			
		//figure out which song is in each side
		boolean[] markers = new boolean[table.length];
		int col = table[0].length-1;
		int row = table.length-1;
		while(!table[row][col]) col--;
		int making = col;
		
		//this little section is only in the middle here so i can use the making value before i get rid of it
		int longestside = 0;
		if(totalsecs - making > making) longestside = totalsecs - making;
		else longestside = making;
		
		//continue figuring out which song is in each side
		while(row > 0 && table[row][col]) row--;
		row++;
		while(making > 0)
			{
			making = making - secs[row];
			markers[row] = true;
			if(row > 0) row--;
			while(!table[row][col]) col--;
			while(row > 0 && table[row][col] && making-secs[row] != 0) row--;
			if(making-secs[row] == 0) {markers[row] = true; break;}
			if(row != 0 || !table[row][col]) row++;
			}
		
		int usingtape = 0;
		for(int i = tlengths.length-1; i >= 0; i--)
			if(tlengths[i]*60/2 >= longestside) usingtape = tlengths[i];
		System.out.println(usingtape);
		System.out.println("Side A");
		for(int i = 0; i < markers.length; i++)
			if(markers[i]) printMinSec(secs[i]);
		System.out.println("Side B");
		for(int i = 0; i < markers.length; i++)
			if(!markers[i]) printMinSec(secs[i]);
		System.out.println("%");
		}
	
	//just prints out a sum of seconds as minutes seconds
	public static void printMinSec(int secs)
		{
		int sec = secs%60;
		int min = (int)(secs/60);
		System.out.println(min+"m "+sec+"s");
		}
	}
