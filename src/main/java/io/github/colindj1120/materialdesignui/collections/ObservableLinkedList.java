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
package io.github.colindj1120.materialdesignui.collections;

import io.github.colindj1120.materialdesignui.collections.base.ListChangeItem;
import io.github.colindj1120.materialdesignui.enums.collections.UpdateActions;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * ObservableLinkedList extends {@link LinkedList} to provide a mechanism for observing modifications to the list.
 *
 * <p>It allows registration of {@link Consumer} instances that will be notified with {@link ListChangeItem} objects when changes occur. This enables applications to react dynamically to changes in
 * list data, such as updating user interfaces or triggering other data processes.</p>
 *
 * <p>Each change to the list (add, remove, set, etc.) results in the generation of a {@link ListChangeItem} object that describes the nature of the change (e.g., an element was added or removed).
 * This object is then passed to all registered listeners, providing detailed context about the change.</p>
 *
 * <p>This class is particularly useful in scenarios where it is crucial to track not just the state of a list, but also how it changes over time, facilitating complex data binding and
 * synchronization tasks in rich client applications.</p>
 *
 * @param <E>
 *         the type of elements held in this list
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see LinkedList
 * @see Consumer
 * @see ListChangeItem
 * @see UpdateActions
 */
public class ObservableLinkedList<E> extends LinkedList<E> {
    private final List<Consumer<ListChangeItem<E>>> actionListeners = new ArrayList<>();

    /**
     * ObservableLinkedList is a class that extends the LinkedList class to provide observable behavior.
     */
    public ObservableLinkedList() {
        super();
    }

    /**
     * Constructs a list containing the elements of the specified collection; in the order they are returned by the collection's iterator.
     *
     * @param c
     *         the collection whose elements are to be placed into this list
     *
     * @throws NullPointerException
     *         if the specified collection is null
     */
    public ObservableLinkedList(Collection<? extends E> c) {
        super(c);
    }

    /**
     * Registers a new action listener that will be notified whenever the list changes.
     *
     * @param listener
     *         the listener to register
     */
    public void addActionListener(Consumer<ListChangeItem<E>> listener) {
        actionListeners.add(listener);
    }

    /**
     * Removes a previously registered action listener so that it will no longer receive change notifications.
     *
     * @param listener
     *         the listener to remove
     */
    public void removeActionListener(Consumer<ListChangeItem<E>> listener) {
        actionListeners.remove(listener);
    }

    /**
     * Notifies all registered action listeners about a change in the list.
     *
     * @param element
     *         the {@link ListChangeItem} representing the change
     */
    private void notifyActionListeners(ListChangeItem<E> element) {
        actionListeners.forEach(observer -> observer.accept(element));
    }

    /**
     * Adds a single element to the end of the list. This method overrides {@link LinkedList#add(Object)} to include notification of change listeners upon successful addition.
     *
     * @param element
     *         the element to be added to the list
     *
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.ADDED, null, element));
        }
        return added;
    }

    /**
     * Inserts the specified element at the specified position in the list. This method overrides {@link LinkedList#add(int, Object)} to include notification of change listeners upon insertion.
     *
     * @param index
     *         index at which the specified element is to be inserted
     * @param element
     *         element to be inserted
     */
    @Override
    public void add(int index, E element) {
        super.add(index, element);
        notifyActionListeners(new ListChangeItem<>(UpdateActions.ADDED, null, element));
    }

    /**
     * Appends all the elements in the specified collection to the end of the list, in the order that they are returned by the specified collection's iterator.
     *
     * <p>
     * This method overrides {@link LinkedList#addAll(Collection)} to include notification of change listeners upon the successful addition of any elements.
     * </p>
     *
     * @param c
     *         collection containing elements to be added to the list
     *
     * @return {@code true} if the list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        List<E> before  = getBeforeList();
        boolean changed = super.addAll(c);
        if (changed) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.BULK_ADD, before, this));
        }
        return changed;
    }

    /**
     * Inserts all the elements in the specified collection into the list at the specified position.
     *
     * <p>
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). This method overrides {@link LinkedList#addAll(int, Collection)} to
     * include notification of change listeners upon the successful addition of any elements.
     * </p>
     *
     * @param index
     *         index at which to insert the first element from the specified collection
     * @param c
     *         collection containing elements to be added to the list
     *
     * @return {@code true} if the list changed as a result of the call
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        List<E> before  = getBeforeList();
        boolean changed = super.addAll(index, c);
        if (changed) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.BULK_ADD, before, this));
        }
        return changed;
    }

    /**
     * Inserts the specified element at the beginning of this list. This override ensures that change listeners are notified about the addition.
     *
     * @param e
     *         the element to add
     */
    @Override
    public void addFirst(E e) {
        super.addFirst(e);
        notifyActionListeners(new ListChangeItem<>(UpdateActions.ADDED, null, e));
    }

