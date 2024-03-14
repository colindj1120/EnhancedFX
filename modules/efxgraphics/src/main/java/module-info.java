module efx.graphics {
    requires transitive javafx.controls;

    requires org.jetbrains.annotations;

    requires transitive efx.base;
    requires transitive efx.utils;

    exports io.github.colindj1120.enhancedfx.graphics.animation;
    exports io.github.colindj1120.enhancedfx.graphics.effects.base;
    exports io.github.colindj1120.enhancedfx.graphics.effects.ripple;

    exports io.github.colindj1120.enhancedfx.graphics.factory;
    exports io.github.colindj1120.enhancedfx.graphics.shapes;
}