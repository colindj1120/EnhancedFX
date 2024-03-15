/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/EnhancedFX).
 *
 * EnhancedFX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EnhancedFX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with EnhancedFX.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.controls.skins.base;

import io.github.colindj1120.enhancedfx.controls.control.efxtext.EFXTextField;
import io.github.colindj1120.enhancedfx.controls.control.efxtext.base.EFXTextBase;
import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.LabelConfigurator;
import io.github.colindj1120.enhancedfx.utils.*;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Serves as an abstract base skin for enhanced text input controls within the EnhancedFX framework, providing common functionality and styling that adheres to Material Design principles. This base skin is
 * designed to be extended by specific skins for different types of text input controls, such as {@link EFXTextField}, offering a unified approach to handling text input aesthetics and interactions.
 *
 * <p>
 * <h2>Included Elements:</h2>
 * <ul>
 *     <li>A supporting text label, which can be used to display helper text or error messages related to the input from
 *     {@link EFXSupportedControlSkin}.</li>
 *     <li>A character count label, which indicates the number of entered characters and can enforce a maximum character limit
 *     .</li>
 * </ul>
 * <br>
 * These elements are integrated into the skin to provide immediate feedback to users, improving usability and ensuring a
 * consistent visual language across the application.
 * </p>
 *
 * <p>
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Automatic management of text input properties such as padding, border, and background, ensuring they align with
 *     Material Design specifications.</li>
 *     <li>Dynamic response to text input changes, with support for enforcing character limits and updating auxiliary labels
 *     accordingly.</li>
 *     <li>Flexible support for additional information display, enabling developers to easily incorporate helper text and
 *     character count feedback into their text input controls.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Implementing classes are expected to provide specific layout calculations and adjustments to fit the unique requirements
 * of each control type, while leveraging the common functionality provided by {@code EFXTextBaseSkin}.
 * </p>
 *
 * <p>
 * Usage of this skin within custom controls allows for a consistent implementation of Material Design principles across text
 * inputs, simplifying the development process and ensuring a high-quality user experience.
 * </p>
 *
 * @param <T>
 *         The type of the {@link EFXTextBase} control this skin is associated with, ensuring type safety and seamless integration with the JavaFX skinning mechanism.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXSupportedControlSkin
 * @see EFXTextField
 * @see EFXTextBase
 * @see SkinBase
 */
public abstract class EFXTextBaseSkin<T extends EFXTextBase<?>> extends EFXSupportedControlSkin<T> {
    protected static final int LEADING_ICON_OFFSET  = 5;
    protected static final int TRAILING_ICON_OFFSET = 2;

    protected final Label characterCountLabel;

    protected boolean skipMaxCharacterCountLayout = false;

    /**
     * Constructs an instance of {@code EFXTextBaseSkin}, initializing the skin for a given {@code EFXTextField} or any of its subclasses. This constructor sets up the fundamental components of the
     * enhanced text control, including the text field itself, a supporting text label, and a character count label. Each of these components is configured to enhance the standard text field behavior with
     * additional visual cues and information, adhering to Material Design principles for text input.
     *
     * <p>
     * Upon instantiation, the constructor performs the following actions:
     * <ul>
     *     <li>Initializes the inner text field component, which is the primary text input area, by retrieving it from the
     *     supplied control.</li>
     *     <li>Creates and initializes a supporting text label, which can be used to display additional information or guidance
     *     related to the text input, such as helper or error text.</li>
     *     <li>Creates and initializes a character count label, which provides feedback about the number of characters entered
     *     in comparison to the field's maximum character limit.</li>
     *     <li>Sets up the text field and labels by configuring their properties and applying necessary bindings to ensure they
     *     react appropriately to changes in the control's state.</li>
     * </ul>
     * </p>
     *
     * <p>
     * This constructor ensures that all components are properly initialized and configured to provide an enhanced user
     * experience for text input. The setup methods called within this constructor are responsible for the detailed
     * configuration of each component, including setting up listeners, bindings, and visual adjustments.
     * </p>
     *
     * @param control
     *         The {@code EFXTextField} or subclass instance to which this skin is applied. This control provides the text input functionality and additional properties that the skin enhances with Material
     *         Design features.
     */
    protected EFXTextBaseSkin(T control) {
        super(control);
        this.characterCountLabel = new Label();

        setupCharacterCountLabel();
        setupIcons();
    }

