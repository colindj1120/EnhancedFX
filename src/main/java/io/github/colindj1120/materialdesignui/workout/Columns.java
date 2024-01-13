package io.github.colindj1120.materialdesignui.workout;

import com.hyperion.sqlbuilder.sqlexpressions.Column;

import static io.github.colindj1120.materialdesignui.workout.Tables.*;

public class Columns {
    public static final Column barIDCol                 = Column.name("BarID");
    public static final Column barNameCol               = Column.name("BarName");
    public static final Column barWeightCol             = Column.name("BarWeight");
    public static final Column calculatedTrainingMaxCol = Column.name("CalculatedTrainingMax");
    public static final Column dayNumberCol             = Column.name("DayNumber");
    public static final Column descriptionCol           = Column.name("Description");
    public static final Column emailCol                 = Column.name("Email");
    public static final Column exerciseIDCol            = Column.name("ExerciseID");
    public static final Column exerciseOrderNumberCol   = Column.name("ExerciseOrderNumber");
    public static final Column isPoundsCol              = Column.name("IsPounds");
    public static final Column movementTypeIDCol        = Column.name("MovementTypeID");
    public static final Column nameCol                  = Column.name("Name");
    public static final Column oneRepMaxCol             = Column.name("OneRepMax");
    public static final Column passwordHashCol          = Column.name("PasswordHash");
    public static final Column percentageCol            = Column.name("Percentage");
    public static final Column programIDCol             = Column.name("ProgramID");
    public static final Column quantityCol              = Column.name("Quantity");
    public static final Column repSchemeIDCol           = Column.name("RepSchemeID");
    public static final Column repsCol                  = Column.name("Reps");
    public static final Column rpeCol                   = Column.name("RPE");
    public static final Column roundingCol              = Column.name("Rounding");
    public static final Column setsCol                  = Column.name("Sets");
    public static final Column trainingMaxIDCol         = Column.name("TrainingMaxID");
    public static final Column trainingMaxIncreaseCol   = Column.name("TrainingMaxIncrease");
    public static final Column trainingMaxPercentageCol = Column.name("TrainingMaxPercentage");
    public static final Column userIDCol                = Column.name("UserID");
    public static final Column weekNumberCol            = Column.name("WeekNumber");
    public static final Column weightDenominationCol    = Column.name("WeightDenomination");
    public static final Column weightIDCol              = Column.name("WeightID");

    //Bars Table References
    public static final Column bBarIDCol     = barIDCol.reference(BAR_CORRELATION);
    public static final Column bBarNameCol   = barNameCol.reference(BAR_CORRELATION);
    public static final Column bBarWeightCol = barWeightCol.reference(BAR_CORRELATION);

    //Exercise Movement Type Mapping Table References
    public static final Column emmExerciseIDCol     = exerciseIDCol.reference(EXERCISE_MOVEMENT_TYPE_MAPPING_CORRELATION);
    public static final Column emmMovementTypeIDCol = movementTypeIDCol.reference(EXERCISE_MOVEMENT_TYPE_MAPPING_CORRELATION);

    //Exercise Table References
    public static final Column eExerciseIDCol = exerciseIDCol.reference(EXERCISES_CORRELATION);
    public static final Column eNameCol       = nameCol.reference(EXERCISES_CORRELATION);
    public static final Column eUserIDCol     = userIDCol.reference(EXERCISES_CORRELATION);

    //Last Selected Bar Table References
    public static final Column lsbBarIDCol    = barIDCol.reference(LAST_SELECTED_BAR_CORRELATION);
    public static final Column lsbIsPoundsCol = isPoundsCol.reference(LAST_SELECTED_BAR_CORRELATION);
    public static final Column lsbUserIDCol   = userIDCol.reference(LAST_SELECTED_BAR_CORRELATION);

    //Movement Types Table References
    public static final Column mtMovementTypeIDCol = movementTypeIDCol.reference(MOVEMENT_TYPE_CORRELATION);
    public static final Column mtNameCol           = nameCol.reference(MOVEMENT_TYPE_CORRELATION);

