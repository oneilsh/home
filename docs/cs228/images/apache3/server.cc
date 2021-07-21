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
#include <pwd.h>
#include <dirent.h>
#include <netdb.h>
#include <fcntl.h>
using namespace std;

#define NOTFOUND 1
#define DENIED 2
#define SYSTEM 3
#define MSIE 4
#define EUCLID 5

const int BUFSIZE=500;
int cliaddr = 0;

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
	cout << endl << endl << endl;
	cout << "Made fd: " << fd << ",";
	cliaddr = (unsigned int)client_addr.sin_addr.s_addr;
	cout << " address is " << (unsigned int)client_addr.sin_addr.s_addr << ",";
	cout << " family " << client_addr.sin_family << ",";
	cout << " port " << ntohs(client_addr.sin_port) << endl;
	return fd;
	}
	
char * turnNumberToBase(int target, int base)
	{
	int length = 0;
	int number = 0;
	char outstring[100];
	bool palindrome = true;
	char instring[100];
	char *in;
	in = &instring[0];
	char *out;
	out = &outstring[0];
	while(target != 0)
		{
		length++;
		if(target%base > 9)
			*in = target%base+ '7';
		else
			*in = target%base + '0';
		in++;
		target = target/base;
		}
	*in = 0;
	in = &instring[0];
	for(int x = length-1; x >=0; x--)
		{
		*out = *(in+x);
		out++;
		}
	*out = 0;
	out = &outstring[0];
	while(*in != 0)
		{
		if(*in != *out)
			palindrome = false;
		in++;
		out++;
		}
	out = &outstring[0];
	return out;
	}
	
void sendError(int fd, int type, int version)
	{
	if(version == 0)
		{
		if(type == NOTFOUND)
			{
			write(fd, "404 not found", 13);
			}
		else if(type == DENIED)
			{
			write(fd, "403 forbiddenness", 17);
			}
		else if(type == SYSTEM)
			{
			write(fd, "500 system error", 16);
			}
		else if(type == MSIE)
			{
			write(fd, "No MSIE allowed", 15);
			}
		}
	else if(version == 1)
		{
		if(type == NOTFOUND)
			{
			char header[1000];
			strcpy(header, "");
			strcat(header, "HTTP/1.0 404 Not found asshole, you suck\n");
			strcat(header, "Content-type: text/plain\n");
			strcat(header, "Content-length: ");
			strcat(header, "29");
			strcat(header, "\n\n");
			write(fd, header, strlen(header));
			write(fd, "404 not found, mr version 1.0", 29);
			}
		else if(type == DENIED)
			{
			char header[1000];
			strcpy(header, "");
			strcat(header, "HTTP/1.0 403 forbidden idiot, you suck\n");
			strcat(header, "Content-type: text/plain\n");
			strcat(header, "Content-length: ");
			strcat(header, "29");
			strcat(header, "\n\n");
			write(fd, header, strlen(header));
			write(fd, "403 forbidden, mr version 1.0", 29);
			}
		else if(type == SYSTEM)
			{
			char header[1000];
			strcpy(header, "");
			strcat(header, "HTTP/1.0 500 God I'm dying\n");
			strcat(header, "Content-type: text/plain\n");
			strcat(header, "Content-length: ");
			strcat(header, "32");
			strcat(header, "\n\n");
			write(fd, header, strlen(header));
			write(fd, "500 system error, mr version 1.0", 32);
			}
		else if(type == MSIE || type == EUCLID)
			{
			char header[1000];
			strcpy(header, "");
			strcat(header, "HTTP/1.0 500 God I'm dying\n");
			strcat(header, "Content-type: text/plain\n");
			strcat(header, "Content-length: ");
			strcat(header, "44");
			strcat(header, "\n\n");
			write(fd, header, strlen(header));
			if(type == EUCLID)
				write(fd, "You simply can't EUCLID here, mr version 1.0", 44);
			else
				write(fd, "You simply can't use IE here, mr version 1.0", 44);
			}
		}
	else if(version == 2)
		{
		if(type == NOTFOUND)
			{
			char header[1000];			
			strcpy(header, "");
			strcat(header, "HTTP/1.1 404 Not found asshole, you suck\n");
			strcat(header, "Content-type: text/plain\n");
			strcat(header, "Connection: close\n");
			strcat(header, "Content-length: ");
			strcat(header, "29");
			strcat(header, "\n\n");
			write(fd, header, strlen(header));
			write(fd, "404 not found, mr version 1.1", 29);
			}
		else if(type == DENIED)
			{
			char header[1000];
			strcpy(header, "");
			strcat(header, "HTTP/1.1 403 forbidden idiot, you suck\n");
			strcat(header, "Content-type: text/plain\n");
			strcat(header, "Connection: close\n");
			strcat(header, "Content-length: ");
			strcat(header, "29");
			strcat(header, "\n\n");
			write(fd, header, strlen(header));
			write(fd, "403 forbidden, mr version 1.1", 29);
			write(1, header, strlen(header));
			write(1, "403 forbidden, mr version 1.1", 29);
			}
		else if(type == SYSTEM)
			{
			char header[1000];
			strcpy(header, "");		
			strcat(header, "HTTP/1.1 500 God I'm dying\n");
			strcat(header, "Content-type: text/plain\n");
			strcat(header, "Connection: close\n");
			strcat(header, "Content-length: ");
			strcat(header, "32");
			strcat(header, "\n\n");
			write(fd, header, strlen(header));
			write(fd, "500 system error, mr version 1.1", 32);
			}
		else if(type == MSIE || type == EUCLID)
			{
			char header[1000];
			strcpy(header, "");
			strcat(header, "HTTP/1.1 500 God I'm dying\n");
			strcat(header, "Content-type: text/plain\n");
			strcat(header, "Connection: close\n");
			strcat(header, "Content-length: ");
			strcat(header, "24");
			strcat(header, "\n\n");
			write(fd, header, strlen(header));
			if(type == EUCLID)
				write(fd, "EUCLID!!, mr version 1.1", 24);
			else
				write(fd, "no MSIE!, mr version 1.1", 24);
			}
		}
	}
	
