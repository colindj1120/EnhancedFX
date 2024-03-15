package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.LabeledConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Labeled;
import javafx.scene.control.OverrunStyle;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public interface LabeledConfig<T extends LabeledConfigurator<T>> extends ControlConfig<T> {
    @Override
    T getConfigurator();

    @Override
    Labeled getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().textProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        getNode().alignmentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().alignmentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        getNode().textAlignmentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textAlignmentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        getNode().textOverrunProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textOverrunProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        getNode().ellipsisStringProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        getNode().ellipsisStringProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().wrapTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().wrapTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addFontChangeListener(ChangeListener<? super Font> changeListener) {
        getNode().fontProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addFontInvalidationListener(InvalidationListener invalidationListener) {
        getNode().fontProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().graphicProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addGraphicInvalidationListener(InvalidationListener invalidationListener) {
        getNode().graphicProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().underlineProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        getNode().underlineProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().lineSpacingProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().lineSpacingProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        getNode().contentDisplayProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        getNode().contentDisplayProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().labelPaddingProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().labelPaddingProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().graphicTextGapProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        getNode().graphicTextGapProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        getNode().textFillProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTextFillInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textFillProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().mnemonicParsingProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().mnemonicParsingProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().textProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        getNode().alignmentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().alignmentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        getNode().textAlignmentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textAlignmentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        getNode().textOverrunProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textOverrunProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        getNode().ellipsisStringProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        getNode().ellipsisStringProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().wrapTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().wrapTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        getNode().fontProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeFontInvalidationListener(InvalidationListener invalidationListener) {
        getNode().fontProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().graphicProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeGraphicInvalidationListener(InvalidationListener invalidationListener) {
        getNode().graphicProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().underlineProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        getNode().underlineProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().lineSpacingProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().lineSpacingProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        getNode().contentDisplayProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        getNode().contentDisplayProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().labelPaddingProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().labelPaddingProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().graphicTextGapProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        getNode().graphicTextGapProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        getNode().textFillProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTextFillInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textFillProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().mnemonicParsingProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().mnemonicParsingProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Text Property

    default T bindTextProperty(ObservableValue<? extends String> observableValue) {
        getNode().textProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTextProperty() {
        getNode().textProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTextProperty(Property<String> property) {
        getNode().textProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalTextProperty(Property<String> property) {
        getNode().textProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Alignment Property

    default T bindAlignmentProperty(ObservableValue<? extends Pos> observableValue) {
        getNode().alignmentProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindAlignmentProperty() {
        getNode().alignmentProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalAlignmentProperty(Property<Pos> property) {
        getNode().alignmentProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalAlignmentProperty(Property<Pos> property) {
        getNode().alignmentProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Text Alignment Property

    default T bindTextAlignmentProperty(ObservableValue<? extends TextAlignment> observableValue) {
        getNode().textAlignmentProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTextAlignmentProperty() {
        getNode().textAlignmentProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        getNode().textAlignmentProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        getNode().textAlignmentProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Text Overrun Property

    default T bindTextOverrunProperty(ObservableValue<? extends OverrunStyle> observableValue) {
        getNode().textOverrunProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTextOverrunProperty() {
        getNode().textOverrunProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        getNode().textOverrunProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        getNode().textOverrunProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Ellipsis String Property

    default T bindEllipsisStringProperty(ObservableValue<? extends String> observableValue) {
        getNode().ellipsisStringProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindEllipsisStringProperty() {
        getNode().ellipsisStringProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalEllipsisStringProperty(Property<String> property) {
        getNode().ellipsisStringProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalEllipsisStringProperty(Property<String> property) {
        getNode().ellipsisStringProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Wrap Text Property

    default T bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().wrapTextProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindWrapTextProperty() {
        getNode().wrapTextProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalWrapTextProperty(Property<Boolean> property) {
        getNode().wrapTextProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalWrapTextProperty(Property<Boolean> property) {
        getNode().wrapTextProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Font Property

    default T bindFontProperty(ObservableValue<? extends Font> observableValue) {
        getNode().fontProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindFontProperty() {
        getNode().fontProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalFontProperty(Property<Font> property) {
        getNode().fontProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalFontProperty(Property<Font> property) {
        getNode().fontProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Graphic Property

    default T bindGraphicProperty(ObservableValue<? extends Node> observableValue) {
        getNode().graphicProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindGraphicProperty() {
        getNode().graphicProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalGraphicProperty(Property<Node> property) {
        getNode().graphicProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalGraphicProperty(Property<Node> property) {
        getNode().graphicProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Underline Property

    default T bindUnderlineProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().underlineProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindUnderlineProperty() {
        getNode().underlineProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalUnderlineProperty(Property<Boolean> property) {
        getNode().underlineProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalUnderlineProperty(Property<Boolean> property) {
        getNode().underlineProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Line Spacing Property

    default T bindLineSpacingProperty(ObservableValue<? extends Number> observableValue) {
        getNode().lineSpacingProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindLineSpacingProperty() {
        getNode().lineSpacingProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalLineSpacingProperty(Property<Number> property) {
        getNode().lineSpacingProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalLineSpacingProperty(Property<Number> property) {
        getNode().lineSpacingProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Content Display Property

    default T bindContentDisplayProperty(ObservableValue<? extends ContentDisplay> observableValue) {
        getNode().contentDisplayProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindContentDisplayProperty() {
        getNode().contentDisplayProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        getNode().contentDisplayProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        getNode().contentDisplayProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Graphic Text Gap Property

    default T bindGraphicTextGapProperty(ObservableValue<? extends Number> observableValue) {
        getNode().graphicTextGapProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindGraphicTextGapProperty() {
        getNode().graphicTextGapProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        getNode().graphicTextGapProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        getNode().graphicTextGapProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Text Fill Property

    default T bindTextFillProperty(ObservableValue<? extends Paint> observableValue) {
        getNode().textFillProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTextFillProperty() {
        getNode().textFillProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTextFillProperty(Property<Paint> property) {
        getNode().textFillProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalTextFillProperty(Property<Paint> property) {
        getNode().textFillProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Mnemonic Parsing Property

    default T bindMnemonicParsingProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().mnemonicParsingProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindMnemonicParsingProperty() {
        getNode().mnemonicParsingProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        getNode().mnemonicParsingProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        getNode().mnemonicParsingProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setText(String value) {
        getNode().setText(value);
        return getConfigurator();
    }

    default T setAlignment(Pos value) {
        getNode().setAlignment(value);
        return getConfigurator();
    }

    default T setTextAlignment(TextAlignment value) {
        getNode().setTextAlignment(value);
        return getConfigurator();
    }

    default T setTextOverrun(OverrunStyle value) {
        getNode().setTextOverrun(value);
        return getConfigurator();
    }

    default T setEllipsisString(String value) {
        getNode().setEllipsisString(value);
        return getConfigurator();
    }

    default T setWrapText(boolean value) {
        getNode().setWrapText(value);
        return getConfigurator();
    }

    default T setFont(Font value) {
        getNode().setFont(value);
        return getConfigurator();
    }

    default T setGraphic(Node value) {
        getNode().setGraphic(value);
        return getConfigurator();
    }

    default T setUnderline(boolean value) {
        getNode().setUnderline(value);
        return getConfigurator();
    }

    default T setLineSpacing(double value) {
        getNode().setLineSpacing(value);
        return getConfigurator();
    }

    default T setContentDisplay(ContentDisplay value) {
        getNode().setContentDisplay(value);
        return getConfigurator();
    }

    default T setGraphicTextGap(double value) {
        getNode().setGraphicTextGap(value);
        return getConfigurator();
    }

    default T setTextFill(Paint value) {
        getNode().setTextFill(value);
        return getConfigurator();
    }

    default T setMnemonicParsing(boolean value) {
        getNode().setMnemonicParsing(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
