package LAPR.US003;

import java.util.Objects;

public class NotebookEntrie {
    private String date;
    private String parcelID;
    private String duration;
    private String firstCycle;
    private String lastCycle;

    public NotebookEntrie(String date, String parcelID, String duration, String firstCycle, String lastCycle) {
        this.date = date;
        this.parcelID = parcelID;
        this.duration = duration;
        this.firstCycle = firstCycle;
        this.lastCycle = lastCycle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFirstCycle() {
        return firstCycle;
    }

    public void setFirstCycle(String firstCycle) {
        this.firstCycle = firstCycle;
    }

    public String getLastCycle() {
        return lastCycle;
    }

    public void setLastCycle(String lastCycle) {
        this.lastCycle = lastCycle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotebookEntrie that = (NotebookEntrie) o;
        return Objects.equals(date, that.date) && Objects.equals(parcelID, that.parcelID) && Objects.equals(duration, that.duration) && Objects.equals(firstCycle, that.firstCycle) && Objects.equals(lastCycle, that.lastCycle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, parcelID, duration, firstCycle, lastCycle);
    }
}