void doStuff(int fd, int option)
	{
	int len = 0;		// length of reveived data
	char buf[BUFSIZE];	// buffer in which to read
	char string_get[512];
	char string_request[512];
	char string_version[50];
	char string_host[50];
	char string_hostname[512];
	if(option == 0) //yay! server some pages, man
		{
		struct stat servstat;		//DELETE THIS STRUCT
		struct dirent *direntry;		//DELETE THIS STRUCT
		struct passwd *pwentry;
		if((len = read(fd, buf, BUFSIZE-1)) < 0)										//
			{																						//
			perror("Reading from client");												//
			exit(1);																				//
			}																						// Read what they said,
		buf[len] = 0;


		if(len = sscanf(buf, "%s %s %s %s %s", string_get, string_request, string_version, string_host, string_hostname) < 0)				// string_request should = what they actually asked for (for all versions of HTML)
			{																						//
			perror("Stupid scanner. I bet this error never even gets shown");	//
			exit(1);																				//
			}																						//
		//cout << "get is: " << string_get << " request is: " << string_request << " version is: " << string_version << " host is: " << string_host << " hostname is: " << string_hostname << endl;

		int version = 0;			//version = 0 if 0.9, 1 if 1.0 and 2 if 1.1
		if(strstr(string_version, "1.0") != 0)
			version = 1;
		else if(strstr(string_version, "1.1") != 0)
			version = 2;

		/* ------------MSIE BREAK CODE------------*/
		char* msie = strstr(buf, "MSIE");
		if(msie != 0)
			{
			sendError(fd, MSIE, version);
			exit(1);
			}
		/* ------------end MSIE BREAK CODE------------*/

		/* -------------EUCLID BREAK CODE ------------*/	
		cout << "cliaddr is now: " << cliaddr << endl;
		if(cliaddr == 163671750)
			{
			sendError(fd, EUCLID, version);
			exit(1);
			}			
	
		/* ------------- end EUCLID BREAK CODE ------------*/
		
		
		/*----------- ~user notation code --------- */		
		char serving[500];	//what to open
		if(string_request[0] == '/' && string_request[1] == '~')							// All this for handing ~user notation
			{
			char username[500];
			char *p;
			p = &string_request[2];
			int i = 0;
			for(i; *p != '/' && *p != 0; i++)
				{
				username[i] = *p;
				p++;
				}
			username[i+1] = 0;					
			pwentry = getpwnam(username);
			if(pwentry == 0)
				{
				perror("couldnt getpwnam");				//send an error and delete structs
				sendError(fd, NOTFOUND, version);
				exit(1);
				}
			strcpy(serving, pwentry->pw_dir);														//
			strcat(serving, "/pub");
			cout << username << "'s home dir is " << serving << endl;
			char *q;
			q = &serving[strlen(serving)];
			while(*p != 0)
				{
				*q = *p;
				p++; q++;
				}
			*q = 0;
			cout << "serving is now " << serving << endl;
			}
		else						//directly asking, ie no ~user notation
			{
			strcpy(serving, string_request);
			}
		/* -------------- end ~user notation code --------- */

		
		int ret = stat(serving, &servstat);
		if(ret < 0)		
			{
			perror("couldnt stat");
			sendError(fd, NOTFOUND, version);
			exit(1);									// probably send them an error before exiting, and remember to delete the structs
			}
			
		/* ------------- serving directory -------------------*/
		if(S_ISDIR(servstat.st_mode) && (servstat.st_mode & 4))  //This is a directory...
			{
			DIR *directory;
			directory = opendir(serving);
			if(directory == 0)
				{
				perror("couldnt open directory");
				sendError(fd, SYSTEM, version);
				exit(1);								// probably send them an error before exiting, and remember to delete the structs
				}
			direntry = readdir(directory);
			char dirlist[10000];
			dirlist[0]= 0;
			strcat(dirlist, "<BODY>\n");
			while (direntry != 0)
				{
				char pathtofile[1000];
				strcpy(pathtofile, "");
				strcat(pathtofile, serving);
				strcat(pathtofile, "/");
				strcat(pathtofile, direntry->d_name);
				
				strcat(dirlist, "<A href=\"");
				strcat(dirlist, pathtofile);
				strcat(dirlist, "\">");
				strcat(dirlist, direntry->d_name);
				strcat(dirlist, "</A><BR>\n");
								
				direntry = readdir(directory);
				}
			strcat(dirlist, "</BODY>\n");
//			cout << dirlist << " is " << strlen(dirlist) << " bytes long" <<endl;
			closedir(directory);							
			if(version == 0)
				write(fd, dirlist, strlen(dirlist));
			else if (version == 1)
				{
				char header[1000];
				strcpy(header, "");
				strcat(header, "HTTP/1.0 200 Ok, you suck\n");
				strcat(header, "Content-type: text/html\n");
				strcat(header, "Content-length: ");
				char *contlength;
				contlength = turnNumberToBase(strlen(dirlist), 10);
				strcat(header, contlength);
				strcat(header, "\n\n");
				write(fd, header, strlen(header));
				write(fd, dirlist, strlen(dirlist));
				}
			else if (version == 2)
				{
				char header[1000];
				strcpy(header, "");
				strcat(header, "HTTP/1.1 200 Ok, you suck\n");
				strcat(header, "Content-type: text/html\n");
				strcat(header, "Connection: close\n");
				strcat(header, "Content-length: ");

				cout << "length of content should match " << strlen(dirlist) << endl;
	
				char *contlength;
				contlength = turnNumberToBase(strlen(dirlist), 10);
				strcat(header, contlength);
				strcat(header, "\n\n");
				write(fd, header, strlen(header));
				write(fd, dirlist, strlen(dirlist));
				cout << "Sending: " << endl;
				write(1, header, strlen(header));
				write(1, dirlist, strlen(dirlist));
				}
			}
		/* --------------- end serving directory ---------------- */
		
		/* --------------- serving file --------------------------*/
		else if(servstat.st_mode & 4)					//This is a file....
			{
			int in = open(serving, O_RDONLY);			// 
			if(in == -1)									//
				{												// Opening file...
				perror("openingfile"); 					//
				sendError(fd, NOTFOUND, version);
				exit(1);							//  send an error
				}												//
	
	
			char writebuf[BUFSIZE];										//
			if((len = read(in, writebuf, BUFSIZE-1)) < 1)		//
				{																//
				perror("First read on file");	
				sendError(fd, SYSTEM, version);						//
				exit(1);					// send an error
				}																// Give it to them
			char* test; 				//used for testing strstr's
			if(version == 1)
				{
				char header[1000];
				strcpy(header, "");
				strcat(header, "HTTP/1.0 200 Ok, you suck\n");
				if((test = strstr(serving, ".htm")) != 0 || (test = strstr(serving, ".html")) != 0)
					strcat(header, "Content-type: text/html\n");
				else if((test = strstr(serving, ".jpg")) != 0)
					strcat(header, "Content-type: image/jpeg\n");
				else if((test = strstr(serving, ".gif")) != 0)
					strcat(header, "Content-type: image/gif\n");
				else
					strcat(header, "Content-type: text/plain\n");	// correct this to match what the file is
				strcat(header, "Content-length: ");
				
				int filesize = servstat.st_size;
				char *contlength;
				contlength = turnNumberToBase(filesize, 10);
				strcat(header, contlength);
				strcat(header, "\n\n");
				write(fd, header, strlen(header));
				}

			else if(version == 2)
				{
				char header[1000];
				strcpy(header, "");
				strcat(header, "HTTP/1.0 200 Ok, you suck\n");
				if((test = strstr(serving, ".htm")) != 0 || (test = strstr(serving, ".html")) != 0)
					strcat(header, "Content-type: text/html\n");
				else if((test = strstr(serving, ".jpg")) != 0)
					strcat(header, "Content-type: image/jpeg\n");
				else if((test = strstr(serving, ".gif")) != 0)
					strcat(header, "Content-type: image/gif\n");
				else
					strcat(header, "Content-type: text/plain\n");	// correct this to match what the file is
				strcat(header, "Connection: close\n");
				strcat(header, "Content-length: ");
				
				int filesize = servstat.st_size;
				char *contlength;
				contlength = turnNumberToBase(filesize, 10);		// TEST
				strcat(header, contlength);
				strcat(header, "\n\n");
				write(fd, header, strlen(header));
				cout << "Sending: " << endl;
				write(1, header, strlen(header)+1);
				}
				
			int count = len;					
			while(len > 0)					
				{											
				write(fd, writebuf, len);	
				len = read(in, writebuf, BUFSIZE-1);	
				count+=len;						
				}	
			//cout << "I wrote " << count << " bytes" << endl;							
			close(in);										// Probably not necessary as this child will kill itself anyway.	
			}
		
		else					//forbidden!!!! mwa ha hah
			{
			cout << "Error, permission denied. " << endl;
			sendError(fd, DENIED, version);					// for gods sake send him an error
			exit(1);
			}
			
		/* -------------- end serving file -------------------*/	
		delete(&servstat);
		delete(direntry);
		delete(pwentry);	
		close(fd);										// Close file descriptors
		}
	else				//he connected but didnt do anything, bitch him out
		{
		write(fd, "Use a webserver, dumbass", 25);				// for gods sake send him an error
		}


	exit(1);		//oh my god kill the process for christs sake
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
			tv.tv_sec = 1;
			tv.tv_usec = 50000; //millionths of a second
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

