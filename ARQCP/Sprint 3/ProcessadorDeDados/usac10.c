#include <stdio.h>
#include <time.h>

#include "USAC07.h"
#include "USAC0809.h"

void serialize (SensorConfig *sensor, FILE *file, int mediana){
	fprintf(file, "%d,%d,%s,%s,%d#\n", sensor -> sensor_id, sensor -> buffer ->write, sensor -> type, sensor-> unit, mediana );
}
