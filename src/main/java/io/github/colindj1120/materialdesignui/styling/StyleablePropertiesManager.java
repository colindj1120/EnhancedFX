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
package io.github.colindj1120.materialdesignui.styling;

import io.github.colindj1120.materialdesignui.factories.styling.CssFactory;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;

import java.util.*;

/**
 * Manages the CSS metadata properties for Styleable objects within a JavaFX application. This class acts as a central repository for CSS property metadata, allowing for dynamic addition, retrieval,
 * and management of {@link CssMetaData} instances.
 * <p>
 * The {@code StyleablePropertiesManager} is initialized with a list of existing CSS metadata from a parent styleable object. It provides methods to add new CSS metadata via a {@link CssFactory},
 * retrieve metadata based on property names, and obtain an immutable list of all managed CSS metadata.
 * </p>
 * <p>
 * This class is particularly useful in scenarios where custom styling properties are required for JavaFX components, enabling developers to define and manipulate these properties in a structured and
 * efficient manner. It enhances the ability to create custom styled components with dynamic styling capabilities.
 * </p>
 * <p>
 * Additionally, the inner {@code CssBuilder} class offers a fluent API for creating {@code CssMetaData} instances, simplifying the process of defining new styleable properties.
 * </p>
 * <p>
 * Example Usage:
 * <pre>
 * {@code
 * StyleablePropertiesManager manager = new StyleablePropertiesManager(parentCssMetaData);
 * manager.addCssMetaData(new CssBuilder<Styleable, Color>()
 *                          .property("-custom-color")
 *                          .converter(StyleConverter.getColorConverter())
 *                          .initialValue(Color.BLACK)
 *                          .isSettableFunction(node -> node.customColor != null && !node.customColor.isBound())
 *                          .propertyGetter(node -> node.customColor));
 * CssMetaData<Styleable, Color> metaData = manager.findCssMetaData("-custom-color");
 * }
 * </pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CssMetaData
 * @see Styleable
 * @see StyleableProperty
 */
public class StyleablePropertiesManager {
    private final List<CssMetaData<? extends Styleable, ?>> cssMetaDataList;

    /**
     * Constructs a new {@code StyleablePropertiesManager} with an initial list of CSS metadata. This constructor initializes
     * the manager with an empty list of {@link CssMetaData} instances.
     */
    public StyleablePropertiesManager() {
        cssMetaDataList = new ArrayList<>();
    }

    /**
     * Constructs a new {@code StyleablePropertiesManager} with an initial list of CSS metadata. This constructor initializes the manager with a given list of {@link CssMetaData} instances, typically
     * derived from a parent styleable object. It allows for the subsequent addition and management of custom CSS metadata for JavaFX styleable objects.
     *
     * @param parentCssMetaData
     *         The initial list of CSS metadata from a parent styleable object.
     */
    public StyleablePropertiesManager(List<CssMetaData<? extends Styleable, ?>> parentCssMetaData) {
        cssMetaDataList = new ArrayList<>(parentCssMetaData);
    }

    /**
     * Adds a new CSS metadata item to the manager. This method accepts a {@link CssFactory} instance, builds the CSS metadata, and adds it to the manager's internal list. This is useful for
     * dynamically adding new styleable properties to a JavaFX component.
     *
     * @param <T>
     *         The type of the styleable object.
     * @param <V>
     *         The type of the value represented by the CSS metadata.
     * @param builder
     *         The builder used to create the CSS metadata item.
     */
    public <T extends Styleable, V> void addCssMetaData(CssFactory<T, V> builder) {
        cssMetaDataList.add(builder.build());
    }

    /**
     * Finds and returns the CSS metadata for a specified CSS property. This method searches the internal list of CSS metadata for a property matching the given name. If found, it returns the
     * corresponding {@link CssMetaData} instance, otherwise it throws an IllegalArgumentException.
     *
     * @param <S>
     *         The type of the styleable object.
     * @param <V>
     *         The type of the value represented by the CSS metadata.
     * @param cssProperty
     *         The name of the CSS property to search for.
     *
     * @return The {@code CssMetaData} instance corresponding to the specified property.
     *
     * @throws IllegalArgumentException
     *         If no CSS metadata is found for the given property name.
     */
    @SuppressWarnings("unchecked")
    public <S extends Styleable, V> CssMetaData<S, V> findCssMetaData(String cssProperty) {
        try {
            return (CssMetaData<S, V>) cssMetaDataList.stream()
                                                      .filter(cssMetaData -> cssMetaData.getProperty()
                                                                                        .equals(cssProperty))
                                                      .findFirst()
                                                      .orElseThrow(() -> new IllegalArgumentException("Couldn't find CssMetaData for property: " + cssProperty));

        }
        catch (ClassCastException e) {
            throw new IllegalArgumentException("Error Finding Metadata value found can't be casted to type passed in for S and V", e);
        }
    }

    /**
     * Retrieves the CSS metadata at the specified index.
     *
     * @param index The index of the CSS metadata.
     * @param <S>   The type of the styleable object associated with the CSS metadata.
     * @param <V>   The type of the value represented by the CSS metadata.
     * @return The CSS metadata at the specified index.
     */
    @SuppressWarnings("unchecked")
    public <S extends Styleable, V> CssMetaData<S, V> getCssMetaData(int index) {
        return (CssMetaData<S, V>) cssMetaDataList.get(index);
    }

    /**
     * Provides an unmodifiable view of the CSS metadata list managed by this instance. This method returns a list of all {@link CssMetaData} instances currently managed, ensuring that the returned
     * list cannot be modified directly.
     *
     * @return An unmodifiable list of {@code CssMetaData} objects.
     */
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaDataList() {
        return Collections.unmodifiableList(cssMetaDataList);
    }
}
