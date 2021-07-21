/*
Shawn O'Neil - Emulating Diamond inheritance in C

Golly Gee Gosh I've got to be close. I'm fairly sure I've got it this
time, I don't think I need to follow two pointers in any XtoX data
copy methods, as well as in the Genius2MathMajor data copy for just
the Discipline stuff, at least I really hope not.

*/

#include <stdio.h>
#include <stdlib.h>
#include "PG3.h"

void newNMUStudent(NMUStudent *p)
	{
	int i;
	for(i=0; i < 60; i++)
		p->Name[i] = ' ';
	for(i = 0; i < 8; i++)
		p->DOB[i] = '0';
	p->GradYear = 0;
	p->WorkatJob=NMUStudentWorkatJob;
	p->Study=NMUStudentStudy;
	p->Speak=NMUStudentSpeak;
	}
	
void newMathMajor(MathMajor *p)
	{
	int i;
	NMUStudent *g;
	p->a = (NMUStudent*)&p->Name;   /*p's a pointer should point to the address of its name, is this correct syntax?*/
	for(i=0; i < 60; i++)
		p->Name[i] = ' ';
	for(i = 0; i < 8; i++)
		p->DOB[i] = '0';
	p->GradYear = 0;
	p->WorkatJob=NMUStudentWorkatJob;
	p->Study=MathMajorStudy;
	p->Speak=MathMajorSpeak;
	for(i = 0; i < 60; i++)
		p->Discipline[i] = ' ';
	}

void newCSMajor(CSMajor *p)
	{
	int i;
	p->a = (NMUStudent*)&p->Name;
	for(i=0; i < 60; i++)
		p->Name[i] = ' ';
	for(i = 0; i < 8; i++)
		p->DOB[i] = '0';
	p->GradYear = 0;
	p->WorkatJob=CSMajorWorkatJob;
	p->Study=NMUStudentStudy;
	p->Speak=CSMajorSpeak;
	p->YearsOfExperience = 0;
	}

void newGenius(Genius *p)
	{
	int i;
	p->a1 = (NMUStudent*)&p->Name;
	p->a2 = (NMUStudent*)&p->Name;
	for(i=0; i < 60; i++)
		p->Name[i] = ' ';
	for(i = 0; i < 8; i++)
		p->DOB[i] = '0';
	p->GradYear = 0;
	p->WorkatJob=CSMajorWorkatJob;
	p->Study=MathMajorStudy;
	p->Speak=GeniusSpeak;
	p->YearsOfExperience = 0;
	for(i=0; i < 60; i++)
		p->Discipline[i] = ' ';
	p->Salary = 0;
	}

void NMUStudentWorkatJob(void) {	printf("Would you like fries with that?\n");}
void NMUStudentStudy(void) {	printf("Pass the beer.\n");}
void NMUStudentSpeak(void) {	printf("When is deer camp?\n");}

void MathMajorStudy(void) {	printf("Don't close the library yet!\n");}
void MathMajorSpeak(void) {	printf("Int dw f = Int w df!\n");}

void CSMajorWorkatJob(void) {printf("I'm paid to do my homework!\n");}
void CSMajorSpeak(void) {printf("while (1) cout << \"You are a jerk!\"\n");}

void GeniusSpeak(void) {printf("I can do fast Fourier Transforms!\n");}

NMUStudent * MathMajor2NMUStudentPtr (MathMajor *p)
	{
	NMUStudent *g;
	g = (NMUStudent*)p->a;
	return g;
	}
	
NMUStudent * CSMajor2NMUStudentPtr (CSMajor *p)
	{
	NMUStudent *g;
	g = (NMUStudent*)p->a;
	return g;
	}

NMUStudent * Genius2NMUStudentPtr (Genius *p)
	{
	NMUStudent *g;
	g = (NMUStudent*)p->a1;
	return g;
	}
	
MathMajor * Genius2MathMajorPtr (Genius *p)
	{
	MathMajor *g;
	g = (MathMajor*)&p->a1;
	return g;
	}
	
CSMajor * Genius2CSMajorPtr (Genius *p)
	{
	CSMajor *g;
	g = (CSMajor*)&p->a2;
	return g;
	}

