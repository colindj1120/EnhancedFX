package io.github.colindj1120.materialdesignui.workout;

import java.util.Objects;

public class Weekday implements Comparable<Weekday> {
    private int weekNumber;
    private int dayNumber;

    public Weekday(int weekNumber, int dayNumber) {
        this.weekNumber = weekNumber;
        this.dayNumber = dayNumber;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Weekday weekday) {
            return weekNumber == weekday.weekNumber && dayNumber == weekday.dayNumber;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekNumber, dayNumber);
    }

    @Override
    public int compareTo(Weekday other) {
        if (this.weekNumber != other.weekNumber) {
            return Integer.compare(this.weekNumber, other.weekNumber);
        } else {
            return Integer.compare(this.dayNumber, other.dayNumber);
        }
    }

    @Override
    public String toString() {
        return String.format("Week: %d, Day: %d", weekNumber, dayNumber);
    }
}
