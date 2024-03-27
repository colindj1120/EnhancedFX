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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.FloatExpressionFunctions;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.NumberExpressionFunctions;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;
import javafx.beans.binding.FloatBinding;

/**
 * {@code EFXFloatBinding} is a specialized binding class that wraps a {@link FloatBinding} and provides additional functionalities defined by {@link FloatExpressionFunctions} and {@link BindingFunctions}
 * interfaces.
 *
 * <p>It allows for enhanced manipulation and observation of {@code FloatBinding} objects within the EnhancedFX framework.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Encapsulates a {@link FloatBinding} instance for advanced manipulation.</li>
 *     <li>Implements additional float expression and binding functional interfaces for extended operations.</li>
 *     <li>Facilitates the creation of enhanced float bindings with associated beans for improved context and manageability.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * FloatProperty property = new SimpleFloatProperty(2.0f);
 * FloatBinding binding = property.add(3.0f);
 * EFXFloatBinding efxBinding = EFXFloatBinding.create(myBean, binding);
 *
 * efxBinding.addListener((observable, oldValue, newValue) -> {
 *     System.out.println("New value: " + newValue);
 * });
 * }</pre>
 *
 * <p>In this example, an {@code EFXFloatBinding} is created by wrapping a {@link FloatBinding} with a negation operation. It then listens for changes to the float value, demonstrating how {@code
 * EFXFloatBinding} can be used to enhance and observe float bindings.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see FloatBinding
 * @see FloatExpressionFunctions
 * @see BindingFunctions
 */
public class EFXFloatBinding extends EFXBinding<FloatBinding> implements FloatExpressionFunctions<FloatBinding>, NumberExpressionFunctions<FloatBinding> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create an instance of {@code EFXFloatBinding}.
     *
     * <p>This method provides a convenient way to instantiate {@code EFXFloatBinding} objects with a specific {@link FloatBinding} and an associated bean.</p>
     *
     * @param bean
     *         The bean associated with the {@code FloatBinding}. This can be used for contextual information or binding management.
     * @param binding
     *         The {@link FloatBinding} to be encapsulated by the {@code EFXFloatBinding}.
     *
     * @return An instance of {@code EFXFloatBinding} wrapping the provided {@code FloatBinding}.
     */
    public static EFXFloatBinding create(Object bean, FloatBinding binding) {
        return new EFXFloatBinding(bean, binding);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXFloatBinding}.
     *
     * <p>This constructor is protected to enforce the usage of the static factory method {@link #create(Object, FloatBinding)} for instance creation, providing a clear and consistent way to instantiate
     * {@code EFXFloatBinding}.</p>
     *
     * @param bean
     *         The bean associated with this {@code FloatBinding}.
     * @param binding
     *         The {@link FloatBinding} to be encapsulated.
     */
    protected EFXFloatBinding(Object bean, FloatBinding binding) {
        super(bean, binding);
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
    public FloatBinding getObservableValue() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableValue(FloatBinding value) {
        this.binding = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EFXFloatBinding efxBinding) {
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
