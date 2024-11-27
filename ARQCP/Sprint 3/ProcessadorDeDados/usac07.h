#ifndef USAC07_H
#define USAC07_H
typedef struct {
    int *array;
    int length;
    int read;
    int write;
} BufferCircular;

BufferCircular *createBufferCircular(int length);
void insertValue(BufferCircular *buffer, int value);
int readValue(BufferCircular *buffer);
void freeBuffer(BufferCircular *buffer);

#endif
