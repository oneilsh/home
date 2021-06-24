// Shawn O'Neil Dynamic Programming for optimal Matrix Multiplication,  Algorithms with Andy Poe Winter 04 

#include <iostream>
using namespace std;

int* getInput(int nummats);
void computeTable(int nummats, int* dimsarray);
void printOut(int **mults, int **divides, int nummats, int row, int col);

//Main method, calls IO and handes the case for only 1 matrix.
int main()
	{
	int nummats = 0;
	cout << "Please enter number of matrices:  ";
	cin >> nummats;
	if(nummats == 1)
		cout << "You need zero multiplications." << endl;
	else
		{
		int *dimsarray = getInput(nummats);
		computeTable(nummats, dimsarray);
		}
	}

//given a number of matrices, and an array of dimensions (size 1+nummats)
//computes the table of the least amount of multiplies from matrices a through b
//where this is stored in mults[a][b] (arrays start at 0, that is the number
//of mults required for the first through third arrays are stored in mults[0][2])
//and computes the appropropriate divide point for each multipilication in divides
void computeTable(int nummats, int* dimsarray)
	{
	int **mults = new int*[nummats];
	int **divides = new int*[nummats];
	for(int i = 0; i < nummats; i++)
		{
		mults[i] = new int[nummats];
		divides[i] = new int[nummats];
		}
	for(int i = 0; i < nummats; i++)
		for(int j = 0; j < nummats; j++)
			{mults[i][j] = -1; divides[i][j] = -1;}
	for(int i = 0; i < nummats; i++)
		mults[i][i] = 0;
	
	int y = 0;
	for(int i = 1; i < nummats; i++)
		{
		y = 0;
		for(int x = i; x < nummats; x++)
			{
			//here is where the order we want happens within the array, for x,y
			//so what is the least number of multiplications to multiply arrays y through x?
			//cout << x << "," << y << endl;
			//int othermults = mults[x-1][y];
			//if(mults[x][y+1] < othermults) othermults = mults[x][y+1];
			int togethermults = 0;
			if(x-y < 2)
				{
				togethermults = dimsarray[y]*dimsarray[x]*dimsarray[x+1];
				}
			else
				{
				for(int div = y; div < x; div++) 	//divide after the divide number
					{
					int temp = dimsarray[y]*dimsarray[div+1]*dimsarray[x+1];
					temp += mults[div][y];
					temp += mults[x][div+1];
					if(temp < togethermults || togethermults == 0) 
						{
						togethermults = temp;
						divides[x][y] = div;
						}
					}
				}
			
			mults[x][y] = togethermults;
			//increment the y at the end
			y++;
			}
		}
	cout << "You need " << mults[nummats-1][0] << " multiplications." << endl;
	printOut(mults, divides, nummats, 0, nummats-1);
	cout << endl;
	}


//given a divides matrix and a row and column, prints out
//the parenthatized multiplation pattern for the optimal
//matrix multiplication of matrices row through column (again starting at 0)
// nummults = 5, 1,2,3,4,5,6 should be (((( 1 2 ) 3 ) 4 ) 5 )
// or in my format, (((( 0 1 ) 2 ) 3 ) 4 )
void printOut(int **mults, int **divides, int nummats, int row, int col)
	{
	//cout << "row is " << row << " col is " << col << endl;
	if(col-row == 0) cout << " " << row+1 << " ";
	else if(col-row == 1) cout << "( " << row+1 << "  " << row+2 << " )";//cout << "( "<< row << " " << row+1 << " )" << " ";
	else
		{
		int divide = divides[col][row];
		cout << "(";
		printOut(mults, divides, nummats, row, divide);

		printOut(mults, divides, nummats, divide+1, col);
		cout << ")";
		}
	}


//handes input, given a number of matrices, asks for the dimensions and returns them in an array
int* getInput(int nummats)
	{
	int *array;
	array = new int[nummats+1];
	for(int i = 0; i < nummats+1; i++)
		{
		if(i == 0) cout << "Please enter the number of rows in matrix 1:  ";
		else if(i == nummats) cout << "Please enter the number of columns in matrix " << nummats << ":  ";
		else cout << "Please enter the number of columns in matrix " << i << " and rows in matrix " << i+1 << ":  ";
		cin >> array[i];
		}
	return array;
	}
