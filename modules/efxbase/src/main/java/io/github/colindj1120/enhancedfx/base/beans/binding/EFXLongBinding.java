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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.LongExpressionFunctions;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.NumberExpressionFunctions;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;
import javafx.beans.binding.LongBinding;

/**
 * {@code EFXLongBinding} is a specialized binding class that wraps a {@link LongBinding} and provides additional functionalities defined by {@link LongExpressionFunctions} and {@link BindingFunctions}
 * interfaces.
 *
 * <p>It allows for enhanced manipulation and observation of {@code LongBinding} objects within the EnhancedFX framework.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Encapsulates a {@link LongBinding} instance for advanced manipulation.</li>
 *     <li>Implements additional long expression and binding functional interfaces for extended operations.</li>
 *     <li>Facilitates the creation of enhanced long bindings with associated beans for improved context and manageability.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * LongProperty property = new SimpleLongProperty(2);
 * LongBinding binding = property.add(3);
 * EFXLongBinding efxBinding = EFXLongBinding.create(myBean, binding);
 *
 * efxBinding.addListener((observable, oldValue, newValue) -> {
 *     System.out.println("New value: " + newValue);
 * });
 * }</pre>
 *
 * <p>In this example, an {@code EFXLongBinding} is created by wrapping a {@link LongBinding} with a negation operation. It then listens for changes to the long value, demonstrating how {@code
 * EFXLongBinding} can be used to enhance and observe long bindings.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see LongBinding
 * @see LongExpressionFunctions
 * @see BindingFunctions
 */
public class EFXLongBinding extends EFXBinding<LongBinding> implements LongExpressionFunctions<LongBinding>, NumberExpressionFunctions<LongBinding> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create an instance of {@code EFXLongBinding}.
     *
     * <p>This method provides a convenient way to instantiate {@code EFXLongBinding} objects with a specific {@link LongBinding} and an associated bean.</p>
     *
     * @param bean
     *         The bean associated with the {@code LongBinding}. This can be used for contextual information or binding management.
     * @param binding
     *         The {@link LongBinding} to be encapsulated by the {@code EFXLongBinding}.
     *
     * @return An instance of {@code EFXLongBinding} wrapping the provided {@code LongBinding}.
     */
    public static EFXLongBinding create(Object bean, LongBinding binding) {
        return new EFXLongBinding(bean, binding);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXLongBinding}.
     *
     * <p>This constructor is protected to enforce the usage of the static factory method {@link #create(Object, LongBinding)} for instance creation, providing a clear and consistent way to instantiate
     * {@code EFXLongBinding}.</p>
     *
     * @param bean
     *         The bean associated with this {@code LongBinding}.
     * @param binding
     *         The {@link LongBinding} to be encapsulated.
     */
    protected EFXLongBinding(Object bean, LongBinding binding) {
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
    public LongBinding getObservableValue() {
        return this.binding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableValue(LongBinding value) {
        this.binding = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EFXLongBinding efxBinding) {
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
