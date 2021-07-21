#ifndef _LLH_
#define _LLH_
#include <iostream>
#include <string>
using namespace std;

class Tree;
class Node;

class Tree
	{
	  private:
	    Node *root;
	  public:
	    Tree();
	    ~Tree();
	    void add(string name);
	    void grade(int number, double score, string thename);
	    void print();
	    void del(string name);
	    double mean(int exam);
	    bool empty();
	};
	
class Node
	{
	  private:
	    Node *parent;
	    Node *left;
	    Node *right;
	    double grade1, grade2, grade3, grade4, grade5;
	    string name;
	  public:
	    Node();
	    ~Node();
	    void setparent(Node *n);
	    void setname(string thename);
	    void add(string thename);
	    double mean(int exam);
	    void grade(int number, double score, string thename);
	    void print();
	    void del(string thename, Node *&root);
	    string getname();
	    void setchildtonull(string thename);
	    Node * slideleft();
	    double getgrade(int number);
	    double getnumber(int exam);
	    double gettotal(int exam);
	    void resetchild(Node *n);
	};
	
#endif
