#include <fstream>
using namespace std;

int main()
	{
	ofstream file ("testinput.txt");
	file << "2000000\n";
	for(int i = 2000000; i > 0; i--)
		file << i << "\n";
	file << "100\n";
	file << "0\n";
	}
