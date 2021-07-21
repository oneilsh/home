// Shawn O'Neil
// Assignment 3d - Multiple Digit RPN Calculator

import java.awt.*;
import java.awt.event.*;
public class Manager implements ActionListener
{
	private String poststring, instring;
	private DStack ds;
	private TextField infix, x, postfix, value;
	private double valuedouble, xdouble;
	private double numbers[];
	
	public Manager(TextField i, TextField y, TextField p, TextField v)
		{
		instring = "";
		poststring = "";
		valuedouble = 0;
		xdouble = 0;
		infix = i;
		postfix = p;
		value = v;
		x = y;
		DStack ds = new DStack();
		numbers = new double[100];
		}
		
		
	public void actionPerformed(ActionEvent e)
		{
		xdouble = convert(x.getText());
		instring = infix.getText();
		poststring = convertfix(instring);
		valuedouble = calculate(poststring);
		postfix.setText(poststring);
		value.setText(""+valuedouble);
		}
		
	public double convert(String s)
		{
		String error = "";
		double d = -99999;
		boolean ok = true;
		s = s.trim();
		try
			{
			d = (new Double(s)).doubleValue();
			}
		catch (Throwable err)
			{
			error += err;
			ok = false;
			}
		return d;
		}
		
	public String convertfix(String in)
		{
		int number = 0;
		instring = in;
		instring += ' ';
		CStack cs = new CStack();
		poststring = "";
		boolean firstTime = true;
		for(int n = 0; n<instring.length(); n++)
			{
			char ch = instring.charAt(n);
			if(firstTime && ch == '-')
				ch = '$';
			firstTime = false;
			switch(ch)
				{
				case 'x':
					{
					poststring += ch;
					break;
					}
				case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case '.':
					{
					poststring += '#';
					int m = n+1;
					while(isADigit(instring.charAt(m))) m++;
					numbers[number++] = convert(instring.substring(n,m));
					n = m-1;
					break;
					}
				case '+': case '-': case '/': case '*': case '^': case '$':
					{
					while(leftFirst(cs.peek(), ch))
						poststring += cs.pop();
					cs.push(ch);
					break;
					}
				case '(':
					{
					cs.push(ch);
					firstTime = true;
					break;
					}
				case ')':
					{
					while(cs.peek() != '(')
						poststring += cs.pop();
					cs.pop();
					break;
					}
				case 'r': case 'e': case 'l': case 's': case 'c': case 'a':
					{
					cs.push('(');
					cs.push(ch);
					n++;
					firstTime = true;
					break;
					}
				}
			}
		while(!cs.empty())
			poststring += cs.pop();
			
		return poststring;
		}
		
	private boolean leftFirst(char a, char b)
		{
		if( a == '^' && b == '^')
			return false;
		else if(rank(a) >= rank(b))
			return true;
		return false;
		}
		
	private int rank(char c)
		{
		switch(c)
			{
			case '+': case '-':
				return 1;
			case '*': case '/':
				return 2;
			case '$':
				return 3;
			case '^':
				return 4;
			default: return 0;
			}
		}
	private boolean isADigit(char c)
		{
		if('0' <= c && c <= '9') return true;
		if(c == '.') return true;
		return false;
		}
	
	public double calculate(String t)
		{
		int number = 0;
		DStack ds = new DStack();
		for(int n=0; n<t.length(); n++)
			{
			char ch = t.charAt(n);
			switch(ch)
				{
				case '#':
					{
					ds.push(numbers[number++]);
					break;
					}
				case 'x':
					ds.push(xdouble);
					break;
				case '+':
					{
					double a = ds.pop();
					double b = ds.pop();
					ds.push(b+a);
					break;
					}
				case '-':
					{
					double a = ds.pop();
					double b = ds.pop();
					ds.push(b-a);
					break;
					}			
				case '*':
					{
					double a = ds.pop();
					double b = ds.pop();
					ds.push(b*a);
					break;
					}			
				case '/':
					{
					double a = ds.pop();
					double b = ds.pop();
					ds.push(b/a);
					break;
					}
				case '$':
					{
					double a = ds.pop();
					ds.push(-a);
					break;
					}
				case '^':
					{
					double a = ds.pop();
					double b = ds.pop();
					ds.push(Math.pow(b,a));
					break;
					}
				case 'r':
					{
					double a = ds.pop();
					ds.push(Math.sqrt(a));
					break;
					}			
				case 's':
					{
					double a = ds.pop();
					ds.push(Math.sin(a));
					break;
					}			
				case 'c':
					{
					double a = ds.pop();
					ds.push(Math.cos(a));
					break;
					}			
				case 'a':
					{
					double a = ds.pop();
					ds.push(Math.abs(a));
					break;
					}			
				case 'l':
					{
					double a = ds.pop();
					ds.push(Math.log(a));
					break;
					}		
				}	
			}
		return ds.pop();
		}
}