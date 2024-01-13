package io.github.colindj1120.materialdesignui.workout;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public final class AvailableExerciseTemplate implements Comparable<AvailableExerciseTemplate>, Identifiable {
    private final IntegerProperty exerciseID;
    private final StringProperty exerciseName;

    public AvailableExerciseTemplate(int exerciseID, String exerciseName) {
        this.exerciseID = new SimpleIntegerProperty(this, "exerciseID", exerciseID);
        this.exerciseName = new SimpleStringProperty(this, "exerciseName", exerciseName);
    }

    public int getExerciseID() {
        return exerciseID.get();
    }

    public IntegerProperty exerciseIDProperty() {
        return exerciseID;
    }

    public String getExerciseName() {
        return exerciseName.get();
    }

    public StringProperty exerciseNameProperty() {
        return exerciseName;
    }

    @Override
    public String toString() {
        return String.format("Exercise{ ID: %d, Name: %s }", getExerciseID(), getDisplayName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getExerciseID());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AvailableExerciseTemplate aet) {
            return getExerciseID() == aet.getExerciseID();
        }
        return false;
    }

    @Override
    public String getDisplayName() {
        return getExerciseName();
    }

    @Override
    public int compareTo(AvailableExerciseTemplate o) {
        return Integer.compare(getExerciseID(), o.getExerciseID());
    }
}