    /**
     * Appends the specified element to the end of this list. This override ensures that change listeners are notified about the addition.
     *
     * @param e
     *         the element to add
     */
    @Override
    public void addLast(E e) {
        super.addLast(e);
        notifyActionListeners(new ListChangeItem<>(UpdateActions.ADDED, null, e));
    }

    /**
     * Removes the element at the specified position in this list. This override ensures that change listeners are notified about the removal.
     *
     * @param index
     *         the index of the element to be removed
     *
     * @return the element previously at the specified position
     */
    @Override
    public E remove(int index) {
        E removedElement = super.remove(index);
        notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, removedElement, null));
        return removedElement;
    }

    /**
     * Removes and returns the first element from this list. This override ensures that change listeners are notified about the removal.
     *
     * @return the first element from this list
     */
    @Override
    public E remove() {
        E removedElement = super.remove();
        notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, removedElement, null));
        return removedElement;
    }

    /**
     * Removes the first occurrence of the specified element from this list if it is present. If the list does not contain the element, it is unchanged.
     *
     * @param o
     *         element to be removed from this list, if present
     *
     * @return true if the list contained the specified element
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        if (removed) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, (E) o, null));
        }
        return removed;
    }

    /**
     * Removes the first occurrence of the specified element in this list (when traversing the list from head to tail). If the list does not contain the element, it is unchanged.
     *
     * @param o
     *         element to be removed from this list, if present
     *
     * @return true if the element was found and removed
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean removeFirstOccurrence(Object o) {
        boolean removed = super.removeFirstOccurrence(o);
        if (removed) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, (E) o, null));
        }
        return removed;
    }

    /**
     * Removes the last occurrence of the specified element in this list (when traversing the list from head to tail). If the list does not contain the element, it is unchanged.
     *
     * @param o
     *         element to be removed from this list, if present
     *
     * @return true if the element was found and removed
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean removeLastOccurrence(Object o) {
        boolean removed = super.removeLastOccurrence(o);
        if (removed) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, (E) o, null));
        }
        return removed;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     */
    @Override
    public E removeFirst() {
        E removed = super.removeFirst();
        notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, removed, null));
        return removed;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     */
    @Override
    public E removeLast() {
        E removed = super.removeLast();
        notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, removed, null));
        return removed;
    }

    /**
     * Removes all of this list's elements that are also contained in the specified collection.
     *
     * @param c
     *         collection containing elements to be removed from this list
     *
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        List<E> before   = getBeforeList();
        boolean modified = super.removeAll(c);

        if (modified && !actionListeners.isEmpty()) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.BULK_REMOVE, before, this));
        }

        return modified;
    }

    /**
     * Removes all the elements of this list that satisfy the given predicate.
     *
     * @param filter
     *         a predicate which returns {@code true} for elements to be removed
     *
     * @return {@code true} if any elements were removed
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        List<E> before   = getBeforeList();
        boolean modified = super.removeIf(filter);
        if (modified && !actionListeners.isEmpty()) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.BULK_REMOVE, before, this));
        }
        return modified;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     *
     * @param c
     *         collection containing elements to be retained in this list
     *
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        List<E> before   = getBeforeList();
        boolean modified = super.retainAll(c);
        if (modified) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.BULK_REMOVE, before, this));
        }
        return modified;
    }

    /**
     * Replaces each element of this list with the result of applying the operator to that element. All registered action listeners are notified after the operation is complete.
     *
     * @param operator
     *         the operator to apply to each element
     */
    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        List<E> before = getBeforeList();
        super.replaceAll(operator);
        notifyActionListeners(new ListChangeItem<>(UpdateActions.BULK_REPLACED, before, this));
    }

    /**
     * Removes all the elements from this list. The list will be empty after this call returns. All registered action listeners are notified.
     */
    @Override
    public void clear() {
        if (!isEmpty()) {
            List<E> before = getBeforeList();
            super.clear();
            notifyActionListeners(new ListChangeItem<>(UpdateActions.CLEARED, before, this));
        }
    }

    /**
     * Replaces the element at the specified position in this list with the specified element. The previous element at that position is returned. All registered action listeners are notified.
     *
     * @param index
     *         index of the element to replace
     * @param element
     *         element to be stored at the specified position
     *
     * @return the element previously at the specified position
     */
    @Override
    public E set(int index, E element) {
        E oldElement = super.set(index, element);
        notifyActionListeners(new ListChangeItem<>(UpdateActions.REPLACED, oldElement, element));
        return oldElement;
    }

    /**
     * Inserts the specified element at the beginning of this list. All registered action listeners are notified.
     *
     * @param e
     *         the element to push
     */
    @Override
    public void push(E e) {
        super.push(e);
        notifyActionListeners(new ListChangeItem<>(UpdateActions.ADDED, null, e));
    }

    /**
     * Pops an element from the stack represented by this list. In other words, removes and returns the first element of this list. All registered action listeners are notified.
     *
     * @return the element at the front of this list (which is the top of the stack represented by this list)
     */
    @Override
    public E pop() {
        E poppedElement = super.pop();
        notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, poppedElement, null));
        return poppedElement;
    }

    /**
     * Attempts to add the specified element as the tail (last element) of this list. If successful, all registered action listeners are notified of the addition.
     *
     * @param e
     *         element to be added to the tail of this list
     *
     * @return {@code true} if the element was added successfully
     */
    @Override
    public boolean offer(E e) {
        boolean offered = super.offer(e);
        if (offered) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.ADDED, null, e));
        }
        return offered;
    }

    /**
     * Inserts the specified element at the front of this list. If the element was successfully added, all registered action listeners are notified of the addition.
     *
     * @param e
     *         element to be inserted at the front of this list
     *
     * @return {@code true} if the element was added successfully
     */
    @Override
    public boolean offerFirst(E e) {
        boolean offered = super.offerFirst(e);
        if (offered) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.ADDED, null, e));
        }
        return offered;
    }

    /**
     * Inserts the specified element at the end of this list. If the element was successfully added, all registered action listeners are notified of the addition.
     *
     * @param e
     *         element to be inserted at the end of this list
     *
     * @return {@code true} if the element was added successfully
     */
    @Override
    public boolean offerLast(E e) {
        boolean offered = super.offerLast(e);
        if (offered) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.ADDED, null, e));
        }
        return offered;
    }

    /**
     * Retrieves and removes the head (first element) of this list. If the list is not empty, all registered action listeners are notified of the removal.
     *
     * @return the head of this list, or {@code null} if this list is empty
     */
    @Override
    public E poll() {
        E polled = super.poll();
        if (Objects.nonNull(polled)) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, polled, null));
        }
        return polled;
    }

    /**
     * Retrieves and removes the first element of this list. If the list is not empty, all registered action listeners are notified of the removal.
     *
     * @return the first element of this list, or {@code null} if this list is empty
     */
    @Override
    public E pollFirst() {
        E polled = super.pollFirst();
        if (Objects.nonNull(polled)) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, polled, null));
        }
        return polled;
    }

    /**
     * Retrieves and removes the last element of this list. If the list is not empty, all registered action listeners are notified of the removal.
     *
     * @return the last element of this list, or {@code null} if this list is empty
     */
    @Override
    public E pollLast() {
        E polled = super.pollLast();
        if (Objects.nonNull(polled)) {
            notifyActionListeners(new ListChangeItem<>(UpdateActions.REMOVED, polled, null));
        }
        return polled;
    }

    /**
     * Captures the current state of the list before any modifications. This method is used internally to determine the state of the list prior to modifications for notifying action listeners.
     *
     * @return a snapshot of the list before modifications, or an empty list if no action listeners are registered
     */
    @NotNull
    private List<E> getBeforeList() {
        return actionListeners.isEmpty() ? Collections.emptyList() : new ArrayList<>(this);
    }
}

