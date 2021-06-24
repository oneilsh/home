#ifndef _LLP_
#define _LLP_
class LL;
class Node;
#include <iostream>
#include <string>
#include "htable.h"
using namespace std;

class LL
	{
		private:
			Node *root;
		public:
			LL();
			~LL();
			void add(string thename);
			void print(string thename);
			void fullprint();
			void grade(int number, double score, string thename);
			void del(string thename, HTable *table);
	};
	
class Node
	{
		private:
			string name;
			Node *next;
			double grade1, grade2, grade3, grade4, grade5;
		public:
			Node();
			~Node();
			void print(string thename);
			void grade(int number, double score, string thename);
			void del(string thename, Node *q, Node *&root, HTable *table);
			
			void setnext(Node *q);   
			void fullprint();
			void add(string thename);
			void setname(string newname);
			
	};
#endif
