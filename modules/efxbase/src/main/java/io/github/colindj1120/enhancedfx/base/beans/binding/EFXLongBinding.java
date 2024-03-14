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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.LongExpressionFunctions;
import javafx.beans.binding.LongBinding;

/**
 * The {@code EFXLongBinding} class serves as a foundational component within the EnhancedFX framework, aimed at facilitating
 * the seamless integration of long numeric expressions with the broader application model or state. By establishing a robust link
 * between a {@link LongBinding} and its associated bean object, this class underscores the synergy between the dynamic updates in the
 * application's user interface (UI) or logic and the changes within the underlying data model.
 *
 * <p>
 * This class also implements {@link LongExpressionFunctions} which provide they functionality to access all the StringBinding
 * functions directly.
 * </p>
 *
 * <p>
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Direct Connection:</b> Forges a direct link between a {@code LongBinding} and its corresponding bean,
 *       enabling the binding to reactively represent changes in the underlying model or state.</li>
 *   <li><b>Long Expression Utilities:</b> Inherits from {@link LongExpressionFunctions}, offering an array of
 *       functionalities tailored for long expressions, enhancing ease of use and flexibility.</li>
 *   <li><b>Responsive UI Updates:</b> Facilitates the automatic and real-time update of UI components or logical
 *       operations in response to variations in the associated long values, thereby improving interactivity and user
 *       engagement.</li>
 *   <li><b>Improved Code Manageability:</b> Clarifies the relationship between bindings and their respective beans,
 *       promoting cleaner, more maintainable code architecture.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * LongBinding userAgeBinding = Bindings.createLongBinding(() ->
 *     userProfile.ageProperty().get(), userProfile.ageProperty());
 * Object bean = userProfile; // Model object with the age property
 * EFXLongBinding association = EFXLongBinding.create(userAgeBinding, bean);
 * ageDisplayUIComponent.valueProperty().bind(association.getBinding());
 * }</pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see LongBinding
 * @see EFXBinding
 * @see LongExpressionFunctions
 */
public class EFXLongBinding implements EFXBinding<Number>, LongExpressionFunctions {
    /**
     * Factory method to create a new {@code EFXLongBinding}. This method establishes an association between a
     * {@link LongBinding} and a specific bean object. It encapsulates the relationship between the long binding and the underlying
     * property or model it represents or observes.
     *
     * @param binding
     *         the {@link LongBinding} to be associated with the bean. This binding represents a numeric expression that evaluates to a
     *         long value, depending on one or more observables.
     * @param bean
     *         the bean object related to the {@code LongBinding}. This object typically represents the underlying model or entity that
     *         the binding observes or depends upon.
     *
     * @return a new instance of {@code EFXLongBinding} that encapsulates the relationship between the provided
     *         {@code LongBinding} and the bean.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}, ensuring that each {@code EFXLongBinding} has a valid bean reference.
     */
    public static EFXLongBinding create(LongBinding binding, Object bean) {
        return new EFXLongBinding(binding, bean);
    }

    /**
     * The bean associated with the {@link LongBinding} in this {@code EFXLongBinding}. The bean represents the underlying
     * model or object that influences the value of the {@code LongBinding}. It provides a contextual link between the binding and the
     * part of the application state it represents or observes.
     */
    private final Object bean;

    /**
     * The {@link LongBinding} instance that is part of this association. This binding encapsulates a computed long value that, when
     * observed, reflects changes in the application state, often based on the state of the {@code bean}. It is the core functional
     * component of the association, providing the dynamic link between the application state and the UI or other dependent logic.
     */
    private final LongBinding binding;

    /**
     * Private constructor to instantiate a {@code EFXLongBinding}. Called internally by the
     * {@link #create(LongBinding, Object)} factory method, it initializes the association with a specific {@code LongBinding} and
     * bean, performing a null check on the bean to ensure its validity.
     *
     * @param binding
     *         the {@code LongBinding} to associate.
     * @param bean
     *         the bean object related to the binding.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}.
     */
    private EFXLongBinding(LongBinding binding, Object bean) {
        checkBeanIsNotNull(bean, EFXLongBinding.class);
        this.binding = binding;
        this.bean = bean;
    }

    /**
     * Retrieves the {@link LongBinding} associated with this {@code EFXLongBinding}.
     *
     * @return the {@code LongBinding} instance associated with this association.
     */
    public LongBinding getBinding() {
        return this.binding;
    }

    /**
     * Retrieves the bean associated with the {@code LongBinding}. The bean represents the underlying property or object that the
     * {@code LongBinding} depends on.
     *
     * @return the bean object associated with the {@code LongBinding}.
     */
    public Object getBean() {
        return this.bean;
    }

    /**
     * Generates a string representation of this {@code EFXLongBinding}, including the binding's and bean's string
     * representations.
     *
     * @return a string representation of this {@code EFXLongBinding}.
     */
    @Override
    public String toString() {
        return String.format("LongBinding{%s}, Bean{%s}", this.binding.toString(), this.bean.toString());
    }
}