    @NotNull
    protected ChangeListener<Font> handleFontChange(T control) {
        return (observable, oldFont, newFont) -> {
            Font supportingLabelFont     = supportingTextLabel.getFont();
            Font characterCountLabelFont = characterCountLabel.getFont();

            EFXUIUtils.setLabelFont(supportingTextLabel, supportingLabelFont, newFont);
            EFXUIUtils.setLabelFont(characterCountLabel, characterCountLabelFont, newFont);
            control.requestLayout();
        };
    }

    @NotNull
    protected ChangeListener<String> handleTextChanged(T control) {
        return (observable, oldText, newText) -> {
            int maxCharacterCount = control.getMaxCharCount();

            if (!control.isMaxCharacterCountEnabled()) {
                return;
            }

            if (newText.length() > maxCharacterCount) {
                control.getInnerControl()
                       .setText(oldText);
            } else if (newText.length() >= maxCharacterCount - 1) {
                /* Determines if a layout request is necessary based on the text's length relative to the maximum character count
                 * and a specific condition for skipping layout updates.
                 *
                 * The requestLayoutCondition is set to true under two circumstances:
                 *      1. When the new text's length matches the maximum character count (newText.length() == maxCharacterCount)
                 *         and we are not opting out of layout updates for reaching the maximum character count
                 *         (skipMaxCharacterCountLayout is false).
                 *      2. When the new text's length does not match the maximum character count, and we are opting out of layout
                 *         updates when reaching the maximum character count (skipMaxCharacterCountLayout is true).
                 *
                 * This logic ensures layout requests are made only when needed, enhancing performance by avoiding unnecessary
                 * updates.
                 */
                final boolean requestLayoutCondition = (newText.length() == maxCharacterCount) != skipMaxCharacterCountLayout;

                if (requestLayoutCondition) {
                    control.requestLayout();
                    skipMaxCharacterCountLayout = !skipMaxCharacterCountLayout;
                }
            }
        };
    }

    private void setupCharacterCountLabel() {
        EFXTextBase<?> control = getSkinnable();

        StringProperty charCountOverMax = EFXExpressionUtils.expressionToStringProperty(createCharacterCountExpression(control));

        InvalidationListener layoutInvalidListener = EFXUIUtils.requestControlLayout(control);

        LabelConfigurator.create(characterCountLabel)
                         .bindBackgroundProperty(EFXUIUtils.TRANSPARENT_BACKGROUND_PROPERTY)
                         .bindManagedProperty(EFXPropertyUtils.toBooleanProperty(false))
                         .bindVisibleProperty(Bindings.createBooleanBinding(control::isMaxCharacterCountEnabled, control.maxCharCountStateProperty()))
                         .bindTextProperty(charCountOverMax)
                         .addVisibleChangeListener(EFXUIUtils.manageLabelVisibility(characterCountLabel, getChildren(), control))
                         .addStyleClass("character-count-label");

        CustomControlConfigurator.create(control)
                                 .addObjectPropertyInvalidationListener(control.maxCharCountPosProperty(), layoutInvalidListener)
                                 .addObjectPropertyInvalidationListener(control.maxCharCountStateProperty(), layoutInvalidListener)
                                 .addObjectPropertyChangeListener(control.maxCharCountProperty(),
                                                                  trimTextToMaxCountIfRequired(control));
    }

    /**
     * Sets up the icons for this component. This method adds a change listener to both the leading icon and trailing icon. When the icon changes, the registered change listener will be notified.
     */
    protected void setupIcons() {
        T control = getSkinnable();

        control.leadingIconProperty()
               .addListener(getIconChangeListener());
        control.trailingIconProperty()
               .addListener(getIconChangeListener());
    }

    /**
     * Returns a ChangeListener that handles the change of an icon in a node.
     *
     * <p>This listener removes the old icon from the children of the node if the new icon is null.</p>
     * <p>If the new icon is not null, it removes the old icon and adds the new icon to the children of the node.</p>
     * <p>If the old icon is null and the new icon is not null, it adds the new icon to the children of the node.</p>
     * <p>Finally, it requests layout for the skinnable node.</p>
     *
     * @return a ChangeListener that handles the change of an icon
     */
    @NotNull
    protected ChangeListener<Node> getIconChangeListener() {
        return (obs, oldIcon, newIcon) -> {
            if (Objects.isNull(newIcon)) {
                getChildren().remove(oldIcon);
            } else if (Objects.nonNull(oldIcon)) {
                getChildren().remove(oldIcon);
                getChildren().add(newIcon);
            } else {
                newIcon.setManaged(false);
                getChildren().add(newIcon);
            }
            getSkinnable().requestLayout();
        };
    }

