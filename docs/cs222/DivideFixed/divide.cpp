//  Shawn O'Neil - Program 1 for CS222
//  Integer Division for very long strings.
//  Fixed the powers of 10 quotient bug

#include <iostream>
#include <string>
#include <assert.h>
using namespace std;

string add(string n, string d);
string subtract(string top, string bottom);
void RemLead0(string &s);
string RemLastChar(string s);
bool isSmaller(string one, string two);
bool checkAllNumbers(string n);
void divide(string top, string bot, string &q, string &r);

int main()
	{
 	cout << "Enter an integer:  ";
 	string n;
 	getline (cin,n);
 	cout << "Enter another integer:  ";
 	string d;
 	getline (cin,d);
	RemLead0(d);
	RemLead0(n);
	if(!checkAllNumbers(n))
		{
		cout << "Numerator contains non-number characters. Quitting. " << endl;
		return 1;
		}
	if(!checkAllNumbers(d))
		{
		cout << "Denominator contains non-number characters. Quitting. " << endl;
		return 1;
		}
	if(d == "0")
		{
		cout << "Divide by zero error. Quitting. " << endl;
		return 1;
		}
	string q = "";
	string r = "";
 	divide(n,d,q,r);
	cout << q << "  remainder  " << r << endl;
	}


// A method to divide two strings.
void divide(string top, string bot, string &q, string &r)
	{
	string quo = "";
	string temp = bot;
	if(top == bot)
		{
		q = "1";
		r = "0";
		return;
		}
	int counter = 0;
	while(isSmaller(temp, top))
		{
		temp = temp +"0";
		counter++;
		}
	temp = RemLastChar(temp);
	counter--;
	for(int i = counter; counter >= 0; counter--)
		{
		i = i;	//so my computer doesn't bitch about unused i
		int count = 0;
		while(isSmaller(temp, top))
			{
			top = subtract(top, temp);
			count++;
			}
		quo = quo + (char)(count + '0');
		temp = RemLastChar(temp);
		}
	q = quo;
	r = top;
	}

// Subtracts the bot string from the top string, checking to make sure the top string is bigger. Uses the nines compliment method.
string subtract(string top, string bot)
	{
	int lentop = top.length()-1;
	int lenbot = bot.length()-1;
	if(isSmaller(top, bot) && top != bot)
		cout << "Subtraction resulted in negative number. Not responsible for really messed up answers. " << endl;
	for(int i = 0; i < lentop - lenbot; i++)
		{
		bot = "0" + bot;
		lenbot++;
		}

	string nines = "";
	for(int i = 0; i < lenbot + 1; i++)
		{
		nines = nines + (char)((9 - (bot[i]-'0'))+'0');
		}
	string ninesplus1 = add(nines, "1");
	string subtotal = add(ninesplus1, top);
	string answer = subtotal.substr(1);
	RemLead0(answer);
	return answer;
	}

// Adds two numerical strings n, and d.
string add(string n, string d)
	{
	int len1 = n.length()-1;
	int len2 = d.length()-1;
	int carry = 0;
	string sum  = "";

	while(len1 >= 0 || len2 >= 0)
		{
		int digit1, digit2;

		if(len1 >= 0) digit1 = n[len1]-'0';
		else digit1 = 0;

		if(len2 >= 0) digit2 = d[len2]-'0';
		else digit2 = 0;

		int i = digit1+digit2+carry;
		if(i  > 9)							//or carry = i > 9 ? 1 : 0;
			carry = 1;						//OR carry = i / 10;
		else
			carry = 0;
		int digit = i%10;
		sum = ( (char)(digit + '0') )+ sum;
		len1--;
		len2--;
		}
	if(carry == 1)
		sum = '1' + sum;
	return sum;
	}
//Returns true if all characters in the string are numbers, false otherwise.
bool checkAllNumbers(string n)
	{
	for(int i = 0; i < (int)n.length(); i++)
		{
		if('0' > n[i] || n[i] > '9')
			return false;
		}
	return true;
	}

// Returns a string just like the one it takes, but without the last character.
string RemLastChar(string s)
	{
	string answer = "";
	for(int i = 0; i < (int)s.length() - 1; i++)
		{
		answer = answer + s[i];
		}
	return answer;
	}

// Checks to see if numerical string one is smaller than string two. If it is, it returns true.
// I could've sworn one could do this with built in c++ string libraries, but for some reason I couln't get it to work.
bool isSmaller(string one, string two)
	{
	int lenone = one.length();
	int lentwo = two.length();
	//cout << "one: " << one <<" len1: " << lenone << " two: "<< two <<" len2: " << lentwo << endl;
	if(lentwo > lenone)
		{
		//cout << one << " is smaller than " << two << endl;
		return true;
		}
	if(lenone == lentwo)
		{
		int i = 0;
		while(one[i] == two[i] && i != lenone-1)
			{
			i++;
			}
		if(one[i] <= two[i])
			{
			//cout << one << " is smaller than " << two << endl;
			return true;
			}
		else
			{
			//cout << one << " is not smaller than " << two << endl;
			return false;
			}
		}
	//cout << one << " is not smaller than " << two << endl;
	return false;
	}

// Removes the leading 0's from a string. If the string is all 0's, it leaves the last one on.
void RemLead0(string &s)
	{
	while(s[0] =='0')		/// or while(s != "0" && s[0] == '0')
		{
		if(s == "0") break;	//and take this out
		s = s.substr(1);
		}
	}
