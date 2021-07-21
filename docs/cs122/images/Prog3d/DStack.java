public class DStack
	{
	private double data[];
	private int top;
	
	public DStack()
		{
		data = new double[100];
		top = -1;
		}
		
	public void push(double c)
		{
		if(top<100)
			{
			top++;
			data[top] = c;
			}
		}
		
	public double pop()
		{
		if(top < 0)
			return -99999.99;
		return data[top--];
		}
		
	public boolean empty()
		{
		return(top<0);
		}
	}