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
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.coreui;
    requires org.kordamp.ikonli.javafx;

    opens io.github.colindj1120.materialdesignui;
    exports io.github.colindj1120.materialdesignui;

    opens io.github.colindj1120.materialdesignui.animation;
    exports io.github.colindj1120.materialdesignui.animation;

    opens io.github.colindj1120.materialdesignui.beans.binding;
    exports io.github.colindj1120.materialdesignui.beans.binding;

    opens io.github.colindj1120.materialdesignui.beans.property;
    exports io.github.colindj1120.materialdesignui.beans.property;

    opens io.github.colindj1120.materialdesignui.beans.property.base;
    exports io.github.colindj1120.materialdesignui.beans.property.base;

    opens io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty;
    exports io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty;

    opens io.github.colindj1120.materialdesignui.beans.value;
    exports io.github.colindj1120.materialdesignui.beans.value;

    opens io.github.colindj1120.materialdesignui.binding;
    exports io.github.colindj1120.materialdesignui.binding;

    opens io.github.colindj1120.materialdesignui.collections;
    exports io.github.colindj1120.materialdesignui.collections;

    opens io.github.colindj1120.materialdesignui.collections.base;
    exports io.github.colindj1120.materialdesignui.collections.base;

    opens io.github.colindj1120.materialdesignui.consumers;
    exports io.github.colindj1120.materialdesignui.consumers;

    opens io.github.colindj1120.materialdesignui.controls;
    exports io.github.colindj1120.materialdesignui.controls;

    opens io.github.colindj1120.materialdesignui.controls.base.effects;
    exports io.github.colindj1120.materialdesignui.controls.base.effects;

    opens io.github.colindj1120.materialdesignui.controls.base.innertext;
    exports io.github.colindj1120.materialdesignui.controls.base.innertext;

    opens io.github.colindj1120.materialdesignui.converters;
    exports io.github.colindj1120.materialdesignui.converters;

    opens io.github.colindj1120.materialdesignui.css;
    exports io.github.colindj1120.materialdesignui.css;

    exports io.github.colindj1120.materialdesignui.effects.base;
    opens io.github.colindj1120.materialdesignui.effects.base;

    exports io.github.colindj1120.materialdesignui.effects.ripple;
    opens io.github.colindj1120.materialdesignui.effects.ripple;

    opens io.github.colindj1120.materialdesignui.enums;
    exports io.github.colindj1120.materialdesignui.enums;

    exports io.github.colindj1120.materialdesignui.enums.collections;
    opens io.github.colindj1120.materialdesignui.enums.collections;

    exports io.github.colindj1120.materialdesignui.enums.controls;
    opens io.github.colindj1120.materialdesignui.enums.controls;

    exports io.github.colindj1120.materialdesignui.enums.controls.enhancedtextfield;
    opens io.github.colindj1120.materialdesignui.enums.controls.enhancedtextfield;

    exports io.github.colindj1120.materialdesignui.enums.effects;
    opens io.github.colindj1120.materialdesignui.enums.effects;

    opens io.github.colindj1120.materialdesignui.exceptions;
    exports io.github.colindj1120.materialdesignui.exceptions;

    exports io.github.colindj1120.materialdesignui.factories.effects;
    opens io.github.colindj1120.materialdesignui.factories.effects;

    exports io.github.colindj1120.materialdesignui.factories.styling;
    opens io.github.colindj1120.materialdesignui.factories.styling;

    exports io.github.colindj1120.materialdesignui.skins;
    opens io.github.colindj1120.materialdesignui.skins;

    opens io.github.colindj1120.materialdesignui.styling;
    exports io.github.colindj1120.materialdesignui.styling;

    opens io.github.colindj1120.materialdesignui.styling.base;
    exports io.github.colindj1120.materialdesignui.styling.base;

    opens io.github.colindj1120.materialdesignui.utils;
    exports io.github.colindj1120.materialdesignui.utils;

    opens io.github.colindj1120.materialdesignui.testing.controllers;
    exports io.github.colindj1120.materialdesignui.testing.controllers to javafx.fxml;
}