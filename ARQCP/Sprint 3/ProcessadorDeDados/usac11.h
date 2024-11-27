#ifndef USAC11_H
#define USAC11_H

// Definição da estrutura BufferCircular
typedef struct {
    int *array;
    int length;
    int read;
    int write;
} BufferCircular;

// Definição da estrutura SensorConfig
typedef struct {
    BufferCircular *buffer;
    int sensor_id;
    int time;
    char type[50];
    char unit[50];
} SensorConfig;

// Protótipos das funções
BufferCircular *criarBufferCircular(int length, const char *path);
void insertValue(BufferCircular *buffer, int value);
int calcularMediana(BufferCircular *buffer);
void esperarMilissegundos(int milissegundos);
void obterDataHoraAtual(char *nomeArquivo);

#endif /* USAC11_H */
