package io.github.colindj1120.materialdesignui.workout;

import javafx.beans.property.ObjectPropertyBase;

public class ExerciseTemplateProperty<T> extends ObjectPropertyBase<T> {
    private final Object bean;
    private final String name;
    private final ExerciseTemplateColumn column;

    public ExerciseTemplateProperty(Object bean, String name, T initialValue, ExerciseTemplateColumn column) {
        this.bean = bean;
        this.name = name;
        set(initialValue);
        this.column = column;
    }

    @Override
    public Object getBean() {
        return bean;
    }

    @Override
    public String getName() {
        return name;
    }

    public ExerciseTemplateColumn getColumn() {
        return column;
    }
}

