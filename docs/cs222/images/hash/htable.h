#ifndef _LLH_
#define _LLH_
class HTable;
#include <iostream>
#include <string>
#include "list.h"
using namespace std;



class HTable
	{
	  private:
	    LL t[25013];
		 double scores[5];
		 int numbers[5];
	  public:
	    HTable();
	    ~HTable();
		 void add(string thename);
		 void grade(int number, double score, string thename);
		 void del(string thename);
		 double mean(int test);

	    int lookup(string name);
		 void redogrades(int test, double score);
		 void print();
	};

#endif
