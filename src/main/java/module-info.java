module MaterialDesignUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.jetbrains.annotations;
    requires com.hyperion;
    requires java.sql;
    requires java.sql.rowset;
    requires DatabaseUtility;
    requires fr.brouillard.oss.cssfx;

    opens io.github.colindj1120.materialdesignui;
    exports io.github.colindj1120.materialdesignui to javafx.graphics;
}