typedef struct {
    char type[50];
    int min;
    int max;
} SensorValues;

void serialize(Values *values, FILE *output);
