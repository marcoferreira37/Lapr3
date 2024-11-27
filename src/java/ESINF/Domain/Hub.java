package ESINF.Domain;

import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Hub {
    private String localId;
    private Coordinates coordinates;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;
    private boolean promoted = false;

    public Hub(String localId, Coordinates coordinates, LocalDateTime startingTime, LocalDateTime endingTime) {
        this.localId = localId;
        this.coordinates = coordinates;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public int getNumeroColaboradores() {
        return extrairNumeroUtilizadores(localId);
    }

    private int extrairNumeroUtilizadores(String id) {
        int length = id.length();
        int indice = length - 1;

        while (indice >= 0 && Character.isDigit(id.charAt(indice))) {
            indice--;
        }

        String numeroString = id.substring(indice + 1, length);

        try {
            return Integer.parseInt(numeroString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public boolean promoteToHub() {
        this.promoted = true;
        return true;
    }
    public void setToLocation(boolean promoted) {
        this.promoted = false;
    }

    public boolean isPromoted() {
        return promoted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hub hub = (Hub) o;
        return  Objects.equals(localId, hub.localId) && Objects.equals(coordinates, hub.coordinates) && Objects.equals(startingTime, hub.startingTime) && Objects.equals(endingTime, hub.endingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localId, coordinates, startingTime, endingTime);
    }

    @Override
    public String toString() {
        if (!promoted) {
            return String.format("Location { localId='%5s', coordinates { %s }, %dh:%02d - %dh:%02d }",
                    localId, coordinates,
                    startingTime.getHour(), startingTime.getMinute(),
                    endingTime.getHour(), endingTime.getMinute());
        } else {
            return String.format("Hub { localId='%5s', coordinates { %s }, %dh:%02d - %dh:%02d }",
                    localId, coordinates,
                    startingTime.getHour(), startingTime.getMinute(),
                    endingTime.getHour(), endingTime.getMinute());
        }
    }

}
