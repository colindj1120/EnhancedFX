module efx.controls {
    requires transitive javafx.controls;

    requires org.jetbrains.annotations;

    requires transitive efx.base;
    requires transitive efx.graphics;
    requires transitive efx.utils;

    exports io.github.colindj1120.enhancedfx.controls.control.base;
    exports io.github.colindj1120.enhancedfx.controls.control.efxcontrol;
    exports io.github.colindj1120.enhancedfx.controls.control.efxcontrol.base;
    exports io.github.colindj1120.enhancedfx.controls.control.efxlabeled.base;
    exports io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons;
    exports io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.base;
    exports io.github.colindj1120.enhancedfx.controls.control.efxsupportedcontrol;
    exports io.github.colindj1120.enhancedfx.controls.control.efxsupportedcontrol.base;
    exports io.github.colindj1120.enhancedfx.controls.control.efxtext;
    exports io.github.colindj1120.enhancedfx.controls.control.efxtext.base;

    exports io.github.colindj1120.enhancedfx.controls.css;
    exports io.github.colindj1120.enhancedfx.controls.css.base;

    exports io.github.colindj1120.enhancedfx.controls.skins;
    exports io.github.colindj1120.enhancedfx.controls.skins.base;
}