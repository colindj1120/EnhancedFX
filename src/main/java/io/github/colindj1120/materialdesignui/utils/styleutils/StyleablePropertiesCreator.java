package io.github.colindj1120.materialdesignui.utils.styleutils;

import io.github.colindj1120.materialdesignui.utils.consumers.TriConsumer;
import javafx.css.*;

import java.util.function.Consumer;

public class StyleablePropertiesCreator {
    /* *************************************************************************
     *                                                                         *
     * Styleable Properties                                                    *
     *                                                                         *
     **************************************************************************/

    public StyleableIntegerProperty createStyleableIntegerProperty(String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
                                                                   TriConsumer<StyleableIntegerProperty, Integer, Consumer<Integer>> invalidatedCallback) {
        return new StyleableIntegerProperty() {
            private int oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
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
        };
    }

    public StyleableIntegerProperty createStyleableIntegerProperty(int defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
                                                                   TriConsumer<StyleableIntegerProperty, Integer, Consumer<Integer>> invalidatedCallback) {
        return new StyleableIntegerProperty(defaultValue) {
            private int oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
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
        };
    }

    public StyleableDoubleProperty createStyleableDoubleProperty(String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
                                                                 TriConsumer<StyleableDoubleProperty, Double, Consumer<Double>> invalidatedCallback) {
        return new StyleableDoubleProperty() {
            private double oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
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
        };
    }

    public StyleableDoubleProperty createStyleableDoubleProperty(double defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
                                                                 TriConsumer<StyleableDoubleProperty, Double, Consumer<Double>> invalidatedCallback) {
        return new StyleableDoubleProperty(defaultValue) {
            private double oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
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
        };
    }

    public StyleableFloatProperty createStyleableFloatProperty(float defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
                                                               TriConsumer<StyleableFloatProperty, Float, Consumer<Float>> invalidatedCallback) {
        return new StyleableFloatProperty(defaultValue) {
            private float oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
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
        };
    }

    public StyleableFloatProperty createStyleableFloatProperty(String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
                                                               TriConsumer<StyleableFloatProperty, Float, Consumer<Float>> invalidatedCallback) {
        return new StyleableFloatProperty() {
            private float oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
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
        };
    }

    public StyleableLongProperty createStyleableLongProperty(String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
                                                             TriConsumer<StyleableLongProperty, Long, Consumer<Long>> invalidatedCallback) {
        return new StyleableLongProperty() {
            private long oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
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
        };
    }

    public StyleableLongProperty createStyleableLongProperty(long defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
                                                             TriConsumer<StyleableLongProperty, Long, Consumer<Long>> invalidatedCallback) {
        return new StyleableLongProperty(defaultValue) {
            private long oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
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
        };
    }

    public StyleableBooleanProperty createStyleableBooleanProperty(boolean defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Boolean> cssMetaData,
                                                                   TriConsumer<StyleableBooleanProperty, Boolean, Consumer<Boolean>> invalidatedCallback) {
        return new StyleableBooleanProperty(defaultValue) {
            private boolean oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
            }

            @Override
            public CssMetaData<? extends Styleable, Boolean> getCssMetaData() {
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
        };
    }

    public StyleableBooleanProperty createStyleableBooleanProperty(String name, Object bean, CssMetaData<? extends Styleable, Boolean> cssMetaData,
                                                                   TriConsumer<StyleableBooleanProperty, Boolean, Consumer<Boolean>> invalidatedCallback) {
        return new StyleableBooleanProperty() {
            private boolean oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
            }

            @Override
            public CssMetaData<? extends Styleable, Boolean> getCssMetaData() {
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
        };
    }

    public StyleableStringProperty createStyleableStringProperty(String name, Object bean, CssMetaData<? extends Styleable, String> cssMetaData,
                                                                 TriConsumer<StyleableStringProperty, String, Consumer<String>> invalidatedCallback) {
        return new StyleableStringProperty() {
            private String oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
            }

            @Override
            public CssMetaData<? extends Styleable, String> getCssMetaData() {
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
        };
    }

    public StyleableStringProperty createStyleableStringProperty(String defaultValue, String name, Object bean, CssMetaData<? extends Styleable, String> cssMetaData,
                                                                 TriConsumer<StyleableStringProperty, String, Consumer<String>> invalidatedCallback) {
        return new StyleableStringProperty(defaultValue) {
            private String oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
            }

            @Override
            public CssMetaData<? extends Styleable, String> getCssMetaData() {
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
        };
    }

    public <T> StyleableObjectProperty<T> createStyleableObjectProperty(String name, Object bean, CssMetaData<? extends Styleable, T> cssMetaData,
                                                                        TriConsumer<StyleableObjectProperty<T>, T, Consumer<T>> invalidatedCallback) {
        return new StyleableObjectProperty<>() {
            private T oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
            }

            @Override
            public CssMetaData<? extends Styleable, T> getCssMetaData() {
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
        };
    }

