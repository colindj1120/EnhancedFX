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

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

/**
 * Provides utility methods for converting various {@link ObservableValue} types to their corresponding {@link Property} counterparts in JavaFX.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Observable to Property Conversion:</b> Facilitates the conversion of observable values to properties, supporting a wide range of types including Object, String, Boolean, Integer, Long, Double,
 *     and Float.</li>
 *     <li><b>Enhanced Binding Support:</b> Enables easy binding between observable values and properties, simplifying the process of updating UI components in response to data changes.</li>
 *     <li><b>Customization with Bean and Name Parameters:</b> Offers methods that allow specifying the bean and property name for the created properties, providing greater control over property metadata.</li>
 *     <li><b>Utility Focus:</b> Designed as a utility class with static methods, preventing instantiation and promoting a functional programming approach to property and observable value handling.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <em>Example demonstrating the conversion of an {@link ObservableValue} to a {@link Property} and binding them together.</em>
 * <pre>
 * {@code
 *     ObservableValue<String> observableStringValue = new ReadOnlyStringWrapper("Hello, world!");
 *     StringProperty stringProperty = EFXObservableUtils.observableToStringProperty(observableStringValue);
 *
 *     // Listen for changes
 *     stringProperty.addListener((observable, oldValue, newValue) -> {
 *         System.out.println("New value: " + newValue);
 *     });
 *
 *     // Output: New value: Hello, world!
 * }
 * </pre>
 *
 * <p>EFXObservableUtils simplifies the task of working with observable values and properties in JavaFX applications, enhancing the responsiveness and dynamism of UI components.</p>
 *
 * <p>It is part of the EnhancedFX library, which aims to extend JavaFX's capabilities and ease common UI development tasks.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableValue
 * @see Property
 * @see SimpleObjectProperty
 * @see SimpleStringProperty
 * @see SimpleBooleanProperty
 * @see SimpleIntegerProperty
 * @see SimpleLongProperty
 * @see SimpleDoubleProperty
 * @see SimpleFloatProperty
 */
public class EFXObservableUtils {
    /**
     * Private constructor to prevent instantiation of the {@code EFXObservableUtils} class.
     *
     * <p>This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code EFXObservableUtils}.</p>
     */
    private EFXObservableUtils() {}

    //region To Property Functions
    //*****************************************************************
    // To Property Functions
    //*****************************************************************

    //region Object Property
    //*****************************************************************
    // Object Property
    //*****************************************************************

    /**
     * Converts an ObservableValue to an ObjectProperty.
     *
     * @param <T>
     *         the type of value in the ObservableValue
     * @param observable
     *         the ObservableValue to convert
     *
     * @return the ObjectProperty that is bound to the ObservableValue
     */
    public static <T> ObjectProperty<T> observableToObjectProperty(ObservableValue<T> observable) {
        ObjectProperty<T> property = new SimpleObjectProperty<>();
        property.bind(observable);
        return property;
    }

    /**
     * Converts an ObservableValue to an ObjectProperty and binds them together.
     *
     * @param <T>
     *         the type of the value contained in the ObservableValue and ObjectProperty
     * @param observable
     *         the ObservableValue to convert and bind
     * @param bean
     *         the bean to use for the ObjectProperty
     * @param name
     *         the name to use for the ObjectProperty
     *
     * @return the ObjectProperty that is bound to the ObservableValue
     */
    public static <T> ObjectProperty<T> observableToObjectProperty(ObservableValue<T> observable, Object bean, String name) {
        ObjectProperty<T> property = new SimpleObjectProperty<>(bean, name);
        property.bind(observable);
        return property;
    }

    //endregion Object Property

    //region String Property
    //*****************************************************************
    // String Property
    //*****************************************************************

    /**
     * Converts an ObservableValue to a StringProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue to convert and bind
     *
     * @return the StringProperty that is bound to the ObservableValue
     */
    public static StringProperty observableToStringProperty(ObservableValue<String> observable) {
        SimpleStringProperty property = new SimpleStringProperty();
        property.bind(observable);
        return property;
    }

    /**
     * Converts an ObservableValue of type String to a StringProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type String to convert and bind
     * @param bean
     *         the bean to use for the StringProperty
     * @param name
     *         the name to use for the StringProperty
     *
     * @return the StringProperty that is bound to the ObservableValue
     */
    public static StringProperty observableToStringProperty(ObservableValue<String> observable, Object bean, String name) {
        SimpleStringProperty property = new SimpleStringProperty(bean, name);
        property.bind(observable);
        return property;
    }

    //endregion String Property

    //region Boolean Property
    //*****************************************************************
    // Boolean Property
    //*****************************************************************

    /**
     * Converts an ObservableValue of type Boolean to a BooleanProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Boolean to convert and bind
     *
     * @return the BooleanProperty that is bound to the ObservableValue
     */
    public static BooleanProperty observableToBooleanProperty(ObservableValue<Boolean> observable) {
        SimpleBooleanProperty property = new SimpleBooleanProperty();
        property.bind(observable);
        return property;
    }

