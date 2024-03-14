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

import io.github.colindj1120.enhancedfx.base.beans.binding.base.NumberExpressionFunctions;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.EFXBinding;
import javafx.beans.binding.NumberBinding;

/**
 * {@code EFXNumberBinding} class serves as a bridge linking a {@link NumberBinding} to a specific bean object, facilitating
 * the synchronization of UI components or other observers with the state of a numeric value that may depend on multiple observable
 * sources. This class is part of the EnhancedFX library, aimed at enhancing JavaFX application development by providing a more
 * intuitive and robust way to work with bindings.
 *
 * <p>NumberBindingAssociations are particularly useful in complex JavaFX UIs where the value of a UI component needs
 * to be kept in sync with underlying model data that can change over time or in response to user actions. By associating a
 * {@code NumberBinding} with a bean, developers can more easily track and manage these dependencies, ensuring that the UI accurately
 * reflects the current state of the application.</p>
 *
 * <p>
 * This class also implements {@link NumberExpressionFunctions} which provide they functionality to access all the StringBinding
 * functions directly.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li><b>Type Safety:</b> Operates with {@code NumberBinding} to ensure numeric value changes are properly managed and observed
 *     .</li>
 *     <li><b>Bean Association:</b> Links a {@code NumberBinding} to a specific bean, contextualizing the binding within the
 *     application's data model.</li>
 *     <li><b>Utility Methods:</b> Implements {@link NumberExpressionFunctions} to offer additional numeric operations and
 *     transformations.</li>
 *     <li><b>Extensibility:</b> Designed to be extended or used as-is for various application-specific requirements.</li>
 * </ul>
 *
 * <p>
 * <h2>Usage Example:</h2>
 * Here's how you might use {@code EFXNumberBinding} to bind the numeric value of a model's property to a
 * label's text property in a JavaFX application:
 * <pre>{@code
 * NumberBinding numericValueBinding = Bindings.createDoubleBinding(() ->
 *     model.getNumericValue(), model.numericValueProperty());
 * Object bean = model; // The model object containing the numeric property
 * EFXNumberBinding association = EFXNumberBinding.create(numericValueBinding, bean);
 * label.textProperty().bind(association.getBinding().asString());
 * }</pre>
 * </>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see NumberBinding
 * @see EFXBinding
 * @see NumberExpressionFunctions
 */
public class EFXNumberBinding implements EFXBinding<Number>, NumberExpressionFunctions {
    /**
     * Factory method to create a new {@code EFXNumberBinding}. This method establishes an association between a
     * {@link NumberBinding} and a specific bean object. It encapsulates the relationship between the number binding and the underlying
     * property or model it represents or observes.
     *
     * @param binding
     *         the {@link NumberBinding} to be associated with the bean. This binding represents a numeric expression that depends on
     *         one or more observables.
     * @param bean
     *         the bean object related to the {@code NumberBinding}. This object typically represents the underlying model or entity
     *         that the binding observes or depends upon.
     *
     * @return a new instance of {@code EFXNumberBinding} that encapsulates the relationship between the provided
     *         {@code NumberBinding} and the bean.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}, ensuring that each {@code EFXNumberBinding} has a valid bean reference.
     */
    public static EFXNumberBinding create(NumberBinding binding, Object bean) {
        return new EFXNumberBinding(binding, bean);
    }

    /**
     * The bean associated with the {@link NumberBinding} in this {@code EFXNumberBinding}. The bean represents the underlying
     * model or object that influences the value of the {@code NumberBinding}. It provides a contextual link between the binding and
     * the part of the application state it represents or observes.
     */
    private final Object bean;

    /**
     * The {@link NumberBinding} instance that is part of this association. This binding encapsulates a computed numeric value that,
     * when observed, reflects changes in the application state, often based on the state of the {@code bean}. It is the core
     * functional component of the association, providing the dynamic link between the application state and the UI or other dependent
     * logic.
     */
    private final NumberBinding binding;

    /**
     * Private constructor to instantiate a {@code EFXNumberBinding}. Called internally by the
     * {@link #create(NumberBinding, Object)} factory method, it initializes the association with a specific {@code NumberBinding} and
     * bean, performing a null check on the bean to ensure its validity.
     *
     * @param binding
     *         the {@code NumberBinding} to associate.
     * @param bean
     *         the bean object related to the binding.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}.
     */
    private EFXNumberBinding(NumberBinding binding, Object bean) {
        checkBeanIsNotNull(bean, EFXNumberBinding.class);
        this.binding = binding;
        this.bean = bean;
    }

    /**
     * Retrieves the {@link NumberBinding} associated with this {@code EFXNumberBinding}.
     *
     * @return the {@code NumberBinding} instance associated with this association.
     */
    public NumberBinding getBinding() {
        return this.binding;
    }

    /**
     * Retrieves the bean associated with the {@code NumberBinding}. The bean represents the underlying property or object that the
     * {@code NumberBinding} depends on.
     *
     * @return the bean object associated with the {@code NumberBinding}.
     */
    public Object getBean() {
        return this.bean;
    }

    /**
     * Generates a string representation of this {@code EFXNumberBinding}, including the binding's and bean's string
     * representations.
     *
     * @return a string representation of this {@code EFXNumberBinding}.
     */
    @Override
    public String toString() {
        return String.format("NumberBinding{%s}, Bean{%s}", this.binding.toString(), this.bean.toString());
    }
}
