package LAPR.US002;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WateringPlan {
    private final LocalDateTime date;
    private final Parcel parcel;
    private final int activationTime;
    private final LocalDateTime firstCycle;
    private final LocalDateTime lastCycle;

    public WateringPlan(LocalDateTime date, Parcel parcel, int activationTime, LocalDateTime firstCycle, LocalDateTime lastCycle) {
        this.date = date;
        this.parcel = parcel;
        this.activationTime = activationTime;
        this.firstCycle = firstCycle;
        this.lastCycle = lastCycle;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public int getActivationTime() {
        return activationTime;
    }

    public LocalDateTime getFirstCycle() {
        return firstCycle;
    }
    public LocalDateTime getLastCycle() {
        return lastCycle;
    }
}
