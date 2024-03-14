module efx.testing {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive efx.base;
    requires transitive efx.controls;
    requires transitive efx.graphics;
    requires transitive efx.utils;

    requires org.jetbrains.annotations;

    requires fr.brouillard.oss.cssfx;

    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.coreui;
    requires org.kordamp.ikonli.javafx;

    exports io.github.colindj1120.enhancedfx to javafx.graphics;
    exports io.github.colindj1120.enhancedfx.controllers to javafx.fxml;
    opens io.github.colindj1120.enhancedfx.controllers to javafx.fxml;
}