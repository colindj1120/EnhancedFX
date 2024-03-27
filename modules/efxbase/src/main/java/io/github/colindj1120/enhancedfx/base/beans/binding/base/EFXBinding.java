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

import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.beans.binding.Binding;

import java.util.Objects;
import java.util.Optional;

/**
 * Provides a foundational abstract class for EnhancedFX bindings, encapsulating common functionality and utilities for managing {@link Binding} instances. This class serves as the base for creating custom
 * binding implementations within the EnhancedFX framework, allowing for extended control and manipulation of bindings associated with JavaFX properties.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Associates a bean object with the binding for contextual information and management.</li>
 *     <li>Encapsulates a {@link Binding} instance, offering a framework for observing changes to JavaFX properties.</li>
 *     <li>Provides abstract methods for retrieving and setting the observable value, enabling subclass implementations to define specific behaviors.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * public class CustomStringBinding extends EFXBinding<StringBinding> {
 *     public CustomStringBinding(Object bean, StringBinding binding) {
 *         super(bean, binding);
 *     }
 *
 *     @Override
 *     public StringBinding getObservableValue() {
 *         return binding;
 *     }
 *
 *     @Override
 *     public void setObservableValue(StringBinding value) {
 *         this.binding = value;
 *     }
 * }
 * }</pre>
 *
 * <p>This example demonstrates how to extend {@code EFXBinding} to create a custom binding implementation for a {@code StringBinding}. It highlights the requirement to implement methods for accessing and
 * modifying the encapsulated {@code StringBinding}.</p>
 *
 * @param <T>
 *         the specific type of {@link Binding} being managed
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Binding
 */
public abstract class EFXBinding<T extends Binding<?>> {
    protected final Object bean;
    protected       T      binding;

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXBinding} with the specified bean and binding.
     *
     * @param bean
     *         the bean associated with this binding, providing context or a reference to the owning object
     * @param binding
     *         the {@link Binding} instance to be managed by this {@code EFXBinding}
     */
    protected EFXBinding(final Object bean, T binding) {
        EFXObjectUtils.isNotNull(bean, () -> String.format("The bean object must not be null in %s", this.getClass()
                                                                                                         .getSimpleName()));
        this.bean = bean;
        this.binding = binding;
    }

    //endregion Constructor

    //region Abstract Methods
    //*****************************************************************
    // Abstract Methods
    //*****************************************************************

    /**
     * Retrieves the observable value managed by this {@code EFXBinding}.
     *
     * @return the current {@link Binding} instance
     */
    public abstract T getObservableValue();

    /**
     * Sets the observable value for this {@code EFXBinding}.
     *
     * @param value
     *         the new {@link Binding} instance to manage
     */
    public abstract void setObservableValue(T value);

    //endregion Abstract Methods

    //region Concrete Methods
    //*****************************************************************
    // Concrete Methods
    //*****************************************************************

    /**
     * Gets the bean associated with this binding.
     *
     * @return the bean object
     */
    public Object getBean() {
        return bean;
    }

    //endregion Concrete Methods

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(bean);
    }

    /**
     * Determines whether the specified object is equal to this {@code EFXBinding} instance. Equality is based on a comparison of the bean objects associated with the bindings and the observable values they
     * manage. This method overrides the {@link Object#equals(Object)} method to provide a deep comparison suited to the enhanced binding functionalities within the EnhancedFX framework.
     *
     * <p>The equality check involves the following steps:</p>
     * <ol>
     *     <li>Verifying that the other object is not {@code null} and is an instance of {@code EFXBinding}.</li>
     *     <li>Comparing the bean objects for equality. The bean object provides context for the binding and is considered a part of its identity.</li>
     *     <li>Ensuring the observable value managed by this binding is of the same type as that managed by the other binding. This is a type-safety check to prevent class cast exceptions.</li>
     *     <li>Comparing the observable values for equality. The actual binding instances (observable values) are compared using their {@code equals} method.</li>
     * </ol>
     *
     * <p>This method aims to provide a comprehensive equality check that accounts for both the contextual information associated with the binding (the bean) and the functional aspect of the binding (the
     * observable value).</p>
     *
     * @param obj
     *         the reference object with which to compare
     *
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return Optional.ofNullable(obj)
                       .filter(EFXBinding.class::isInstance)
                       .map(EFXBinding.class::cast)
                       .filter(other -> Objects.equals(this.bean, other.getBean()))
                       .filter(other -> binding.getClass()
                                               .isInstance(other.getObservableValue()))
                       .map(other -> Objects.equals(this.binding, other.getObservableValue()))
                       .orElse(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String bindingClassName = binding.getClass()
                                         .getSimpleName();
        return String.format("""
                             EFXBinding {
                                Bean=%s
                                %s=%s
                             }
                             """, bean.toString(), bindingClassName, binding.toString());
    }

    //endregion Overridden Methods
}
