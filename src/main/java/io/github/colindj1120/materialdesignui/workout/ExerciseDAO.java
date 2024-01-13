package io.github.colindj1120.materialdesignui.workout;

import com.hyperion.sqlbuilder.builders.DerbyBuilder;
import com.hyperion.sqlbuilder.datatypes.Join.DerbyJoin;
import com.hyperion.sqlbuilder.sqlexpressions.OrderByExpression;
import io.github.colindj1120.database.DatabaseUtility;
import io.github.colindj1120.database.processors.QueryResultProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hyperion.sqlbuilder.datatypes.Join.Join.LEFT_JOIN;
import static com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator.EQUALS;
import static com.hyperion.sqlbuilder.sqlexpressions.Comparison.comparison;
import static com.hyperion.sqlbuilder.sqlexpressions.LogicalGroup.beginLogicalGroup;
import static com.hyperion.sqlbuilder.sqlexpressions.ParameterPlaceholder.parameterPlaceholder;

public class ExerciseDAO {
    private static final String          JDBC_DRIVER         = "org.apache.derby.jdbc.ClientDriver";
    private static final String          connStr             = "jdbc:derby://localhost:1527/C:/Derby/WorkoutDB";
    private static final DatabaseUtility DBUtil              = DatabaseUtility.getInstance(JDBC_DRIVER, connStr);
    private static final String          EXERCISE_NAME_ALIAS = "ExerciseName";

    public static ObservableList<AvailableExerciseTemplate> getExerciseNamesByMovementType(String movementTypeName) {
        DerbyBuilder exerciseByMovementTypeQuery = getExerciseByMovementTypeQuery();

        try {
            return DBUtil.executeQuery(exerciseByMovementTypeQuery.build(), getAllExerciseNamesProcessor(), 1, movementTypeName);
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(e.getCause()
                                 .toString());
            alert.setTitle("Error");
            return FXCollections.emptyObservableList();
        }
    }

    private static DerbyBuilder getExerciseByMovementTypeQuery() {
        //@formatter:off
        return new DerbyBuilder().select(Columns.eNameCol.alias(EXERCISE_NAME_ALIAS), Columns.eExerciseIDCol)
                                 .from(Tables.eExercisesTable)
                                 .join(LEFT_JOIN, Tables.emmExerciseMovementMappingTable)
                                 .on(comparison(Columns.eExerciseIDCol, EQUALS, Columns.emmExerciseIDCol))
                                 .join(LEFT_JOIN, Tables.mtMovementTypesTable)
                                 .on(comparison(Columns.emmMovementTypeIDCol, EQUALS, Columns.mtMovementTypeIDCol))
                                 .where(beginLogicalGroup().andGroup(comparison(Columns.eUserIDCol, EQUALS, parameterPlaceholder()),
                                                                     comparison(Columns.mtNameCol, EQUALS, parameterPlaceholder())))
                                 .orderBy(OrderByExpression.column(Columns.eNameCol.alias(EXERCISE_NAME_ALIAS)));
        //@formatter:on
    }

    public static ObservableList<AvailableExerciseTemplate> getAllExerciseNames() {
        DerbyBuilder allExerciseNamesQuery = getAllExerciseNamesQuery();

        try {
            return DBUtil.executeQuery(allExerciseNamesQuery.build(), getAllExerciseNamesProcessor(), 1);
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(e.getCause()
                                 .toString());
            alert.setTitle("Error");
            return FXCollections.emptyObservableList();
        }
    }

    private static QueryResultProcessor<ResultSet, ObservableList<AvailableExerciseTemplate>> getAllExerciseNamesProcessor() {
        return rs -> {
            List<AvailableExerciseTemplate> exerciseNames = new ArrayList<>();
            while (rs.next()) {
                String exerciseName = rs.getString(EXERCISE_NAME_ALIAS);
                int exerciseID = rs.getInt(Columns.eExerciseIDCol.nameOnly()
                                                                 .render());
                AvailableExerciseTemplate aet = new AvailableExerciseTemplate(exerciseID, exerciseName);
                exerciseNames.add(aet);
            }
            return FXCollections.observableArrayList(exerciseNames);
        };
    }

    private static DerbyBuilder getAllExerciseNamesQuery() {
        return new DerbyBuilder().select(Columns.eNameCol.alias(EXERCISE_NAME_ALIAS), Columns.eExerciseIDCol)
                                 .from(Tables.eExercisesTable)
                                 .where(comparison(Columns.eUserIDCol, EQUALS, parameterPlaceholder()))
                                 .orderBy(OrderByExpression.column(Columns.eNameCol.alias(EXERCISE_NAME_ALIAS)));
    }

