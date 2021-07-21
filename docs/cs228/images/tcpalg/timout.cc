#include <iostream>
#include <stdlib.h>
#include <math.h>
using namespace std;

int main()
	{
	double difference;
	double sample;
	double rtt;
	double delta;
	double deviation;
	double alpha;
	double timeout;
	double numdevs;
	double absdifference;
	double n;
	
	cout << "Initial round trip time guess: ";
	cin >> rtt;

	
	cout << "Initial Deviation time guess: " ;
	cin >> deviation;


	cout << "Delta (how much each sample affects est of RTT, sugg .125): ";
	cin >> delta;

	
	cout << "Alpha (how much each sample affects est of DEV, sugg .25): ";
	cin >> alpha;

	cout << "N (how 'loose' to set the timeout, sug ints 2 to 4)";
	cin >> n;	
	
	while(1)
		{
		cout << "New sample please";
		cin >> sample;
		difference = sample - rtt;
		absdifference = abs(difference);
		rtt = rtt + delta*difference;
		deviation=deviation + alpha*(absdifference - deviation);
		timeout = rtt + n*deviation;
		cout << "This RTT is: " << rtt << endl;
		cout << "This Deviation is: " << deviation << endl;
		cout << "Timeout should be set to: " << timeout << endl;
		}	
	exit(1);
	}
	
	

