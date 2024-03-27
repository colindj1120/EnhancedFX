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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.bindingfunctions.NumberBindingFunctions;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.NumberExpressionFunctions;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;
import javafx.beans.binding.NumberBinding;

/**
 * {@code EFXNumberBinding} is a specialized binding class that wraps a {@link NumberBinding} and provides additional functionalities as defined by {@link NumberExpressionFunctions} and
 * {@link NumberBindingFunctions} interfaces. This class allows for the enhanced observation and manipulation of {@code NumberBinding} objects within the EnhancedFX framework.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Encapsulates a {@link NumberBinding} instance, enabling advanced manipulation.</li>
 *     <li>Implements additional number expression and number binding functionalities for extended operations.</li>
 *     <li>Supports associating a bean with the binding for context and manageability.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * IntegerProperty integerProperty = new SimpleIntegerProperty(10);
 * NumberBinding squaredValueBinding = Bindings.multiply(integerProperty, integerProperty);
 * EFXNumberBinding efxNumberBinding = EFXNumberBinding.create(myBean, squaredValueBinding);
 *
 * efxNumberBinding.addListener((observable, oldValue, newValue) -> {
 *     System.out.println("New squared value: " + newValue);
 * });
 * }</pre>
 *
 * <p>In this example, an {@code EFXNumberBinding} is created to wrap a {@code NumberBinding} that represents the square of an {@code IntegerProperty}. The {@code EFXNumberBinding} then listens for changes
 * to this squared value, illustrating how it can be used to observe and respond to changes in numerical bindings.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see NumberBinding
 * @see NumberExpressionFunctions
 * @see NumberBindingFunctions
 */
public class EFXNumberBinding extends EFXBinding<NumberBinding> implements NumberExpressionFunctions<NumberBinding>, NumberBindingFunctions<NumberBinding> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create an instance of {@code EFXNumberBinding}.
     *
     * <p>This method provides a convenient way to instantiate {@code EFXNumberBinding} objects by encapsulating a given {@link NumberBinding} with an associated bean.</p>
     *
     * @param bean
     *         The bean associated with the {@code NumberBinding}, used for contextual information.
     * @param binding
     *         The {@link NumberBinding} to be wrapped by the {@code EFXNumberBinding}.
     *
     * @return An instance of {@code EFXNumberBinding} wrapping the provided {@code NumberBinding}.
     */
    public static EFXNumberBinding create(Object bean, NumberBinding binding) {
        return new EFXNumberBinding(bean, binding);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXNumberBinding}.
     *
     * <p>This constructor is protected to enforce the usage of the static factory method {@link #create(Object, NumberBinding)} for creating instances, ensuring consistency and clarity in how instances are
     * instantiated.</p>
     *
     * @param bean
     *         The bean associated with this {@code NumberBinding}.
     * @param binding
     *         The {@link NumberBinding} to be wrapped.
     */
    protected EFXNumberBinding(Object bean, NumberBinding binding) {
        super(bean, binding);
        this.binding = binding;
    }

    //endregion Constructor

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NumberBinding getObservableValue() {
        return this.binding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableValue(NumberBinding value) {
        this.binding = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EFXNumberBinding efxBinding) {
            return super.equals(efxBinding);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
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
