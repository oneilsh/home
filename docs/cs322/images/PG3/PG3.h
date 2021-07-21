typedef struct NMUStudent
	{
	char Name[60];
	char DOB[8];
	int GradYear;
	void (*WorkatJob)(void);
	void (*Study)(void);
	void (*Speak)(void);
	} NMUStudent;

typedef struct MathMajor
	{
	NMUStudent *a;

	char Discipline[60];

	char Name[60];
	char DOB[8];
	int GradYear;
	void (*WorkatJob)(void);
	void (*Study)(void);
	void (*Speak)(void);	
	} MathMajor;

typedef struct CSMajor
	{
	NMUStudent *a;
	
	int YearsOfExperience;

	char Name[60];
	char DOB[8];
	int GradYear;
	void (*WorkatJob)(void);
	void (*Study)(void);
	void (*Speak)(void);
	} CSMajor;

typedef struct Genius
	{
	NMUStudent *a1;
	char Discipline[60];

	NMUStudent *a2;
	int YearsOfExperience;

	int Salary;
	
	char Name[60];
	char DOB[8];
	int GradYear;
	void (*WorkatJob)(void);
	void (*Study)(void);
	void (*Speak)(void);
	} Genius;

void newNMUStudent(NMUStudent *p);
void NMUStudentWorkatJob(void);
void NMUStudentStudy(void);
void NMUStudentSpeak(void);

void newMathMajor(MathMajor *p);
/*void MathMajorWorkatJob(void);*/
void MathMajorStudy(void);
void MathMajorSpeak(void);

void newCSMajor(CSMajor *p);
void CSMajorWorkatJob(void);
/*void CSMajorStudy(void);*/
void CSMajorSpeak(void);

void newGenius(Genius *p);
/*void GeniusWorkatJob(void);*/
/*void GeniusStudy(void);*/
void GeniusSpeak(void);

NMUStudent * MathMajor2NMUStudentPtr (MathMajor *p);
NMUStudent * CSMajor2NMUStudentPtr (CSMajor *p);
NMUStudent * Genius2NMUStudentPtr (Genius *p);
MathMajor * Genius2MathMajorPtr (Genius *p);
CSMajor * Genius2CSMajorPtr (Genius *p);

void NMUStudent2NMUStudent(NMUStudent *l, NMUStudent *m);
void MathMajor2NMUStudent(NMUStudent *l, MathMajor *m);
void MathMajor2MathMajor(MathMajor *l, MathMajor *m);
void CSMajor2NMUStudent(NMUStudent *l, CSMajor *m);
void CSMajor2CSMajor(CSMajor *l, CSMajor *m);
void Genius2NMUStudent(NMUStudent *l, Genius *m);
void Genius2MathMajor(MathMajor *l, Genius *m);
void Genius2CSMajor(CSMajor *l, Genius *m);
void Genius2Genius(Genius *l, Genius *m);
