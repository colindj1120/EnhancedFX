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
package io.github.colindj1120.enhancedfx.utils;

import javafx.beans.binding.*;
import javafx.beans.property.*;

/**
 * Utility class in the EnhancedFX library for converting JavaFX bindings into properties. This class provides methods to
 * transform bindings of various types into their corresponding properties, facilitating the integration of binding values into
 * JavaFX's property-based UI components and application logic. By converting bindings to properties, developers can easily
 * observe changes in the binding values and bind them to UI component properties or other application logic that operates on
 * properties.
 *
 * <h2>Binding Types That Can Be Converted To Properties:</h2>
 * <ul>
 *     <li>{@link ObjectBinding} to {@link ObjectProperty}</li>
 *     <li>{@link BooleanBinding} to {@link BooleanProperty}</li>
 *     <li>{@link StringBinding} to {@link StringProperty}</li>
 *     <li>{@link IntegerBinding} to {@link IntegerProperty}</li>
 *     <li>{@link LongBinding} to {@link LongProperty}</li>
 *     <li>{@link DoubleBinding} to {@link DoubleProperty}</li>
 *     <li>{@link FloatBinding} to {@link FloatProperty}</li>
 * </ul>
 *
 * <p>These utilities support a seamless transition from the declarative nature of bindings to the interactive
 * and dynamic world of properties in JavaFX. By providing a straightforward way to convert between these two
 * mechanisms, {@code EFXBindingUtils} enhances the flexibility and power of JavaFX's property and binding system.</p>
 *
 * <h2>Usage example:</h2>
 * <pre>{@code
 * DoubleBinding volumeBinding = volumeSlider.valueProperty().multiply(100);
 * DoubleProperty volumeProperty = EFXBindingUtils.bindingToDoubleProperty(volumeBinding);
 * volumeLabel.textProperty().bind(volumeProperty.asString().concat("%"));
 * }</pre>
 *
 * <p>This class is part of the EnhancedFX library, which aims to provide a comprehensive set of utilities
 * and components for building modern JavaFX applications with a focus on Material Design principles.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Binding
 * @see ObjectBinding
 * @see BooleanBinding
 * @see StringBinding
 * @see IntegerBinding
 * @see DoubleBinding
 * @see FloatBinding
 * @see LongBinding
 * @see ObjectProperty
 * @see BooleanProperty
 * @see StringProperty
 * @see IntegerProperty
 * @see LongProperty
 * @see DoubleProperty
 * @see FloatProperty
 */
public class EFXBindingUtils {
    /**
     * Private constructor to prevent instantiation of the {@code EFXBindingUtils} class.
     *
     * <p>
     * This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code EFXBindingUtils}.
     * </p>
     */
    private EFXBindingUtils() {}

    /**
     * Converts a generic {@link Binding} into an {@link ObjectProperty}. This method facilitates the use of a binding as a
     * property, allowing for the binding's value to be observed and bound to other properties or UI components. The returned
     * {@link ObjectProperty} is updated automatically whenever the binding's value changes.
     *
     * @param <T>
     *         The type of the value encapsulated by the binding.
     * @param binding
     *         The {@link Binding<T>} to be converted into an {@link ObjectProperty<T>}.
     *
     * @return An {@link ObjectProperty<T>} that reflects the current and future values of the provided binding.
     */
    public static <T> ObjectProperty<T> bindingToObjectProperty(Binding<T> binding) {
        ObjectProperty<T> property = new SimpleObjectProperty<>();
        property.bind(binding);
        return property;
    }

    /**
     * Converts a {@link StringBinding} into a {@link StringProperty}. This allows the string value produced by the binding to be
     * observed and manipulated as a property, enabling integration with property-based APIs and UI components in JavaFX.
     *
     * @param binding
     *         The {@link StringBinding} to be converted into a {@link StringProperty}.
     *
     * @return A {@link StringProperty} that reflects the current and future values of the provided binding.
     */
    public static StringProperty bindingToStringProperty(StringBinding binding) {
        StringProperty property = new SimpleStringProperty();
        property.bind(binding);
        return property;
    }

    /**
     * Converts a {@link BooleanBinding} into a {@link BooleanProperty}. This method enables the boolean value generated by the
     * binding to be used as a property, facilitating its observation and binding to other boolean properties or UI components
     * that expect a property interface.
     *
     * @param binding
     *         The {@link BooleanBinding} to be converted into a {@link BooleanProperty}.
     *
     * @return A {@link BooleanProperty} that mirrors the current and future values of the provided binding.
     */
    public static BooleanProperty bindingToBooleanProperty(BooleanBinding binding) {
        BooleanProperty property = new SimpleBooleanProperty();
        property.bind(binding);
        return property;
    }

    /**
     * Converts an {@link IntegerBinding} into an {@link IntegerProperty}. This transformation allows the integer value produced
     * by the binding to be treated as a property, making it compatible with property-based mechanisms and UI components within
     * JavaFX.
     *
     * @param binding
     *         The {@link IntegerBinding} to be converted into an {@link IntegerProperty}.
     *
     * @return An {@link IntegerProperty} that reflects the current and future integer values of the binding.
     */
    public static IntegerProperty bindingToIntegerProperty(IntegerBinding binding) {
        IntegerProperty property = new SimpleIntegerProperty();
        property.bind(binding);
        return property;
    }

    /**
     * Converts a {@link DoubleBinding} into a {@link DoubleProperty}. By converting the binding to a property, the double value
     * it generates can be observed and bound to other properties, enhancing integration with JavaFX's property system and UI
     * components.
     *
     * @param binding
     *         The {@link DoubleBinding} to be converted into a {@link DoubleProperty}.
     *
     * @return A {@link DoubleProperty} that represents the current and future double values of the binding.
     */
    public static DoubleProperty bindingToDoubleProperty(DoubleBinding binding) {
        DoubleProperty property = new SimpleDoubleProperty();
        property.bind(binding);
        return property;
    }

    /**
     * Converts a {@link FloatBinding} into a {@link FloatProperty}. This conversion facilitates the use of the float value from
     * the binding as a property, allowing for its observation and binding within the JavaFX property and UI framework.
     *
     * @param binding
     *         The {@link FloatBinding} to be converted into a {@link FloatProperty}.
     *
     * @return A {@link FloatProperty} that reflects the current and future float values of the binding.
     */
    public static FloatProperty bindingToFloatProperty(FloatBinding binding) {
        FloatProperty property = new SimpleFloatProperty();
        property.bind(binding);
        return property;
    }

    /**
     * Converts a {@link LongBinding} into a {@link LongProperty}. This method allows the Long value produced by the binding to be
     * observed and bound to other properties or UI components. The returned {@link LongProperty} is updated automatically
     * whenever the binding's value changes.
     *
     * @param binding
     *         The {@link LongBinding} to be converted into a {@link LongProperty}.
     *
     * @return A {@link LongProperty} that reflects the current and future values of the provided binding.
     */
    public static LongProperty bindingToLongProperty(LongBinding binding) {
        LongProperty property = new SimpleLongProperty();
        property.bind(binding);
        return property;
    }
}
