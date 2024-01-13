package io.github.colindj1120.materialdesignui.utils.styleutils;

import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class StyleablePropertiesManager {
    private final List<CssMetaData<? extends Styleable, ?>> cssMetaDataList;

    public StyleablePropertiesManager(List<CssMetaData<? extends Styleable, ?>> parentCssMetaData) {
        cssMetaDataList = new ArrayList<>(parentCssMetaData);
    }

    public <T extends Styleable, V> CssMetaData<T, V> createCssMetaData(final String property, final StyleConverter<?, V> converter, final Function<T, Boolean> isSettableFunction,
                                                                        final Function<T, StyleableProperty<V>> propertyGetter) {
        return createCssMetaData(property, converter, null, false, null, isSettableFunction, propertyGetter);
    }

    public <T extends Styleable, V> CssMetaData<T, V> createCssMetaData(final String property, final StyleConverter<?, V> converter, final V initialValue,
                                                                        final Function<T, Boolean> isSettableFunction, final Function<T, StyleableProperty<V>> propertyGetter) {
        return createCssMetaData(property, converter, initialValue, false, null, isSettableFunction, propertyGetter);
    }

    public <T extends Styleable, V> CssMetaData<T, V> createCssMetaData(final String property, final StyleConverter<?, V> converter, final V initialValue, final boolean inherits,
                                                                        final Function<T, Boolean> isSettableFunction, final Function<T, StyleableProperty<V>> propertyGetter) {
        return createCssMetaData(property, converter, initialValue, inherits, null, isSettableFunction, propertyGetter);
    }

    public <T extends Styleable, V> CssMetaData<T, V> createCssMetaData(final String property, final StyleConverter<?, V> converter, final V initialValue, final boolean inherits,
                                                                        final List<CssMetaData<? extends Styleable, ?>> subProperties, final Function<T, Boolean> isSettableFunction,
                                                                        final Function<T, StyleableProperty<V>> propertyGetter) {
        CssMetaData<T, V> cssMetaData = new CssMetaData<>(property, converter, initialValue, inherits, subProperties) {
            @Override
            public boolean isSettable(T styleable) {
                return isSettableFunction.apply(styleable);
            }

            @Override
            public StyleableProperty<V> getStyleableProperty(T styleable) {
                return propertyGetter.apply(styleable);
            }
        };
        cssMetaDataList.add(cssMetaData);
        return cssMetaData;
    }

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

    public List<CssMetaData<? extends Styleable, ?>> getCssMetaDataList() {
        return Collections.unmodifiableList(cssMetaDataList);
    }
}