    //Rep Schemes Table References
    public static final Column rsPercentageCol  = percentageCol.reference(REP_SCHEMES_CORRELATION);
    public static final Column rsRepSchemeIDCol = repSchemeIDCol.reference(REP_SCHEMES_CORRELATION);
    public static final Column rsRpeCol         = rpeCol.reference(REP_SCHEMES_CORRELATION);
    public static final Column rsRepsCol        = repsCol.reference(REP_SCHEMES_CORRELATION);
    public static final Column rsSetsCol        = setsCol.reference(REP_SCHEMES_CORRELATION);

    //Selected Weights Table References
    public static final Column swWeightIDCol = weightIDCol.reference(SELECTED_WEIGHTS_CORRELATION);
    public static final Column swUserIDCol   = userIDCol.reference(SELECTED_WEIGHTS_CORRELATION);

    //Training Max Table References
    public static final Column tmExerciseIDCol            = exerciseIDCol.reference(TRAINING_MAX_CORRELATION);
    public static final Column tmCalculatedTrainingMaxCol = calculatedTrainingMaxCol.reference(TRAINING_MAX_CORRELATION);
    public static final Column tmNameCol                  = nameCol.reference(TRAINING_MAX_CORRELATION);
    public static final Column tmOneRepMaxCol             = oneRepMaxCol.reference(TRAINING_MAX_CORRELATION);
    public static final Column tmRoundingCol              = roundingCol.reference(TRAINING_MAX_CORRELATION);
    public static final Column tmTrainingMaxIncreaseCol   = trainingMaxIncreaseCol.reference(TRAINING_MAX_CORRELATION);
    public static final Column tmTrainingMaxPercentageCol = trainingMaxPercentageCol.reference(TRAINING_MAX_CORRELATION);
    public static final Column tmTrainingMaxIDCol         = trainingMaxIDCol.reference(TRAINING_MAX_CORRELATION);

    //Weights Table References
    public static final Column wIsPoundsCol           = isPoundsCol.reference(WEIGHTS_CORRELATION);
    public static final Column wQuantityCol           = quantityCol.reference(WEIGHTS_CORRELATION);
    public static final Column wWeightDenominationCol = weightDenominationCol.reference(WEIGHTS_CORRELATION);
    public static final Column wWeightIDCol           = weightIDCol.reference(WEIGHTS_CORRELATION);
    public static final Column wUserIDCol             = userIDCol.reference(WEIGHTS_CORRELATION);

    //Workout Programs Table References
    public static final Column wpDescriptionCol = descriptionCol.reference(WORKOUT_PROGRAMS_CORRELATION);
    public static final Column wpNameCol        = nameCol.reference(WORKOUT_PROGRAMS_CORRELATION);
    public static final Column wpProgramIDCol   = programIDCol.reference(WORKOUT_PROGRAMS_CORRELATION);
    public static final Column wpUserIDCol      = userIDCol.reference(WORKOUT_PROGRAMS_CORRELATION);

    //Workout Program Template Table References
    public static final Column wptDayNumberCol           = dayNumberCol.reference(WORKOUT_PROGRAM_TEMPLATE_CORRELATION);
    public static final Column wptExerciseIDCol          = exerciseIDCol.reference(WORKOUT_PROGRAM_TEMPLATE_CORRELATION);
    public static final Column wptExerciseOrderNumberCol = exerciseOrderNumberCol.reference(WORKOUT_PROGRAM_TEMPLATE_CORRELATION);
    public static final Column wptProgramIDCol           = programIDCol.reference(WORKOUT_PROGRAM_TEMPLATE_CORRELATION);
    public static final Column wptRepSchemeIDCol         = repSchemeIDCol.reference(WORKOUT_PROGRAM_TEMPLATE_CORRELATION);
    public static final Column wptWeekNumberCol          = weekNumberCol.reference(WORKOUT_PROGRAM_TEMPLATE_CORRELATION);
}