    /**
     * Converts an ObservableValue of type Boolean to a BooleanProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Boolean to convert and bind
     * @param bean
     *         the bean to use for the BooleanProperty
     * @param name
     *         the name to use for the BooleanProperty
     *
     * @return the BooleanProperty that is bound to the ObservableValue
     */
    public static BooleanProperty observableToBooleanProperty(ObservableValue<Boolean> observable, Object bean, String name) {
        SimpleBooleanProperty property = new SimpleBooleanProperty(bean, name);
        property.bind(observable);
        return property;
    }

    //endregion Boolean Property

    //region Integer Property
    //*****************************************************************
    // Integer Property
    //*****************************************************************

    /**
     * Converts an ObservableValue of type Integer to an IntegerProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Integer to convert and bind
     *
     * @return the IntegerProperty that is bound to the ObservableValue
     */
    public static IntegerProperty observableToIntegerProperty(ObservableValue<Integer> observable) {
        SimpleIntegerProperty property = new SimpleIntegerProperty();
        property.bind(observable);
        return property;
    }

    /**
     * Converts an ObservableValue of type Integer to an IntegerProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Integer to convert and bind
     * @param bean
     *         the bean to use for the IntegerProperty
     * @param name
     *         the name to use for the IntegerProperty
     *
     * @return the IntegerProperty that is bound to the ObservableValue
     */
    public static IntegerProperty observableToIntegerProperty(ObservableValue<Integer> observable, Object bean, String name) {
        SimpleIntegerProperty property = new SimpleIntegerProperty(bean, name);
        property.bind(observable);
        return property;
    }

    //endregion Integer Property

    //region Long Property
    //*****************************************************************
    // Long Property
    //*****************************************************************

    /**
     * Converts an ObservableValue of type Long to a LongProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Long to convert and bind
     *
     * @return the LongProperty that is bound to the ObservableValue
     */
    public static LongProperty observableToLongProperty(ObservableValue<Long> observable) {
        SimpleLongProperty property = new SimpleLongProperty();
        property.bind(observable);
        return property;
    }

    /**
     * Converts an ObservableValue of type Long to a LongProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Long to convert and bind
     * @param bean
     *         the bean to use for the LongProperty
     * @param name
     *         the name to use for the LongProperty
     *
     * @return the LongProperty that is bound to the ObservableValue
     */
    public static LongProperty observableToLongProperty(ObservableValue<Long> observable, Object bean, String name) {
        SimpleLongProperty property = new SimpleLongProperty(bean, name);
        property.bind(observable);
        return property;
    }

    //endregion Long Property

    //region Double Property
    //*****************************************************************
    // Double Property
    //*****************************************************************

    /**
     * Converts a given ObservableValue of type Double to a DoubleProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Double to convert and bind
     *
     * @return the DoubleProperty that is bound to the ObservableValue
     */
    public static DoubleProperty observableToDoubleProperty(ObservableValue<Double> observable) {
        SimpleDoubleProperty property = new SimpleDoubleProperty();
        property.bind(observable);
        return property;
    }

    /**
     * Converts an ObservableValue of type Double to a DoubleProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Double to convert and bind
     * @param bean
     *         the bean to use for the DoubleProperty
     * @param name
     *         the name to use for the DoubleProperty
     *
     * @return the DoubleProperty that is bound to the ObservableValue
     */
    public static DoubleProperty observableToDoubleProperty(ObservableValue<Double> observable, Object bean, String name) {
        SimpleDoubleProperty property = new SimpleDoubleProperty(bean, name);
        property.bind(observable);
        return property;
    }

    //endregion Double Property

    //region Float Property
    //*****************************************************************
    // Float Property
    //*****************************************************************

    /**
     * Converts an ObservableValue of type Float to a FloatProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Float to convert and bind
     *
     * @return the FloatProperty that is bound to the ObservableValue
     */
    public static FloatProperty observableToFloatProperty(ObservableValue<Float> observable) {
        SimpleFloatProperty property = new SimpleFloatProperty();
        property.bind(observable);
        return property;
    }

    /**
     * Converts an ObservableValue of type Float to a FloatProperty and binds them together.
     *
     * @param observable
     *         the ObservableValue of type Float to convert and bind
     * @param bean
     *         the bean to use for the FloatProperty
     * @param name
     *         the name to use for the FloatProperty
     *
     * @return the FloatProperty that is bound to the ObservableValue
     */
    public static FloatProperty observableToFloatProperty(ObservableValue<Float> observable, Object bean, String name) {
        SimpleFloatProperty property = new SimpleFloatProperty(bean, name);
        property.bind(observable);
        return property;
    }

    //endregion Float Property

    //endregion To Property Functions
}
