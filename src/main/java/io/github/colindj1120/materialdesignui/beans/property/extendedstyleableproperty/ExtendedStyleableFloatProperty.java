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
package io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty;

import io.github.colindj1120.materialdesignui.beans.property.base.ExtendedStyleablePropertyBase;
import io.github.colindj1120.materialdesignui.consumers.TriConsumer;
import javafx.css.CssMetaData;
import javafx.css.StyleOrigin;
import javafx.css.Styleable;
import javafx.css.StyleableFloatProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class ExtendedStyleableFloatProperty extends StyleableFloatProperty implements ExtendedStyleablePropertyBase {
    private final Consumer<Void>                                              invalidatedVoidCallback;
    private final Consumer<StyleableFloatProperty>                            invalidatedPropCallback;
    private final TriConsumer<StyleableFloatProperty, Float, Consumer<Float>> invalidatedCachedCallback;
    private final String                                                      name;
    private final Object                                                      bean;
    private final CssMetaData<? extends Styleable, Number>                    cssMetaData;
    private       Float                                                       oldValue = get();

    private ExtendedStyleableFloatProperty(Builder builder) {
        super();
        this.invalidatedVoidCallback   = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback   = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name                      = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean                      = builder.bean;
        this.cssMetaData               = builder.cssMetaData;

        invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, this.getClass());
    }

    private ExtendedStyleableFloatProperty(Builder builder, Float defaultValue) {
        super(defaultValue);
        this.invalidatedVoidCallback   = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback   = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name                      = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean                      = builder.bean;
        this.cssMetaData               = builder.cssMetaData;
    }

    @Override
    protected void invalidated() {
        Optional.ofNullable(invalidatedVoidCallback)
                .ifPresent(callback -> callback.accept(null));
        Optional.ofNullable(invalidatedPropCallback)
                .ifPresent(callback -> callback.accept(this));
        Optional.ofNullable(invalidatedCachedCallback)
                .ifPresent(callback -> callback.accept(this, oldValue, val -> oldValue = val));
    }

    @Override
    public void applyStyle(StyleOrigin origin, Number v) {
        if (Objects.nonNull(origin)) {
            super.applyStyle(origin, v);

        }
    }

    @Override
    public CssMetaData<? extends Styleable, Number> getCssMetaData() {
        return cssMetaData;
    }

    @Override
    public Object getBean() {
        return bean;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class Builder {
        private Consumer<Void>                                              invalidatedVoidCallback;
        private Consumer<StyleableFloatProperty>                            invalidatedPropCallback;
        private TriConsumer<StyleableFloatProperty, Float, Consumer<Float>> invalidatedCachedCallback;
        private String                                                      name;
        private Object                                                      bean;
        private CssMetaData<? extends Styleable, Number>                    cssMetaData;
        private Float                                                       defaultValue;

        public Builder invalidatedVoidCallback(Consumer<Void> invalidatedVoidCallback) {
            this.invalidatedVoidCallback = invalidatedVoidCallback;
            return this;
        }

        public Builder invalidatedPropCallback(Consumer<StyleableFloatProperty> invalidatedPropCallback) {
            this.invalidatedPropCallback = invalidatedPropCallback;
            return this;
        }

        public Builder invalidatedCachedCallback(TriConsumer<StyleableFloatProperty, Float, Consumer<Float>> invalidatedCachedCallback) {
            this.invalidatedCachedCallback = invalidatedCachedCallback;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder bean(Object bean) {
            this.bean = bean;
            return this;
        }

        public Builder cssMetaData(CssMetaData<? extends Styleable, Number> cssMetaData) {
            this.cssMetaData = cssMetaData;
            return this;
        }

        public Builder defaultValue(Float defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public ExtendedStyleableFloatProperty build() {
            if (Objects.isNull(defaultValue)) {
                return new ExtendedStyleableFloatProperty(this);
            } else {
                return new ExtendedStyleableFloatProperty(this, defaultValue);
            }
        }
    }
}