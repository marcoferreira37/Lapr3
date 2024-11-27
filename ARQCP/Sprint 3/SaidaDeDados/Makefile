prog: usac12.o usac13.o
	gcc usac12.o usac13.o -g -o prog

usac12.o: usac12.c
	gcc -Wall -Wextra -fanalyzer -c usac12.c -g -o usac12.o

usac13.o: usac13.c
	gcc -Wall -Wextra -fanalyzer -c usac13.c -g -o usac13.o

run: prog
	./prog intermedio output 60000

clean:
	rm -f *.o prog