    private StringExpression createCharacterCountExpression(EFXTextBase<?> control) {
        ReadOnlyIntegerProperty controlLengthProperty            = control.lengthProperty();
        IntegerProperty         controlMaxCharacterCountProperty = control.maxCharCountProperty();
        return Bindings.concat(controlLengthProperty.asString(), "/", controlMaxCharacterCountProperty.asString());
    }

    private ChangeListener<Number> trimTextToMaxCountIfRequired(EFXTextBase<?> control) {
        return (obs, oldMax, newMax) -> {
            TextInputControl innerField = control.getInnerControl();
            if (control.isMaxCharacterCountEnabled() && innerField.getLength() > newMax.intValue()) {
                innerField.setText(innerField.getText(0, control.getMaxCharCount()));
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset,
                                     double leftInset) {
        return leftInset + 0 + rightInset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset,
                                      double leftInset) {
        T control = getSkinnable();

        double leadingWidth  = EFXNodeUtils.getNodeWidth(control.getLeadingIcon());
        double trailingWidth = EFXNodeUtils.getNodeWidth(control.getTrailingIcon());

        return leftInset + leadingWidth + control.getInnerControl()
                                                 .prefWidth(-1) + trailingWidth + rightInset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        EFXTextBase<?> control       = getSkinnable();
        double         leadingHeight = EFXNodeUtils.getNodeHeight(control.getLeadingIcon());
        double            trailingHeight = EFXNodeUtils.getNodeHeight(control.getTrailingIcon());
        double            iconMax        = topInset + Math.max(leadingHeight, trailingHeight) + bottomInset;

        double height = topInset + control.getInnerControl()
                                          .prefHeight(-1) + bottomInset;

        return Math.max(iconMax, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInnerField(x, y, w, h);
        layoutLeadingIcon(x, y, w, h);
        layoutTrailingIcon(x, y, w, h);
        layoutCharacterCountLabel(x, w, h);
    }

    private void layoutInnerField(double x, double y, double w, double h) {
        T control = getSkinnable();

        double xOffset = EFXNodeUtils.getNodeWidth(control.getLeadingIcon(), LEADING_ICON_OFFSET);

        double wOffset = EFXNodeUtils.getNodeWidth(control.getTrailingIcon(), TRAILING_ICON_OFFSET);

        control.getInnerControl()
               .resizeRelocate(x + xOffset, y, w - xOffset - wOffset, h);
    }

    private void layoutLeadingIcon(double x, double y, double w, double h) {
        T control = getSkinnable();
        Optional.ofNullable(control.getLeadingIcon())
                .ifPresent(icon -> {
                    double iconWidth   = icon.prefWidth(-1);
                    double iconHeight  = icon.prefHeight(-1);
                    double verticalPos = y + (h - iconHeight) / 2;
                    icon.resizeRelocate(x, verticalPos, iconWidth, iconHeight);
                });
    }

    private void layoutTrailingIcon(double x, double y, double w, double h) {
        T control = getSkinnable();
        Optional.ofNullable(control.getTrailingIcon())
                .ifPresent(icon -> {
                    double iconWidth      = icon.prefWidth(-1);
                    double iconHeight     = icon.prefHeight(-1);
                    double verticalCenter = (h - iconHeight) / 2;
                    icon.resizeRelocate(x + w - iconWidth, y + verticalCenter, iconWidth, iconHeight);
                });
    }

    /**
     * Layouts the character count label within the given bounds.
     *
     * @param x
     *         The x-coordinate for the top-left corner of the bounding box.
     * @param w
     *         The width of the bounding box.
     * @param h
     *         The height of the bounding box.
     */
    private void layoutCharacterCountLabel(double x, double w, double h) {
        T control = getSkinnable();
        if (control.isMaxCharacterCountEnabled()) {
            double characterCountLabelWidth  = characterCountLabel.prefWidth(-1);
            double characterCountLabelHeight = characterCountLabel.prefHeight(-1);
            double characterCountLabelX      = x + w - characterCountLabelWidth + 3;

            double characterCountLabelY;
            if (control.isMaxCharacterCountPosBelow()) {
                characterCountLabelY = h + EFXInsetUtils.getBottomBorderInset(control) + characterCountLabelHeight;
            } else {
                characterCountLabelY = 0 - EFXInsetUtils.getTopBorderInset(control) - characterCountLabelHeight;
            }
            characterCountLabel.resizeRelocate(characterCountLabelX, characterCountLabelY, characterCountLabelWidth,
                                               characterCountLabelHeight);
        }
    }
}
