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
 * Utility class for converting various types of JavaFX expressions to corresponding properties. This class provides static
 * methods to facilitate the conversion of expressions into properties, enabling the use of expressions in a property-based
 * context. Each method creates a new property of the appropriate type and binds it to the given expression, ensuring that changes
 * in the expression are reflected in the property.
 *
 * <h2>Expression Types That Can Be Converted To Properties:</h2>
 * <ul>
 *     <li>{@link ObjectExpression} to {@link ObjectProperty}</li>
 *     <li>{@link BooleanExpression} to {@link BooleanProperty}</li>
 *     <li>{@link StringExpression} to {@link StringProperty}</li>
 *     <li>{@link IntegerExpression} to {@link IntegerProperty}</li>
 *     <li>{@link LongExpression} to {@link LongProperty}</li>
 *     <li>{@link DoubleExpression} to {@link DoubleProperty}</li>
 *     <li>{@link FloatExpression} to {@link FloatProperty}</li>
 * </ul>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * BooleanExpression booleanExpression = ...;
 * BooleanProperty booleanProperty = ExpressionUtils.expressionToBooleanProperty(booleanExpression);
 *
 * IntegerExpression integerExpression = ...;
 * IntegerProperty integerProperty = ExpressionUtils.expressionToIntegerProperty(integerExpression);
 *
 * StringExpression stringExpression = ...;
 * StringProperty stringProperty = ExpressionUtils.expressionToStringProperty(stringExpression);
 * }</pre>
 *
 * <p>By converting expressions to properties, developers can easily integrate expression-based logic into property-driven UI
 * components or application logic.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Binding
 * @see ObjectExpression
 * @see BooleanExpression
 * @see StringExpression
 * @see IntegerExpression
 * @see DoubleExpression
 * @see FloatExpression
 * @see LongExpression
 * @see ObjectProperty
 * @see BooleanProperty
 * @see StringProperty
 * @see IntegerProperty
 * @see LongProperty
 * @see DoubleProperty
 * @see FloatProperty
 */
public class ExpressionUtils {
    /**
     * Private constructor to prevent instantiation of the {@code ExpressionUtils} class.
     *
     * <p>
     * This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code ExpressionUtils}.
     * </p>
     */
    private ExpressionUtils() {}

    /**
     * Creates an ObjectProperty that binds to the given ObjectExpression.
     *
     * @param expression
     *         the ObjectExpression to bind the ObjectProperty to
     * @param <T>
     *         the type of the property value
     *
     * @return the ObjectProperty that is bound to the given ObjectExpression
     */
    public static <T> ObjectProperty<T> expressionToObjectProperty(ObjectExpression<T> expression) {
        ObjectProperty<T> property = new SimpleObjectProperty<>();
        property.bind(expression);
        return property;
    }

    /**
     * Creates a {@link StringProperty} and binds it to the given {@link StringExpression}. Changes in the expression will be
     * reflected in the property.
     *
     * @param expression
     *         the string expression to bind to the property
     *
     * @return the created string property
     */
    public static StringProperty expressionToStringProperty(StringExpression expression) {
        StringProperty property = new SimpleStringProperty();
        property.bind(expression);
        return property;
    }

    /**
     * Converts a BooleanExpression to a BooleanProperty.
     *
     * @param expression
     *         the BooleanExpression to convert
     *
     * @return the BooleanProperty representing the given expression
     */
    public static BooleanProperty expressionToBooleanProperty(BooleanExpression expression) {
        BooleanProperty property = new SimpleBooleanProperty();
        property.bind(expression);
        return property;
    }

    /**
     * Converts an IntegerExpression to an IntegerProperty.
     *
     * @param expression
     *         the IntegerExpression to convert
     *
     * @return the converted IntegerProperty
     */
    public static IntegerProperty expressionToIntegerProperty(IntegerExpression expression) {
        IntegerProperty property = new SimpleIntegerProperty();
        property.bind(expression);
        return property;
    }

    /**
     * Converts a DoubleExpression to a DoubleProperty by creating a new instance of SimpleDoubleProperty and binding it to the
     * given expression.
     *
     * @param expression
     *         the DoubleExpression to convert to a DoubleProperty
     *
     * @return the newly created DoubleProperty bound to the given expression
     */
    public static DoubleProperty expressionToDoubleProperty(DoubleExpression expression) {
        DoubleProperty property = new SimpleDoubleProperty();
        property.bind(expression);
        return property;
    }

    /**
     * Converts a FloatExpression into a FloatProperty by creating a new SimpleFloatProperty and binding it to the provided
     * expression.
     *
     * @param expression
     *         The FloatExpression to convert into a FloatProperty.
     *
     * @return A FloatProperty representing the provided expression.
     */
    public static FloatProperty expressionToFloatProperty(FloatExpression expression) {
        FloatProperty property = new SimpleFloatProperty();
        property.bind(expression);
        return property;
    }

    /**
     * Converts a LongExpression to a LongProperty by creating a new LongProperty and binding it to the given expression.
     *
     * @param expression
     *         the LongExpression to convert
     *
     * @return a new LongProperty bound to the given expression
     */
    public static LongProperty expressionToLongProperty(LongExpression expression) {
        LongProperty property = new SimpleLongProperty();
        property.bind(expression);
        return property;
    }
}
