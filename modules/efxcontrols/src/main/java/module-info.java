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

    exports io.github.colindj1120.enhancedfx.controls.factory.configurators.base;
    exports io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.controls;
    exports io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.layout;
    exports io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.scene;

    exports io.github.colindj1120.enhancedfx.controls.factory.configurators.controls;
    exports io.github.colindj1120.enhancedfx.controls.factory.configurators.layout;
    exports io.github.colindj1120.enhancedfx.controls.factory.configurators.scene;

    exports io.github.colindj1120.enhancedfx.controls.factory.property;
    exports io.github.colindj1120.enhancedfx.controls.factory.property.base;

    exports io.github.colindj1120.enhancedfx.controls.skins;
    exports io.github.colindj1120.enhancedfx.controls.skins.base;
}