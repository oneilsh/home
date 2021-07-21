/*Shawn O'Neil's Program */

#include <stream.h>		
#include <stdio.h>	
#include <string.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <netdb.h>
#include <fcntl.h>
using namespace std;

const int BUFSIZE=500;

int MakeSocket(char *port) {
	int s;   //socket to make
	int fd;	//the file descriptor to return 			
	int len;	//lenght of something apparently
	int ret; //check errors on this!
	
	int portnum;
	int *ppointer;							//port number pointer
	ppointer = &portnum;
	struct servent *sp;					//struct for finding port num
												//if work like "telnet" is entered
	
	struct sockaddr_in my_addr; 		//my address info
	//FILLING OUT MY ADDR INFO
	my_addr.sin_family = AF_INET;		//set my family type
	
	sscanf(port, "%d", ppointer);		//set my portnum to use
	if(portnum > 0)
		my_addr.sin_port = htons(portnum);
	else{
		sp=getservbyname(port, "tcp");
		portnum = sp->s_port;
		my_addr.sin_port = sp->s_port;
		}
	
	char localhostname[255];
	if((gethostname(localhostname, 255)) < 0){				//um, so now i have 
		perror("Could not get my Hostname: ");	//the dns name of this
		exit(1);
		}													//machine
	struct hostent *hp;
	if((hp = gethostbyname(localhostname)) == 0){
		perror("Couldn't gethostbyname:");
		exit(1);
		}
	bcopy((char *)hp->h_addr, (char *)&my_addr.sin_addr, hp->h_length);														
	
	s = socket(my_addr.sin_family, SOCK_STREAM, 0);
	if(s < 0){
		perror("Couldn't Make Socket:");
		exit(1);
		}	
	ret = bind(s, (struct sockaddr *)&my_addr, sizeof(my_addr));
	if(ret < 0){
		perror("Couldn't bind socket: ");
		exit(1);
		}
	
	listen(s, 3);
	return s;

}

int makeFileDescriptor(int s){
	int fd;
	struct sockaddr_in client_addr;
	int sa_len = sizeof(client_addr);
	fd = accept(s, (struct sockaddr *)&client_addr, (unsigned int *)&sa_len);
	if(fd < 0){
		perror("Couldn't Create File Descriptor: ");
		exit(1);
		}
//	cout << "Connection: ";
//	cout << "address is " << (unsigned int)my_addr.sin_addr.s_addr;
//	cout << "family " << my_addr.sin_family;
//	cout << "port " << ntohs(my_addr.sin_port);
//	cout << endl;
	return fd;
	}

double getnumfromstring(char *in)
	{
	char* beginning = in;
	bool foundadot = false;
	double total = 0;
	while(*in != 0)
		{
		if('0' <= *in && *in <= '9')
			total = total*10+(*in - '0');
		else if(*in == '.')
				foundadot = true;
		else if(*in == '-')
				return -1;
		in++;
		}
	if(foundadot)
		total = total/100;
	return total;
	}

main(int argc, char *argv[]) {

	int s;			//socket descriptor
	int fd; 			// file descriptor
	int len;		// length of reveived data
	char buf[BUFSIZE];	// buffer in which to read
	char list[1000][20];
	char command[64];
	char number[64];
	char price[1024];
	s = MakeSocket(argv[1]);	
	for(int i = 0; i < 1000; i++)
		{
		strcpy(list[i], "0.00");
		}


while(1)
	{
	fd = makeFileDescriptor(s);
	if (fd < 1) 
		{
		perror("Making socket");
		exit(1);
		}

	len = 0;
	
	if((len = read(fd, buf, BUFSIZE-1)) < 0){
		perror("Reading");
		exit(1);
		}
	buf[len] = 0;
	write(1, buf, len);
	if(len = sscanf(buf, "%s %s %s", command, number, price) <0){
		perror("Stupid Scanner, parse error");
	   strcpy(command, "bogusdude");	
		}
	
	int num;
	if(len = sscanf(number, "%d", &num) < 0 ){
		perror("Error Scanning number from string");
		}
	cout << "Recieved '" << command << "' Command, '" << num << "' number, and '" << price << "' price." << endl;
	//cout << "In number form the number should be: " << num << endl;

	//double theprice = getnumfromstring(price);
	
	if(command != NULL)
		{
		
		
		//LIST Command Recieved
		if(strcmp(command, "LIST") == 0)
			{
			cout << "trying to list" << endl;
			cout << "Sending LIST for item: " << number << endl;
			if (0 <= num && num < 1000)
				{
				if(strcmp(list[num], "0.00") == 0)
					{
					if(len = write(fd, "E1", 3) < 0)
						perror("Error Sending List info on nonexistant item.");
					}
				else
					{
					if(len = write(fd, list[num], strlen(list[num])) < 0)
						perror("Error sending List info on existing item");
					}
				}
			else
				{
				cout << "Bad List Number Recieved" << endl;
				if(len = write(fd, "E2", 3) < 0)
					perror("Error sending error for bad list number recieved.");
				}
			}
			
		//ADD commmand Recived
		else if(strcmp(command, "ADD") == 0)
			{
			cout << "trying to add" << endl;
			if(0 <= num && num < 1000)
				{
				if(strcmp(list[num], "0.00") == 0 && getnumfromstring(price) > 0)
					{
					strcpy(list[num],price);
					cout << "just changed price due to an ADD" << endl;
					}
				else
					{
					if(len = write(fd, "E3", 3) < 0)
						perror("Error sending error on item already exists");
					}
				}
			else
				{
				cout << "Bad List Number Recieved, added out of bounds" << endl;
				if(len = write(fd, "E4", 4) < 0)
					perror("Error sending error for bad ADD, out of bounds.");
				}
			}
			
		//Bid Command Recieved
		else if(strcmp(command, "BID") == 0)
			{
			cout << "trying to bid" << endl;
			if(0 <= num && num < 1000)
				{
				cout << getnumfromstring(price) << " should be greater than " << getnumfromstring(list[num]) << endl;	
				if(getnumfromstring(price) > getnumfromstring(list[num]) && strcmp(list[num], "0.00") != 0)
					{
					strcpy(list[num], price);
					}
				else
					{
					cout << "Bid was too low" << endl;
					if(len = write(fd, "E5", 3) < 0)
						perror("Error sending bid too low error.");
					}
				}
			else
				{
				cout << "Bad List Number Recieved" << endl;
				if(len = write(fd, "E6", 3) < 0)
					perror("Error sending out of bounds error on BID");
				}
			}
		
		//Bogus Command Recieved
		else
			{
			cout << "Bogus command: '" << command << "' not recognized." << endl;
			if(len = write(fd, "E7", 3) < 0)
				perror("Error sending bogus command Error");
			}
		
		}
	
	else
		{
		cout << "Error: Recieved Null Command" << endl;
		
		}
		
	
	close(fd);
	cout << "CONTENTS OF LIST" << endl;
	for(int i = 0; i < 1000; i++)
		{
		if(strcmp(list[i],"0.00") != 0)
			cout << list[i] << endl;
		}
	cout << endl << endl;
	
	
}
//char buf[100]
//int len = read(in, buf, 100);
//while len > 0
//	write out, buf, len
//	len = read in, buf, 100
//write(fd,b,1)
//	
}


