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

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label.LabelConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.EFXTextField;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base.EFXTextBase;
import io.github.colindj1120.enhancedfx.utils.*;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
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
 * EFXTextBaseSkin is an abstract class that defines the basic structure and behavior of skins for controls extending {@link EFXTextBase}.
 *
 * <p>This skin implementation focuses on enhancing text-based controls with additional features such as character count display and icon placements (leading and trailing).</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Automatic layout management for leading and trailing icons.</li>
 *     <li>Character count display with dynamic visibility based on the control's state.</li>
 *     <li>Customizable appearance and behavior through properties and CSS.</li>
 *     <li>Responds to font changes in the associated control to maintain visual consistency across related UI components.</li>
 *     <li>Ensures text content does not exceed a specified maximum character count, with the ability to update layout based on character count constraints.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 *     EFXTextBase<?> myCustomTextField = new MyCustomTextField();
 *     EFXTextBaseSkin<?> myCustomTextFieldSkin = new MyCustomTextFieldSkin(myCustomTextField);
 *     myCustomTextField.setSkin(myCustomTextFieldSkin);
 *
 *     myCustomTextField.setMaxCharCount(50);
 *     myCustomTextField.setLeadingIcon(new ImageView(myIconImage));
 *     myCustomTextField.setTrailingIcon(new ImageView(myTrailingIconImage));
 *     myCustomTextField.setAlwaysFloatingLabel(true);
 * }
 * </pre>
 *
 * <p>This class requires subclasses to implement the {@link #initialize()} method to set up any additional components or behavior specific to the custom control skin. It also provides methods to handle font
 * changes and text updates in a manner that aligns with the extended features of the {@link EFXTextBase} controls.</p>
 *
 * <p>Subclasses are encouraged to override the layout methods to customize the positioning of the inner text control, icons, and character count label to match specific design requirements.</p>
 *
 * <p>Note: This skin automatically adds the necessary listeners and bindings to the control properties to react to changes in real-time, ensuring the UI remains consistent and functional across user
 * interactions.</p>
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

    protected final Label characterCountLabel = new Label();

    protected boolean skipMaxCharacterCountLayout = false;

    /**
     * Initializes the control skin by setting up essential parts, including character count labels and icons.
     *
     * <p>This method extends the base class initialization process to include setup routines specific to the control skin being implemented.</p>
     *
     * <p>
     * <em>The method performs the following actions in order:</em>
     * <ol>
     *     <li>It calls {@code super.initialize()} to ensure that any initialization logic defined in the superclass is executed. This is crucial for maintaining the proper initialization hierarchy and
     *         ensuring that the skin is correctly set up according to the framework's requirements.</li>
     *     <li>It invokes {@code setupCharacterCountLabel()} to configure the character count label. This setup includes binding properties to the control's state, adjusting visibility based on the control's
     *         current configuration, and ensuring the label correctly reflects the number of characters entered versus the maximum allowed.</li>
     *     <li>It calls {@code setupIcons()} to configure any leading or trailing icons associated with the control. This includes positioning the icons relative to the control and ensuring they are properly
     *         displayed based on the control's current state and configuration.</li>
     * </ol>
     * </p>
     *
     * <p>This override is part of the skin's customization process, allowing developers to add or modify components that are part of the control's appearance and behavior. It is an essential step in
     * creating a control skin that meets specific design requirements.</p>
     */
    @Override
    protected void initialize() {
        super.initialize();
        this.setupCharacterCountLabel();
        this.setupIcons();
    }

    /**
     * Constructs an {@code EFXTextBaseSkin} instance for the specified control.
     *
     * <p>This constructor initializes the skin with the control that it will be associated with. It calls the superclass constructor with the control as an argument, ensuring that the base skin
     * functionalities are properly initialized.</p>
     *
     * @param control
     *         The control for which this skin is being created. This control should not be {@code null}.
     */
    protected EFXTextBaseSkin(T control) {
        super(control);
    }

    /**
     * Produces a {@code ChangeListener<Font>} that updates the font of supporting text labels within a control.
     *
     * <p>This listener is designed to synchronize font changes in the control with its associated labels, maintaining visual consistency.</p>
     *
     * @param control
     *         The control whose font change this listener will handle.
     *
     * @return A {@code ChangeListener<Font>} that updates the font of supporting and character count labels when the control's font changes.
     */
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

    /**
     * Generates a {@code ChangeListener<String>} that reacts to text changes within the control.
     *
     * <p>This listener ensures that the text content does not exceed the specified maximum character count, reverting to the old text if necessary. It also requests a layout update based on specific
     * conditions related to the character count.</p>
     *
     * @param control
     *         The control whose text changes this listener will handle.
     *
     * @return A {@code ChangeListener<String>} that enforces maximum character count constraints and potentially requests layout updates.
     */
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

    /**
     * Configures the character count label for a control.
     *
     * <p>This method sets up bindings and listeners for the character count label to reflect changes in the control's state, such as the current character count relative to the maximum allowed characters.</p>
     *
     * <p>The label's visibility and text content are dynamically updated based on the control's current character count and whether the maximum character count feature is enabled. It also configures the
     * label's background, managed state, and style class.</p>
     */
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
                                 .addObjectPropertyChangeListener(control.maxCharCountProperty(), trimTextToMaxCountIfRequired(control));
    }

    /**
     * Sets up the icons for this component. This method adds a change listener to both the leading icon and trailing icon. When the icon changes, the registered change listener will be notified.
     */
    private void setupIcons() {
        T control = getSkinnable();

        control.leadingIconProperty()
               .addListener(getIconChangeListener());
        control.trailingIconProperty()
               .addListener(getIconChangeListener());
    }

    /**
     * Returns a ChangeListener that handles the change of an icon in a node.
     *
     * <p>
     * <em>Steps:</em>
     * <ol>
     *     <li>This listener removes the old icon from the children of the node if the new icon is null.</li>
     *     <li>If the new icon is not null, it removes the old icon and adds the new icon to the children of the node.</li>
     *     <li>If the old icon is null and the new icon is not null, it adds the new icon to the children of the node.</li>
     *     <li>Finally, it requests layout for the skinnable node.</li>
     * </ol>
     * </p>
     *
     * @return a ChangeListener that handles the change of an icon
     */
    @NotNull
    private ChangeListener<Node> getIconChangeListener() {
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

    /**
     * Creates a {@code StringExpression} representing the current character count and the maximum character count for a given {@link EFXTextBase} control.
     *
     * <p>This expression is typically used for displaying the character count information to the user, formatted as "current/maximum".</p>
     *
     * @param control
     *         The {@code EFXTextBase} control for which the character count expression is created.
     *
     * @return A {@code StringExpression} that binds to the control's current character length and maximum character count, formatted as a string.
     */
    private StringExpression createCharacterCountExpression(EFXTextBase<?> control) {
        ReadOnlyIntegerProperty controlLengthProperty = control.lengthProperty();
        return Bindings.concat(controlLengthProperty.asString(), "/", control.maxCharCountProperty()
                                                                             .asString());
    }

    /**
     * Returns a {@code ChangeListener<Number>} that, when attached to a control's maximum character count property, trims the text within the control to the maximum allowed character count if necessary.
     *
     * <p>This listener ensures that the text content of the control does not exceed the newly set maximum character count.</p>
     *
     * <p>When the maximum character count is decreased, and the current text length exceeds this new maximum, the text is automatically trimmed to fit within the updated limit. This method is particularly
     * useful for dynamically updating the maximum character count constraints of a control and enforcing these constraints.</p>
     *
     * @param control
     *         The {@code EFXTextBase} control to which the maximum character count constraint applies.
     *
     * @return A {@code ChangeListener<Number>} that enforces the maximum character count constraint by trimming the text if it exceeds the maximum allowed characters.
     */
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
     * <p>
     * Computes the minimum width of the control.
     *
     * <p>This implementation provides a basic calculation that considers only the left and right insets, essentially setting the minimum width to the sum of these insets.</p>
     *
     * <p>This method's simplistic approach assumes that the control does not have a minimum width requirement beyond its insets. Subclasses may override this method to incorporate the widths of contained
     * elements or to apply a different calculation logic based on specific needs.</p>
     *
     * @param height
     *         The height of the control. This parameter is not directly used in the calculation but is part of the method signature for overriding purposes.
     * @param topInset
     *         The top inset of the control. This parameter is not directly used in the calculation but is part of the method signature.
     * @param rightInset
     *         The right inset of the control. This value is added to the left inset to calculate the minimum width.
     * @param bottomInset
     *         The bottom inset of the control. This parameter is not directly used in the calculation but is part of the method signature.
     * @param leftInset
     *         The left inset of the control. This value is added to the right inset to calculate the minimum width.
     *
     * @return The calculated minimum width of the control, which is determined as the sum of the left and right insets.
     */
    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return leftInset + 0 + rightInset;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Calculates the preferred width of the control based on its content, including any leading and trailing icons.
     *
     * <p>This method considers the width of these icons in addition to the preferred width of the control's inner content and insets.</p>
     *
     * @param height
     *         The height of the control. This parameter is not directly used but is part of the method signature for overriding purposes.
     * @param topInset
     *         The top inset of the control.
     * @param rightInset
     *         The right inset of the control.
     * @param bottomInset
     *         The bottom inset of the control.
     * @param leftInset
     *         The left inset of the control.
     *
     * @return The calculated preferred width of the control, incorporating the widths of any leading and trailing icons, the preferred width of the inner control, and the horizontal insets.
     */
    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        T control = getSkinnable();

        double leadingWidth  = EFXNodeUtils.getNodeWidth(control.getLeadingIcon());
        double trailingWidth = EFXNodeUtils.getNodeWidth(control.getTrailingIcon());

        return leftInset + leadingWidth + control.getInnerControl()
                                                 .prefWidth(-1) + trailingWidth + rightInset;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Calculates the preferred height of the control, taking into account the heights of any leading and trailing icons.</p>
     *
     * <p>The method compares the combined height of the control's inner content with the maximum height of the icons, ensuring the control's preferred height is sufficient to accommodate both.</p>
     *
     * @param width
     *         The width of the control. This parameter is not directly used but is part of the method signature for overriding purposes.
     * @param topInset
     *         The top inset of the control.
     * @param rightInset
     *         The right inset of the control. This parameter is not directly used but is part of the method signature.
     * @param bottomInset
     *         The bottom inset of the control.
     * @param leftInset
     *         The left inset of the control. This parameter is not directly used but is part of the method signature.
     *
     * @return The calculated preferred height of the control, which is the greatest of the combined height of the control's inner content (plus top and bottom insets) and the maximum icon height.
     */
    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        EFXTextBase<?> control        = getSkinnable();
        double         leadingHeight  = EFXNodeUtils.getNodeHeight(control.getLeadingIcon());
        double         trailingHeight = EFXNodeUtils.getNodeHeight(control.getTrailingIcon());
        double         iconMax        = topInset + Math.max(leadingHeight, trailingHeight) + bottomInset;

        double height = topInset + control.getInnerControl()
                                          .prefHeight(-1) + bottomInset;

        return Math.max(iconMax, height);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Lays out the children of the control, including positioning and sizing any leading and trailing icons, the control's inner content, and the character count label if present.
     *
     * <p>This method orchestrates the spatial arrangement of the control's components within the available width and height.</p>
     *
     * @param x
     *         The X coordinate of the layout area's origin.
     * @param y
     *         The Y coordinate of the layout area's origin.
     * @param w
     *         The width of the layout area.
     * @param h
     *         The height of the layout area.
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInnerField(x, y, w, h);
        layoutLeadingIcon(x, y, w, h);
        layoutTrailingIcon(x, y, w, h);
        layoutCharacterCountLabel(x, w, h);
    }

    /**
     * Lays out the inner control of the skin, adjusting its size and position based on the presence and size of any leading and trailing icons.
     *
     * <p>This method calculates the available space for the inner control by considering the width of the icons and positioning the control accordingly within the provided layout bounds.</p>
     *
     * @param x
     *         The X coordinate of the layout area's origin.
     * @param y
     *         The Y coordinate of the layout area's origin.
     * @param w
     *         The width of the layout area.
     * @param h
     *         The height of the layout area.
     */
    private void layoutInnerField(double x, double y, double w, double h) {
        T control = getSkinnable();

        double xOffset = EFXNodeUtils.getNodeWidth(control.getLeadingIcon(), LEADING_ICON_OFFSET);
        double wOffset = EFXNodeUtils.getNodeWidth(control.getTrailingIcon(), TRAILING_ICON_OFFSET);

        control.getInnerControl()
               .resizeRelocate(x + xOffset, y, w - xOffset - wOffset, h);
    }

    /**
     * Positions and sizes the leading icon of the control, if present. The icon is aligned vertically in the center of the available space and placed at the start of the control.
     *
     * @param x
     *         The X coordinate of the layout area's origin.
     * @param y
     *         The Y coordinate of the layout area's origin.
     * @param w
     *         The width of the layout area.
     * @param h
     *         The height of the layout area.
     */
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

    /**
     * Positions and sizes the trailing icon of the control, if present.
     *
     * <p>The icon is aligned vertically in the center of the available space and placed at the end of the control. This method calculates the correct position of the trailing icon to ensure it is visibly
     * separated from the control's content and other components.</p>
     *
     * @param x
     *         The X coordinate of the layout area's origin.
     * @param y
     *         The Y coordinate of the layout area's origin.
     * @param w
     *         The width of the layout area.
     * @param h
     *         The height of the layout area.
     */
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
            characterCountLabel.resizeRelocate(characterCountLabelX, characterCountLabelY, characterCountLabelWidth, characterCountLabelHeight);
        }
    }
}
