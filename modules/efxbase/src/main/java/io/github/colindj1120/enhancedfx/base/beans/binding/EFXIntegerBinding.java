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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.IntegerExpressionFunctions;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.NumberExpressionFunctions;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;
import javafx.beans.binding.IntegerBinding;

/**
 * {@code EFXIntegerBinding} is a specialized binding class that wraps a {@link IntegerBinding} and provides additional functionalities defined by {@link IntegerExpressionFunctions} and {@link BindingFunctions}
 * interfaces.
 *
 * <p>It allows for enhanced manipulation and observation of {@code IntegerBinding} objects within the EnhancedFX framework.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Encapsulates a {@link IntegerBinding} instance for advanced manipulation.</li>
 *     <li>Implements additional integer expression and binding functional interfaces for extended operations.</li>
 *     <li>Facilitates the creation of enhanced integer bindings with associated beans for improved context and manageability.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * IntegerProperty property = new SimpleIntegerProperty(2);
 * IntegerBinding binding = property.add(3);
 * EFXIntegerBinding efxBinding = EFXIntegerBinding.create(myBean, binding);
 *
 * efxBinding.addListener((observable, oldValue, newValue) -> {
 *     System.out.println("New value: " + newValue);
 * });
 * }</pre>
 *
 * <p>In this example, an {@code EFXIntegerBinding} is created by wrapping a {@link IntegerBinding} with a negation operation. It then listens for changes to the integer value, demonstrating how {@code
 * EFXIntegerBinding} can be used to enhance and observe integer bindings.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see IntegerBinding
 * @see IntegerExpressionFunctions
 * @see BindingFunctions
 */
public class EFXIntegerBinding extends EFXBinding<IntegerBinding> implements IntegerExpressionFunctions<IntegerBinding>, NumberExpressionFunctions<IntegerBinding> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create an instance of {@code EFXIntegerBinding}.
     *
     * <p>This method provides a convenient way to instantiate {@code EFXIntegerBinding} objects with a specific {@link IntegerBinding} and an associated bean.</p>
     *
     * @param bean
     *         The bean associated with the {@code IntegerBinding}. This can be used for contextual information or binding management.
     * @param binding
     *         The {@link IntegerBinding} to be encapsulated by the {@code EFXIntegerBinding}.
     *
     * @return An instance of {@code EFXIntegerBinding} wrapping the provided {@code IntegerBinding}.
     */
    public static EFXIntegerBinding create(Object bean, IntegerBinding binding) {
        return new EFXIntegerBinding(bean, binding);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXIntegerBinding}.
     *
     * <p>This constructor is protected to enforce the usage of the static factory method {@link #create(Object, IntegerBinding)} for instance creation, providing a clear and consistent way to instantiate
     * {@code EFXIntegerBinding}.</p>
     *
     * @param bean
     *         The bean associated with this {@code IntegerBinding}.
     * @param binding
     *         The {@link IntegerBinding} to be encapsulated.
     */
    protected EFXIntegerBinding(Object bean, IntegerBinding binding) {
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
    public IntegerBinding getObservableValue() {
        return this.binding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableValue(IntegerBinding value) {
        this.binding = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EFXIntegerBinding efxBinding) {
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
