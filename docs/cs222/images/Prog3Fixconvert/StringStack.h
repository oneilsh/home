#ifndef _LLH_
#define _LLH_
#include <iostream>
#include <string>
using namespace std;

//Definition of a Stack of strings, using a singularly-linked list with a dummy head node
//Shawn O'Neil
class StringStack;
class Node;

class StringStack
	{
	  private:
	    Node *head;
	  public:
	    StringStack();
	    ~StringStack();
	    void push(string s);
	    string pop();
	    bool empty();
	};

class Node
	{
	  private:
		 Node *next;
		 string data;
	  public:
	    Node();
	    ~Node();
	    void setnext(Node *n);
	    Node *getnext();
	    void setdata(string s);
	    string getdata();
	    
	};
	
#endif