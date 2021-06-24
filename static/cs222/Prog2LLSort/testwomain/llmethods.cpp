//CS222 Shawn O'Neil Program 2, Linked List Merge Sort
//Working Version
#include <iostream>
#include "ll.h"
using namespace std;


//Sorts the linked list from head down
void LL::sort()
	{
	if(head != NULL)
		head = head->sort();
	}

//Sorts the linked list from this node downward - doesn't work, probably broken merge
LLN * LLN::sort()
	{
	if(next == NULL)
		{
		return this;
		}
	else
		{
		LLN *list = this;
		LLN *one = split();
		one = one->sort();
		list = list->sort();
		list = list->merge(one);
		return list;
		}
	}

//Splits the linked list from this point downward Returns pointer to the head of other list
LLN * LLN::split()
	{
	if(next != NULL)
		{
		LLN *temp = next;
		next = temp->split();
		return temp;
		}
	else
		{
		return NULL;
		}
	}

//Merges two sorted linked lists, returns head of sorted linked list - I believe this is broken
LLN * LLN::merge(LLN *o)
	{
	if(o == NULL)
		{
		return this;
		}
	if(next == NULL)
		{
		if(s <= o->s)
			{
			next = o;
			return this;
			}
		}
	LLN *temp;
	if(s <= o->s)
		{
		temp = this;
		temp->next = o->merge(next);
		}
	else
		{
		temp = o;
		temp->next = merge(o->next);
		}
	return temp;
	}


