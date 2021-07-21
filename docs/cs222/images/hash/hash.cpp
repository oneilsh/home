//Shawn O'Neil - Hash Table. ADD, DELETE, MEAN, GRADE implemented. PRINT works, but prints the
//entire thing in no particular order.
//CS222 - Winter 03.
#include <iostream>
#include <string>
#include "htable.h"
//#include "list.h"
using namespace std;

#define NOTFOUND 1
#define OUTRANGE 2
#define DUPLICATE 3
#define NONONZERO 4


//Hash Table constructor
HTable::HTable()
	{
	for(int i = 0; i < 5; i++)
		{
		scores[i] = 0;
		numbers[i] = 0;
		}
	}

//Hash Table destructor
HTable::~HTable()
	{
	}

//Given a name, looks up its position in the table
int HTable::lookup(string thename)
	{
	int current = 0;
	for(int i = 0; i < thename.length(); i++)
		{
		current = (current*256+(1+(int)(unsigned char)thename[i]))%25013;
		}
	return current;
	}

//Adds a name to the table
void HTable::add(string thename)
	{
	t[lookup(thename)].add(thename);
	}

//deletes a name from the table
void HTable::del(string thename)
	{
	t[lookup(thename)].del(thename, this);
	}

//Sets thenames grade on test number to score, throws outrange if score or test are no good
void HTable::grade(int number, double score, string thename)
	{
	if(score <= 0)
		throw OUTRANGE;
	else if(number != 1 && number != 2 && number != 3 && number != 4 && number != 5)
		throw OUTRANGE;
	else
		{
		t[lookup(thename)].grade(number, score, thename);
		scores[number] += score;
		numbers[number]++;
		}
	}

//returns the mean on test
double HTable::mean(int test)
	{
	if(numbers[test] == 0)
		throw NONONZERO;
	else return scores[test]/numbers[test];
	}

//prints the entire hash table in no particular order
void HTable::print()
	{
	//dont be stupid, you cant full print a hash table! well, ill
	//implement some approximation...
	for(int i = 0; i < 25013; i++)
	t[i].fullprint();
	}

//Used by delete, at the node in the linked list level to fix the totals so the mean funciton will work
void HTable::redogrades(int test, double score)
	{
	scores[test] -= score;
	numbers[test]--;
	}

//Main method, use ADD , DELETE, GRADE, MEAN, and PRINT (only if you really want to...)
int mainf(int argc, char **argv)
	{
	cout << "Happy birthday euclid." << endl;
	HTable t;
	string action;
	int number;
	double grade;
	string name;
	while(cin >> action, cin)
		{
		cin.ignore();
		if(action == "ADD")
			{
			getline(cin, name);
			try
				{
				t.add(name);
				cout << "Successfully added " << name << "." << endl;
				cout << endl;
				}
			catch(int e)
				{
				if(e == NOTFOUND)
					cout << "Could not add, ERROR" << endl;
				else if(e == DUPLICATE)
					cout << "Could not add " << name << ". Name already exists." << endl;
				else if(e == OUTRANGE)
					cout << "Could not add, ERROR" << endl;
				cout << endl;
				}
			}

		else if(action == "PRINT")
			{
			try
				{
			//	cout << endl;
				cout << "Statistics: " << endl;
				t.print();
				cout << endl;
				}
			catch(int e)
				{
				if(e == NOTFOUND)
					cout << "Could not print, ERROR" << endl;
				else if(e == DUPLICATE)
					cout << "Could not print, ERROR" << endl;
				else if(e == OUTRANGE)
					cout << "Could not print, ERROR" << endl;
				cout << endl;
				}
			}

		else if(action == "GRADE")
			{
			cin >> number;
			cin.ignore();
			cin >> grade;
			cin.ignore();
			getline(cin, name);
			try
				{
				t.grade(number, grade, name);
				cout << "Set " << name << "'s grade on test " << number << " to " << grade << "." << endl;
				cout << endl;
				}
			catch(int e)
				{
				if(e == NOTFOUND)
					cout << "Could not grade " << name << ", no match in database." << endl;
				else if(e == DUPLICATE)
					cout << "Could not grade, ERROR" << endl;
				else if(e == OUTRANGE)
					cout << "Could not grade " << name << ", test or number or score out of range" << endl;
				cout << endl;
				}
			}

		else if(action == "DELETE")
			{
			getline(cin, name);
			try
				{
				t.del(name);
				cout << "Successfully deleted " << name << "." << endl;
				cout << endl;
				}
			catch(int e)
				{
				if(e == NOTFOUND)
					cout << "Could not delete " << name << ", no match in database." << endl;
				else if(e == DUPLICATE)
					cout << "Could not delete, ERROR" << endl;
				else if(e == OUTRANGE)
					cout << "Could not delete " << name << ", test number out of range ERROR" << endl;
				cout << endl;
				}
			}

		else if(action == "MEAN")
			{
			cin >> number;
			try
				{
				double mean = t.mean(number);
				if(mean != 0)
					cout << "The mean on test " << number << " is " << t.mean(number) << endl;
				else
					cout << "There are no nonzero means in the database for test " << number << endl;
				cout << endl;
				}
			catch(int e)
				{
				if(e == NOTFOUND)
					cout << "Could not get mean for test " << number << ", no match in database ERROR" << endl;
				else if(e == DUPLICATE)
					cout << "Could not get mean, ERROR" << endl;
				else if(e == OUTRANGE)
					cout << "Could not get mean for test " << number << ", test number out of range." << endl;
				else if(e == NONONZERO)
					cout << "Could not get mean for test " << number << ", no nonzero test scores." << endl;
				cout << endl;
				}
			}

		}
	cout << "Bye, thanks for using." << endl;
	}
