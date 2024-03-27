module efx.controls {
    requires transitive javafx.controls;

    requires org.jetbrains.annotations;

    requires transitive efx.base;
    requires transitive efx.graphics;
    requires transitive efx.utils;

    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.base;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol.base;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.base;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.efxbuttons;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.efxbuttons.base;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxsupportedcontrol;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxsupportedcontrol.base;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext;
    exports io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base;

    exports io.github.colindj1120.enhancedfx.controls.css;
    exports io.github.colindj1120.enhancedfx.controls.css.base;

    exports io.github.colindj1120.enhancedfx.controls.skins;
    exports io.github.colindj1120.enhancedfx.controls.skins.base;
    exports io.github.colindj1120.enhancedfx.controls.complexcontrol;
}