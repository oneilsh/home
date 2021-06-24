/* My dumb client -- just an illustration */

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
	if((gethostname(localhostname, 255)) < 0)				//um, so now i have 
		perror("Could not get my Hostname: ");	//the dns name of this
															//machine
	struct hostent *hp;
	if((hp = gethostbyname(localhostname)) == 0)
		perror("Couldn't gethostbyname:");
	bcopy((char *)hp->h_addr, (char *)&my_addr.sin_addr, hp->h_length);														
	
	s = socket(my_addr.sin_family, SOCK_STREAM, 0);
	if(s < 0){
		perror("Couldn't Make Socket:");
		}	
	ret = bind(s, (struct sockaddr *)&my_addr, sizeof(my_addr));
	if(ret < 0){
		perror("Couldn't bind socket: ");
		}
	
	listen(s, 3);
	struct sockaddr_in client_addr;
	int sa_len = sizeof(client_addr);
	fd = accept(s, (struct sockaddr *)&client_addr, (unsigned int *)&sa_len);
	if(fd < 0){
		perror("Couldn't Create File Descriptor: ");
		}
	cout << "Connection: ";
	cout << "address is " << (unsigned int)my_addr.sin_addr.s_addr;
	cout << "family " << my_addr.sin_family;
	cout << "port " << ntohs(my_addr.sin_port);
	cout << endl;
	return fd;
}

main(int argc, char *argv[]) {


	int fd; 			// socket descriptor
	int len;		// length of reveived data
	char buf[BUFSIZE];	// buffer in which to read

	fd = MakeSocket(argv[1]);
	if (fd < 1) {
		perror("Making socket");
		exit(1);
	}
	
	if((len = read(fd, buf, BUFSIZE-1)) < 0)
		perror("Reading");
	buf[len] = 0;
	
	int in = open(buf, O_RDONLY);
	if(in == -1){
		perror("Opening file");
		exit(1);
		}
	char writebuf[BUFSIZE];
	if((len = read(in, writebuf, BUFSIZE-1)) < 1)
		perror("First read on file");
	int count = len;
	while(len > 0){
		write(fd, writebuf, len);
		len = read(in, writebuf, BUFSIZE-1);
//		writebuf[len] = 0;
//		cout << "Read " << len << "Bytes.\n";
//		cout << "\"" << writebuf << "\"\n";
		cout << "Currently Writing Data" << endl;
		count += len;
		}
	cout << "Wrote a total of " << count << "bytes." << endl;
	close(fd);
	read(fd, buf, BUFSIZE-1);	
//char buf[100]
//int len = read(in, buf, 100);
//while len > 0
//	write out, buf, len
//	len = read in, buf, 100
//write(fd,b,1)
//	
}