    public static ObservableList<ExerciseTemplate> getAllExerciseTemplates(String programName) {
        DerbyBuilder programSqlBuilder = new DerbyBuilder().select(Columns.wpProgramIDCol, Columns.wptWeekNumberCol, Columns.wptDayNumberCol, Columns.wptExerciseIDCol, Columns.wptExerciseOrderNumberCol,
                                                                   Columns.eNameCol.alias(EXERCISE_NAME_ALIAS), Columns.rsRepSchemeIDCol, Columns.rsSetsCol, Columns.rsRepsCol, Columns.rsRpeCol, Columns.rsPercentageCol)
                                                           .from(Tables.wpWorkoutProgramsTable)
                                                           .join(DerbyJoin.LEFT_JOIN, Tables.wptWorkoutProgramTemplateTable)
                                                           .on(comparison(Columns.wpProgramIDCol, EQUALS, Columns.wptProgramIDCol))
                                                           .join(DerbyJoin.LEFT_JOIN, Tables.eExercisesTable)
                                                           .on(comparison(Columns.wptExerciseIDCol, EQUALS, Columns.eExerciseIDCol))
                                                           .join(DerbyJoin.LEFT_JOIN, Tables.rsRepSchemesTable)
                                                           .on(comparison(Columns.wptRepSchemeIDCol, EQUALS, Columns.rsRepSchemeIDCol))
                                                           .where(beginLogicalGroup().andGroup(comparison(Columns.wpNameCol, EQUALS, parameterPlaceholder()),
                                                                                               comparison(Columns.wpUserIDCol, EQUALS, parameterPlaceholder())))
                                                           .orderBy(OrderByExpression.column(Columns.wptWeekNumberCol, Columns.wptDayNumberCol, Columns.wptExerciseOrderNumberCol));

        try {
            QueryResultProcessor<ResultSet, ObservableList<ExerciseTemplate>> processor = rs -> {
                final List<ExerciseTemplate> exercises = new ArrayList<>();

                while (rs.next()) {
                    int weekNumber = rs.getInt(Columns.wptWeekNumberCol.nameOnly()
                                                                       .render());
                    int dayNumber = rs.getInt(Columns.wptDayNumberCol.nameOnly()
                                                                     .render());
                    Weekday weekDay = new Weekday(weekNumber, dayNumber);

                    if (weekDay.getWeekNumber() != 0 && weekDay.getDayNumber() != 0) {
                        exercises.add(getExerciseTemplate(rs, weekDay));
                    }
                }

                return FXCollections.observableArrayList(exercises);
            };

            return DBUtil.executeQuery(programSqlBuilder.build(), processor, programName, 1);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return FXCollections.emptyObservableList();
        }
    }

    private static ExerciseTemplate getExerciseTemplate(ResultSet rs, Weekday weekDay) throws SQLException {
        int exerciseID = rs.getInt(Columns.wptExerciseIDCol.nameOnly()
                                                           .render());

        String exerciseName = rs.getString(EXERCISE_NAME_ALIAS);

        AvailableExerciseTemplate aet = new AvailableExerciseTemplate(exerciseID, exerciseName);

        Integer programID = rs.getInt(Columns.wpProgramIDCol.nameOnly()
                                                            .render());
        Integer repSchemeID = rs.getInt(Columns.rsRepSchemeIDCol.nameOnly()
                                                                .render());
        Double rpe = (rs.getObject(Columns.rsRpeCol.nameOnly()
                                                   .render()) != null) ? rs.getDouble(Columns.rsRpeCol.nameOnly()
                                                                                                      .render()) : Double.NaN;
        Double percentage = (rs.getObject(Columns.rsPercentageCol.nameOnly()
                                                                 .render()) != null) ? rs.getDouble(Columns.rsPercentageCol.nameOnly()
                                                                                                                           .render()) : Double.NaN;
        Integer sets = rs.getInt(Columns.rsSetsCol.nameOnly()
                                                  .render());
        Integer reps = rs.getInt(Columns.rsRepsCol.nameOnly()
                                                  .render());
        Integer orderNum = rs.getInt(Columns.wptExerciseOrderNumberCol.nameOnly()
                                                                      .render());

        return new ExerciseTemplate(programID, repSchemeID, weekDay, aet, rpe, percentage, sets, reps, orderNum);
    }
}

