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

import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol.EFXControl;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol.base.EFXControlBase;
import javafx.scene.control.SkinBase;

import java.lang.reflect.Constructor;

/**
 * Represents the base class for all control skins in the EnhancedFX framework, providing a foundation for customizing the appearance and behavior of {@link EFXControl} instances.
 *
 * <p>This abstract class extends {@link SkinBase}, incorporating additional functionality tailored to the needs of EnhancedFX controls.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Initialization: Abstract {@code initialize} method for subclasses to set up bindings, listeners, and other initial state.</li>
 *     <li>Reflection-based Factory Creation: Static {@code create} method to dynamically instantiate skin classes using reflection, simplifying skin creation and initialization.</li>
 *     <li>Layout and Size Computation: Overrides {@code computeMinWidth}, {@code computePrefWidth}, {@code computeMaxWidth}, {@code computeMinHeight}, {@code computePrefHeight}, and {@code computeMaxHeight}
 *         methods to provide default implementations for size computations based on control properties.</li>
 *     <li>Layout Management: Custom {@code layoutChildren} method to arrange the visual components of the control within the skin.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * public class MyCustomControlSkin extends EFXControlSkin<MyCustomControl> {
 *     public MyCustomControlSkin(MyCustomControl control) {
 *         super(control);
 *     }
 *
 *     @Override
 *     protected void initialize() {
 *         // Initialize skin-specific settings here
 *     }
 *
 *     @Override
 *     protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
 *         // Compute and return the preferred width
 *         return 100; // Example value
 *     }
 *
 *     @Override
 *     protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
 *         // Compute and return the preferred height
 *         return 50; // Example value
 *     }
 *
 *     @Override
 *     protected void layoutChildren(double x, double y, double w, double h) {
 *         // Custom layout logic here
 *     }
 * }
 *
 * // Usage
 * MyCustomControl customControl = new MyCustomControl();
 * customControl.setSkin(EFXControlSkin.create(MyCustomControlSkin.class, customControl));
 * }
 * </pre>
 *
 * <p>This example illustrates how to extend {@code EFXControlSkin} for a custom control, implementing required methods to ensure proper appearance and behavior. The {@code create} method is used to
 * instantiate the skin, leveraging the provided factory mechanism.</p>
 *
 * <h2>Note:</h2>
 * Subclasses are responsible for calling {@code super.initialize()} during initialization if the superclass implements its own initialization logic, ensuring a complete setup process.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXControl
 * @see SkinBase
 */
public abstract class EFXControlSkin<T extends EFXControl<?>> extends SkinBase<T> {
    /**
     * Initializes the control skin or any subclass thereof.
     *
     * <p>This abstract method is meant to be implemented by concrete skin classes to perform initialization tasks specific to each control skin.</p>
     *
     * <p>Implementations of this method should set up necessary bindings, configure listeners, and perform any other initialization required to prepare the control skin for use. This may include setting up
     * the initial visual state of the control, preparing animations, or constructing additional JavaFX nodes that the skin requires.</p>
     *
     * <p>Note: Subclasses must call {@code super.initialize()} if the superclass implements its own initialization logic to ensure the entire initialization chain is executed properly.</p>
     */
    protected abstract void initialize();

    /**
     * Creates an instance of a specific {@link EFXControlSkin} subclass for the given {@link EFXControlBase} control.
     *
     * <p>This method dynamically constructs an instance of the specified skin class, invoking its protected constructor and passing the control as an argument.</p>
     *
     * <p>The method uses reflection to find and invoke the appropriate constructor for the skin class, making it accessible if necessary. After creating the skin instance, it calls the {@code initialize}
     * method on the newly created instance to complete its setup.</p>
     *
     * <p>This factory method simplifies the process of creating skin instances for custom controls within the EnhancedFX framework, ensuring that skins are correctly initialized and associated with their
     * respective controls.</p>
     *
     * <p>Note: This method throws a {@code RuntimeException} if reflection fails to create the skin instance, encapsulating the underlying exception that caused the failure.</p>
     *
     * @param <U>
     *         The type parameter of the skin class extending {@code EFXControlSkin}.
     * @param <T>
     *         The type parameter of the control class extending {@code EFXControlBase}.
     * @param clazz
     *         The {@code Class} object representing the skin class to be instantiated.
     * @param control
     *         The control instance for which the skin is being created.
     *
     * @return An initialized instance of the specified skin class for the given control.
     *
     * @throws RuntimeException
     *         if there is an error creating the skin instance, including reflection-related errors or failures within the skin's constructor.
     */
    protected static <U extends EFXControlSkin<?>, T extends EFXControlBase<?>> U create(Class<U> clazz, T control) {
        try {
            Constructor<U> constructor = clazz.getDeclaredConstructor(control.getClass());
            constructor.setAccessible(true); // Enables access to protected constructor
            U instance = constructor.newInstance(control);

            instance.initialize(); // Assuming an initialization method needs to be called
            return instance;
        }
        catch (Exception e) {
            throw new RuntimeException(String.format("Error creating %s instance", clazz.getSimpleName()), e);
        }
    }

    /**
     * Constructs an {@code EFXControlSkin} instance for the specified control.
     *
     * <p>This constructor initializes the skin with the control that it will be associated with. It calls the superclass constructor with the control as an argument, ensuring that the base skin
     * functionalities are properly initialized.</p>
     *
     * @param control
     *         The control for which this skin is being created. This control should not be {@code null}.
     */
    protected EFXControlSkin(T control) {
        super(control);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset);

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().getMaxWidth() == Double.MAX_VALUE) {return Double.MAX_VALUE;}
        return getSkinnable().prefWidth(-1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset);

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().getMaxHeight() == Double.MAX_VALUE) {return Double.MAX_VALUE;}
        return getSkinnable().prefHeight(-1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
    }
}
