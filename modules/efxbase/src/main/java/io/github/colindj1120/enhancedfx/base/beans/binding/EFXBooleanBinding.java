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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.BooleanExpressionFunctions;
import javafx.beans.binding.BooleanBinding;

/**
 * {@code EFXBooleanBinding} serves as a connector between a {@link BooleanBinding} and a bean object, thereby creating a
 * meaningful link between the state of a boolean expression and the application's model or underlying data. Part of the EnhancedFX
 * library, this class enriches JavaFX development by simplifying the management of bindings that are critical for reactive UI
 * designs.
 *
 * <p>This class allows developers to encapsulate the logic of boolean value changes within the UI, ensuring that any
 * changes in the observed properties or expressions directly reflect on the UI components or other areas of interest within the
 * application. It is particularly useful in scenarios where the UI needs to dynamically respond to changes in the application's state,
 * such as toggling visibility, enabling/disabling controls, or triggering other boolean conditions.</p>
 *
 * <p>
 * This class also implements {@link BooleanExpressionFunctions} which provide they functionality to access all the StringBinding
 * functions directly.
 * </p>
 *
 * <p1>
 * <h2>Features:</h2>
 * <ul>
 *     <li><b>Integration with Boolean Bindings:</b> Works with {@code BooleanBinding} to facilitate the observation of
 *     boolean expressions derived from observable values.</li>
 *     <li><b>Direct Association with Beans:</b> Directly associates a boolean binding with a specific bean, tying the
 *     binding's logic to a concrete part of the application's model or data.</li>
 *     <li><b>Enhanced Boolean Expression Utilities:</b> Implements {@link BooleanExpressionFunctions} to provide
 *     additional utilities for boolean expressions, such as logical operations and value transformations.</li>
 *     <li><b>Customizable:</b> Designed for flexibility, allowing it to be adapted or extended for various
 *     use cases across different domains.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Example Usage:</h2>
 * Below is an example illustrating how to use {@code EFXBooleanBinding} in a JavaFX application to bind a
 * model property to a UI component's enabled state:
 * <pre>{@code
 * BooleanBinding conditionBinding = Bindings.createBooleanBinding(() ->
 *     model.isActiveProperty().get(), model.isActiveProperty());
 * Object bean = model; // Model object with the isActive property
 * EFXBooleanBinding association = EFXBooleanBinding.create(conditionBinding, bean);
 * button.disableProperty().bind(association.getBinding().not());
 * }</pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BooleanBinding
 * @see EFXBinding
 * @see BooleanExpressionFunctions
 */
public class EFXBooleanBinding implements EFXBinding<Boolean>, BooleanExpressionFunctions {
    /**
     * Factory method to create a new {@code EFXBooleanBinding}. This method establishes an association between a
     * {@link BooleanBinding} and a specific bean object. It encapsulates the relationship between the boolean binding and the
     * underlying property or model it represents or observes.
     *
     * @param binding
     *         the {@link BooleanBinding} to be associated with the bean. This binding represents a boolean expression that depends on
     *         one or more observables.
     * @param bean
     *         the bean object related to the {@code BooleanBinding}. This object typically represents the underlying model or entity
     *         that the binding observes or depends upon.
     *
     * @return a new instance of {@code EFXBooleanBinding} that encapsulates the relationship between the provided
     *         {@code BooleanBinding} and the bean.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}, ensuring that each {@code EFXBooleanBinding} has a valid bean reference.
     */
    public static EFXBooleanBinding create(BooleanBinding binding, Object bean) {
        return new EFXBooleanBinding(binding, bean);
    }

    /**
     * The bean associated with the {@link BooleanBinding} in this {@code EFXBooleanBinding}. The bean represents the
     * underlying model or object that influences the value of the {@code BooleanBinding}. It provides a contextual link between the
     * binding and the part of the application state it represents or observes.
     */
    private final Object bean;

    /**
     * The {@link BooleanBinding} instance that is part of this association. This binding encapsulates a computed boolean value that,
     * when observed, reflects changes in the application state, often based on the state of the {@code bean}. It is the core
     * functional component of the association, providing the dynamic link between the application state and the UI or other dependent
     * logic.
     */
    private final BooleanBinding binding;

    /**
     * Private constructor to instantiate a {@code EFXBooleanBinding}. Called internally by the
     * {@link #create(BooleanBinding, Object)} factory method, it initializes the association with a specific {@code BooleanBinding}
     * and bean, performing a null check on the bean to ensure its validity.
     *
     * @param binding
     *         the {@code BooleanBinding} to associate.
     * @param bean
     *         the bean object related to the binding.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}.
     */
    private EFXBooleanBinding(BooleanBinding binding, Object bean) {
        checkBeanIsNotNull(bean, EFXBooleanBinding.class);
        this.binding = binding;
        this.bean = bean;
    }

    /**
     * Retrieves the {@link BooleanBinding} associated with this {@code EFXBooleanBinding}.
     *
     * @return the {@code BooleanBinding} instance associated with this association.
     */
    public BooleanBinding getBinding() {
        return this.binding;
    }

    /**
     * Retrieves the bean associated with the {@code BooleanBinding}. The bean represents the underlying property or object that the
     * {@code BooleanBinding} depends on.
     *
     * @return the bean object associated with the {@code BooleanBinding}.
     */
    public Object getBean() {
        return this.bean;
    }

    /**
     * Generates a string representation of this {@code EFXBooleanBinding}, including the binding's and bean's string
     * representations.
     *
     * @return a string representation of this {@code EFXBooleanBinding}.
     */
    @Override
    public String toString() {
        return String.format("BooleanBinding{%s}, Bean{%s}", this.binding.toString(), this.bean.toString());
    }

}
