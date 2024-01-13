package io.github.colindj1120.materialdesignui.controls;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.UnaryOperator;

public class CharacterLimitedTextField extends TextField {

    private final IntegerProperty maxCharacterCount     = new SimpleIntegerProperty(this, "maxCharacterCount", 100);
    private final AtomicBoolean   filterHasBeenCombined = new AtomicBoolean(false);
    public CharacterLimitedTextField() {
        super();

        // setup limit filter with initial maxCharacterCount
        setTextFormatter(new TextFormatter<>(getLimitFilter()));

        textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > maxCharacterCount.get()) {
                throw new IllegalArgumentException(String.format("Text length exceeds character limit of %d", maxCharacterCount.get()));
            }
        });

        textFormatterProperty().addListener((obs, oldFormatter, newFormatter) -> {
            if (newFormatter != null) {
                UnaryOperator<TextFormatter.Change> providedFilter = newFormatter.getFilter();

                UnaryOperator<TextFormatter.Change> combinedFilter = change -> {
                    change = providedFilter.apply(change);
                    return getLimitFilter().apply(change);
                };

                if (!filterHasBeenCombined.get()) {
                    filterHasBeenCombined.set(true);
                    setTextFormatter(new TextFormatter<>(combinedFilter));
                } else {
                    filterHasBeenCombined.set(false);
                }

            } else {
                filterHasBeenCombined.set(true);
                setTextFormatter(new TextFormatter<>(getLimitFilter()));
            }
        });
    }

    public int getMaxCharacterCount() {
        if (maxCharacterCount.get() == 0) {
            throw new IllegalStateException("maxCharacterCount must be initialized to use this component");
        }
        return maxCharacterCount.get();
    }

    public IntegerProperty maxCharacterCountProperty() {
        return maxCharacterCount;
    }

    public void setMaxCharacterCount(int maxCharacterCount) {
        String currentText = getText();
        if (currentText.length() > maxCharacterCount) {
            throw new IllegalArgumentException("New character limit cannot be less than current text length");
        }
        this.maxCharacterCount.set(maxCharacterCount);
        setTextFormatter(new TextFormatter<>(getLimitFilter()));
    }

    private UnaryOperator<TextFormatter.Change> getLimitFilter() {
        return change -> {
            if (change.isAdded()) {
                if (change.getControlNewText()
                          .length() > maxCharacterCount.get()) {
                    return null;
                }
            }
            return change;
        };
    }
}

