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
package io.github.colindj1120.enhancedfx.base.collections;

import io.github.colindj1120.enhancedfx.base.collections.base.ObservablePropertyCollection;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a queue data structure that is observable. This class extends {@link ObservablePropertyCollection<T>}, allowing for the observation of changes to the queue's contents.
 *
 * <p>It encapsulates a {@link Queue<T>} backed by a {@link LinkedList<T>}, providing standard queue operations such as enqueue and dequeue, while changes are reflected in an associated
 * {@link ObservableList<T>}. This enables easy integration with JavaFX UI components that can bind to or display the contents of the queue, reacting to changes in real-time.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *   <li>Enqueue elements to the end of the queue.</li>
 *   <li>Dequeue elements from the front of the queue.</li>
 *   <li>Peek at the element at the front of the queue without removing it.</li>
 *   <li>Check if the queue is empty.</li>
 *   <li>Bind the queue's contents to JavaFX UI components for dynamic updates.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * QueueProperty<String> observableQueue = new QueueProperty<>();
 * observableQueue.enqueue("First");
 * observableQueue.enqueue("Second");
 *
 * observableQueue.getCollection().addListener((ListChangeListener.Change<? extends String> change) -> {
 *     while (change.next()) {
 *         if (change.wasAdded()) {
 *             System.out.println("Added: " + change.getAddedSubList());
 *         } else if (change.wasRemoved()) {
 *             System.out.println("Removed: " + change.getRemoved());
 *         }
 *     }
 * });
 *
 * System.out.println("Peek: " + observableQueue.peek()); // Output: Peek: First
 * observableQueue.dequeue();
 * System.out.println("After dequeue, Peek: " + observableQueue.peek()); // Output: After dequeue, Peek: Second
 * }</pre>
 *
 * <p>This example demonstrates the creation of an {@code ObservableQueue}, adding elements to it, and setting up a listener to observe changes. It showcases enqueueing elements, peeking at the front
 * element, and dequeue elements while observing the modifications to the queue.</p>
 *
 * @param <T>
 *         the type of elements held in this queue
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservablePropertyCollection
 * @see Queue
 * @see ObservableList
 */
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

