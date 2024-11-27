#include <stdio.h>
#include "asm.h"
int array[]={1,20,13,4,135,6};
int *ptr = array;
int num=6;
int main (void){

	printf("The original array:\n");
	for (int i=0;i<num;i++){
		printf ("%d\n", array[i]);
	}
	sort_array(ptr,num);
	printf("The sorted array:\n");
	for (int i=0;i<num;i++){
		printf ("%d\n", array[i]);
	}
}
