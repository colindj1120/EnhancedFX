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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.bindingfunctions.BindingFunctions;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.ObjectExpressionFunctions;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;
import javafx.beans.binding.ObjectBinding;

/**
 * {@code EFXObjectBinding} is a specialized binding class that wraps an {@link ObjectBinding} of type {@code T}, providing additional functionalities as defined by {@link ObjectExpressionFunctions} and
 * {@link BindingFunctions} interfaces. It is designed to facilitate the enhanced observation and manipulation of {@code ObjectBinding} instances within the EnhancedFX framework, particularly for objects.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Encapsulates an {@code ObjectBinding<T>} instance, enabling advanced manipulation and observation.</li>
 *     <li>Implements additional object expression and binding functional interfaces for extended operations on object bindings.</li>
 *     <li>Supports associating a bean with the binding for context and manageability, enhancing the integration with JavaFX's property system.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * ObjectProperty<String> property = new SimpleObjectProperty<>("Initial");
 * ObjectBinding<String> objectBinding = Bindings.select(property, "length");
 * EFXObjectBinding<String> efxObjectBinding = EFXObjectBinding.create(myBean, objectBinding);
 *
 * efxObjectBinding.addListener((observable, oldValue, newValue) -> {
 *     System.out.println("New value length: " + newValue);
 * });
 * }</pre>
 *
 * <p>This example demonstrates creating an {@code EFXObjectBinding} for a string length binding. It shows how {@code EFXObjectBinding} can be used to observe changes in properties of complex objects,
 * facilitating more sophisticated property binding scenarios.</p>
 *
 * @param <T>
 *         The type of the object being bound.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see ObjectBinding
 * @see ObjectExpressionFunctions
 * @see BindingFunctions
 */
public class EFXObjectBinding<T> extends EFXBinding<ObjectBinding<T>> implements ObjectExpressionFunctions<T, ObjectBinding<T>>, BindingFunctions<T, ObjectBinding<T>> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create an instance of {@code EFXObjectBinding}.
     *
     * <p>This method provides a convenient way to instantiate {@code EFXObjectBinding} objects by wrapping a given {@link ObjectBinding} with an associated bean.</p>
     *
     * @param <T>
     *         The type of the object being bound.
     * @param bean
     *         The bean associated with the {@code ObjectBinding}, used for contextual information.
     * @param binding
     *         The {@link ObjectBinding} to be wrapped by the {@code EFXObjectBinding}.
     *
     * @return An instance of {@code EFXObjectBinding} wrapping the provided {@code ObjectBinding}.
     */
    public static <T> EFXObjectBinding<T> create(Object bean, ObjectBinding<T> binding) {
        return new EFXObjectBinding<>(bean, binding);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXObjectBinding}.
     *
     * <p>This constructor is private to enforce the usage of the static factory method {@link #create(Object, ObjectBinding)} for creating instances, ensuring consistency and clarity in how instances are
     * instantiated.</p>
     *
     * @param bean
     *         The bean associated with this {@code ObjectBinding}.
     * @param binding
     *         The {@link ObjectBinding} to be wrapped.
     */
    protected EFXObjectBinding(Object bean, ObjectBinding<T> binding) {
        super(bean, binding);
        this.binding = binding;
    }

    //endregion Constructor

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    @Override
    public ObjectBinding<T> getObservableValue() {
        return this.binding;
    }

    @Override
    public void setObservableValue(ObjectBinding<T> value) {
        this.binding = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EFXObjectBinding<?> efxBinding) {
            return super.equals(efxBinding);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("""
                             %s {
                                %s
                             }
                             """, getClass().getSimpleName(), EFXStringUtils.addSpacesToEveryLine(super.toString(), EFXStringUtils.IndentationLevel.LEVEL_1));
    }

    //endregion Overridden Methods
}
