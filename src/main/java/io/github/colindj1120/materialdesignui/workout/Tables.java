package io.github.colindj1120.materialdesignui.workout;

import com.hyperion.sqlbuilder.sqlexpressions.Table;

public class Tables {
    public static final Table barsTable                        = Table.name("Bars");
    public static final Table exercisesTable                   = Table.name("Exercises");
    public static final Table exerciseMovementMappingTable     = Table.name("Exercise_Movement_Mapping");
    public static final Table exerciseOrderTemporaryTable      = Table.name("Exercise_Order_Temporary");
    public static final Table lastPlateMathTogglePositionTable = Table.name("Last_Plate_Math_Toggle_Position");
    public static final Table lastSelectedBarTable             = Table.name("Last_Selected_Bar");
    public static final Table movementTypesTable               = Table.name("Movement_Types");
    public static final Table repSchemesTable                  = Table.name("Rep_Schemes");
    public static final Table selectedWeightsTable             = Table.name("Selected_Weights");
    public static final Table trainingMaxTable                 = Table.name("Training_Max");
    public static final Table usersTable                       = Table.name("Users");
    public static final Table weightsTable                     = Table.name("Weights");
    public static final Table workoutProgramsTable             = Table.name("Workout_Programs");
    public static final Table workoutProgramTemplateTable      = Table.name("Workout_Program_Template");

    //Correlated Table References
    static final String BAR_CORRELATION                            = "b";
    static final String EXERCISE_MOVEMENT_TYPE_MAPPING_CORRELATION = "emm";
    static final String EXERCISES_CORRELATION                      = "e";
    static final String LAST_SELECTED_BAR_CORRELATION              = "lsb";
    static final String MOVEMENT_TYPE_CORRELATION                  = "mt";
    static final String REP_SCHEMES_CORRELATION                    = "rs";
    static final String SELECTED_WEIGHTS_CORRELATION               = "sw";
    static final String TRAINING_MAX_CORRELATION                   = "tm";
    static final String WEIGHTS_CORRELATION                        = "w";
    static final String WORKOUT_PROGRAMS_CORRELATION               = "wp";
    static final String WORKOUT_PROGRAM_TEMPLATE_CORRELATION       = "wpt";

    public static final Table bBarsTable                      = barsTable.correlation(BAR_CORRELATION);
    public static final Table eExercisesTable                 = exercisesTable.correlation(EXERCISES_CORRELATION);
    public static final Table emmExerciseMovementMappingTable = exerciseMovementMappingTable.correlation(EXERCISE_MOVEMENT_TYPE_MAPPING_CORRELATION);
    public static final Table lsbLastSelectedBarTable         = lastSelectedBarTable.correlation(LAST_SELECTED_BAR_CORRELATION);
    public static final Table mtMovementTypesTable            = movementTypesTable.correlation(MOVEMENT_TYPE_CORRELATION);
    public static final Table rsRepSchemesTable               = repSchemesTable.correlation(REP_SCHEMES_CORRELATION);
    public static final Table swSelectedWeightsTable          = selectedWeightsTable.correlation(SELECTED_WEIGHTS_CORRELATION);
    public static final Table tmTrainingMaxTable              = trainingMaxTable.correlation(TRAINING_MAX_CORRELATION);
    public static final Table wWeightsTable                   = weightsTable.correlation(WEIGHTS_CORRELATION);
    public static final Table wpWorkoutProgramsTable          = workoutProgramsTable.correlation(WORKOUT_PROGRAMS_CORRELATION);
    public static final Table wptWorkoutProgramTemplateTable  = workoutProgramTemplateTable.correlation(WORKOUT_PROGRAM_TEMPLATE_CORRELATION);
}