    public <T> StyleableObjectProperty<T> createStyleableObjectProperty(T defaultValue, String name, Object bean, CssMetaData<? extends Styleable, T> cssMetaData,
                                                                        TriConsumer<StyleableObjectProperty<T>, T, Consumer<T>> invalidatedCallback) {
        return new StyleableObjectProperty<>(defaultValue) {
            private T oldValue = get();

            @Override
            protected void invalidated() {
                invalidatedCallback.accept(this, oldValue, val -> oldValue = val);
            }

            @Override
            public CssMetaData<? extends Styleable, T> getCssMetaData() {
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
        };
    }

    /* *************************************************************************
     *                                                                         *
     * Simple Styleable Properties                                             *
     *                                                                         *
     **************************************************************************/

    /* *************************************************************************
     *                                                                         *
     * Simple Integer Styleable Properties                                     *
     *                                                                         *
     **************************************************************************/

    public SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData) {
        return new SimpleStyleableIntegerProperty(cssMetaData);
    }

    public SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Integer initialValue) {
        return new SimpleStyleableIntegerProperty(cssMetaData, initialValue);
    }

    public SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name) {
        return new SimpleStyleableIntegerProperty(cssMetaData, bean, name);
    }

    public SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Integer initialValue) {
        return new SimpleStyleableIntegerProperty(cssMetaData, bean, name, initialValue);
    }

    /* *************************************************************************
     *                                                                         *
     * Simple Double Styleable Properties                                      *
     *                                                                         *
     **************************************************************************/

    public SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData) {
        return new SimpleStyleableDoubleProperty(cssMetaData);
    }

    public SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Double initialValue) {
        return new SimpleStyleableDoubleProperty(cssMetaData, initialValue);
    }

    public SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name) {
        return new SimpleStyleableDoubleProperty(cssMetaData, bean, name);
    }

    public SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Double initialValue) {
        return new SimpleStyleableDoubleProperty(cssMetaData, bean, name, initialValue);
    }

    /* *************************************************************************
     *                                                                         *
     * Simple Float Styleable Properties                                       *
     *                                                                         *
     **************************************************************************/

    public SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData) {
        return new SimpleStyleableFloatProperty(cssMetaData);
    }

    public SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Float initialValue) {
        return new SimpleStyleableFloatProperty(cssMetaData, initialValue);
    }

    public SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name) {
        return new SimpleStyleableFloatProperty(cssMetaData, bean, name);
    }

    public SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Float initialValue) {
        return new SimpleStyleableFloatProperty(cssMetaData, bean, name, initialValue);
    }

    /* *************************************************************************
     *                                                                         *
     * Simple Long Styleable Properties                                       *
     *                                                                         *
     **************************************************************************/

    public SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData) {
        return new SimpleStyleableLongProperty(cssMetaData);
    }

    public SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Long initialValue) {
        return new SimpleStyleableLongProperty(cssMetaData, initialValue);
    }

    public SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name) {
        return new SimpleStyleableLongProperty(cssMetaData, bean, name);
    }

    public SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Long initialValue) {
        return new SimpleStyleableLongProperty(cssMetaData, bean, name, initialValue);
    }

    /* *************************************************************************
     *                                                                         *
     * Simple Boolean Styleable Properties                                     *
     *                                                                         *
     **************************************************************************/

    public SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData) {
        return new SimpleStyleableBooleanProperty(cssMetaData);
    }

    public SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Boolean initialValue) {
        return new SimpleStyleableBooleanProperty(cssMetaData, initialValue);
    }

    public SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Object bean, String name) {
        return new SimpleStyleableBooleanProperty(cssMetaData, bean, name);
    }

    public SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Object bean, String name, Boolean initialValue) {
        return new SimpleStyleableBooleanProperty(cssMetaData, bean, name, initialValue);
    }

    /* *************************************************************************
     *                                                                         *
     * Simple String Styleable Properties                                      *
     *                                                                         *
     **************************************************************************/

    public SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData) {
        return new SimpleStyleableStringProperty(cssMetaData);
    }

    public SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, String initialValue) {
        return new SimpleStyleableStringProperty(cssMetaData, initialValue);
    }

    public SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, Object bean, String name) {
        return new SimpleStyleableStringProperty(cssMetaData, bean, name);
    }

    public SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, Object bean, String name, String initialValue) {
        return new SimpleStyleableStringProperty(cssMetaData, bean, name, initialValue);
    }

    /* *************************************************************************
     *                                                                         *
     * Simple Object Styleable Properties                                      *
     *                                                                         *
     **************************************************************************/

    public <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData) {
        return new SimpleStyleableObjectProperty<>(cssMetaData);
    }

    public <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, T initialValue) {
        return new SimpleStyleableObjectProperty<>(cssMetaData, initialValue);
    }

    public <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, Object bean, String name) {
        return new SimpleStyleableObjectProperty<>(cssMetaData, bean, name);
    }

    public <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, Object bean, String name, T initialValue) {
        return new SimpleStyleableObjectProperty<>(cssMetaData, bean, name, initialValue);
    }
}
