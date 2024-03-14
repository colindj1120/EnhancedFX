module EnhancedFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires fr.brouillard.oss.cssfx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.coreui;
    requires org.kordamp.ikonli.javafx;

    opens io.github.colindj1120.enhancedfx;
    exports io.github.colindj1120.enhancedfx;

    opens io.github.colindj1120.enhancedfx.animation;
    exports io.github.colindj1120.enhancedfx.animation;

    opens io.github.colindj1120.enhancedfx.beans.binding;
    exports io.github.colindj1120.enhancedfx.beans.binding;

    opens io.github.colindj1120.enhancedfx.beans.property;
    exports io.github.colindj1120.enhancedfx.beans.property;

    opens io.github.colindj1120.enhancedfx.beans.property.base;
    exports io.github.colindj1120.enhancedfx.beans.property.base;

    opens io.github.colindj1120.enhancedfx.beans.property.extendedstyleableproperty;
    exports io.github.colindj1120.enhancedfx.beans.property.extendedstyleableproperty;

    opens io.github.colindj1120.enhancedfx.beans.value;
    exports io.github.colindj1120.enhancedfx.beans.value;

    opens io.github.colindj1120.enhancedfx.binding;
    exports io.github.colindj1120.enhancedfx.binding;

    opens io.github.colindj1120.enhancedfx.binding.base;
    exports io.github.colindj1120.enhancedfx.binding.base;

    exports io.github.colindj1120.enhancedfx.binding.base.numberexpressions;
    opens io.github.colindj1120.enhancedfx.binding.base.numberexpressions;

    exports io.github.colindj1120.enhancedfx.binding.base.booleanexpressions;
    opens io.github.colindj1120.enhancedfx.binding.base.booleanexpressions;

    opens io.github.colindj1120.enhancedfx.collections;
    exports io.github.colindj1120.enhancedfx.collections;

    opens io.github.colindj1120.enhancedfx.collections.base;
    exports io.github.colindj1120.enhancedfx.collections.base;

    opens io.github.colindj1120.enhancedfx.consumers;
    exports io.github.colindj1120.enhancedfx.consumers;

    opens io.github.colindj1120.enhancedfx.controls;
    exports io.github.colindj1120.enhancedfx.controls;

    exports io.github.colindj1120.enhancedfx.controls.base;
    opens io.github.colindj1120.enhancedfx.controls.base;

    opens io.github.colindj1120.enhancedfx.controls.base.effects;
    exports io.github.colindj1120.enhancedfx.controls.base.effects;

    opens io.github.colindj1120.enhancedfx.controls.base.inner.innertext;
    exports io.github.colindj1120.enhancedfx.controls.base.inner.innertext;

    opens io.github.colindj1120.enhancedfx.converters;
    exports io.github.colindj1120.enhancedfx.converters;

    opens io.github.colindj1120.enhancedfx.css;
    exports io.github.colindj1120.enhancedfx.css;

    exports io.github.colindj1120.enhancedfx.effects.base;
    opens io.github.colindj1120.enhancedfx.effects.base;

    exports io.github.colindj1120.enhancedfx.effects.ripple;
    opens io.github.colindj1120.enhancedfx.effects.ripple;

    opens io.github.colindj1120.enhancedfx.enums;
    exports io.github.colindj1120.enhancedfx.enums;

    exports io.github.colindj1120.enhancedfx.enums.collections;
    opens io.github.colindj1120.enhancedfx.enums.collections;

    exports io.github.colindj1120.enhancedfx.enums.controls.enhancedsupportedcontrol;
    opens io.github.colindj1120.enhancedfx.enums.controls.enhancedsupportedcontrol;

    exports io.github.colindj1120.enhancedfx.enums.controls.enhancedtextfield;
    opens io.github.colindj1120.enhancedfx.enums.controls.enhancedtextfield;

    exports io.github.colindj1120.enhancedfx.enums.effects;
    opens io.github.colindj1120.enhancedfx.enums.effects;

    opens io.github.colindj1120.enhancedfx.exceptions;
    exports io.github.colindj1120.enhancedfx.exceptions;

    exports io.github.colindj1120.enhancedfx.factories.configurators.base;
    opens io.github.colindj1120.enhancedfx.factories.configurators.base;

    exports io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.controls;
    opens io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.controls;

    exports io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.layout;
    opens io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.layout;

    exports io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.scene;
    opens io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.scene;

    exports io.github.colindj1120.enhancedfx.factories.configurators.controls;
    opens io.github.colindj1120.enhancedfx.factories.configurators.controls;

    exports io.github.colindj1120.enhancedfx.factories.configurators.layout;
    opens io.github.colindj1120.enhancedfx.factories.configurators.layout;

    exports io.github.colindj1120.enhancedfx.factories.configurators.scene;
    opens io.github.colindj1120.enhancedfx.factories.configurators.scene;

    exports io.github.colindj1120.enhancedfx.factories.effects;
    opens io.github.colindj1120.enhancedfx.factories.effects;

    exports io.github.colindj1120.enhancedfx.factories.styling;
    opens io.github.colindj1120.enhancedfx.factories.styling;

    opens io.github.colindj1120.enhancedfx.shapes;
    exports io.github.colindj1120.enhancedfx.shapes;

    exports io.github.colindj1120.enhancedfx.skins;
    opens io.github.colindj1120.enhancedfx.skins;

    opens io.github.colindj1120.enhancedfx.styling;
    exports io.github.colindj1120.enhancedfx.styling;

    opens io.github.colindj1120.enhancedfx.styling.base;
    exports io.github.colindj1120.enhancedfx.styling.base;

    opens io.github.colindj1120.enhancedfx.utils;
    exports io.github.colindj1120.enhancedfx.utils;

    opens io.github.colindj1120.enhancedfx.testing.controllers;
    exports io.github.colindj1120.enhancedfx.testing.controllers to javafx.fxml;
    exports io.github.colindj1120.enhancedfx.binding.associations;
    opens io.github.colindj1120.enhancedfx.binding.associations;
    exports io.github.colindj1120.enhancedfx.enums.controls.enhancedtextbase;
    opens io.github.colindj1120.enhancedfx.enums.controls.enhancedtextbase;
    exports io.github.colindj1120.enhancedfx.controls.base.inner.base;
    opens io.github.colindj1120.enhancedfx.controls.base.inner.base;
    exports io.github.colindj1120.enhancedfx.enums.styling;
    opens io.github.colindj1120.enhancedfx.enums.styling;
}