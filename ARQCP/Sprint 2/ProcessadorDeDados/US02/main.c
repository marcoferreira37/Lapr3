#include <stdio.h>
#include "asm.h"

int main() {

    int length;
     // Prompt the user for the length of the array
    printf("Enter the length of the array: ");
    scanf("%d", &length);
    int array[length];
    int *pointer = &array[0];
    int read_index = 0;
    int write_index = 0;
    int value_to_enqueue;
    // Prompt the user for the value to enqueue
    printf("Enter the value to enqueue: ");
    scanf("%d", &value_to_enqueue);

    enqueue_value(pointer, length, &read_index, &write_index, value_to_enqueue);
    printf("Value enqueued: %d\n", value_to_enqueue);

 return 0;
}

