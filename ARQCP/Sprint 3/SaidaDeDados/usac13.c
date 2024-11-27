#include <stdio.h>

// Definition of the structure to store sensor values
typedef struct {
    char sensorType[50];  // Type of the sensor
    int minValue;        // Minimum value of the sensor (in hundredths)
    int maxValue;        // Maximum value of the sensor (in hundredths)
} SensorValues;

// Function to serialize sensor values and write to a file
void serialize(SensorValues *sensorValues, FILE *outputFile)
{
    // Converting values from hundredths to real numbers with two decimal places
    int minValueAux = sensorValues->minValue;
    float minRealValue = (float) minValueAux / 100;

    int maxValueAux = sensorValues->maxValue;
    float maxRealValue = (float) maxValueAux / 100;

    // Writing the values to the file
    fprintf(outputFile, "%s:\nMinimum:%.2f\nMaximum:%.2f\n\n", sensorValues->sensorType, minRealValue, maxRealValue);
}
