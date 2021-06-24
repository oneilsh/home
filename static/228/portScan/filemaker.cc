#include <stdio.h>
#include <iostream.h>
#include <fstream>
using namespace std;

int main()
	{
	ofstream o("longfile");
	for(int i = 0; i < 1000; i++)
		{
		o << i << endl;
		}
	}

