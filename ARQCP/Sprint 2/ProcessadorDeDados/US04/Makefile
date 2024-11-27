main: main.o sortArray.o
	gcc -g main.o sortArray.o -Wall -Wextra -fanalyzer -z noexecstack -o main

main.o: main.c
	gcc -g -Wall -Wextra -fanalyzer -z noexecstack -c main.c -o main.o

sortArray.o: sortArray.s
	gcc -g -Wall -Wextra -fanalyzer -z noexecstack -c sortArray.s -o sortArray.o

clean:
	rm -f *.o main

run: main
	./main

debug:
	gdb -tui ./main
