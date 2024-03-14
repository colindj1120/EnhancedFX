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
package io.github.colindj1120.enhancedfx.binding.associations;

import io.github.colindj1120.enhancedfx.binding.base.BindingAssociation;
import io.github.colindj1120.enhancedfx.binding.base.stringexpressions.StringExpressionFunctions;
import javafx.beans.binding.StringBinding;

/**
 * {@code StringBindingAssociation} provides a concrete implementation of {@link BindingAssociation} for {@code String} type bindings,
 * combining a {@link StringBinding} with an associated bean. This class facilitates the management and use of string bindings within
 * the application, tying them directly to their respective data models or objects.
 *
 * <p>
 * This class also implements {@link StringExpressionFunctions} which provide they functionality to access all the StringBinding
 * functions directly.
 * </p>
 *
 * <p>
 * This association class makes it easier to track and manipulate bindings in relation to their beans, ensuring a clear and
 * maintainable link between UI elements and the underlying application state. By encapsulating both the {@code StringBinding} and its
 * associated bean within a single entity, it provides a streamlined approach to handling dynamic string expressions that react to
 * changes in the application's data model.
 * </p>
 *
 * <p1>
 * <h2>Capabilities:</h2>
 * <ul>
 *      <li>Creation of {@code StringBindingAssociation} instances through a static factory method, ensuring a tight coupling
 *      between a {@code StringBinding} and its corresponding bean.</li>
 *      <li>Implementation of {@code StringExpressionFunctions}, allowing for enhanced manipulation and querying of the associated
 *      string binding's value.</li>
 *      <li>Validation of the associated bean to ensure it is not {@code null}, promoting robustness and reliability in the
 *      association's usage.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Usage Example:</h2>
 * <pre>
 * ObservableStringValue myObservableStringValue = ...;
 * Object myBean = ...;
 *
 * StringBinding myStringBinding = Bindings.createStringBinding(() -> {
 *     return myObservableStringValue.get() + " transformed";
 * }, myObservableStringValue);
 *
 * StringBindingAssociation myStringBindingAssoc = StringBindingAssociation.create(myStringBinding, myBean);
 *
 * // Now, myStringBindingAssoc encapsulates both the StringBinding and its associated bean, allowing for centralized management
 * // and use within your application.
 * </pre>
 * </p>
 *
 * <p>
 * This approach simplifies the management of string bindings, especially when working with complex data models or UI components
 * that require dynamic string content based on the application's state. By associating bindings with their beans directly,
 * {@code StringBindingAssociation} helps maintain a clean and understandable flow of data within the application.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BindingAssociation
 * @see StringBinding
 * @see StringExpressionFunctions
 */
public class StringBindingAssociation implements BindingAssociation<String>, StringExpressionFunctions {
    /**
     * A factory method that creates a new {@code StringBindingAssociation} instance. This method encapsulates the association between
     * a {@link StringBinding} and its corresponding bean. It provides a concrete implementation of the {@code BindingAssociation}
     * interface for {@code String} type bindings.
     *
     * @param binding
     *         the {@code StringBinding} to be associated with a bean
     * @param bean
     *         the bean object that the {@code StringBinding} is associated with. This bean typically represents the underlying
     *         property or object that the {@code StringBinding} observes or depends upon.
     *
     * @return a new instance of {@code StringBindingAssociation} encapsulating the relationship between the provided
     *         {@code StringBinding} and bean
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}, ensuring that every {@code StringBindingAssociation} has a valid bean reference
     */
    public static StringBindingAssociation create(StringBinding binding, Object bean) {
        return new StringBindingAssociation(binding, bean);
    }

    /**
     * The bean associated with the {@link StringBinding} in this {@code StringBindingAssociation}. The bean typically represents the
     * underlying model or object that influences the value of the {@code StringBinding}. It acts as a contextual link between the
     * binding and the part of the application state it represents or depends upon.
     */
    private final Object bean;

    /**
     * The {@link StringBinding} instance that is part of this association. This binding encapsulates a computed value that, when
     * observed, reflects changes in the application state, often based on the state of the {@code bean}. It is the core functional
     * component of the association, providing the dynamic link between the application state and the UI or other dependent logic.
     */
    private final StringBinding binding;

    /**
     * Private constructor to instantiate a {@code StringBindingAssociation}. This constructor is called internally by the
     * {@link #create(StringBinding, Object)} factory method. It initializes the association with a specific {@code StringBinding} and
     * bean, performing a null check on the bean to ensure its validity.
     *
     * @param binding
     *         the {@code StringBinding} to associate
     * @param bean
     *         the bean object related to the binding
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}
     */
    private StringBindingAssociation(StringBinding binding, Object bean) {
        checkBeanIsNotNull(bean, DoubleBindingAssociation.class); // The reference class should be StringBindingAssociation.class
        this.binding = binding;
        this.bean = bean;
    }

    /**
     * Retrieves the {@link StringBinding} associated with this {@code StringBindingAssociation}.
     *
     * @return the {@code StringBinding} instance associated with this association
     */
    @Override
    public StringBinding getBinding() {
        return this.binding;
    }

    /**
     * Retrieves the bean associated with the {@code StringBinding}. The bean represents the underlying property or object that the
     * {@code StringBinding} depends on.
     *
     * @return the bean object associated with the {@code StringBinding}
     */
    @Override
    public Object getBean() {
        return this.bean;
    }

    /**
     * Generates a string representation of this {@code StringBindingAssociation}, including its binding's and bean's string
     * representations.
     *
     * @return a string representation of this {@code StringBindingAssociation}
     */
    @Override
    public String toString() {
        return String.format("StringBinding{%s}, Bean{%s}", this.binding.toString(), this.bean.toString());
    }

}
