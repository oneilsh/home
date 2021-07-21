#ifndef _LLH_
#define _LLH_
#include <string>
using namespace std;

class LL;
class LLN;

class LL
	{
	  private:
	LLN *head;
	  public:
	LL ();
	~LL ();
	void print ();
	void split();
	void addfront (string str);
	void sort (); //Sorts a LL in alphabetical order
	};

class LLN
	{
	  private:
  	LLN *next;
  	string s;
 	  public:
	LLN (string str, LLN *n);
	~LLN ();
	void print ();

	LLN *sort (); //Sorts the list from this point down;
                //returns the new head of this sorted list

	LLN *split (); //Splits the list in half.  If original list is
                 //1->2->3->4->5->... , the new lists are 1->3->5->...
                 //and 2->4->6->... this is still node 1 (of course) and
                 //the pointer to node 2 is returned.

	LLN *merge (LLN *o); //merges two sorted lists.  this is the head of
                       //one list; o is the head of the other.  The new
                       //head of the merged list is returned.
	};
#endif
