#include <stdio.h>
#include <stdlib.h>

#include "USAC07.h"
typedef struct {
    BufferCircular *buffer;
    int sensor_id;
    int time;
    char type[50];
    char unit[50];
} SensorConfig;

int read_and_insert(FILE *arquivo, SensorConfig *config){
	if(arquivo == NULL){
		printf("Erro no ficheiro.\n");
		return 0;
	}
	float valueFile;
		fscanf(arquivo, "sensor_id:%d#type:%49[^#]#value:%f#unit:%49[^#]#time:%d\n", &(config->sensor_id), config->type, &valueFile, config->unit, &(config->time));

		int res = (int) (valueFile *100);

	return res;
}

