public class CStack
	{
	private char data[];
	private int top;
	
	public CStack()
		{
		data = new char[1000];
		top = -1;
		}
		
	public void push(char c)
		{
		if(top<100)
			{
			top++;
			data[top] = c;
			}
		}
		
	public char peek()
		{
		if(top < 0)
			return '(';
		return data[top];
		}
		
	public char pop()
		{
		if(top < 0)
			return '(';
		return data[top--];
		}
		
	public boolean empty()
		{
		return(top<0);
		}
	}