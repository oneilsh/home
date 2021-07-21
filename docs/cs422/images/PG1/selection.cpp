//Shawn O'Neil CS422 Linear Time Selection Winter 04
// found the 100th element (from beginning) in a list of 2,000,000, sorted last to first (2000000 ..... 0)
// in 50 seconds, using ~100 MBs of memory!

#include <iostream>
#include <string>

using namespace std;

string* getInput(int s);
string* breakIntoFifth(int n, string *input, int *fifthlength);
string getElement(string *input, int n, int k, bool median);
string linSelSort(string *input, int n, int k);

//handles input and output
//line 1: number of elements in input (s)
//lines 2 through s+1: inputs into the input array (str)
//line s+2: the element to look for
int main()
	{
	while(1)
		{
		int s = 0;
		int k = 0;
		
		cin >> s;
		if (s == 0)
				exit(0);
		else
			{
			string *str = getInput(s);
			cin >> k;
			string answer = linSelSort(str, s, k);

			//cout << "The " << k << "th element is " << answer << endl;
			cout << answer << endl;
			//delete[] str;
			}
		}
	}
	
//returns the k'th element in a list *input n long, in n time.
string linSelSort(string *input, int n, int k)
	{
	if(n < 6) return getElement(input, n, k, false);
	else
		{
		string output;
		int lengthoffifth = 0;
		string *fifth = breakIntoFifth(n, input, &lengthoffifth);
		string pivot = linSelSort(fifth, lengthoffifth, (int)lengthoffifth/2);
		string *bef = new string[n]; int befct = 0;
		string *eq = new string[n]; int eqct = 0;
		string *aft = new string[n]; int aftct = 0;
		
		for(int i = 0; i < n; i++)
			{
			if(pivot.compare(input[i]) > 0) //pivot is greater than input[i]
				{ bef[befct] = input[i]; befct++; }
			else if(pivot.compare(input[i]) == 0) //pivot is equal to input[i]
				{ eq[eqct] = input[i]; eqct++; }
			else if(pivot.compare(input[i]) < 0) //pivot is less than input[i]
				{ aft[aftct] = input[i]; aftct++; }
			}
		
		string *returning;
		if(k <= befct) 	
			{
			returning = bef;
			n = befct;
			}
		else if(k > befct+eqct) 
			{
			returning = aft;
			k = k - befct - eqct;
			n = aftct;
			}
		else return eq[0];
		output = linSelSort(returning, n, k);
		delete[] fifth;
		delete[] bef;
		delete[] eq;
		delete[] aft;
		//delete[] input;
		return output;
		}
	}
	
//breaks an array into chunks of five, returning an array 1/5 the size made out the medians of the chunks
string* breakIntoFifth(int n, string *input, int *fifthlength)
	{
	int lenoutput = 0;
	if(n%5 == 0) lenoutput = n/5;
	else lenoutput = (int)(n/5)+1;
	*fifthlength = lenoutput;
	string *output = new string[lenoutput];
	
	string *chunk = new string[5];
	int chunkpos = 0;
	int outputpos = 0;
	for(int i = 0; i < n; i++)
		{
		chunk[chunkpos] = input[i];
		if(chunkpos == 4 || i == n-1) //if we run out of room in our chunk or we are at the end of the input
			{
			output[outputpos] = getElement(chunk, chunkpos, 0, true);
			outputpos++;
			chunkpos = 0;
			}
		else
			chunkpos++;
		}
	delete[] chunk;
	return output;
	}

//given s, reads the next s lines from standard in, buildig and array of string to return
string *getInput(int s)
	{
	string *array;
	array = new string[s];
	string bogus;
	getline(cin, bogus);
	for(int i = 0; i < s; i++)
		{
		getline(cin, array[i]);
		}	
	return array;
	}
	
//brute force get an element from the list
//if median = true, returns the median
//else returns the kth element
//Bubble sort is constant time on a limited size input ;-)
string getElement(string *input, int n, int k, bool median)
	{
	if(n == 1) return input[0];
	string output;
	bool sorted = false;
	while(!sorted)
		{
		sorted = true;
		for(int i = 0; i < n-1; i++)
			{
			if(input[i].compare(input[i+1]) > 0)
				{
				string temp = input[i];
				input[i] = input[i+1];
				input[i+1] = temp;
				sorted = false;
				}
			}
		}
	if(median == true)
		return input[(int)n/2];
	else if(k <= n)
		return input[k-1];
	string sucker = "sucker, you asked for an element outside the list";
	return sucker;
	}
