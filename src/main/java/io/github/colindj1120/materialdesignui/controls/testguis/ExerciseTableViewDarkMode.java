//package io.github.colindj1120.customtextfielddemo.testguis;
//
//import io.github.colindj1120.customtextfielddemo.workout.ExerciseDAO;
//import io.github.colindj1120.customtextfielddemo.workout.ExerciseTemplate;
//import io.github.palexdev.materialfx.controls.MFXComboBox;
//import io.github.palexdev.materialfx.controls.MFXTableColumn;
//import io.github.palexdev.materialfx.controls.MFXTableRow;
//import io.github.palexdev.materialfx.controls.MFXTableView;
//import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
//import io.github.palexdev.materialfx.enums.FloatMode;
//import io.github.palexdev.materialfx.enums.SortState;
//import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
//import javafx.collections.ObservableList;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.VBox;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.function.Consumer;
//import java.util.function.Function;
//import java.util.stream.IntStream;
//
//public class ExerciseTableViewDarkMode extends VBox {
//    private MFXTableView<ExerciseTemplate>   tableView;
//    private MFXTableColumn<ExerciseTemplate> nameColumn;
//    private MFXTableColumn<ExerciseTemplate> rpeColumn;
//    private MFXTableColumn<ExerciseTemplate> percentageColumn;
//    private MFXTableColumn<ExerciseTemplate> setsColumn;
//    private MFXTableColumn<ExerciseTemplate> repsColumn;
//
//    private final int numColumns       = 5;
//    private final int nameColNum       = 0;
//    private final int rpeColNum        = 1;
//    private final int percentageColNum = 2;
//    private final int setsColNum       = 3;
//    private final int repsColNum       = 4;
//
//    public ExerciseTableViewDarkMode() {
//        ObservableList<ExerciseTemplate> exerciseTemplates = ExerciseDAO.getAllExerciseTemplates("Standard 5x5");
//
//        try {
//            setupTableColumns();
//            setupTableView(exerciseTemplates);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        setSpacing(20);
//        setAlignment(Pos.CENTER);
//        getStylesheets().add("/test.css");
//        setStyle("-fx-background-color: black");
//        setPadding(new Insets(20, 20, 20, 20));
//        getChildren().add(tableView);
//    }
//
//    private void setupTableColumns() {
//        IntStream.range(0, numColumns)
//                 .forEachOrdered(i -> {
//                     switch (i) {
//                         case nameColNum -> setupColumn(this::setNameColumn, "Name", 200, exercise -> new MFXTableRowCell<>(ExerciseTemplate::getExerciseName));
//                         case rpeColNum -> setupColumn(this::setRpeColumn, "RPE", 50, exercise -> new MFXTableRowCell<>(ExerciseTemplate::getRpe, e -> (e == null || e.isNaN()) ? "" : e.toString()));
//                         case percentageColNum -> setupColumn(this::setPercentageColumn, "Percentage", 50,
//                                                              exercise -> new MFXTableRowCell<>(ExerciseTemplate::getPercentage, e -> (e == null || e.isNaN()) ? "" : e + "%"));
//                         case setsColNum -> setupColumn(this::setSetsColumn, "Sets", 50, exercise -> new MFXTableRowCell<>(ExerciseTemplate::getSets));
//                         case repsColNum -> setupColumn(this::setRepsColumn, "Reps", 50, exercise -> new MFXTableRowCell<>(ExerciseTemplate::getReps));
//                         default -> throw new UnsupportedOperationException("Error retrieving column event handler. Column number exceeds the maximum number of columns");
//                     }
//                 });
//    }
//
//    private void setupColumn(Consumer<MFXTableColumn<ExerciseTemplate>> columnConsumer, String name, double prefWidth, Function<ExerciseTemplate,
//            MFXTableRowCell<ExerciseTemplate, ?>> rowCellFactory) {
//        MFXTableColumn<ExerciseTemplate> column = new MFXTableColumn<>(name, false);
//        column.setPrefWidth(prefWidth);
//        column.setSortState(SortState.UNSORTED);
//        column.setRowCellFactory(rowCellFactory);
//        columnConsumer.accept(column);
//    }
//
//    private void setupTableView(ObservableList<ExerciseTemplate> exerciseTemplates) {
//        tableView = new MFXTableView<>();
//        tableView.setFooterVisible(false);
//        tableView.setPrefHeight(450);
//        tableView.setTableRowFactory(exerciseTemplate -> {
//            MFXTableRow<ExerciseTemplate> row = new MFXTableRow<>(tableView, exerciseTemplate);
//            IntStream.range(0, numColumns)
//                     .forEachOrdered(i -> {
//                         MFXTableRowCell<ExerciseTemplate, ?> cell = row.getCells()
//                                                                        .get(i);
//                         EventHandler<MouseEvent> mouseEvent = switch (i) {
//                             case nameColNum -> getNameColumnMouseEvent(row, cell, exerciseTemplates);
//                             case rpeColNum -> getRpeColumnMouseEvent(row, cell, exerciseTemplates);
//                             case percentageColNum -> getPercentageColumnMouseEvent(row, cell, exerciseTemplates);
//                             case setsColNum -> getSetsColumnMouseEvent(row, cell, exerciseTemplates);
//                             case repsColNum -> getRepsColumnMouseEvent(row, cell, exerciseTemplates);
//                             default -> throw new UnsupportedOperationException("Error retrieving column event handler. Column number exceeds the maximum number of columns");
//                         };
//
//                         cell.setOnMouseClicked(mouseEvent);
//                     });
//
//            row.selectedProperty()
//               .addListener((obs, oldVal, newVal) -> row.getCells()
//                                                        .forEach(cell -> cell.setGraphic(null)));
//
//            return row;
//        });
//
//        if (nameColumn != null &&
//            rpeColumn != null &&
//            percentageColumn != null &&
//            setsColumn != null &&
//            repsColumn != null) {
//            tableView.getTableColumns().addAll(List.of(nameColumn, rpeColumn, percentageColumn, setsColumn, repsColumn));
//
//        }
//
//        tableView.setItems(exerciseTemplates);
//    }
//
//    private EventHandler<MouseEvent> getNameColumnMouseEvent(MFXTableRow<ExerciseTemplate> row, MFXTableRowCell<ExerciseTemplate, ?> rowCell, ObservableList<ExerciseTemplate> exerciseTemplates) {
//        return event -> {
//            if (event.getClickCount() == 2) {
//                MFXComboBox<ExerciseTemplate> availableExercises = new MFXComboBox<>(exerciseTemplates);
//                availableExercises.prefWidthProperty()
//                                  .bind(rowCell.widthProperty()
//                                               .subtract(20));
//                availableExercises.prefHeightProperty()
//                                  .bind(rowCell.heightProperty()
//                                               .subtract(10));
//                availableExercises.getSelectionModel()
//                                  .selectItem(row.getData());
//                availableExercises.setConverter(FunctionalStringConverter.to(t -> Objects.isNull(t) ? "" : t.getExercise()
//                                                                                                            .getDisplayName()));
//
//                availableExercises.setFloatMode(FloatMode.DISABLED);
//                availableExercises.setTranslateX(-3);
//                availableExercises.setTranslateY(-3);
//
//                availableExercises.getSelectionModel()
//                                  .selectedItemProperty()
//                                  .addListener((observable, oldValue, newValue) -> {
//                                      rowCell.setGraphic(null);
//                                      rowCell.setText(newValue.getExercise()
//                                                              .getDisplayName());
//
//                                      //Heres where I would update the database with the new value
//                                      row.getData()
//                                         .setExercise(newValue.getExercise());
//                                  });
//
//                rowCell.setGraphic(availableExercises);
//            }
//        };
//    }
//
//    private EventHandler<MouseEvent> getRpeColumnMouseEvent(MFXTableRow<ExerciseTemplate> row, MFXTableRowCell<ExerciseTemplate, ?> rowCell, ObservableList<ExerciseTemplate> exerciseTemplates) {
//        return event -> {};
//    }
//
//    private EventHandler<MouseEvent> getPercentageColumnMouseEvent(MFXTableRow<ExerciseTemplate> row, MFXTableRowCell<ExerciseTemplate, ?> rowCell,
//                                                                   ObservableList<ExerciseTemplate> exerciseTemplates) {
//        return event -> {};
//    }
//
//    private EventHandler<MouseEvent> getSetsColumnMouseEvent(MFXTableRow<ExerciseTemplate> row, MFXTableRowCell<ExerciseTemplate, ?> rowCell, ObservableList<ExerciseTemplate> exerciseTemplates) {
//        return event -> {};
//    }
//
//    private EventHandler<MouseEvent> getRepsColumnMouseEvent(MFXTableRow<ExerciseTemplate> row, MFXTableRowCell<ExerciseTemplate, ?> rowCell, ObservableList<ExerciseTemplate> exerciseTemplates) {
//        return event -> {};
//    }
//
//    private void setNameColumn(MFXTableColumn<ExerciseTemplate> nameColumn) {
//        this.nameColumn = nameColumn;
//    }
//
//    private void setRpeColumn(MFXTableColumn<ExerciseTemplate> rpeColumn) {
//        this.rpeColumn = rpeColumn;
//    }
//
//    private void setPercentageColumn(MFXTableColumn<ExerciseTemplate> percentageColumn) {
//        this.percentageColumn = percentageColumn;
//    }
//
//    private void setSetsColumn(MFXTableColumn<ExerciseTemplate> setsColumn) {
//        this.setsColumn = setsColumn;
//    }
//
//    private void setRepsColumn(MFXTableColumn<ExerciseTemplate> repsColumn) {
//        this.repsColumn = repsColumn;
//    }
//}
