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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.BooleanExpressionFunctions;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;
import javafx.beans.binding.BooleanBinding;

/**
 * {@code EFXBooleanBinding} is a specialized binding class that wraps a {@link BooleanBinding} and provides additional functionalities defined by {@link BooleanExpressionFunctions} and {@link BindingFunctions}
 * interfaces.
 *
 * <p>It allows for enhanced manipulation and observation of {@code BooleanBinding} objects within the EnhancedFX framework.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Encapsulates a {@link BooleanBinding} instance for advanced manipulation.</li>
 *     <li>Implements additional boolean expression and binding functional interfaces for extended operations.</li>
 *     <li>Facilitates the creation of enhanced boolean bindings with associated beans for improved context and manageability.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * BooleanProperty property = new SimpleBooleanProperty(false);
 * BooleanBinding binding = property.not();
 * EFXBooleanBinding efxBinding = EFXBooleanBinding.create(myBean, binding);
 *
 * efxBinding.addListener((observable, oldValue, newValue) -> {
 *     System.out.println("New value: " + newValue);
 * });
 * }</pre>
 *
 * <p>In this example, an {@code EFXBooleanBinding} is created by wrapping a {@link BooleanBinding} with a negation operation. It then listens for changes to the boolean value, demonstrating how {@code
 * EFXBooleanBinding} can be used to enhance and observe boolean bindings.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see BooleanBinding
 * @see BooleanExpressionFunctions
 * @see BindingFunctions
 */
public class EFXBooleanBinding extends EFXBinding<BooleanBinding> implements BooleanExpressionFunctions<BooleanBinding>, BindingFunctions<Boolean, BooleanBinding> {

    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create an instance of {@code EFXBooleanBinding}.
     *
     * <p>This method provides a convenient way to instantiate {@code EFXBooleanBinding} objects with a specific {@link BooleanBinding} and an associated bean.</p>
     *
     * @param bean
     *         The bean associated with the {@code BooleanBinding}. This can be used for contextual information or binding management.
     * @param binding
     *         The {@link BooleanBinding} to be encapsulated by the {@code EFXBooleanBinding}.
     *
     * @return An instance of {@code EFXBooleanBinding} wrapping the provided {@code BooleanBinding}.
     */
    public static EFXBooleanBinding create(Object bean, BooleanBinding binding) {
        return new EFXBooleanBinding(bean, binding);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXBooleanBinding}.
     *
     * <p>This constructor is protected to enforce the usage of the static factory method {@link #create(Object, BooleanBinding)} for instance creation, providing a clear and consistent way to instantiate
     * {@code EFXBooleanBinding}.</p>
     *
     * @param bean
     *         The bean associated with this {@code BooleanBinding}.
     * @param binding
     *         The {@link BooleanBinding} to be encapsulated.
     */
    protected EFXBooleanBinding(Object bean, BooleanBinding binding) {
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
    public BooleanBinding getObservableValue() {
        return this.binding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableValue(BooleanBinding value) {
        this.binding = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EFXBooleanBinding efxBinding) {
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
