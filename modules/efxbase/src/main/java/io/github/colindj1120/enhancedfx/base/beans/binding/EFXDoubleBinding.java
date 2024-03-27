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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.bindingfunctions.NumberBindingFunctions;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.DoubleExpressionFunctions;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;
import javafx.beans.binding.DoubleBinding;

/**
 * {@code EFXDoubleBinding} is a specialized binding class that wraps a {@link DoubleBinding} and provides additional functionalities defined by {@link DoubleExpressionFunctions} and {@link BindingFunctions}
 * interfaces.
 *
 * <p>It allows for enhanced manipulation and observation of {@code DoubleBinding} objects within the EnhancedFX framework.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Encapsulates a {@link DoubleBinding} instance for advanced manipulation.</li>
 *     <li>Implements additional double expression and binding functional interfaces for extended operations.</li>
 *     <li>Facilitates the creation of enhanced double bindings with associated beans for improved context and manageability.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * DoubleProperty property = new SimpleDoubleProperty(2.0);
 * DoubleBinding binding = property.add(3.0);
 * EFXDoubleBinding efxBinding = EFXDoubleBinding.create(myBean, binding);
 *
 * efxBinding.addListener((observable, oldValue, newValue) -> {
 *     System.out.println("New value: " + newValue);
 * });
 * }</pre>
 *
 * <p>In this example, an {@code EFXDoubleBinding} is created by wrapping a {@link DoubleBinding} with a negation operation. It then listens for changes to the double value, demonstrating how {@code
 * EFXDoubleBinding} can be used to enhance and observe double bindings.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see DoubleBinding
 * @see DoubleExpressionFunctions
 * @see BindingFunctions
 */
public class EFXDoubleBinding extends EFXBinding<DoubleBinding> implements DoubleExpressionFunctions<DoubleBinding>, NumberBindingFunctions<DoubleBinding> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create an instance of {@code EFXDoubleBinding}.
     *
     * <p>This method provides a convenient way to instantiate {@code EFXDoubleBinding} objects with a specific {@link DoubleBinding} and an associated bean.</p>
     *
     * @param bean
     *         The bean associated with the {@code DoubleBinding}. This can be used for contextual information or binding management.
     * @param binding
     *         The {@link DoubleBinding} to be encapsulated by the {@code EFXDoubleBinding}.
     *
     * @return An instance of {@code EFXDoubleBinding} wrapping the provided {@code DoubleBinding}.
     */
    public static EFXDoubleBinding create(Object bean, DoubleBinding binding) {
        return new EFXDoubleBinding(bean, binding);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXDoubleBinding}.
     *
     * <p>This constructor is protected to enforce the usage of the static factory method {@link #create(Object, DoubleBinding)} for instance creation, providing a clear and consistent way to instantiate
     * {@code EFXDoubleBinding}.</p>
     *
     * @param bean
     *         The bean associated with this {@code DoubleBinding}.
     * @param binding
     *         The {@link DoubleBinding} to be encapsulated.
     */
    protected EFXDoubleBinding(Object bean, DoubleBinding binding) {
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
    public DoubleBinding getObservableValue() {
        return this.binding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableValue(DoubleBinding value) {
        this.binding = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EFXDoubleBinding efxBinding) {
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
