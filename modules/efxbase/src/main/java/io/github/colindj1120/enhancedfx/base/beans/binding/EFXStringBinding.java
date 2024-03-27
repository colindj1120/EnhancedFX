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
import io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions.StringExpressionFunctions;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;
import javafx.beans.binding.StringBinding;

/**
 * {@code EFXStringBinding} is a specialized binding class that wraps a {@link StringBinding} and provides additional functionalities defined by {@link StringExpressionFunctions} and {@link BindingFunctions}
 * interfaces.
 *
 * <p>It allows for enhanced manipulation and observation of {@code StringBinding} objects within the EnhancedFX framework.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Encapsulates a {@link StringBinding} instance for advanced manipulation.</li>
 *     <li>Implements additional string expression and binding functional interfaces for extended operations.</li>
 *     <li>Facilitates the creation of enhanced string bindings with associated beans for improved context and manageability.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * StringProperty property = new SimpleStringProperty("Hello");
 * StringBinding binding = property.concat(" World");
 * EFXStringBinding efxBinding = EFXStringBinding.create(myBean, binding);
 *
 * efxBinding.addListener((observable, oldValue, newValue) -> {
 *     System.out.println("New value: " + newValue);
 * });
 * }</pre>
 *
 * <p>In this example, an {@code EFXStringBinding} is created by wrapping a {@link StringBinding} with a negation operation. It then listens for changes to the string value, demonstrating how {@code
 * EFXStringBinding} can be used to enhance and observe string bindings.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see StringBinding
 * @see StringExpressionFunctions
 * @see BindingFunctions
 */
public class EFXStringBinding extends EFXBinding<StringBinding> implements StringExpressionFunctions<StringBinding>, BindingFunctions<String, StringBinding> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create an instance of {@code EFXStringBinding}.
     *
     * <p>This method provides a convenient way to instantiate {@code EFXStringBinding} objects with a specific {@link StringBinding} and an associated bean.</p>
     *
     * @param bean
     *         The bean associated with the {@code StringBinding}. This can be used for contextual information or binding management.
     * @param binding
     *         The {@link StringBinding} to be encapsulated by the {@code EFXStringBinding}.
     *
     * @return An instance of {@code EFXStringBinding} wrapping the provided {@code StringBinding}.
     */
    public static EFXStringBinding create(Object bean, StringBinding binding) {
        return new EFXStringBinding(bean, binding);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXStringBinding}.
     *
     * <p>This constructor is protected to enforce the usage of the static factory method {@link #create(Object, StringBinding)} for instance creation, providing a clear and consistent way to instantiate
     * {@code EFXStringBinding}.</p>
     *
     * @param bean
     *         The bean associated with this {@code StringBinding}.
     * @param binding
     *         The {@link StringBinding} to be encapsulated.
     */
    protected EFXStringBinding(Object bean, StringBinding binding) {
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
    public StringBinding getObservableValue() {
        return this.binding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableValue(StringBinding value) {
        this.binding = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EFXStringBinding efxBinding) {
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
