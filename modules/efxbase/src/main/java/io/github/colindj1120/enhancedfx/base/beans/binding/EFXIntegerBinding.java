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
package io.github.colindj1120.enhancedfx.base.beans.binding;

import io.github.colindj1120.enhancedfx.base.beans.binding.base.EFXBinding;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.IntegerExpressionFunctions;
import javafx.beans.binding.IntegerBinding;

/**
 * The {@code EFXIntegerBinding} class is a critical part of the EnhancedFX framework, aiming to bridge the gap between numeric
 * expressions that produce integer values and the application's data model or state. This association simplifies the process of
 * linking an {@link IntegerBinding} with a specific bean object, facilitating real-time updates in the application's UI or logic in
 * response to changes in the state represented by the integer binding.
 *
 * <p>
 * This class also implements {@link IntegerExpressionFunctions} which provide they functionality to access all the StringBinding
 * functions directly.
 * </p>
 *
 * <p>
 * <h2>Features and Benefits:</h2>
 * <ul>
 *   <li><b>Direct Association:</b> Links an {@code IntegerBinding} with a bean, providing a clear and direct
 *       connection between the UI or logic and the underlying data model or state.</li>
 *   <li><b>Integer Expression Utilities:</b> Inherits functionalities from {@link IntegerExpressionFunctions},
 *       offering a wide range of operations and utilities for integer expressions.</li>
 *   <li><b>Dynamic Updates:</b> Ensures that UI components or logic react dynamically to changes in the
 *       associated integer values, enhancing the application's interactivity and user experience.</li>
 *   <li><b>Enhanced Maintainability:</b> By providing a clear association between the binding and its bean,
 *       it helps in maintaining a cleaner and more manageable codebase.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Usage Example:</h2>
 * This example demonstrates how to use {@code EFXIntegerBinding} to bind a model's integer property
 * to update a UI component dynamically:
 * <pre>{@code
 * IntegerBinding volumeLevelBinding = Bindings.createIntegerBinding(() ->
 *     audioSettings.volumeLevelProperty().get(), audioSettings.volumeLevelProperty());
 * Object bean = audioSettings; // The model object with the volume level property
 * EFXIntegerBinding association = EFXIntegerBinding.create(volumeLevelBinding, bean);
 * volumeControlUIComponent.valueProperty().bind(association.getBinding());
 * }</pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see IntegerBinding
 * @see EFXBinding
 * @see IntegerExpressionFunctions
 */
public class EFXIntegerBinding implements EFXBinding<Number>, IntegerExpressionFunctions {
    /**
     * Factory method to create a new {@code EFXIntegerBinding}. This method establishes an association between an
     * {@link IntegerBinding} and a specific bean object. It encapsulates the relationship between the integer binding and the
     * underlying property or model it represents or observes.
     *
     * @param binding
     *         the {@link IntegerBinding} to be associated with the bean. This binding represents a numeric expression that evaluates
     *         to an integer value, depending on one or more observables.
     * @param bean
     *         the bean object related to the {@code IntegerBinding}. This object typically represents the underlying model or entity
     *         that the binding observes or depends upon.
     *
     * @return a new instance of {@code EFXIntegerBinding} that encapsulates the relationship between the provided
     *         {@code IntegerBinding} and the bean.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}, ensuring that each {@code EFXIntegerBinding} has a valid bean reference.
     */
    public static EFXIntegerBinding create(IntegerBinding binding, Object bean) {
        return new EFXIntegerBinding(binding, bean);
    }

    /**
     * The bean associated with the {@link IntegerBinding} in this {@code EFXIntegerBinding}. The bean represents the
     * underlying model or object that influences the value of the {@code IntegerBinding}. It provides a contextual link between the
     * binding and the part of the application state it represents or observes.
     */
    private final Object bean;

    /**
     * The {@link IntegerBinding} instance that is part of this association. This binding encapsulates a computed integer value that,
     * when observed, reflects changes in the application state, often based on the state of the {@code bean}. It is the core
     * functional component of the association, providing the dynamic link between the application state and the UI or other dependent
     * logic.
     */
    private final IntegerBinding binding;

    /**
     * Private constructor to instantiate a {@code EFXIntegerBinding}. Called internally by the
     * {@link #create(IntegerBinding, Object)} factory method, it initializes the association with a specific {@code IntegerBinding}
     * and bean, performing a null check on the bean to ensure its validity.
     *
     * @param binding
     *         the {@code IntegerBinding} to associate.
     * @param bean
     *         the bean object related to the binding.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}.
     */
    private EFXIntegerBinding(IntegerBinding binding, Object bean) {
        checkBeanIsNotNull(bean, EFXIntegerBinding.class);
        this.binding = binding;
        this.bean = bean;
    }

    /**
     * Retrieves the {@link IntegerBinding} associated with this {@code EFXIntegerBinding}.
     *
     * @return the {@code IntegerBinding} instance associated with this association.
     */
    public IntegerBinding getBinding() {
        return this.binding;
    }

    /**
     * Retrieves the bean associated with the {@code IntegerBinding}. The bean represents the underlying property or object that the
     * {@code IntegerBinding} depends on.
     *
     * @return the bean object associated with the {@code IntegerBinding}.
     */
    public Object getBean() {
        return this.bean;
    }

    /**
     * Generates a string representation of this {@code EFXIntegerBinding}, including the binding's and bean's string
     * representations.
     *
     * @return a string representation of this {@code EFXIntegerBinding}.
     */
    @Override
    public String toString() {
        return String.format("IntegerBinding{%s}, Bean{%s}", this.binding.toString(), this.bean.toString());
    }
}
