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
#include <sys/wait.h>
#include <netdb.h>
#include <fcntl.h>
using namespace std;

const int BUFSIZE=500;
int numConnects = 0;	//how many people are connected
int connection[100];	//the array of connections, file descriptors

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
	//cout << "Made fd: " << fd << endl;
	return fd;
	}
	
int splitLine(char *input, char *word[])
	{
	int count = 0;
	int currentWordLen;
	bool inword=false;
	for(int i = 1; input[i] != 0; i++)	//start at 1 to get rid of leading '/'
		{
		if(input[i] == '_')
			{
			inword = false;
			word[count][currentWordLen] = 0;
			count++;
			currentWordLen = 0;
			}
		else
			{
			word[count][currentWordLen++] = input[i];
			inword = true;
			}
		}
	word[count][currentWordLen] = 0;
	count++;
	return count;
	}
	
void doStuff(int fd, int option)
	{
	int len;		// length of reveived data
	char buf[BUFSIZE];	// buffer in which to read
	char string_one[512];
	char string_two[512];

	if(option == 0)			//outputting with options asked for
		{	
		if(len = read(fd, buf, BUFSIZE-1) < 0)			
			{
			perror("Reading error");
			exit(1);
			}
		if(len = sscanf(buf, "%s %s", string_one, string_two) < 0)
			{
			perror("Stupid Scanner");
			exit(1);
			}
		
		char *word[10];
		for(int i = 0; i < 10; i++)
			{
			word[i] = new char[20];
			}
		int count = splitLine(string_two, word);
		word[count]	= 0;
		
		//cout << "Execing " << count << " commands: ";
		//for(int i = 0; i < count; i++)
	//		cout << word[i] << " ";
		//cout << endl;			
		
		if(len = dup2(fd, 1) < 0)
			{
			perror("Couldnt dup");
			exit(1);
			}
		if(strcmp(word[0],"fortune") == 0 || strcmp(word[0],"echo") == 0 || strcmp(word[0],"man") == 0)
			execvp(word[0], word);
		else if(strcmp(word[0],"listfortunes") == 0)
			execlp("ls", "ls", "-R", "/usr/share/fortune", 0);
		else
			execlp("cat", "cat", "helpfile", 0);
		}
		
	else		//Outputting default down the fd
		{
		if(len = dup2(fd, 1) < 0)
			{
			perror("Couldnt dup");
			exit(1);
			}
		execlp("fortune", "fortune",  0);
		}
	cout << "Couldn't exec for some reason. Try something like http://server:port/fortune_-o OR http://server:port/help" << endl;
	exit(1);		//this shouldnt happen
	}

main(int argc, char *argv[]) 
	{

	int s;			//socket descriptor
	int fd; 			// file descriptor
	int len;		// length of reveived data
	char buf[BUFSIZE];	// buffer in which to read
	int ret; 			//various system call crap
	char string_one[512];
	char string_two[512];
	struct timeval tv;
	fd_set rfds;		//set of file descriptors
	int max;		//biggest file descripter in set
	s = MakeSocket(argv[1]);
	//fd = makeFileDescriptor(s);
	if (s < 1) 
		{
		perror("Making socket");
		exit(1);
		}	


	while(1)
		{
		int fd = makeFileDescriptor(s);

		int pid = fork();
		if(pid < 0)
			{
			perror("Fork Broke");
			exit(1);
			}
		else if(pid == 0)	//I am the child
			{
			FD_ZERO(&rfds);		//initialize all the fds that we shall pay attention to to 0
			FD_SET(fd, &rfds);		//pay attention to the socket, dammit
			max = fd;
			tv.tv_sec = 0;
			tv.tv_usec = 5000; //millionths of a second
			ret = select(max+1, &rfds, 0, 0, &tv);	//who do we listen to?
			if(ret > 0) //do what they asked for
				{
				doStuff(fd, 0);
				}
			else
				{
				cout << "tv timed out, outputting default" << endl;
				doStuff(fd, 1);
				}
			}
		else	
			{
			close(fd);
			}
		}
	}

