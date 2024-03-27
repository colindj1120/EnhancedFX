module efx.utils {
    requires transitive javafx.base;
    requires transitive javafx.controls;

    requires transitive org.jetbrains.annotations;

    exports io.github.colindj1120.enhancedfx.utils.consumers;
    exports io.github.colindj1120.enhancedfx.utils.exceptions;
    exports io.github.colindj1120.enhancedfx.utils;
    exports io.github.colindj1120.enhancedfx.utils.converters.stringconverters;
    exports io.github.colindj1120.enhancedfx.utils.converters.styleconverters;
}