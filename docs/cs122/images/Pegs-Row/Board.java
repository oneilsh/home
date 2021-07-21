import java.awt.*;
import java.awt.event.*;
public class Board
{
private static final int HOLE=0;
private static final int PEG=1;
private static final int NO_HOLE=2;
private int board[][];
private int pegs;
private int size;

	public Board(int size)
		{
		this.size = size;
		board = new int[size][size];
		for(int x=0; x<size; x++)
			for(int y=0; y<size; y++)
				if(x+y<size)
					board[x][y]=PEG;
				else
					board[x][y] = NO_HOLE;
		board[0][0] = HOLE;
		pegs = (size*(size + 1))/2 -1;
		}

	public Board jump(int row, int col, int dr, int dc)
		{
		if(board[row][col] != PEG) return null;
		if(row+dr<0 || row +dr > (size -1) || col+dc <0 || col+dc >(size -1) || board[row+dr][col+dc] != PEG) return null;
		if(row+2*dr<0 || row + 2*dr > (size -1) || col + 2* dc < 0 ||col + 2*dc >(size -1) ||board[row+2*dr][col+2*dc] != HOLE) return null;
		Board b = new Board(size);
		for(int x = 0; x< size; x++)
			for(int y = 0; y<size; y++)
				b.board[x][y] = board[x][y];
		b.pegs = pegs;
		b.board[row][col] = HOLE;
		b.board[row + dr][col + dc] = HOLE;
		b.board[row + 2*dr][col + 2*dc] = PEG;
		b.pegs--;
		return b;
		}

	public boolean solved()
		{
		if(pegs == 1)
			return true;
		return false;
		}

	public String toString()
		{
		String s = "";
		for(int r = (size -1); r>= 0; r--)
			{
			for(int n = 0; n<r; n++)
				s+=" ";
			for(int n = 0; n< size-r; n++)
				s+=board[r][n] + " ";
			s+="\n";
			}
		return s;
		}
		
	public void paint(Graphics g)
		{
		int b = 0;
		for(int r = 0; r < size; r++)
			{
			for(int n = 0; n< size-r; n++)
				{
				if(board[r][n] == HOLE)
					g.drawOval(21*n+b, 18*r, 15, 15);
				if(board[r][n] == PEG)
					g.fillOval(21*n+b, 18*r, 15, 15);
				}
			b+=10;
			}
		}

}