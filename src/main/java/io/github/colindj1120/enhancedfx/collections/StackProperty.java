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

import java.util.EmptyStackException;
import java.util.Stack;

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
