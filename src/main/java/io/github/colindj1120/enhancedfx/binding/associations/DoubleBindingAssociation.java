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
import io.github.colindj1120.enhancedfx.binding.base.numberexpressions.DoubleExpressionFunctions;
import javafx.beans.binding.DoubleBinding;

/**
 * {@code DoubleBindingAssociation} is part of the EnhancedFX library and acts as a bridge between a {@link DoubleBinding} and a bean
 * object, facilitating a seamless integration of numeric expressions with the application's data model or UI state management. This
 * association enhances the ability to respond dynamically to changes in the application's state through numeric expressions that
 * evaluate to double values.
 *
 * <p>
 * This class also implements {@link DoubleExpressionFunctions} which provide they functionality to access all the StringBinding
 * functions directly.
 * </p>
 *
 * <p>
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Numeric Expression Association:</b> Directly associates {@code DoubleBinding} with a specific bean object,
 *       allowing numeric expressions to be closely tied to the application's state or model.</li>
 *   <li><b>Double Expression Utilities:</b> Implements {@link DoubleExpressionFunctions}, providing a rich set of functionalities
 *       for operations on numeric expressions, such as arithmetic operations, comparisons, and conversions.</li>
 *   <li><b>Bean Contextualization:</b> The associated bean object contextualizes the double binding within the application's
 *       model, enabling a clear representation of what part of the state or model the numeric expression observes or represents.</li>
 *   <li><b>Enhanced Observability:</b> Facilitates the observability of double values within the application, allowing UI components
 *       or other logic to react dynamically to changes in the associated numeric expressions.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Example Usage:</h2>
 * An example illustrating the integration of {@code DoubleBindingAssociation} with a UI component to reflect changes in a model's
 * property:
 * <pre>{@code
 * DoubleBinding volumeBinding = Bindings.createDoubleBinding(() ->
 *     audioModel.volumeProperty().get() * 100, audioModel.volumeProperty());
 * Object bean = audioModel; // Model object with the volume property
 * DoubleBindingAssociation association = DoubleBindingAssociation.create(volumeBinding, bean);
 * volumeSlider.valueProperty().bind(association.getBinding());
 * }</pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see DoubleBinding
 * @see BindingAssociation
 * @see DoubleExpressionFunctions
 */
public class DoubleBindingAssociation implements BindingAssociation<Number>, DoubleExpressionFunctions {
    /**
     * Factory method to create a new {@code DoubleBindingAssociation}. This method establishes an association between a
     * {@link DoubleBinding} and a specific bean object. It encapsulates the relationship between the double binding and the underlying
     * property or model it represents or observes.
     *
     * @param binding
     *         the {@link DoubleBinding} to be associated with the bean. This binding represents a numeric expression that evaluates to
     *         a double value, depending on one or more observables.
     * @param bean
     *         the bean object related to the {@code DoubleBinding}. This object typically represents the underlying model or entity
     *         that the binding observes or depends upon.
     *
     * @return a new instance of {@code DoubleBindingAssociation} that encapsulates the relationship between the provided
     *         {@code DoubleBinding} and the bean.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}, ensuring that each {@code DoubleBindingAssociation} has a valid bean reference.
     */
    public static DoubleBindingAssociation create(DoubleBinding binding, Object bean) {
        return new DoubleBindingAssociation(binding, bean);
    }

    /**
     * The bean associated with the {@link DoubleBinding} in this {@code DoubleBindingAssociation}. The bean represents the underlying
     * model or object that influences the value of the {@code DoubleBinding}. It provides a contextual link between the binding and
     * the part of the application state it represents or observes.
     */
    private final Object bean;

    /**
     * The {@link DoubleBinding} instance that is part of this association. This binding encapsulates a computed double value that,
     * when observed, reflects changes in the application state, often based on the state of the {@code bean}. It is the core
     * functional component of the association, providing the dynamic link between the application state and the UI or other dependent
     * logic.
     */
    private final DoubleBinding binding;

    /**
     * Private constructor to instantiate a {@code DoubleBindingAssociation}. Called internally by the
     * {@link #create(DoubleBinding, Object)} factory method, it initializes the association with a specific {@code DoubleBinding} and
     * bean, performing a null check on the bean to ensure its validity.
     *
     * @param binding
     *         the {@code DoubleBinding} to associate.
     * @param bean
     *         the bean object related to the binding.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}.
     */
    private DoubleBindingAssociation(DoubleBinding binding, Object bean) {
        checkBeanIsNotNull(bean, DoubleBindingAssociation.class);
        this.binding = binding;
        this.bean = bean;
    }

    /**
     * Retrieves the {@link DoubleBinding} associated with this {@code DoubleBindingAssociation}.
     *
     * @return the {@code DoubleBinding} instance associated with this association.
     */
    public DoubleBinding getBinding() {
        return this.binding;
    }

    /**
     * Retrieves the bean associated with the {@code DoubleBinding}. The bean represents the underlying property or object that the
     * {@code DoubleBinding} depends on.
     *
     * @return the bean object associated with the {@code DoubleBinding}.
     */
    public Object getBean() {
        return this.bean;
    }

    /**
     * Generates a string representation of this {@code DoubleBindingAssociation}, including the binding's and bean's string
     * representations.
     *
     * @return a string representation of this {@code DoubleBindingAssociation}.
     */
    @Override
    public String toString() {
        return String.format("DoubleBinding{%s}, Bean{%s}", this.binding.toString(), this.bean.toString());
    }
}
