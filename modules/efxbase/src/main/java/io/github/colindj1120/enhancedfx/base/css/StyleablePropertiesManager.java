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
package io.github.colindj1120.enhancedfx.base.css;

import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code StyleablePropertiesManager} class manages CSS metadata for JavaFX styleable objects, facilitating the dynamic addition, management, and retrieval of {@link CssMetaData}. This utility class
 * supports the customization and extension of styling capabilities of JavaFX components through CSS, enabling developers to maintain a structured and accessible collection of custom CSS properties.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Initialization with an existing list of CSS metadata, allowing for inheritance and extension of styles from parent styleable objects.</li>
 *   <li>Dynamic addition of new CSS metadata through {@link CssFactory} instances, supporting the definition of custom styleable properties.</li>
 *   <li>Retrieval of specific CSS metadata by property name, with support for type-safe casting and error handling.</li>
 *   <li>Access to the complete, unmodifiable list of managed CSS metadata, ensuring encapsulation and data integrity.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * The following example demonstrates how to utilize {@code StyleablePropertiesManager} to manage custom CSS metadata for a JavaFX component:
 * <pre>
 * {@code
 * // Create a manager instance, possibly extending from parent styles
 * StyleablePropertiesManager manager = new StyleablePropertiesManager(parentStyles);
 *
 * // Dynamically add custom CSS metadata
 * manager.addCssMetaData(new CssFactory<>(...));
 *
 * // Retrieve CSS metadata by property name
 * CssMetaData<Styleable, Color> backgroundColorMetaData = manager.findCssMetaData("-fx-background-color");
 *
 * // Access the complete list of CSS metadata
 * List<CssMetaData<? extends Styleable, ?>> allMetaData = manager.getCssMetaDataList();
 * }
 * </pre>
 *
 * <p>This class serves as a foundational tool for developers looking to enhance the styling system of JavaFX applications, providing both flexibility and robustness in managing CSS properties.</p>
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
     * Constructs a new {@code StyleablePropertiesManager} with an initial list of CSS metadata. This constructor initializes the manager with an empty list of {@link CssMetaData} instances.
     */
    public StyleablePropertiesManager() {
        cssMetaDataList = new ArrayList<>();
    }

    /**
     * Constructs a new {@code StyleablePropertiesManager} with an initial list of CSS metadata. This constructor initializes the manager with a given list of {@link CssMetaData} instances, typically derived
     * from a parent styleable object. It allows for the further addition and management of custom CSS metadata for JavaFX styleable objects.
     *
     * @param parentCssMetaData
     *         The initial list of CSS metadata from a parent styleable object.
     */
    public StyleablePropertiesManager(List<CssMetaData<? extends Styleable, ?>> parentCssMetaData) {
        cssMetaDataList = new ArrayList<>(parentCssMetaData);
    }

    /**
     * Adds a new CSS metadata item to the manager. This method accepts a {@link CssFactory} instance, builds the CSS metadata, and adds it to the manager's internal list. This is useful for dynamically adding
     * new styleable properties to a JavaFX component.
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
     * Finds and returns the CSS metadata for a specified CSS property. This method searches the internal list of CSS metadata for a property matching the given name. If found, it returns the corresponding
     * {@link CssMetaData} instance, otherwise it throws an IllegalArgumentException.
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
     * @param index
     *         The index of the CSS metadata.
     * @param <S>
     *         The type of the styleable object associated with the CSS metadata.
     * @param <V>
     *         The type of the value represented by the CSS metadata.
     *
     * @return The CSS metadata at the specified index.
     */
    @SuppressWarnings("unchecked")
    public <S extends Styleable, V> CssMetaData<S, V> getCssMetaData(int index) {
        return (CssMetaData<S, V>) cssMetaDataList.get(index);
    }

    /**
     * Provides an unmodifiable view of the CSS metadata list managed by this instance. This method returns a list of all {@link CssMetaData} instances currently managed, ensuring that the returned list cannot
     * be modified directly.
     *
     * @return An unmodifiable list of {@code CssMetaData} objects.
     */
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaDataList() {
        return Collections.unmodifiableList(cssMetaDataList);
    }
}
