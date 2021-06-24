#include <iostream>
#include <string>
#include "htable.h"
using namespace std;

#define NOTFOUND 1
#define OUTRANGE 2
#define DUPLICATE 3

//Linked List constructor
LL::LL()
	{
	root = NULL;
	}

//Linked List destructor
LL::~LL()
	{
	delete root;
	}

//Add a name to the linked list
void LL::add(string thename)
	{
	if(root != NULL)
		{
		root -> add(thename);
		}
	else
		{
		root = new Node();
		root->setname(thename);
		}
	}

//Delete a name from the linked list
void LL::del(string thename, HTable *table)
	{
	if(root == NULL)
		throw NOTFOUND;
	else
		root->del(thename, NULL, root, table);
	}

//prints a name in the linked list, throws not found if name doesnt exist
void LL::print(string thename)
	{
	if(root != NULL)
		root->print(thename);
	else
		throw NOTFOUND;
	}

//prints the entire linked list
void LL::fullprint()
	{
	if(root != NULL)
		{
		root->fullprint();
		cout << endl;
		}
	}

//Sets the grade for thename on test number to score
void LL::grade(int number, double score, string thename)
	{
	if(number != 1 && number != 2 && number != 3 && number != 4 && number != 5)
		throw OUTRANGE;
	if(root != NULL)
		root->grade(number, score, thename);
	else
		throw NOTFOUND;
	}

//Node constructor
Node::Node()
	{
	next = NULL;
	grade1 = grade2 = grade3 = grade4 = grade5 = 0;
	}

//Node destructor
Node::~Node()
	{
	if(next != NULL)
		delete next;
	}

//Node add method, adds to THE END OF THE LINKED LIST
void Node::add(string thename)
	{
	if(name == thename)
		throw DUPLICATE;
	else if(next != NULL)
		next->add(thename);
	else
		{
		next = new Node();
		next -> setname(thename);
		}
	}

//Sets thenames grade on test number to score
void Node::grade(int number, double score, string thename)
	{
	if(thename == name)
		{
		if(number == 1)
			grade1 = score;
		else if(number == 2)
			grade2 = score;
		else if(number == 3)
			grade3 = score;
		else if(number == 4)
			grade4 = score;
		else if(number == 5)
			grade5 = score;
		}
	else if(next != NULL)
		{
		next->grade(number, score, thename);
		}
	else
		throw NOTFOUND;
	}

//prints the stuff for the thename
void Node::print(string thename)
	{
	if(name == thename)
		{
		cout << name << " : " << grade1 << " , " << grade2 << " , " << grade3 << " , " << grade4 << " , " << grade5 << endl;
		}
	else
		{
		if(next == NULL)
			throw NOTFOUND;
		else
			next->print(thename);
		}
	}

//prints the node and everything after it
void Node::fullprint()
	{
	cout << name << " : "<<grade1 <<" , "<< grade2<< " , "<< grade3 << " , " << grade4 << " , " << grade5 <<endl;
	if(next != NULL)
		next->fullprint();
	}

//deletes thename from the list. uses table to set the mean stuff so that will work
void Node::del(string thename, Node *parent, Node *&root, HTable *table)
	{
	if(name == thename)
		{
		if(parent != NULL)
			parent->setnext(next);
		else
			root=next;
		next = NULL;
		if(grade1 != 0)
			table->redogrades(1, grade1);
		if(grade2 != 0)
			table->redogrades(2, grade2);
		if(grade3 != 0)
			table->redogrades(3, grade3);
		if(grade4 != 0)
			table->redogrades(4, grade4);
		if(grade5 != 0)
			table->redogrades(5, grade5);
		delete this;
		}
	else if(next != NULL)
		{
		next->del(thename, this, root, table);
		}
	else
		{
		throw NOTFOUND;
		}
	}

//sets the name for this node
void Node::setname(string newname)
	{
	name = newname;
	}

//sets the next pointer for this node
void Node::setnext(Node *q)
	{
	next = q;
	}

/*int main()
	{
	LL h;
	try
		{
		h.add("hi");
		}
	catch(int e)
		{
		if(e == 1)
			cout << "notfound" << endl;
		else if(e == 2)
			cout << "outrange" << endl;
		else if(e == 3)
			cout << "duplicate" << endl;
		}
	h.fullprint();
	try
		{
		h.add("hi");
		}
	catch(int e)
		{
		if(e == 1)
			cout << "notfound" << endl;
		else if(e == 2)
			cout << "outrange" << endl;
		else if(e == 3)
			cout << "duplicate" << endl;
		}
	h.fullprint();
	try
		{
		h.add("bob");
		}
	catch(int e)
		{
		if(e == 1)
			cout << "notfound" << endl;
		else if(e == 2)
			cout << "outrange" << endl;
		else if(e == 3)
			cout << "duplicate" << endl;
		}
	h.fullprint();
	try
		{
		h.print("hi");
		}
	catch(int e)
		{
		if(e == 1)
			cout << "notfound" << endl;
		else if(e == 2)
			cout << "outrange" << endl;
		else if(e == 3)
			cout << "duplicate" << endl;
		}
	h.fullprint();
	try
		{
		h.del("hi");
		}
	catch(int e)
		{
		if(e == 1)
			cout << "notfound" << endl;
		else if(e == 2)
			cout << "outrange" << endl;
		else if(e == 3)
			cout << "duplicate" << endl;
		}
	h.fullprint();
	try
		{
		h.del("bob");
		}
	catch(int e)
		{
		if(e == 1)
			cout << "notfound" << endl;
		else if(e == 2)
			cout << "outrange" << endl;
		else if(e == 3)
			cout << "duplicate" << endl;
		}
	h.fullprint();
	}
	*/
