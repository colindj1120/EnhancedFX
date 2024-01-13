package io.github.colindj1120.materialdesignui.controls;

import io.github.colindj1120.materialdesignui.styling.Stylesheets;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesCreator;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesManager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.css.*;
import javafx.css.converter.BooleanConverter;
import javafx.css.converter.SizeConverter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.List;

public class MDTextField extends Pane {
    private static final StyleablePropertiesManager STYLEABLE_PROPERTIES_MANAGER = new StyleablePropertiesManager(Pane.getClassCssMetaData());
    private static final StyleablePropertiesCreator STYLEABLE_PROPERTIES_CREATOR = new StyleablePropertiesCreator();
    private static final String STYLE = "md-text-field";

    private static final PseudoClass MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("max-char-count-enabled");
    private static final PseudoClass FLOATING_MODE = PseudoClass.getPseudoClass("float-mode");

    private final TextField textField           = new TextField();
    private final Label     characterCountLabel = new Label();
    private final Button    btn                 = new Button("Switch Enabled");

    private StyleableIntegerProperty maxCharacterCount;
    private StyleableBooleanProperty maxCharCountEnabled;



    static {
        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, Number>createCssMetaData("-max-char-count", SizeConverter.getInstance(), 50,
                                                                            node -> node.maxCharacterCount != null && node.isMaxCharCountEnabled() && !node.maxCharacterCount.isBound(),
                                                                            node -> node.maxCharacterCount);
        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, Boolean>createCssMetaData("-max-char-count-enabled", BooleanConverter.getInstance(), true,
                                                                             node -> !node.maxCharCountEnabled.isBound(), node -> node.maxCharCountEnabled);
    }

    public MDTextField() {
        super();
        setupStyle();
        setupStyleableProperties();
        setupCountLabel();
        setupTextField();

        btn.setOnAction(e -> setMaxCharCountEnabled(!isMaxCharCountEnabled()));

        this.getChildren()
            .addAll(btn, textField, characterCountLabel);
        setLayout();
    }

    private void setLayout() {
        textField.layoutYProperty()
                 .bind(Bindings.when(maxCharCountEnabled)
                               .then(characterCountLabel.heightProperty())
                               .otherwise(0));

        characterCountLabel.layoutXProperty()
                           .bind(textField.widthProperty()
                                          .subtract(characterCountLabel.widthProperty()));

        btn.layoutYProperty()
           .bind(textField.layoutYProperty()
                          .add(textField.heightProperty()
                                        .add(10)));

        minWidthProperty().bind(textField.widthProperty());
        maxWidthProperty().bind(textField.widthProperty());
        minHeightProperty().bind(Bindings.createDoubleBinding(() -> btn.getBoundsInParent()
                                                                       .getMaxY(), btn.boundsInParentProperty()));
        maxHeightProperty().bind(Bindings.createDoubleBinding(() -> btn.getBoundsInParent()
                                                                       .getMaxY(), btn.boundsInParentProperty()));
    }

    private void setupStyle() {
        this.getStyleClass()
            .add(STYLE);
        this.getStylesheets()
            .add(Stylesheets.MD_TEXT_FIELD.getStyleSheet());

        setStyle("-fx-border-style: solid; -fx-border-width: 1");
    }

    private void setupTextField() {
        textField.textProperty()
                 .addListener((observable, oldValue, newValue) -> {
                     if (newValue.length() > maxCharacterCount.get() && isMaxCharCountEnabled()) {
                         textField.setText(oldValue);
                     }
                 });
    }

    private void setupCountLabel() {
        IntegerProperty textLengthProperty = new SimpleIntegerProperty();
        textLengthProperty.bind(Bindings.length(textField.textProperty()));

        characterCountLabel.textProperty()
                           .bind(Bindings.concat(textLengthProperty.asString(), "/", maxCharacterCount.asString()));

        characterCountLabel.visibleProperty()
                           .bind(maxCharCountEnabled);

        maxCharCountEnabled.addListener((observable) -> pseudoClassStateChanged(MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS, maxCharCountEnabled.get()));

        maxCharacterCount.addListener((obs, oldMax, newMax) -> {
            if(isMaxCharCountEnabled() && textField.getLength() > newMax.intValue()) {
                textField.setText(textField.getText(0, getMaxCharacterCount()));
            }
        });

        characterCountLabel.getStyleClass()
                           .setAll("count-label");
    }

    private void setupStyleableProperties() {
        maxCharacterCount = STYLEABLE_PROPERTIES_CREATOR.createStyleableIntegerProperty(50, "maxCharacterCount", this,
                                                                                        STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-max-char-count"),
                                                                                        (prop, oldValue, oldValueSetter) -> {
                                                                                            int value = prop.get();
                                                                                            if (value < 0) {
                                                                                                if (prop.isBound()) {
                                                                                                    prop.unbind();
                                                                                                }
                                                                                                prop.set(oldValue);
                                                                                                throw new IllegalArgumentException("value cannot be negative.");
                                                                                            }
                                                                                            oldValueSetter.accept(value);  // update the old value
                                                                                        });

        maxCharCountEnabled = STYLEABLE_PROPERTIES_CREATOR.createSimpleStyleableBooleanProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-max-char-count-enabled"), this,
                                                                                                "maxCharacterCountEnabled", false);
    }

    private int getMaxCharacterCount() {
        return maxCharacterCount.get();
    }

    public StyleableIntegerProperty maxCharacterCountProperty() {
        return maxCharacterCount;
    }

    private void setMaxCharacterCount(int maxCharacterCount) {
        this.maxCharacterCount.set(maxCharacterCount);
    }

    private boolean isMaxCharCountEnabled() {
        return maxCharCountEnabled.get();
    }

    public StyleableBooleanProperty maxCharCountEnabledProperty() {
        return maxCharCountEnabled;
    }

    private void setMaxCharCountEnabled(boolean maxCharCountEnabled) {
        this.maxCharCountEnabled.set(maxCharCountEnabled);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return STYLEABLE_PROPERTIES_MANAGER.getCssMetaDataList();
    }
}
