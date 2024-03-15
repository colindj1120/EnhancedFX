package io.github.colindj1120.enhancedfx.base.factory.property;

import io.github.colindj1120.enhancedfx.base.factory.property.base.PropertyConfiguratorBase;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;

/**
 * A concrete implementation of the {@link PropertyConfiguratorBase} for JavaFX {@link Property} objects, offering a fluent API for
 * configuring properties. This class enables applications to easily manipulate property values, add listeners, and manage bindings in
 * a chainable and readable manner. It simplifies the process of working with properties by abstracting away the boilerplate code
 * associated with property configuration.
 * <p>
 * The {@code PropertyConfigurator} supports operations such as adding change and invalidation listeners, binding properties
 * uni-directionally and bidirectionally, and setting property values directly. This utility class is designed to enhance the JavaFX
 * property system's flexibility and usability, promoting a more declarative approach to UI development.
 * </p>
 * <p>
 * <h2>Example usage:</h2>
 * <pre>
 * PropertyConfigurator.create(someProperty)
 *     .addChangeListener((obs, oldValue, newValue) -> System.out.println("Value changed!"))
 *     .setValue(newValue)
 *     .bind(anotherProperty);
 * </pre>
 * This example demonstrates how to create a configurator for a property, add a change listener, set a value, and bind it to another
 * property, all in a fluent and intuitive manner.
 * </p>
 *
 * @param <T>
 *         the type of the property value that this configurator manages
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Property
 * @see PropertyConfiguratorBase
 */
@SuppressWarnings("UnusedReturnValue")
public class PropertyConfigurator<T> extends PropertyConfiguratorBase<T> {
    /**
     * Creates and returns a new instance of {@link PropertyConfigurator} for the specified {@link Property}. This method serves as a
     * factory for generating property configurators, facilitating the fluent configuration of JavaFX properties. By returning a
     * {@link PropertyConfigurator}, it allows for chaining multiple configuration operations in a concise and readable manner.
     *
     * @param <T>
     *         the type of the property value that the configurator will manage
     * @param property
     *         the {@link Property} to be configured by the newly created {@code PropertyConfigurator}
     *
     * @return a new instance of {@link PropertyConfigurator} initialized with the given {@code Property}
     */
    public static <T> PropertyConfigurator<T> create(Property<T> property) {
        return new PropertyConfigurator<>(property);
    }

    /**
     * Constructs a {@code PropertyConfigurator} with the specified {@link Property}. This constructor initializes the configurator
     * with a property, setting up the base environment for further property configuration tasks. It serves as the foundational setup
     * step for the configurator, enabling it to perform various configuration actions on the given property.
     * <p>
     * This constructor is protected to ensure that instances of {@code PropertyConfigurator} are created through the designated
     * factory methods, maintaining consistency in how configurators are instantiated and managed.
     * </p>
     *
     * @param property
     *         the {@link Property} that this configurator will manage and configure. This property represents the target for all
     *         subsequent configuration operations performed by this configurator.
     */
    protected PropertyConfigurator(Property<T> property) {
        super(property);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PropertyConfigurator<T> addChangeListener(ChangeListener<? super T> listener) {
        this.property.addListener(listener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PropertyConfigurator<T> addInvalidationListener(InvalidationListener invalidationListener) {
        this.property.addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PropertyConfigurator<T> bind(Property<T> otherProperty) {
        property.bind(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PropertyConfigurator<T> unbind() {
        property.unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PropertyConfigurator<T> bindBidirectional(Property<T> otherProperty) {
        property.bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PropertyConfigurator<T> unbindBidirectional(Property<T> otherProperty) {
        property.unbindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PropertyConfigurator<T> setValue(T value) {
        property.setValue(value);
        return this;
    }
}
