#include <stdlib.h>
#include <stdio.h>
#include "mdarray.h"
	
extern mdarray newmdarray(int dimct, int *dim);
extern void delmdarray(mdarray m);
extern double getEl(mdarray m, int *posn);
extern void setEl(mdarray m, int *posn, double d);

int main()
	{
	mdarray y;
	int *dim = malloc(5 * sizeof(int));
	int *posn = malloc(5 * sizeof(int));
	double answer = 0;
	
	dim[0]=4;
	dim[1]=5;
	dim[2]=3;
	dim[3]=6;
	dim[4]=7;
	posn[0]=2;
	posn[1]=3;
	posn[2]=1;
	posn[3]=4;
	posn[4]=5;
	y = newmdarray(5, dim);
	setEl(y, posn, 3.14159);
	answer = getEl(y, posn);
	printf("Got and retrieved the value %f\n", answer);
	delmdarray(y);
	return 0;
	}

