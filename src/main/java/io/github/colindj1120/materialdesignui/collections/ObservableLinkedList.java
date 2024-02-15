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
import io.github.colindj1120.materialdesignui.enums.collections.ListAction;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ObservableLinkedList<E> extends LinkedList<E> {
    private final List<Consumer<ListChangeItem<E>>> actionListeners = new ArrayList<>();

    public void addActionListener(Consumer<ListChangeItem<E>> listener) {
        actionListeners.add(listener);
    }

    public void removeActionListener(Consumer<ListChangeItem<E>> listener) {
        actionListeners.remove(listener);
    }

    private void notifyActionListeners(ListChangeItem<E> element) {
        actionListeners.forEach(observer -> observer.accept(element));
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added) {
            notifyActionListeners(new ListChangeItem<>(ListAction.ADDED, null, element));
        }
        return added;
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
        notifyActionListeners(new ListChangeItem<>(ListAction.ADDED, null, element));
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        List<E> before  = getBeforeList();
        boolean changed = super.addAll(c);
        if (changed) {
            if (!actionListeners.isEmpty()) {
                notifyActionListeners(new ListChangeItem<>(ListAction.BULK_ADD, before, this));
            }
        }
        return changed;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        List<E> before  = getBeforeList();
        boolean changed = super.addAll(index, c);
        if (changed) {
            if (!actionListeners.isEmpty()) {
                notifyActionListeners(new ListChangeItem<>(ListAction.BULK_ADD, before, this));
            }
        }
        return changed;
    }

    @Override
    public void addFirst(E e) {
        super.addFirst(e);
        notifyActionListeners(new ListChangeItem<>(ListAction.ADDED, null, e));
    }

    @Override
    public void addLast(E e) {
        int size = size();
        super.addLast(e);
        notifyActionListeners(new ListChangeItem<>(ListAction.ADDED, null, e));
    }

    @Override
    public E remove(int index) {
        E removedElement = super.remove(index);
        notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, removedElement, null));
        return removedElement;
    }

    @Override
    public E remove() {
        E removedElement = super.remove();
        notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, removedElement, null));
        return removedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        if (removed) {
            // Safe cast since remove succeeded
           notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, (E) o, null));
        }
        return removed;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean removeFirstOccurrence(Object o) {
        int index = indexOf(o);
        boolean removed = super.removeFirstOccurrence(o);
        if (removed) {
            notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, (E) o, null));
        }
        return removed;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean removeLastOccurrence(Object o) {
        boolean removed = super.removeLastOccurrence(o);
        if (removed) {
            notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, (E) o, null));
        }
        return removed;
    }

    @Override
    public E removeFirst() {
        E removed = super.removeFirst();
        notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, removed, null));
        return removed;
    }

    @Override
    public E removeLast() {
        E removed = super.removeLast();
        notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, removed, null));
        return removed;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        List<E> before   = getBeforeList();
        boolean modified = super.removeAll(c);

        if (modified && !actionListeners.isEmpty()) {
            notifyActionListeners(new ListChangeItem<>(ListAction.BULK_REMOVE, before, this));
        }

        return modified;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        List<E> before   = getBeforeList();
        boolean modified = super.removeIf(filter);

        if (modified && !actionListeners.isEmpty()) {
            notifyActionListeners(new ListChangeItem<>(ListAction.BULK_REMOVE, before, this));
        }

        return modified;
    }

    @NotNull
    private List<E> getBeforeList() {
        return actionListeners.isEmpty() ? Collections.emptyList() : new ArrayList<>(this);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        // Only copy the list if there are bulk listeners registered
        List<E> before = getBeforeList();
        boolean modified = super.retainAll(c);

        if (modified && !actionListeners.isEmpty()) {
            notifyActionListeners(new ListChangeItem<>(ListAction.BULK_REMOVE, before, this));
        }

        return modified;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        // Only copy the list if there are bulk listeners registered
        List<E> before = getBeforeList();
        super.replaceAll(operator);

        if (!actionListeners.isEmpty()) {
            notifyActionListeners(new ListChangeItem<>(ListAction.BULK_REPLACED, before, this));
        }
    }

    @Override
    public void clear() {
        if (!isEmpty()) { // Check to prevent unnecessary notifications
            // Only copy the list if there are bulk listeners registered
            List<E> before = getBeforeList();
            super.clear();

            if (!actionListeners.isEmpty()) {
                notifyActionListeners(new ListChangeItem<>(ListAction.CLEARED, before, this));
            }
        }
    }

    @Override
    public E set(int index, E element) {
        E oldElement = super.set(index, element);
        notifyActionListeners(new ListChangeItem<>(ListAction.REPLACED, oldElement, element));
        return oldElement;
    }

    @Override
    public void push(E e) {
        super.push(e);
        notifyActionListeners(new ListChangeItem<>(ListAction.ADDED, null, e));
    }

    @Override
    public E pop() {
        E poppedElement = super.pop();
        notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, poppedElement, null));
        return poppedElement;
    }

    @Override
    public boolean offer(E e) {
        boolean offered = super.offer(e);
        if (offered) {
            notifyActionListeners(new ListChangeItem<>(ListAction.ADDED, null, e));
        }
        return offered;
    }

    @Override
    public boolean offerFirst(E e) {
        boolean offered = super.offerFirst(e);
        if (offered) {
            notifyActionListeners(new ListChangeItem<>(ListAction.ADDED, null, e));
        }
        return offered;
    }

    @Override
    public boolean offerLast(E e) {
        boolean offered = super.offerLast(e);
        if (offered) {
            notifyActionListeners(new ListChangeItem<>(ListAction.ADDED, null, e));
        }
        return offered;
    }

    @Override
    public E poll() {
        E polled = super.poll();
        if (polled != null) {
            notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, polled, null));
        }
        return polled;
    }

    @Override
    public E pollFirst() {
        E polled = super.pollFirst();
        if (polled != null) {
            notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, polled, null));
        }
        return polled;
    }

    @Override
    public E pollLast() {
        E polled = super.pollLast();
        if (polled != null) {
            notifyActionListeners(new ListChangeItem<>(ListAction.REMOVED, polled, null));
        }
        return polled;
    }
}

