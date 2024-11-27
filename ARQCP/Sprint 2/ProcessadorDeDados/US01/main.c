#include <stdio.h>
#include "asm.h"

int main (void){
	char input[]="sensor_id:8#type:atmospheric_temperature#value:21.60#unit:celsius#time:2470030";
	char token[5000];
	int output;

	printf ("Input string: %s\n", input);

	printf("Enter the desired token:\n");
	scanf ("%s", token);


	extract_token(input, token, &output);

	printf("Extracted value: %d.%02d\n", output/100, output%100);
    return 0;
}
