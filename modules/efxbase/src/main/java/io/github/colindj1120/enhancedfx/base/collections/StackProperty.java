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

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Provides a property wrapper around a stack data structure, enabling observable modifications to a stack of elements.
 *
 * <p>It extends {@link ObservablePropertyCollection<T>}, allowing observers to track changes to the stack's contents via an underlying {@link ObservableList<T>}. This class encapsulates a {@link Stack<T>},
 * offering traditional stack operations such as push, pop, and peek, while also ensuring that any modifications are reflected in the observable list and thus can be observed by listeners. It is particularly
 * useful in JavaFX applications where changes to data structures need to be reflected in the UI without manual synchronization.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *   <li>Pushing elements onto the stack.</li>
 *   <li>Popping elements from the stack, with an {@link EmptyStackException} thrown if the stack is empty.</li>
 *   <li>Peeking at the top element of the stack without removing it.</li>
 *   <li>Checking if the stack is empty.</li>
 *   <li>Observing changes to the stack through the associated {@link ObservableList}.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * StackProperty<String> observableStack = new StackProperty<>();
 * observableStack.push("Hello");
 * observableStack.push("World");
 *
 * observableStack.getCollection().addListener((ListChangeListener.Change<? extends String> c) -> {
 *     while (c.next()) {
 *         if (c.wasAdded()) {
 *             System.out.println("Added: " + c.getAddedSubList());
 *         } else if (c.wasRemoved()) {
 *             System.out.println("Removed: " + c.getRemoved());
 *         }
 *     }
 * });
 *
 * System.out.println("Top of the stack: " + observableStack.peek()); // Output: Top of the stack: World
 * observableStack.pop();
 * System.out.println("After pop, Top of the stack: " + observableStack.peek()); // Output: After pop, Top of the stack: Hello
 * }</pre>
 *
 * <p>This example demonstrates creating an `observableStack`, adding elements, and setting up a listener to observe changes. It illustrates pushing elements to the stack, peeking at the top element, and
 * popping elements while observing the modifications to the stack.</p>
 *
 * @param <T>
 *         the type of elements in the stack
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservablePropertyCollection
 * @see Stack
 * @see ObservableList
 */
public class StackProperty<T> extends ObservablePropertyCollection<T> {
    private final Stack<T> stack = new Stack<>();

    /**
     * A property class that represents a stack of elements.
     */
    public StackProperty() {
        super();
    }

    /**
     * Constructs a StackProperty with the specified initial value.
     *
     * @param initialValue
     *         the initial value of the stack
     */
    public StackProperty(ObservableList<T> initialValue) {
        super(initialValue);
        this.stack.addAll(initialValue);
    }

    /**
     * Constructs a StackProperty associated with a specific bean and property name.
     *
     * @param bean
     *         the bean this property belongs to
     * @param name
     *         the name of the property
     */
    public StackProperty(Object bean, String name) {
        super(bean, name);
    }

    /**
     * Constructs a StackProperty with the specified initial value, bean, and property name.
     *
     * @param initialValue
     *         the initial value of the stack
     * @param bean
     *         the bean this property belongs to
     * @param name
     *         the name of the property
     */
    public StackProperty(ObservableList<T> initialValue, Object bean, String name) {
        super(initialValue, bean, name);
        this.stack.addAll(initialValue);
    }

    /**
     * Pushes an item onto the top of the stack.
     *
     * @param item
     *         the item to be pushed onto the stack
     */
    public void push(T item) {
        stack.push(item);
        collectionProperty.add(item);
    }

    /**
     * Removes and returns the top element of the stack. If the stack is empty, it throws an EmptyStackException.
     *
     * @return the top element of the stack
     *
     * @throws EmptyStackException
     *         if the stack is empty
     */
    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        T item = stack.pop();
        collectionProperty.remove(item);
        return item;
    }

    /**
     * Retrieves the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     */
    public T peek() {
        return stack.peek();
    }

    /**
     * Returns whether the stack is empty or not.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Retrieves the collection of elements from the ObservablePropertyCollection.
     *
     * @return the ObservableList containing the elements of the collection
     */
    @Override
    public ObservableList<T> getCollection() {
        return collectionProperty.get();
    }

    /**
     * Sets the collection to a new ObservableList.
     *
     * @param stack
     *         the new observable list to be set
     */
    @Override
    public void setCollection(ObservableList<T> stack) {
        this.collectionProperty.set(stack);
        this.stack.clear();
        this.stack.addAll(stack);
    }
}
