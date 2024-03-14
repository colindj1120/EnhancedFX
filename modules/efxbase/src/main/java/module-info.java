module efx.base {
    requires transitive javafx.controls;

    requires org.jetbrains.annotations;

    requires transitive efx.utils;

    exports io.github.colindj1120.enhancedfx.base.beans;
    exports io.github.colindj1120.enhancedfx.base.beans.binding;
    exports io.github.colindj1120.enhancedfx.base.beans.binding.base;


    exports io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty;
    exports io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.base;

    exports io.github.colindj1120.enhancedfx.base.collections;
    exports io.github.colindj1120.enhancedfx.base.collections.base;

    exports io.github.colindj1120.enhancedfx.base.css;

    exports io.github.colindj1120.enhancedfx.base.enums;

    exports io.github.colindj1120.enhancedfx.base.exceptions;

    exports io.github.colindj1120.enhancedfx.base.factory;
}