void NMUStudent2NMUStudent(NMUStudent *l, NMUStudent *m)
	{
	int i;
	for(i=0; i < 60; i++)
		l->Name[i] = m->Name[i];
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->DOB[i];
	l->GradYear = m->GradYear;
	/*l->WorkatJob = m->WorkatJob;	
	l->Study = m->Study;
	l->Speak = m->Speak;*/
	}
	
void MathMajor2NMUStudent(NMUStudent *l, MathMajor *m)
	{
	int i;
	for(i=0; i < 60; i++)
		l->Name[i] = m->a->Name[i];
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->a->DOB[i];
	l->GradYear = m->a->GradYear;
	/*l->WorkatJob = m->WorkatJob;
	l->Study = m->Study;
	l->Speak = m->Speak;*/
	}
	
void MathMajor2MathMajor(MathMajor *l, MathMajor *m)
	{
	int i;
	l->a = m->a;
	for(i=0; i < 60; i++)
		l->Name[i] = m->Name[i];;
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->DOB[i];
	l->GradYear = m->GradYear;
	/*l->WorkatJob = m->WorkatJob;
	l->Study = m->WorkatJob;
	l->Speak = m->Speak;*/
	for(i = 0; i < 60; i++)
		l->Discipline[i] = m->Discipline[i];;
	}
	
void CSMajor2NMUStudent(NMUStudent *l, CSMajor *m)
	{
	int i;
	for(i=0; i < 60; i++)
		l->Name[i] = m->a->Name[i];
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->a->DOB[i];
	l->GradYear = m->a->GradYear;
	/*l->WorkatJob = m->WorkatJob;
	l->Study = m->Study;
	l->Speak = m->Speak;*/
	}
	
void CSMajor2CSMajor(CSMajor *l, CSMajor *m)
	{
	int i;
	l->a = m->a;
	for(i=0; i < 60; i++)
		l->Name[i] = m->Name[i];
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->DOB[i];
	l->GradYear = m->GradYear;
	/*l->WorkatJob = m->WorkatJob;
	l->Study = m->Study;
	l->Speak = m->Speak;*/
	l->YearsOfExperience = m->YearsOfExperience;
	}
	
void Genius2NMUStudent(NMUStudent *l, Genius *m)
	{
	int i;
	for(i=0; i < 60; i++)
		l->Name[i] = m->a1->Name[i];
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->a1->DOB[i];
	l->GradYear = m->a1->GradYear;
	/*l->WorkatJob = m->WorkatJob;
	l->Study = m->Study;
	l->Speak = m->Speak;*/
	}
	
void Genius2MathMajor(MathMajor *l, Genius *m)
	{
	int i;
	l->a = m->a1;
	for(i=0; i < 60; i++)
		l->Name[i] = m->a1->Name[i];
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->a1->DOB[i];
	l->GradYear = m->a1->GradYear;
	/*l->WorkatJob = m->WorkatJob;
	l->Study = m->WorkatJob;
	l->Speak = m->Speak;*/
	for(i = 0; i < 60; i++)
		l->Discipline[i] = m->Discipline[i];;
	}
	
void Genius2CSMajor(CSMajor *l, Genius *m)
	{
	int i;
	l->a = m->a1;
	for(i=0; i < 60; i++)
		l->Name[i] = m->a1->Name[i];
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->a1->DOB[i];
	l->GradYear = m->a1->GradYear;
	/*l->WorkatJob = m->WorkatJob;
	l->Study = m->Study;
	l->Speak = m->Speak;*/
	l->YearsOfExperience = m->YearsOfExperience;
	}
	
void Genius2Genius(Genius *l, Genius *m)
	{
	int i;
	l->a1 = m->a1;;
	l->a2 = m->a2;
	for(i=0; i < 60; i++)
		l->Name[i] = m->Name[i];
	for(i = 0; i < 8; i++)
		l->DOB[i] = m->DOB[i];
	l->GradYear = m->GradYear;
	/*l->WorkatJob = m->WorkatJob;
	l->Study = m->Study;
	l->Speak = m->Speak;*/
	l->YearsOfExperience = m->YearsOfExperience;
	for(i=0; i < 60; i++)
		l->Discipline[i] = m->Discipline[i];
	l->Salary = m->Salary;
	}
