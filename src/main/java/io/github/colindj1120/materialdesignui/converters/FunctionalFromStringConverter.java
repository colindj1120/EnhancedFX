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
package io.github.colindj1120.materialdesignui.converters;

import javafx.util.StringConverter;

import java.util.Objects;
import java.util.function.Supplier;

@FunctionalInterface
public interface FunctionalFromStringConverter<T> {
    T fromString(String string);

    static <T> StringConverter<T> of(FunctionalFromStringConverter<T> converter) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                throw new UnsupportedOperationException("ToString conversion not implemented");
            }

            @Override
            public T fromString(String string) {
                return converter.fromString(string);
            }
        };
    }

    static <T> StringConverter<T> of(FunctionalFromStringConverter<T> converter, T defaultReturn) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                throw new UnsupportedOperationException("ToString conversion not implemented");
            }

            @Override
            public T fromString(String string) {
                if (Objects.isNull(string) || string.isEmpty()) {
                    return defaultReturn;
                }
                return converter.fromString(string);
            }
        };
    }

    static <T, X extends RuntimeException> StringConverter<T> of(FunctionalFromStringConverter<T> converter, Supplier<X> exceptionSupplier) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                throw new UnsupportedOperationException("ToString conversion not implemented");
            }

            @Override
            public T fromString(String string) {
                if (Objects.isNull(string) || string.isEmpty()) {
                    throw exceptionSupplier.get();
                }
                return converter.fromString(string);
            }
        };
    }
}
