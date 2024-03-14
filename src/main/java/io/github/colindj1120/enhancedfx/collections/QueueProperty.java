/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.collections;

import io.github.colindj1120.enhancedfx.collections.base.ObservablePropertyCollection;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.Queue;

public class QueueProperty<T> extends ObservablePropertyCollection<T> {
    private final Queue<T> queue = new LinkedList<>();

    /**
     * A property class that represents a queue of elements.
     */
    public QueueProperty() {
        super();
    }

    /**
     * Constructs a QueueProperty with the specified initial value.
     *
     * @param initialValue
     *         the initial value of the queue
     */
    public QueueProperty(ObservableList<T> initialValue) {
        super(initialValue);
        this.queue.addAll(initialValue);
    }

    /**
     * Constructs a QueueProperty associated with a specific bean and property name.
     *
     * @param bean
     *         the bean this property belongs to
     * @param name
     *         the name of the property
     */
    public QueueProperty(Object bean, String name) {
        super(bean, name);
    }

    /**
     * Constructs a QueueProperty with the specified initial value, bean, and property name.
     *
     * @param initialValue
     *         the initial value of the queue
     * @param bean
     *         the bean this property belongs to
     * @param name
     *         the name of the property
     */
    public QueueProperty(ObservableList<T> initialValue, Object bean, String name) {
        super(initialValue, bean, name);
        this.queue.addAll(initialValue);
    }

    /**
     * Adds an item to the end of this queue.
     *
     * @param item
     *         the item to be added to this queue.
     */
    public void enqueue(T item) {
        queue.offer(item);
        collectionProperty.add(item);
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return The head of this queue, or null if this queue is empty.
     */
    public T dequeue() {
        T item = queue.poll();
        if (item != null) {
            collectionProperty.remove(item);
        }
        return item;
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return The head of this queue, or null if this queue is empty.
     */
    public T peek() {
        return queue.peek();
    }

    /**
     * Checks if this queue is empty.
     *
     * @return true if this queue contains no elements.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Retrieves the current collection as an ObservableList.
     *
     * @return the observable list wrapped by this property
     */
    @Override
    public ObservableList<T> getCollection() {
        return collectionProperty.get();
    }

    /**
     * Sets the collection of the QueueProperty to the specified ObservableList. This method replaces the existing collection with the new one.
     * <p>
     * Note that the method clears the current queue and adds all elements from the new collection into the queue.
     *
     * @param queue
     *         the new ObservableList to be set as the collection of the QueueProperty
     */
    @Override
    public void setCollection(ObservableList<T> queue) {
        this.collectionProperty.set(queue);
        this.queue.clear();
        this.queue.addAll(queue);
    }
}

