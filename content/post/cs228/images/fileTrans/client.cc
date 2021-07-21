/* My dumb client -- just an illustration */

#include <stream.h>		
#include <stdio.h>	
#include <string.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

const int BUFSIZE=500;

int MakeSocket(char *host, char *port) {
	int s; 			
	int len;	
	struct sockaddr_in sa; 
	struct hostent *hp;
	struct servent *sp;
	int portnum;	
	int *ppointer;
	ppointer = &portnum;
	int ret;

	if((hp = gethostbyname(host)) == 0)
		perror("In gethostbyname");
	bcopy((char *)hp->h_addr, (char *)&sa.sin_addr, hp->h_length);
	sa.sin_family = hp->h_addrtype;
	sscanf(port, "%d", ppointer);
	if (portnum > 0) {
		sa.sin_port = htons(portnum);
	}
	else {
		sp=getservbyname(port, "tcp");
		portnum = sp->s_port;
		sa.sin_port = sp->s_port;
	}
	if((s = socket(hp->h_addrtype, SOCK_STREAM, 0)) < 0)
		perror("Making Socket");
	ret = connect(s, (struct sockaddr *)&sa, sizeof(sa));
	if(ret < 0)
		{
		perror("Couldn't Connect Socket");
		}
	else
		{
		cout << "Connect to host " << host  << " port " << port << endl;
		return s;
		}
}

main(int argc, char *argv[]) {


	int s; 			// socket descriptor
	int len;		// length of reveived data
	char buf[BUFSIZE];	// buffer in which to read
	int ret;		// return code from various system calls
	s = MakeSocket(argv[1], argv[2]);
	if (s < 1) {
		perror("Making socket from main");
		exit(1);
	}

	
		//cin.getline(buf,BUFSIZE);
		
		cout << "File to read (no spaces for now):";
		cin >> buf;
		cout << "Opening: " << buf << endl;
		//write(1, buf, strlen(buf)+1);
		len = write(s, buf, strlen(buf)+1);
		if(len < 0)
			perror("Writing to server");
			
		if((len = read(s, buf, BUFSIZE-1)) < 0)
			perror("On first read");
		int count = len;
		while(len > 0){
			//cout << buf;
			write(1, buf, len);
			len = read(s, buf, BUFSIZE-1);
			
			count += len;
			}
		write(s, "goodbye", strlen("goodbye"));	
		cout << "Read a total of " << count << " bytes." << endl;
		
	
}


