package io.github.colindj1120.materialdesignui.workout;

import java.util.Objects;

public class ExerciseTemplate {
    private ExerciseTemplateProperty<Integer>                   programID;
    private ExerciseTemplateProperty<Integer>                   repSchemeID;
    private ExerciseTemplateProperty<Weekday>                   weekday;
    private ExerciseTemplateProperty<AvailableExerciseTemplate> exercise;
    private ExerciseTemplateProperty<Double>                    rpe;
    private ExerciseTemplateProperty<Double>                    percentage;
    private ExerciseTemplateProperty<Integer>                   sets;
    private ExerciseTemplateProperty<Integer>                   reps;
    private ExerciseTemplateProperty<Integer>                   exerciseOrderNumber;

    public ExerciseTemplate(int programID, int repSchemeID, Weekday weekday, AvailableExerciseTemplate exercise, Double rpe, Double percentage, int sets, int reps, int exerciseOrderNumber) {
        this.programID           = new ExerciseTemplateProperty<>(this, "programID", programID, ExerciseTemplateColumn.PROGRAM_ID);
        this.repSchemeID         = new ExerciseTemplateProperty<>(this, "repSchemeID", repSchemeID, ExerciseTemplateColumn.REP_SCHEME_ID);
        this.weekday             = new ExerciseTemplateProperty<>(this, "weekday", weekday, ExerciseTemplateColumn.WEEKDAY);
        this.exercise            = new ExerciseTemplateProperty<>(this, "exercise", exercise, ExerciseTemplateColumn.EXERCISE);
        this.rpe                 = new ExerciseTemplateProperty<>(this, "rpe", rpe, ExerciseTemplateColumn.RPE);
        this.percentage          = new ExerciseTemplateProperty<>(this, "percentage", percentage, ExerciseTemplateColumn.PERCENTAGE);
        this.sets                = new ExerciseTemplateProperty<>(this, "sets", sets, ExerciseTemplateColumn.SETS);
        this.reps                = new ExerciseTemplateProperty<>(this, "reps", reps, ExerciseTemplateColumn.REPS);
        this.exerciseOrderNumber = new ExerciseTemplateProperty<>(this, "exerciseOrderNumber", exerciseOrderNumber, ExerciseTemplateColumn.EXERCISE_ORDER_NUMBER);
    }

    public int getProgramID() {
        return programID.get();
    }

    public ExerciseTemplateProperty<Integer> programIDProperty() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID.set(programID);
    }

    public int getRepSchemeID() {
        return repSchemeID.get();
    }

    public ExerciseTemplateProperty<Integer> repSchemeIDProperty() {
        return repSchemeID;
    }

    public void setRepSchemeID(int repSchemeID) {
        this.repSchemeID.set(repSchemeID);
    }

    public Weekday getWeekday() {
        return weekday.get();
    }

    public ExerciseTemplateProperty<Weekday> weekdayProperty() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday.set(weekday);
    }

    public void setWeekday(int weekNum, int dayNum) {
        setWeek(weekNum);
        setDay(dayNum);
    }

    public void setDay(int dayNum) {
        this.weekday.get()
                    .setDayNumber(dayNum);
    }

    public int getDay() {
        return this.weekday.get()
                           .getDayNumber();
    }

    public void setWeek(int weekNum) {
        this.weekday.get()
                    .setWeekNumber(weekNum);
    }

    public int getWeek() {
        return this.weekday.get()
                           .getWeekNumber();
    }

    public AvailableExerciseTemplate getExercise() {
        return exercise.get();
    }

    public int getExerciseID() {
        return getExercise().getExerciseID();
    }

    public String getExerciseName() {
        return getExercise().getExerciseName();
    }

    public ExerciseTemplateProperty<AvailableExerciseTemplate> exerciseProperty() {
        return exercise;
    }

    public void setExercise(AvailableExerciseTemplate exercise) {
        this.exercise.set(exercise);
    }

    public Double getRpe() {
        return rpe.get();
    }

    public ExerciseTemplateProperty<Double> rpeProperty() {
        return rpe;
    }

    public void setRpe(double rpe) {
        this.rpe.set(rpe);
    }

    public boolean isRpeNaN() {
        return rpe.get()
                  .isNaN();
    }

    public Double getPercentage() {
        return percentage.get();
    }

    public ExerciseTemplateProperty<Double> percentageProperty() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage.set(percentage);
    }

    public boolean isPercentageNaN() {
        return percentage.get()
                         .isNaN();
    }

    public int getSets() {
        return sets.get();
    }

    public ExerciseTemplateProperty<Integer> setsProperty() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets.set(sets);
    }

    public int getReps() {
        return reps.get();
    }

    public ExerciseTemplateProperty<Integer> repsProperty() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps.set(reps);
    }

    public int getOrderNumber() {
        return exerciseOrderNumber.get();
    }

    public ExerciseTemplateProperty<Integer> exerciseOrderNumberProperty() {
        return exerciseOrderNumber;
    }

    public void setExerciseOrderNumber(int exerciseOrderNumber) {
        this.exerciseOrderNumber.set(exerciseOrderNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExerciseTemplate e) {
            return getExercise().equals(e.getExercise());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getExercise());
    }

    @Override
    public String toString() {
        return String.format("ExerciseTemplate { progID:%d, repID:%d, %s, weekday:%s, rpe:%f, percentage:%f, sets:%d, reps:%d, exerciseOrderNumber=%d }", programID.get(), repSchemeID.get(),
                             exercise.get(), weekday.get(), rpe.get(), percentage.get(), sets.get(), reps.get(), exerciseOrderNumber.get());
    }

    public ExerciseTemplate duplicate() {
        return new ExerciseTemplate(getProgramID(), getRepSchemeID(), getWeekday(), getExercise(), getRpe(), getPercentage(), getSets(), getReps(), getOrderNumber());
    }

    public void copyFrom(ExerciseTemplate other) {
        this.programID           = other.programID;
        this.repSchemeID         = other.repSchemeID;
        this.weekday             = other.weekday;
        this.exercise            = other.exercise;
        this.rpe                 = other.rpe;
        this.percentage          = other.percentage;
        this.sets                = other.sets;
        this.reps                = other.reps;
        this.exerciseOrderNumber = other.exerciseOrderNumber;
    }
}

