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
import io.github.colindj1120.enhancedfx.binding.base.numberexpressions.FloatExpressionFunctions;
import javafx.beans.binding.FloatBinding;

/**
 * {@code FloatBindingAssociation} is a part of the EnhancedFX library designed to facilitate the seamless integration of float-based
 * numeric expressions with an application's data model or UI state. It establishes a direct link between a {@link FloatBinding} and a
 * bean object, allowing for dynamic updates in response to changes in the application's state.
 *
 * <p>
 * This class also implements {@link FloatExpressionFunctions} which provide they functionality to access all the StringBinding
 * functions directly.
 * </p>
 *
 * <p>
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Float Numeric Expression Association:</b> Directly associates {@code FloatBinding} with a bean object, enabling
 *       real-time updates of UI components or logic based on the state of float expressions.</li>
 *   <li><b>Float Expression Utilities:</b> Implements {@link FloatExpressionFunctions}, offering a comprehensive suite of
 *       functionalities for float expressions, including arithmetic operations, comparisons, and conversion utilities.</li>
 *   <li><b>Contextual Bean Linkage:</b> The association with a bean object provides context, linking the float binding
 *       to a specific part of the application's model, enhancing clarity and maintainability.</li>
 *   <li><b>Dynamic Application State Observability:</b> Enhances the observability of float values within the application,
 *       allowing components and logic to react dynamically to changes in the associated float expressions.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Example Usage:</h2>
 * Demonstrating how {@code FloatBindingAssociation} can be used to bind a UI component's property to a model's property:
 * <pre>{@code
 * FloatBinding opacityBinding = Bindings.createFloatBinding(() ->
 *     model.opacityProperty().get(), model.opacityProperty());
 * Object bean = model; // The model object with the opacity property
 * FloatBindingAssociation association = FloatBindingAssociation.create(opacityBinding, bean);
 * uiComponent.opacityProperty().bind(association.getBinding());
 * }</pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see FloatBinding
 * @see BindingAssociation
 * @see FloatExpressionFunctions
 */
public class FloatBindingAssociation implements BindingAssociation<Number>, FloatExpressionFunctions {
    /**
     * Factory method to create a new {@code FloatBindingAssociation}. This method establishes an association between a
     * {@link FloatBinding} and a specific bean object. It encapsulates the relationship between the float binding and the underlying
     * property or model it represents or observes.
     *
     * @param binding
     *         the {@link FloatBinding} to be associated with the bean. This binding represents a numeric expression that evaluates to
     *         a float value, depending on one or more observables.
     * @param bean
     *         the bean object related to the {@code FloatBinding}. This object typically represents the underlying model or entity
     *         that the binding observes or depends upon.
     *
     * @return a new instance of {@code FloatBindingAssociation} that encapsulates the relationship between the provided
     *         {@code FloatBinding} and the bean.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}, ensuring that each {@code FloatBindingAssociation} has a valid bean reference.
     */
    public static FloatBindingAssociation create(FloatBinding binding, Object bean) {
        return new FloatBindingAssociation(binding, bean);
    }

    /**
     * The bean associated with the {@link FloatBinding} in this {@code FloatBindingAssociation}. The bean represents the underlying
     * model or object that influences the value of the {@code FloatBinding}. It provides a contextual link between the binding and the
     * part of the application state it represents or observes.
     */
    private final Object bean;

    /**
     * The {@link FloatBinding} instance that is part of this association. This binding encapsulates a computed float value that, when
     * observed, reflects changes in the application state, often based on the state of the {@code bean}. It is the core functional
     * component of the association, providing the dynamic link between the application state and the UI or other dependent logic.
     */
    private final FloatBinding binding;

    /**
     * Private constructor to instantiate a {@code FloatBindingAssociation}. Called internally by the
     * {@link #create(FloatBinding, Object)} factory method, it initializes the association with a specific {@code FloatBinding} and
     * bean, performing a null check on the bean to ensure its validity.
     *
     * @param binding
     *         the {@code FloatBinding} to associate.
     * @param bean
     *         the bean object related to the binding.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}.
     */
    private FloatBindingAssociation(FloatBinding binding, Object bean) {
        checkBeanIsNotNull(bean, FloatBindingAssociation.class);
        this.binding = binding;
        this.bean = bean;
    }

    /**
     * Retrieves the {@link FloatBinding} associated with this {@code FloatBindingAssociation}.
     *
     * @return the {@code FloatBinding} instance associated with this association.
     */
    public FloatBinding getBinding() {
        return this.binding;
    }

    /**
     * Retrieves the bean associated with the {@code FloatBinding}. The bean represents the underlying property or object that the
     * {@code FloatBinding} depends on.
     *
     * @return the bean object associated with the {@code FloatBinding}.
     */
    public Object getBean() {
        return this.bean;
    }

    /**
     * Generates a string representation of this {@code FloatBindingAssociation}, including the binding's and bean's string
     * representations.
     *
     * @return a string representation of this {@code FloatBindingAssociation}.
     */
    @Override
    public String toString() {
        return String.format("FloatBinding{%s}, Bean{%s}", this.binding.toString(), this.bean.toString());
    }
}
