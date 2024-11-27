#include <stdio.h>
#include "mediana.h"
#include "sortArray.h"

int array[] = {};

int num = 0;
int* vec = array;

int main(void) {

	printf("array: [ ");

	for(int i = 0; i < num; i++){
        printf("%d ", array[i]);
    }
    printf("]\n");

    printf("num: %d\n" , num);

	int res = mediana(vec, num);

	printf("mediana: %d\n", res);

	return 0;
}
