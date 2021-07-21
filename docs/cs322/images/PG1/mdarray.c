#include <stdio.h>
#include <stdlib.h>
#include "mdarray.h"


mdarray newmdarray(int dimct, int *dim)
	{
	mdarray m;
	int i;
	int elements = 1;
	

	m.dimct = dimct;
	m.dim = malloc(dimct * sizeof(int));
	for(i = 0; i < dimct; i++)
		{
		m.dim[i] = dim[i];
		elements = elements * dim[i];
		}
	m.val = malloc(elements * sizeof(double));

	return m;
	}

void delmdarray(mdarray m)
	{
	free (m.dim);
	free (m.val);
	}

double getEl(mdarray m, int *posn)
	{
	int count = 0;
	int mult = 1;
	int i;
	
	for(i = m.dimct-1; i >= 0; i--)
		{
		count += mult*posn[i];
		mult = mult*m.dim[i];
		}

	return m.val[count];	
	}
	
void setEl(mdarray m, int *posn, double d)
	{
	int count = 0;
	int mult = 1;
	int i;
	
	/*hey this is easier...*/
	for(i = m.dimct-1; i >= 0; i--)
		{
		count += mult*posn[i];
		mult = mult*m.dim[i];
		}

	/*for(i = 2; i <= m.dimct; i++)
		{
		mult = posn[m.dimct - i];
		for(j = 1; j < i; j++)
			{
				mult = mult*m.dim[m.dimct-j];
		
			}
			count += mult;
		}
	count += posn[m.dimct-1];
	m.val[count] = d;*/
	m.val[count] = d;
	}
