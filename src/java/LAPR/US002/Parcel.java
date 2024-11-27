package LAPR.US002;

public class Parcel {
    private String parcelId;
    private int duration;
    private String regularity;

     private String mix;
     private int recurrence;

    public Parcel(String parcelId, int duration, String regularity, String mix, int recurrence) {
        this.parcelId = parcelId;
        this.duration = duration;
        this.regularity = regularity;
        this.mix = mix;
        this.recurrence = recurrence;
    }

    public String getParcelId() {
        return parcelId;
    }

    public int getDuration() {
        return duration;
    }

    public String getMix() {
        return mix;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public String getRegularity() {
        return regularity;
    }
}
