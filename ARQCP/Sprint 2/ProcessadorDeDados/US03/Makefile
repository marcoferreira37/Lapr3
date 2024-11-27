main: main.o asm.o
	gcc main.o asm.o -o main -no-pie

main.o: main.c asm.h
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o

asm.o: asm.s
	gcc -g -Wall -Wextra -fanalyzer -c asm.s -o asm.o

run: main
	./main

clean:
	rm -f *.o main

debug: main
	gdb -tui ./main
