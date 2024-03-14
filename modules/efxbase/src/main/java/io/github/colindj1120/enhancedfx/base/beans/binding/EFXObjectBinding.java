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

import io.github.colindj1120.enhancedfx.base.beans.binding.base.ObjectExpressionFunctions;
import io.github.colindj1120.enhancedfx.base.beans.binding.base.EFXBinding;
import javafx.beans.binding.ObjectBinding;

/**
 * {@code EFXObjectBinding} is a generic class that establishes a robust linkage between an {@link ObjectBinding} of type
 * {@code T} and its associated bean object. By implementing the {@link EFXBinding} interface, it offers a structured approach
 * to managing bindings that represent complex data types or objects within an application.
 *
 * <p>
 * This class also implements {@link ObjectExpressionFunctions} which provide they functionality to access all the StringBinding
 * functions directly.
 * </p>
 *
 * <p>
 * This class is part of the EnhancedFX binding associations framework, designed to facilitate the creation, management, and
 * utilization of bindings within JavaFX applications. It extends the capabilities of object bindings by integrating them with
 * associated beans, thereby enhancing data handling and UI updates based on changes in the application's state.
 * </p>
 *
 * <p>
 * <h2>Capabilities:</h2>
 * <ul>
 *      <li>Creation of {@code EFXObjectBinding} instances through a static factory method, promoting a consistent and
 *      type-safe method of associating object bindings with their respective beans.</li>
 *      <li>Implementation of {@code ObjectExpressionFunctions}, enabling advanced operations and manipulations on the associated
 *      {@code ObjectBinding}.</li>
 *      <li>Ensures a clear and maintainable link between the UI elements or application logic and the underlying data model
 *      represented by the bean.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Usage Example:</h2>
 * <pre>
 * Person person = new Person("John Doe", 30);
 * ObjectProperty<Person> personProperty = new SimpleObjectProperty<>(person);
 *
 * ObjectBinding<String> nameBinding = Bindings.selectString(personProperty, "name");
 * EFXObjectBinding<String> nameBindingAssoc = EFXObjectBinding.create(nameBinding, person);
 *
 * // Use nameBindingAssoc for further manipulation or binding to UI components, ensuring the link between the binding and its
 * // associated bean is maintained and utilized effectively.
 * </pre>
 * </p>
 *
 * <p>
 * {@code EFXObjectBinding} serves as a fundamental tool in developing data-driven JavaFX applications, offering a
 * streamlined mechanism for linking object bindings to their data sources. It is particularly useful in scenarios where the
 * application logic or UI components need to react dynamically to changes in complex data structures or objects.
 * </p>
 *
 * @param <T>
 *         The type of Object that is being used.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXBinding
 * @see ObjectExpressionFunctions
 */
public class EFXObjectBinding<T> implements EFXBinding<T>, ObjectExpressionFunctions<T> {
    /**
     * Creates a new instance of {@code EFXObjectBinding} with the specified {@link ObjectBinding} and bean. This factory
     * method encapsulates the process of associating an {@code ObjectBinding} of type {@code T} with its corresponding bean object,
     * offering a streamlined approach to binding management within the application.
     *
     * @param <T>
     *         The type parameter of the {@code ObjectBinding} and the association.
     * @param binding
     *         The {@code ObjectBinding} to be associated. Represents a dynamic value that changes in response to the underlying bean's
     *         state.
     * @param bean
     *         The bean object associated with the {@code ObjectBinding}. Typically, it represents the data model or specific property
     *         that the binding observes or depends upon.
     *
     * @return An instance of {@code EFXObjectBinding} that links the provided {@code ObjectBinding} with the specified bean,
     *         facilitating a clear and maintainable connection between the application's data model and UI logic.
     *
     * @throws IllegalArgumentException
     *         If the bean is {@code null}, ensuring that every {@code EFXObjectBinding} is reliably connected to a valid bean
     *         object.
     */
    public static <T> EFXObjectBinding<T> create(ObjectBinding<T> binding, Object bean) {
        return new EFXObjectBinding<>(binding, bean);
    }

    /**
     * The bean object associated with this {@code EFXObjectBinding}. It typically represents the data model or property that
     * influences the value of the associated {@code ObjectBinding}. This association between the bean and the binding allows changes
     * in the bean's state to be reflected in the binding, enabling reactive UI updates or other logic to respond to data changes.
     */
    private final Object bean;

    /**
     * The {@code ObjectBinding} of type {@code T} that is part of this association. This binding encapsulates a computed value that
     * reacts to changes in the application's state, often influenced by the associated bean. It provides a dynamic link between the
     * application's data model and UI elements or other logic that depends on this data, facilitating a responsive and interactive
     * user experience.
     */
    private final ObjectBinding<T> binding;

    /**
     * Constructs an {@code EFXObjectBinding} with a given {@code ObjectBinding} and bean. This constructor ensures the
     * association between an object binding of type {@code T} and its corresponding bean object, establishing a link between the
     * binding and the property or object it observes or depends upon.
     * <p>
     * The bean represents the underlying model or entity that influences the binding's value, serving as a context for the binding
     * within the application.
     * </p>
     *
     * @param binding
     *         the {@link ObjectBinding} to be associated with the bean. It encapsulates a computed value that reflects changes in the
     *         application's state related to the bean.
     * @param bean
     *         the bean object that the binding is associated with. It typically represents the property or object that the binding
     *         observes or depends upon.
     *
     * @throws IllegalArgumentException
     *         if the bean is {@code null}, ensuring that each association has a valid bean reference.
     */
    private EFXObjectBinding(ObjectBinding<T> binding, Object bean) {
        checkBeanIsNotNull(bean, EFXObjectBinding.class);
        this.binding = binding;
        this.bean = bean;
    }

    /**
     * Retrieves the {@link ObjectBinding} associated with this {@code EFXObjectBinding}.
     *
     * @return the {@code ObjectBinding} instance, encapsulating a computed value that reflects changes in the application state
     *         related to the associated bean.
     */
    @Override
    public ObjectBinding<T> getBinding() {
        return this.binding;
    }

    /**
     * Retrieves the bean associated with this {@code EFXObjectBinding}. The bean represents the underlying model or object
     * that influences the binding's computed value.
     *
     * @return the bean object related to the {@code ObjectBinding}.
     */
    @Override
    public Object getBean() {
        return this.bean;
    }

    /**
     * Provides a string representation of this {@code EFXObjectBinding}, including its binding's and bean's string
     * representations for easier debugging and logging.
     *
     * @return a string representation of the {@code EFXObjectBinding}, detailing the binding and its associated bean.
     */
    @Override
    public String toString() {
        return String.format("ObjectBinding{%s}, Bean{%s}", this.binding.toString(), this.bean.toString());
    }

}
