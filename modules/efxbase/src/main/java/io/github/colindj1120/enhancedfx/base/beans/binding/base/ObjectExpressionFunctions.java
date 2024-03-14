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
package io.github.colindj1120.enhancedfx.base.beans.binding.base;

import io.github.colindj1120.enhancedfx.base.beans.binding.base.ObservableValueFunctions;
import javafx.beans.binding.Binding;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableObjectValue;

import java.util.Locale;

/**
 * The {@code ObjectExpressionFunctions} interface extends {@link ObservableValueFunctions} to provide a rich set of functionality for
 * operating with {@link ObservableObjectValue}s in JavaFX. It defines default methods to create bindings that can be used to monitor
 * and react to changes in object properties, allowing for the dynamic and responsive behavior typical in JavaFX application UIs.
 *
 * <p>
 * Implementors of this interface can leverage these methods to easily compare object values, check for nullity, and convert object
 * values to their string representations. This interface is generic, supporting any object type T, thus providing a flexible tool for
 * developing a wide range of JavaFX applications that require property bindings and observations.
 * </p>
 *
 * <p>
 * <h2>The primary functionalities included are:</h2>
 * <ul>
 *     <li>Equality and inequality comparisons</li>
 *     <li>Null checks</li>
 *     <li>String conversions</li>
 * </ul>
 * These operations are fundamental for creating bindings between UI components and the application's
 * data model, ensuring the UI stays consistent with the underlying data state.
 * </p>
 *
 * <p>
 * Usage of this interface facilitates cleaner, more maintainable code by abstracting common binding
 * operations into concise method calls. This approach not only reduces boilerplate code but also enhances
 * readability and maintainability of JavaFX applications.
 * </p>
 *
 * @param <T>
 *         the type of the object value this interface works with
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableObjectValue
 * @see ObjectBinding
 * @see BooleanBinding
 * @see StringBinding
 */
public interface ObjectExpressionFunctions<T> extends ObservableValueFunctions<T> {
    /**
     * Returns the binding associated with the object.
     *
     * @return The binding associated with the object.
     */
    ObjectBinding<T> getBinding();

    /**
     * Returns a BooleanBinding that represents whether this observable object value is equal to the given
     * {@link ObservableObjectValue}. This method delegates to the {@link Binding} returned by the {@link #getBinding()} method.
     *
     * @param other
     *         the {@link ObservableObjectValue} to compare to
     *
     * @return a BooleanBinding representing whether the observable object value is equal to the given {@link ObservableObjectValue}
     */
    default BooleanBinding isEqualTo(final ObservableObjectValue<?> other) {
        return getBinding().isEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this ObjectExpression is equal to the specified object.
     *
     * @param other
     *         the object to compare the value of this ObjectExpression to
     *
     * @return a BooleanBinding that is true if the value of this ObjectExpression is equal to the specified object, a BooleanBinding
     *         of false otherwise
     */
    default BooleanBinding isEqualTo(final Object other) {
        return getBinding().isEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of the current object is not equal to the value of the provided
     * ObservableObjectValue.
     *
     * @param other
     *         the ObservableObjectValue to compare against
     *
     * @return the BooleanBinding representing the result of the comparison
     */
    default BooleanBinding isNotEqualTo(final ObservableObjectValue<?> other) {
        return getBinding().isNotEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the current value of this ObjectExpression is not equal to the given object.
     *
     * @param other
     *         the object to compare equality with
     *
     * @return a BooleanBinding that is true if the current value is not equal to the given object, a BooleanBinding of false
     *         otherwise
     */
    default BooleanBinding isNotEqualTo(final Object other) {
        return getBinding().isNotEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value held by the binding is null or not.
     *
     * @return a BooleanBinding that represents whether the value held by the binding is null or not
     */
    default BooleanBinding isNull() {
        return getBinding().isNull();
    }

    /**
     * Returns a BooleanBinding that represents whether the value of the current ObjectExpression is not null.
     *
     * @return a BooleanBinding that represents whether the value of the current ObjectExpression is not null
     */
    default BooleanBinding isNotNull() {
        return getBinding().isNotNull();
    }

    /**
     * Returns a {@link StringBinding} representation of the current value held by the binding.
     *
     * @return a {@link StringBinding} instance
     */
    default StringBinding asString() {
        return getBinding().asString();
    }

    /**
     * Returns a string representation of the {@link ObjectBinding} using the specified format.
     *
     * @param format
     *         the format string to use for formatting the value
     *
     * @return a string representation of the {@link ObjectBinding}
     */
    default StringBinding asString(String format) {
        return getBinding().asString(format);
    }

    /**
     * Returns a {@link StringBinding} representation of the {@link Binding} using the specified locale and format.
     *
     * @param locale
     *         the locale to use for formatting the value
     * @param format
     *         the format string to use for formatting the value
     *
     * @return a {@link StringBinding} representation of the {@link Binding}
     */
    default StringBinding asString(Locale locale, String format) {
        return getBinding().asString(locale, format);
    }
}
