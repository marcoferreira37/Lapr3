main: main.o asm.o
	gcc -g main.o asm.o -Wall -Wextra -fanalyzer -z noexecstack -o main

main.o: main.c asm.h
	gcc -g -Wall -Wextra -fanalyzer -z noexecstack -c main.c -o main.o

asm.o: asm.s
	gcc -g -Wall -Wextra -fanalyzer -z noexecstack  -c asm.s -o asm.o

run: main
	./main

clean:
	rm main.o asm.o main

debug:
	gdb -tui ./main

