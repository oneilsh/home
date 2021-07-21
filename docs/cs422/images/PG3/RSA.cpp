//Shawn O'Neil RSA encrypt/decrypt and factor to crack, this will run slow ;-)

#include <iostream>
using namespace std;

int AtoEmodM(int A, int e, int M);
int findPhiM(int M);
int findD(int e,int PhiM);


//Main method, handles input and output, YAY!!!!!!
int main()
	{
	string command;
	int N;
	int e;
	int M;
	while(command != "QUIT")
		{
		cin >> command;
		if(cin.eof()) return 0;
		if(command == "ENCRYPT")
			{
			cin >> N;
			cin >> e;
			cin >> M;
			cout << AtoEmodM(M,e,N) << endl;
			}
		else if(command == "DECRYPT")
			{
			cin >> N;
			cin >> e;
			cout << findD(e, findPhiM(N)) << endl;
			}
		command = "bogusnesssdude";
		}
	}

//finds D given, e, PhiM where (d*e)%PhiM = 1
int findD(int e, int PhiM)
	{
	int* row0 = new int[3];
	int* row1 = new int[3];
	int* row2 = new int[3];
	row0[0] = 0; row0[1] = PhiM; row0[2] = 0;
	row1[2] = 1; row1[1] = e;
	row2[1] = 7334;
	while(row2[1] > 1)
		{
		row1[0] = row0[1]/row1[1];
		row2[2] = row0[2]-(row1[0]*row1[2]);
		row2[1] = row0[1]-(row0[1]/row1[1]*row1[1]);
		
		int* temp = row0;
		row0 = row1;
		row1 = row2;
		row2 = new int[3];
		for(int i = 0; i < 3; i++) row2[i] = row1[i];
		delete[] temp;
		}
	
	int answer = row2[2];
	while(answer < 0)
		answer = answer+PhiM;
	return answer;
	}

//Factors M to the first two factors it finds p and q, and returns (p-1)(q-1)
int findPhiM(int M)
	{
	int p = 2;
	int q = 0;
	while(M/p*p != M) p++;
	q = M/p;
	p--;q--;
	return p*q;
	}
	
//returns A^e%M in a fast manner
int AtoEmodM(int A, int e, int M)
	{
	int lastleft = 1;
	int nextleft = A;
	int runningproduct = 1;
	while(e >= 1)
		{
		if(e/2*2 != e) runningproduct = runningproduct * nextleft % M;
		lastleft = nextleft;
		nextleft = lastleft * lastleft % M;
		e = e/2;
		}
	return runningproduct;
	}


















