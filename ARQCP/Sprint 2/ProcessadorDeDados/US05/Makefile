main: main.o mediana.o	sortArray.o
	gcc -g main.o mediana.o sortArray.o -Wall -Wextra -fanalyzer -z noexecstack -o main

main.o: main.c mediana.h sortArray.h
	gcc -g -Wall -Wextra -fanalyzer -z noexecstack -c main.c -o main.o

mediana.o: mediana.s
	gcc -g -Wall -Wextra -fanalyzer -z noexecstack  -c mediana.s -o mediana.o

sortArray.o: sortArray.s
	gcc -g -Wall -Wextra -fanalyzer -z noexecstack -c sortArray.s -o sortArray.o

run: main
	./main

clean:
	rm main.o mediana.o main

debug:
	gdb -tui ./main
