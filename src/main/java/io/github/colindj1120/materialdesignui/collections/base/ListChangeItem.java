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
package io.github.colindj1120.materialdesignui.collections.base;

import io.github.colindj1120.materialdesignui.enums.collections.ListAction;

import java.util.List;

public class ListChangeItem<T> {
    private final ListAction listAction;

    private final List<T> oldList;
    private final List<T> newList;

    private final T oldElement;
    private final T newElement;

    public ListChangeItem(ListAction listAction, T oldElement, T newElement) {

        this.listAction = listAction;
        this.oldElement = oldElement;
        this.newElement = newElement;

        this.oldList = null;
        this.newList = null;
    }

    public ListChangeItem(ListAction listAction, List<T> oldList, List<T> newList) {
        this.listAction = listAction;
        this.oldList    = oldList;
        this.newList    = newList;

        this.oldElement = null;
        this.newElement = null;
    }

    public ListAction getListAction() {
        return listAction;
    }

    public List<T> getOldList() {
        return oldList;
    }

    public List<T> getNewList() {
        return newList;
    }

    public T getOldElement() {
        return oldElement;
    }

    public T getNewElement() {
        return newElement;
    }
}
