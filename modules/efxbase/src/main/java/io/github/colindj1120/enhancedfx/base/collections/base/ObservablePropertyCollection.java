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
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.base.collections.base;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an abstract foundation for creating observable collection properties in JavaFX applications. This class encapsulates the commonalities of observable collections, such as lists, stacks, and queues,
 * and provides a framework for observing and reacting to changes within these collections.
 *
 * <p>At its core, {@code ObservablePropertyCollection} leverages {@link SimpleListProperty} to wrap an {@link ObservableList}. This setup enables automatic notification of changes to the collection,
 * such as additions or removals of elements. It is designed to integrate seamlessly with the JavaFX property binding and change listening systems, making it suitable for applications that require real-time
 * updates to the UI in response to modifications in the collection.</p>
 *
 * <p>Subclasses of {@code ObservablePropertyCollection} are expected to implement specific collection behaviors (e.g., stack push/pop, queue enqueue/dequeue) while benefiting from the built-in
 * observability and property management features of this class. Additionally, subclasses can define custom reactions to collection changes by setting {@code onElementsAdded} and {@code onElementsRemoved}
 * consumers, which are executed whenever elements are added to or removed from the collection, respectively.</p>
 *
 * <p>This class also provides methods for attaching and detaching listeners that can respond to more granular changes within the collection or to the invalidation of the collection property itself. Such
 * listeners allow developers to implement complex interaction patterns and data-driven UI updates with minimal boilerplate code.</p>
 *
 * <p>By serving as an abstract base, {@code ObservablePropertyCollection} encourages a pattern of composition over inheritance, allowing for flexible extension and customization of collection behaviors
 * while maintaining a strong contract for observability and interaction with the JavaFX property system.</p>
 *
 * @param <T>
 *         the type of elements contained in the collection. This generic type allows {@code ObservablePropertyCollection} to be adapted to various data types, enhancing its versatility across different
 *         application domains.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see SimpleListProperty
 * @see ObservableList
 * @see javafx.beans.value.ChangeListener
 * @see javafx.beans.InvalidationListener
 */
public abstract class ObservablePropertyCollection<T> {
    protected final SimpleListProperty<T>           collectionProperty;
    protected       Consumer<Iterable<? extends T>> onElementsAdded;
    protected       Consumer<Iterable<? extends T>> onElementsRemoved;

    /**
     * Constructs an ObservablePropertyCollection with an empty observable list.
     */
    public ObservablePropertyCollection() {
        collectionProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        collectionProperty.addListener(gettListChangeListener());
    }

    /**
     * Constructs an ObservablePropertyCollection with the specified initial elements.
     *
     * @param initialValue
     *         the initial elements of the collection
     */
    public ObservablePropertyCollection(ObservableList<T> initialValue) {
        collectionProperty = new SimpleListProperty<>(initialValue);
        collectionProperty.addListener(gettListChangeListener());
    }

    /**
     * Constructs an ObservablePropertyCollection associated with a specific bean and property name.
     *
     * @param bean
     *         the bean this property belongs to
     * @param name
     *         the name of the property
     */
    public ObservablePropertyCollection(Object bean, String name) {
        collectionProperty = new SimpleListProperty<>(bean, name, FXCollections.observableArrayList());
        collectionProperty.addListener(gettListChangeListener());
    }

    /**
     * Constructs an ObservablePropertyCollection with the specified initial elements, bean, and property name.
     *
     * @param initialValue
     *         the initial elements of the collection
     * @param bean
     *         the bean this property belongs to
     * @param name
     *         the name of the property
     */
    public ObservablePropertyCollection(ObservableList<T> initialValue, Object bean, String name) {
        collectionProperty = new SimpleListProperty<>(bean, name, initialValue);
        collectionProperty.addListener(gettListChangeListener());
    }

    /**
     * Creates and returns a {@link ListChangeListener} specific to handling changes in a list. This listener is designed to react to additions and removals within the list, executing predefined actions based
     * on these changes.
     *
     * <p>
     * The method generates a listener that iterates over the changes to the list. For each change, it checks whether elements were added or removed. If elements were added and a consumer for handling additions
     * has been defined (via {@code onElementsAdded}), it invokes this consumer with the list of added elements. Similarly, if elements were removed and a consumer for handling removals has been defined (via
     * {@code onElementsRemoved}), it invokes this consumer with the list of removed elements.
     * </p>
     *
     * <p>
     * This approach allows for dynamic and customizable handling of list modifications, making it easier to update UI components or perform other actions in response to changes in the underlying data
     * structure.
     * </p>
     *
     * @return A {@link ListChangeListener} designed to react to additions and removals in the list by executing specified actions.
     */

    @NotNull
    private ListChangeListener<T> gettListChangeListener() {
        return change -> {
            while (change.next()) {
                if (change.wasAdded() && Objects.nonNull(onElementsAdded)) {
                    onElementsAdded.accept(change.getAddedSubList());
                }
                if (change.wasRemoved() && Objects.nonNull(onElementsRemoved)) {
                    onElementsRemoved.accept(change.getRemoved());
                }
            }
        };
    }

    /**
     * Sets a consumer to be called when elements are added to the collection.
     *
     * @param onElementsAdded
     *         a consumer that processes added elements
     */
    public void setOnElementsAdded(Consumer<Iterable<? extends T>> onElementsAdded) {
        this.onElementsAdded = onElementsAdded;
    }

    /**
     * Sets a consumer to be called when elements are removed from the collection.
     *
     * @param onElementsRemoved
     *         a consumer that processes removed elements
     */
    public void setOnElementsRemoved(Consumer<Iterable<? extends T>> onElementsRemoved) {
        this.onElementsRemoved = onElementsRemoved;
    }

    /**
     * Adds a change listener to the collection property.
     *
     * @param listener
     *         a listener that is notified of changes to the collection
     */
    public void addChangeListener(ListChangeListener<? super T> listener) {
        collectionProperty.addListener(listener);
    }

    /**
     * Returns the collection property.
     *
     * @return the SimpleListProperty that wraps the observable list
     */
    public SimpleListProperty<T> collectionProperty() {
        return collectionProperty;
    }

    /**
     * Retrieves the current collection as an ObservableList.
     *
     * @return the observable list wrapped by this property
     */
    public abstract ObservableList<T> getCollection();

    /**
     * Sets the collection to a new ObservableList.
     *
     * @param collection
     *         the new observable list to be set
     */
    public abstract void setCollection(ObservableList<T> collection);

    /**
     * Removes a change listener from the collection property.
     *
     * @param listener
     *         the listener to remove
     */
    public void removeChangeListener(ListChangeListener<? super T> listener) {
        collectionProperty.removeListener(listener);
    }

    /**
     * Adds an invalidation listener to the collection property.
     *
     * @param listener
     *         a listener that is notified when the collection becomes invalid
     */
    public void addInvalidationListener(InvalidationListener listener) {
        collectionProperty.addListener(listener);
    }

    /**
     * Removes an invalidation listener from the collection property.
     *
     * @param listener
     *         the listener to remove
     */
    public void removeInvalidationListener(InvalidationListener listener) {
        collectionProperty.removeListener(listener);
    }

    /**
     * Gets the bean associated with the collection property.
     *
     * @return the bean of the property
     */
    public Object getBean() {
        return collectionProperty.getBean();
    }

    /**
     * Gets the name of the collection property.
     *
     * @return the name of the property
     */
    public String getName() {
        return collectionProperty.getName();
    }
}

