module efx.base {
    requires transitive javafx.controls;

    requires org.jetbrains.annotations;

    requires transitive efx.utils;

    exports io.github.colindj1120.enhancedfx.base.beans.base;
    exports io.github.colindj1120.enhancedfx.base.beans.binding;
    exports io.github.colindj1120.enhancedfx.base.beans.binding.base;
    exports io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions;
    exports io.github.colindj1120.enhancedfx.base.beans.binding.base.bindingfunctions;
    exports io.github.colindj1120.enhancedfx.base.beans.binding.base.observablefunctions;
    exports io.github.colindj1120.enhancedfx.base.beans.efxproperty;
    exports io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty;

    exports io.github.colindj1120.enhancedfx.base.collections;
    exports io.github.colindj1120.enhancedfx.base.collections.base;

    exports io.github.colindj1120.enhancedfx.base.css;

    exports io.github.colindj1120.enhancedfx.base.enums;

    exports io.github.colindj1120.enhancedfx.base.factory;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.button;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.button.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.buttonbase;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.buttonbase.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.control;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.control.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.labeled;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.labeled.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.node;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.node.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.parent;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.parent.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.region;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.region.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textarea;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textarea.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textfield;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textfield.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textinputcontrol;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textinputcontrol.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.togglebutton;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.togglebutton.base;

    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombutton;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombutton.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombuttonbase.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customlabel;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customlabel.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customnode;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customnode.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customparent;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customparent.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customregion;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customregion.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextarea;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextarea.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextfield;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextfield.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextinputcontrol;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextinputcontrol.base;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtogglebutton;
    exports io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtogglebutton.base;

    exports io.github.colindj1120.enhancedfx.base.factory.property;
    exports io.github.colindj1120.enhancedfx.base.factory.property.base;
}