typedef struct {
    BufferCircular *buffer;
    int sensor_id;
    int time;
    char type[50];
    char unit[50];
} SensorConfig;

int read_and_insert(FILE *arquivo, SensorConfig *config);
