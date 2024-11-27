#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/time.h>
#include "usac07.h"
#include "usac12.h"

void createNewDirectory(const char *path) {
    struct stat st = {0};

    if (stat(path, &st) == -1) {
        // Directory does not exist
        if (mkdir(path, 0777) != 0) {
            perror("Error creating directory.");
            exit(EXIT_FAILURE);
        }
        printf("New directory created successfully.\n");
    } else if (S_ISDIR(st.st_mode)) {
        // Path exists and is a directory
        printf("The path already exists and is a directory.\n");
    } else {
        // Path exists but is not a directory
        printf("The path is not a directory.\n");
    }
}

SensorOutput* generateSensorOutput(Sensor* sensor){

    SensorOutput* sensorOutput = (SensorOutput*) malloc(sizeof(SensorOutput));

    sensorOutput->sensor_id = sensor->idSensor;
    strcpy(sensorOutput->type, sensor->type);
    strcpy(sensorOutput->unit, sensor->unit);
    sensorOutput->mediana = sensor->median;

    return sensorOutput;
}

void freeSensorOutput(SensorOutput* sensorOutput){
    free(sensorOutput->mediana);
    free(sensorOutput);
}

void delayExecution(int milliseconds) {
    struct timeval start, current;

    gettimeofday(&start, NULL);

    do {
        gettimeofday(&current, NULL);
    } while ((current.tv_sec - start.tv_sec) * 1000 + (current.tv_usec - start.tv_usec)/1000 < milliseconds);
}
