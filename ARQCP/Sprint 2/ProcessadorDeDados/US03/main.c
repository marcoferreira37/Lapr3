#include <stdio.h>
#include "asm.h"

int main() {
    int buffer[] = {1, 2, 3, 4,5};
    int destination[5];
    int read_ptr = 0;
    int write_ptr = 0;
    int num_elements = 5;
    int result;

    result = move_num_vec(buffer, sizeof(buffer) / sizeof(int), &read_ptr, &write_ptr, num_elements, destination);
	printf("%d\n" , result);

    if (result == 1) {
        printf("Elements copied successfully.\n");

    } else {
        printf("No elements copied.\n");
    }

    return 0;
}
