#ifndef USAC12_H
#define USAC12_H

typedef struct {
    int sensor_id;
    char type[100];
    char unit[100];
    Median mediana;
} Output;

#endif
f