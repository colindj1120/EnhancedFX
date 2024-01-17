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

    public static final class StyleableIntegerPropertyCreator {
        public static StyleableIntegerProperty createStyleableIntegerProperty(String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
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

        public static StyleableIntegerProperty createStyleableIntegerProperty(int defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
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
    }

    public static final class StyleableDoublePropertyCreator {
        public static StyleableDoubleProperty createStyleableDoubleProperty(String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
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

        public static StyleableDoubleProperty createStyleableDoubleProperty(double defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
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
    }

    public static final class StyleableFloatPropertyCreator {
        public static StyleableFloatProperty createStyleableFloatProperty(float defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
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

        public static StyleableFloatProperty createStyleableFloatProperty(String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
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
    }

    public static final class StyleableLongPropertyCreator {
        public static StyleableLongProperty createStyleableLongProperty(String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
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

        public static StyleableLongProperty createStyleableLongProperty(long defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Number> cssMetaData,
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
    }

    public static final class StyleableBooleanPropertyCreator {
        public static StyleableBooleanProperty createStyleableBooleanProperty(boolean defaultValue, String name, Object bean, CssMetaData<? extends Styleable, Boolean> cssMetaData,
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

        public static StyleableBooleanProperty createStyleableBooleanProperty(String name, Object bean, CssMetaData<? extends Styleable, Boolean> cssMetaData,
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
    }

    public static final class StyleableStringPropertyCreator {
        public static StyleableStringProperty createStyleableStringProperty(String name, Object bean, CssMetaData<? extends Styleable, String> cssMetaData,
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

        public static StyleableStringProperty createStyleableStringProperty(String defaultValue, String name, Object bean, CssMetaData<? extends Styleable, String> cssMetaData,
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
    }

    public static final class StyleableObjectPropertyCreator {
        public static <T> StyleableObjectProperty<T> createStyleableObjectProperty(String name, Object bean, CssMetaData<? extends Styleable, T> cssMetaData,
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

        public static <T> StyleableObjectProperty<T> createStyleableObjectProperty(T defaultValue, String name, Object bean, CssMetaData<? extends Styleable, T> cssMetaData,
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
    }

    /* *************************************************************************
     *                                                                         *
     * Simple Styleable Property Creator Classes                               *
     *                                                                         *
     **************************************************************************/

    public static final class SimpleStyleableIntegerPropertyCreator {
        public static SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData) {
            return new SimpleStyleableIntegerProperty(cssMetaData);
        }

        public static SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Integer initialValue) {
            return new SimpleStyleableIntegerProperty(cssMetaData, initialValue);
        }

        public static SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name) {
            return new SimpleStyleableIntegerProperty(cssMetaData, bean, name);
        }

        public static SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Integer initialValue) {
            return new SimpleStyleableIntegerProperty(cssMetaData, bean, name, initialValue);
        }

        public static SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData,
                                                                                          Consumer<StyleableIntegerProperty> invalidatedCallback) {
            return new SimpleStyleableIntegerProperty(cssMetaData) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Integer initialValue,
                                                                                          Consumer<StyleableIntegerProperty> invalidatedCallback) {
            return new SimpleStyleableIntegerProperty(cssMetaData, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name,
                                                                                          Consumer<StyleableIntegerProperty> invalidatedCallback) {
            return new SimpleStyleableIntegerProperty(cssMetaData, bean, name) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableIntegerProperty createSimpleStyleableIntegerProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Integer initialValue,
                                                                                          Consumer<StyleableIntegerProperty> invalidatedCallback) {
            return new SimpleStyleableIntegerProperty(cssMetaData, bean, name, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }
    }

    public static final class SimpleStyleableDoublePropertyCreator {
        public static SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData) {
            return new SimpleStyleableDoubleProperty(cssMetaData);
        }

        public static SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Double initialValue) {
            return new SimpleStyleableDoubleProperty(cssMetaData, initialValue);
        }

        public static SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name) {
            return new SimpleStyleableDoubleProperty(cssMetaData, bean, name);
        }

        public static SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Double initialValue) {
            return new SimpleStyleableDoubleProperty(cssMetaData, bean, name, initialValue);
        }

        public static SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Consumer<StyleableDoubleProperty> invalidatedCallback) {
            return new SimpleStyleableDoubleProperty(cssMetaData) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Double initialValue,
                                                                                        Consumer<StyleableDoubleProperty> invalidatedCallback) {
            return new SimpleStyleableDoubleProperty(cssMetaData, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name,
                                                                                        Consumer<StyleableDoubleProperty> invalidatedCallback) {
            return new SimpleStyleableDoubleProperty(cssMetaData, bean, name) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableDoubleProperty createSimpleStyleableDoubleProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Double initialValue,
                                                                                        Consumer<StyleableDoubleProperty> invalidatedCallback) {
            return new SimpleStyleableDoubleProperty(cssMetaData, bean, name, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }
    }

    public static final class SimpleStyleableFloatPropertyCreator {
        public static SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData) {
            return new SimpleStyleableFloatProperty(cssMetaData);
        }

        public static SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Float initialValue) {
            return new SimpleStyleableFloatProperty(cssMetaData, initialValue);
        }

        public static SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name) {
            return new SimpleStyleableFloatProperty(cssMetaData, bean, name);
        }

        public static SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Float initialValue) {
            return new SimpleStyleableFloatProperty(cssMetaData, bean, name, initialValue);
        }

        public static SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Consumer<StyleableFloatProperty> invalidatedCallback) {
            return new SimpleStyleableFloatProperty(cssMetaData) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Float initialValue,
                                                                               Consumer<StyleableFloatProperty> invalidatedCallback) {
            return new SimpleStyleableFloatProperty(cssMetaData, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name,
                                                                               Consumer<StyleableFloatProperty> invalidatedCallback) {
            return new SimpleStyleableFloatProperty(cssMetaData, bean, name) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableFloatProperty createSimpleStyleableFloatProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Float initialValue,
                                                                               Consumer<StyleableFloatProperty> invalidatedCallback) {
            return new SimpleStyleableFloatProperty(cssMetaData, bean, name, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }
    }

    public static final class SimpleStyleableLongPropertyCreator {
        public static SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData) {
            return new SimpleStyleableLongProperty(cssMetaData);
        }

        public static SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Long initialValue) {
            return new SimpleStyleableLongProperty(cssMetaData, initialValue);
        }

        public static SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name) {
            return new SimpleStyleableLongProperty(cssMetaData, bean, name);
        }

        public static SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Long initialValue) {
            return new SimpleStyleableLongProperty(cssMetaData, bean, name, initialValue);
        }

        public static SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Consumer<StyleableLongProperty> invalidatedCallback) {
            return new SimpleStyleableLongProperty(cssMetaData) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Long initialValue, Consumer<StyleableLongProperty> invalidatedCallback) {
            return new SimpleStyleableLongProperty(cssMetaData, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name,
                                                                             Consumer<StyleableLongProperty> invalidatedCallback) {
            return new SimpleStyleableLongProperty(cssMetaData, bean, name) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableLongProperty createSimpleStyleableLongProperty(CssMetaData<? extends Styleable, Number> cssMetaData, Object bean, String name, Long initialValue,
                                                                             Consumer<StyleableLongProperty> invalidatedCallback) {
            return new SimpleStyleableLongProperty(cssMetaData, bean, name, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }
    }

    public static final class SimpleStyleableBooleanPropertyCreator {
        public static SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData) {
            return new SimpleStyleableBooleanProperty(cssMetaData);
        }

        public static SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Boolean initialValue) {
            return new SimpleStyleableBooleanProperty(cssMetaData, initialValue);
        }

        public static SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Object bean, String name) {
            return new SimpleStyleableBooleanProperty(cssMetaData, bean, name);
        }

        public static SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Object bean, String name, Boolean initialValue) {
            return new SimpleStyleableBooleanProperty(cssMetaData, bean, name, initialValue);
        }

        public static SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Consumer<StyleableBooleanProperty> invalidatedCallback) {
            return new SimpleStyleableBooleanProperty(cssMetaData) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Boolean initialValue,
                                                                                   Consumer<StyleableBooleanProperty> invalidatedCallback) {
            return new SimpleStyleableBooleanProperty(cssMetaData, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Object bean, String name,
                                                                                   Consumer<StyleableBooleanProperty> invalidatedCallback) {
            return new SimpleStyleableBooleanProperty(cssMetaData, bean, name) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableBooleanProperty createSimpleStyleableBooleanProperty(CssMetaData<? extends Styleable, Boolean> cssMetaData, Object bean, String name, Boolean initialValue,
                                                                                   Consumer<StyleableBooleanProperty> invalidatedCallback) {
            return new SimpleStyleableBooleanProperty(cssMetaData, bean, name, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }
    }

    public static final class SimpleStyleableStringPropertyCreator {
        public static SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData) {
            return new SimpleStyleableStringProperty(cssMetaData);
        }

        public static SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, String initialValue) {
            return new SimpleStyleableStringProperty(cssMetaData, initialValue);
        }

        public static SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, Object bean, String name) {
            return new SimpleStyleableStringProperty(cssMetaData, bean, name);
        }

        public static SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, Object bean, String name, String initialValue) {
            return new SimpleStyleableStringProperty(cssMetaData, bean, name, initialValue);
        }

        public static SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, Consumer<StyleableStringProperty> invalidatedCallback) {
            return new SimpleStyleableStringProperty(cssMetaData) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, String initialValue,
                                                                                 Consumer<StyleableStringProperty> invalidatedCallback) {
            return new SimpleStyleableStringProperty(cssMetaData, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, Object bean, String name,
                                                                                 Consumer<StyleableStringProperty> invalidatedCallback) {
            return new SimpleStyleableStringProperty(cssMetaData, bean, name) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static SimpleStyleableStringProperty createSimpleStyleableStringProperty(CssMetaData<? extends Styleable, String> cssMetaData, Object bean, String name, String initialValue,
                                                                                 Consumer<StyleableStringProperty> invalidatedCallback) {
            return new SimpleStyleableStringProperty(cssMetaData, bean, name, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }
    }

    public static final class SimpleStyleableObjectPropertyCreator {
        public static <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData) {
            return new SimpleStyleableObjectProperty<>(cssMetaData);
        }

        public static <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, T initialValue) {
            return new SimpleStyleableObjectProperty<>(cssMetaData, initialValue);
        }

        public static <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, Object bean, String name) {
            return new SimpleStyleableObjectProperty<>(cssMetaData, bean, name);
        }

        public static <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, Object bean, String name, T initialValue) {
            return new SimpleStyleableObjectProperty<>(cssMetaData, bean, name, initialValue);
        }

        public static <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, Consumer<StyleableObjectProperty<T>> invalidatedCallback) {
            return new SimpleStyleableObjectProperty<>(cssMetaData) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, T initialValue,
                                                                                        Consumer<StyleableObjectProperty<T>> invalidatedCallback) {
            return new SimpleStyleableObjectProperty<>(cssMetaData, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, Object bean, String name,
                                                                                        Consumer<StyleableObjectProperty<T>> invalidatedCallback) {
            return new SimpleStyleableObjectProperty<>(cssMetaData, bean, name) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }

        public static <T> SimpleStyleableObjectProperty<T> createSimpleStyleableObjectProperty(CssMetaData<? extends Styleable, T> cssMetaData, Object bean, String name, T initialValue,
                                                                                        Consumer<StyleableObjectProperty<T>> invalidatedCallback) {
            return new SimpleStyleableObjectProperty<>(cssMetaData, bean, name, initialValue) {
                @Override
                protected void invalidated() {
                    super.invalidated();
                    invalidatedCallback.accept(this);
                }
            };
        }
    }
}
