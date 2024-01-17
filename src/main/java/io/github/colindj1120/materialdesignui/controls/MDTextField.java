package io.github.colindj1120.materialdesignui.controls;

import io.github.colindj1120.materialdesignui.enums.EnabledStatus;
import io.github.colindj1120.materialdesignui.enums.FloatMode;
import io.github.colindj1120.materialdesignui.enums.TextFieldMode;
import io.github.colindj1120.materialdesignui.styling.Stylesheets;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesCreator.SimpleStyleableObjectPropertyCreator;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesCreator.StyleableIntegerPropertyCreator;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesCreator.StyleableObjectPropertyCreator;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.css.*;
import javafx.css.converter.EnumConverter;
import javafx.css.converter.SizeConverter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MDTextField extends Pane {
    private static final StyleablePropertiesManager STYLEABLE_PROPERTIES_MANAGER = new StyleablePropertiesManager(Pane.getClassCssMetaData());
    private static final String                     STYLE                        = "md-text-field";

    private static final PseudoClass MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS  = PseudoClass.getPseudoClass("max-char-count-enabled");
    private static final PseudoClass SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("supporting-text-enabled");
    private static final PseudoClass FLOAT_BORDER_MODE_PSEUDO_CLASS       = PseudoClass.getPseudoClass("float-border-mode");
    private static final PseudoClass FLOAT_ABOVE_MODE_PSEUDO_CLASS        = PseudoClass.getPseudoClass("float-above-mode");
    private static final PseudoClass FLOAT_INSIDE_MODE_PSEUDO_CLASS       = PseudoClass.getPseudoClass("float-above-mode");
    private static final PseudoClass FLOAT_DISABLED_MODE_PSEUDO_CLASS     = PseudoClass.getPseudoClass("float-disabled-mode");
    private static final PseudoClass TEXT_FIELD_OUTLINE_MODE_PSEUDO_CLASS = PseudoClass.getPseudoClass("outline-mode");
    private static final PseudoClass TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS  = PseudoClass.getPseudoClass("filled-mode");

    private final TextField textField           = new TextField();
    private final Label     characterCountLabel = new Label();
    private final Label     supportingTextLabel = new Label();
    private final Label     floatingTextLabel   = new Label();

    private StyleableIntegerProperty               maxCharacterCount;
    private StyleableObjectProperty<EnabledStatus> maxCharCountState;
    private StyleableObjectProperty<EnabledStatus> supportingTextState;
    private StyleableObjectProperty<FloatMode>     floatMode;
    private StyleableObjectProperty<TextFieldMode> textFieldMode;

    private static final ObjectProperty<Background> transparentBackgroundProperty = new SimpleObjectProperty<>(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

    private static final Map<FloatMode, Timeline> FLOAT_MODE_TIMELINE_MAP = new HashMap<>();

    static {
        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, Number>addCssMetaData("-max-char-count", SizeConverter.getInstance(), 50,
                                                                         node -> node.maxCharacterCount != null && !node.maxCharacterCount.isBound(), node -> node.maxCharacterCount);
        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, EnabledStatus>addCssMetaData("-max-char-count-state", EnumConverter.getEnumConverter(EnabledStatus.class), EnabledStatus.DISABLED,
                                                                                node -> node.maxCharCountState != null && !node.maxCharCountState.isBound(), node -> node.maxCharCountState);
        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, EnabledStatus>addCssMetaData("-supporting-text-state", EnumConverter.getEnumConverter(EnabledStatus.class), EnabledStatus.DISABLED,
                                                                                node -> node.supportingTextState != null && !node.supportingTextState.isBound(), node -> node.supportingTextState);
        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, FloatMode>addCssMetaData("-float-mode", EnumConverter.getEnumConverter(FloatMode.class), FloatMode.DISABLED,
                                                                            node -> node.floatMode != null && !node.floatMode.isBound(), node -> node.floatMode);
        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, TextFieldMode>addCssMetaData("-text-field-mode", EnumConverter.getEnumConverter(TextFieldMode.class), TextFieldMode.OUTLINED,
                                                                                node -> node.textFieldMode != null && !node.textFieldMode.isBound() &&
                                                                                        !(node.getTextFieldMode() == TextFieldMode.FILLED && node.getFloatMode() == FloatMode.BORDER),
                                                                                node -> node.textFieldMode);
    }

    public MDTextField() {
        super();
        setupStyle();
        setupStyleableProperties();
        setupTextField();
        setupCountLabel();
        setupSupportingTextLabel();
        setupFloatingTextLabel();

        this.getChildren()
            .addAll(textField, characterCountLabel, supportingTextLabel, floatingTextLabel);
        setLayout();

        pseudoClassStateChanged(TEXT_FIELD_OUTLINE_MODE_PSEUDO_CLASS, getTextFieldMode() == TextFieldMode.OUTLINED);
        pseudoClassStateChanged(TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS, getTextFieldMode() == TextFieldMode.FILLED);
        pseudoClassStateChanged(MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS, isMaxCharCountEnabled());
        pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS, isSupportingTextEnabled());
        pseudoClassStateChanged(FLOAT_DISABLED_MODE_PSEUDO_CLASS, floatMode.get() == FloatMode.DISABLED);
        pseudoClassStateChanged(FLOAT_BORDER_MODE_PSEUDO_CLASS, floatMode.get() == FloatMode.BORDER);
        pseudoClassStateChanged(FLOAT_INSIDE_MODE_PSEUDO_CLASS, floatMode.get() == FloatMode.INSIDE);
        pseudoClassStateChanged(FLOAT_ABOVE_MODE_PSEUDO_CLASS, floatMode.get() == FloatMode.ABOVE);
    }

    private void setupFloatingTextLabel() {
        floatingTextLabel.setText("Floating Text");
        floatingTextLabel.getStyleClass()
                         .setAll("floating-label");

        floatingTextLabel.backgroundProperty()
                         .bind(Bindings.when(Bindings.createBooleanBinding(() -> textFieldMode.get() == TextFieldMode.FILLED, textFieldMode))
                                       .then(transparentBackgroundProperty)
                                       .otherwise(textField.backgroundProperty()));

        floatingTextLabel.setOnMouseClicked(e -> textField.requestFocus());
    }

    private void setLayout() {
        textField.layoutYProperty()
                 .bind(Bindings.when(Bindings.createBooleanBinding(this::isMaxCharCountEnabled, maxCharCountState))
                               .then(characterCountLabel.heightProperty())
                               .otherwise(0));

        characterCountLabel.layoutXProperty()
                           .bind(textField.widthProperty()
                                          .subtract(characterCountLabel.widthProperty()));

        supportingTextLabel.layoutYProperty()
                           .bind(textField.layoutYProperty()
                                          .add(textField.heightProperty()
                                                        .add(3)));
        supportingTextLabel.layoutXProperty()
                           .bind(textField.layoutXProperty()
                                          .add(8));

        floatingTextLabel.layoutYProperty()
                         .bind(textField.layoutYProperty()
                                        .add(textField.heightProperty()
                                                      .divide(2))
                                        .subtract(floatingTextLabel.heightProperty()
                                                                   .divide(2)));

        floatingTextLabel.layoutXProperty()
                         .bind(textField.layoutXProperty()
                                        .add(8));

        minWidthProperty().bind(textField.widthProperty());
        maxWidthProperty().bind(textField.widthProperty());
        minHeightProperty().bind(Bindings.createDoubleBinding(() -> getChildren().stream()
                                                                                 .mapToDouble(c -> c.getBoundsInParent()
                                                                                                    .getMaxY())
                                                                                 .max()
                                                                                 .orElseThrow(() -> new IllegalArgumentException("MDTextField Max Y Position Not Found")), textField.layoutYProperty(),
                                                              characterCountLabel.layoutYProperty(), supportingTextLabel.layoutYProperty()));
        maxHeightProperty().bind(Bindings.createDoubleBinding(() -> getChildren().stream()
                                                                                 .mapToDouble(c -> c.getBoundsInParent()
                                                                                                    .getMaxY())
                                                                                 .max()
                                                                                 .orElseThrow(() -> new IllegalArgumentException("MDTextField Max Y Position Not Found")), textField.layoutYProperty(),
                                                              characterCountLabel.layoutYProperty(), supportingTextLabel.layoutYProperty()));

    }

    private void setupStyle() {
        this.getStyleClass()
            .add(STYLE);
        this.getStylesheets()
            .add(Stylesheets.MD_TEXT_FIELD.getStyleSheet());
    }

    private void setupTextField() {
        DoubleProperty baseHeight = new SimpleDoubleProperty();
        Platform.runLater(() -> baseHeight.set(textField.getHeight()));

        Scale scale = new Scale();
        scale.setPivotX(0);

        Translate translate = new Translate();
        floatingTextLabel.getTransforms()
                         .add(scale);
        floatingTextLabel.getTransforms()
                         .add(translate);

        Platform.runLater(() -> {
            buildScaleDownTimelines(scale, translate);

            Timeline scaleUpTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.2), new KeyValue(scale.xProperty(), 1.0), new KeyValue(scale.yProperty(), 1.0), new KeyValue(translate.xProperty(), 0),
                                 new KeyValue(translate.yProperty(), 0)));

            textField.focusedProperty()
                     .addListener((observable, oldValue, newValue) -> {
                         System.out.println(textField.getBoundsInParent());
                         System.out.println("Label: " + floatingTextLabel.getBoundsInParent());
                         textField.applyCss();
                         System.out.println("FOCUS");
                         if (floatMode.get() != FloatMode.DISABLED) {
                             if (newValue) {
                                 FLOAT_MODE_TIMELINE_MAP.get(floatMode.get())
                                                        .playFromStart();
                             } else {
                                 scaleUpTimeline.playFromStart();
                             }
                         }
                     });
        });

        textField.fontProperty().addListener((observable, oldFont, newFont) -> {
            System.out.println(textField.getBoundsInParent());
            System.out.println("REBUILD");
            baseHeight.set(textField.getHeight());
            FLOAT_MODE_TIMELINE_MAP.clear();
            buildScaleDownTimelines(scale, translate);
        });

        textField.minHeightProperty()
                 .bind(Bindings.when(floatingTextLabel.visibleProperty()
                                                      .and(Bindings.createBooleanBinding(() -> floatMode.get() == FloatMode.INSIDE, floatMode)))
                               .then(baseHeight.add(floatingTextLabel.heightProperty()))
                               .otherwise(baseHeight));

        textField.alignmentProperty()
                 .bind(Bindings.when(floatingTextLabel.visibleProperty())
                               .then(Pos.BASELINE_LEFT)
                               .otherwise(Pos.CENTER_LEFT));

        textField.textProperty()
                 .addListener((observable, oldValue, newValue) -> {
                     if (newValue.length() > maxCharacterCount.get() && isMaxCharCountEnabled()) {
                         textField.setText(oldValue);
                     }
                 });
    }

    private void buildScaleDownTimelines(Scale scale, Translate translate) {
        //@formatter:off
        Timeline scaleDownAboveTimeline = new Timeline(new KeyFrame(Duration.seconds(0.2),
                                                                    new KeyValue(scale.xProperty(), 0.75),
                                                                    new KeyValue(scale.yProperty(), 0.75),
                                                                    new KeyValue(translate.xProperty(), 0),
                                                                    new KeyValue(translate.yProperty(), textField.getBoundsInParent()
                                                                                                                 .getMinY() - floatingTextLabel.getBoundsInParent()
                                                                                                                                               .getMaxY() - 5)));
        Timeline scaleDownBorderTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.2), new KeyValue(scale.xProperty(), 0.75), new KeyValue(scale.yProperty(), 0.75), new KeyValue(translate.xProperty(), 0),
                             new KeyValue(translate.yProperty(), textField.getBoundsInParent()
                                                                          .getMinY() - floatingTextLabel.getBoundsInParent()
                                                                                                        .getMaxY() - 4 + (floatingTextLabel.getHeight() / 2))));
        Timeline scaleDownInsideTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.2), new KeyValue(scale.xProperty(), 0.75), new KeyValue(scale.yProperty(), 0.75), new KeyValue(translate.xProperty(), 0),
                             new KeyValue(translate.yProperty(), textField.getBoundsInParent()
                                                                          .getMinY() - floatingTextLabel.getBoundsInParent()
                                                                                                        .getMaxY() - 5 + floatingTextLabel.getHeight())));

        FLOAT_MODE_TIMELINE_MAP.putIfAbsent(FloatMode.ABOVE, scaleDownAboveTimeline);
        FLOAT_MODE_TIMELINE_MAP.putIfAbsent(FloatMode.BORDER, scaleDownBorderTimeline);
        FLOAT_MODE_TIMELINE_MAP.putIfAbsent(FloatMode.INSIDE, scaleDownInsideTimeline);
        //formatter:on
    }

    private void setupCountLabel() {
        IntegerProperty textLengthProperty = new SimpleIntegerProperty();
        textLengthProperty.bind(Bindings.length(textField.textProperty()));

        characterCountLabel.textProperty()
                           .bind(Bindings.concat(textLengthProperty.asString(), "/", maxCharacterCount.asString()));

        characterCountLabel.visibleProperty()
                           .bind(Bindings.createBooleanBinding(this::isMaxCharCountEnabled, maxCharCountState));

        maxCharCountState.addListener((observable) -> pseudoClassStateChanged(MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS, isMaxCharCountEnabled()));

        maxCharacterCount.addListener((obs, oldMax, newMax) -> {
            if (isMaxCharCountEnabled() && textField.getLength() > newMax.intValue()) {
                textField.setText(textField.getText(0, getMaxCharacterCount()));
            }
        });

        characterCountLabel.getStyleClass()
                           .setAll("count-label");
    }

    private void setupSupportingTextLabel() {
        supportingTextLabel.visibleProperty()
                           .bind(Bindings.createBooleanBinding(this::isSupportingTextEnabled, supportingTextState));

        supportingTextState.addListener(observable -> pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS, isSupportingTextEnabled()));

        supportingTextLabel.getStyleClass()
                           .setAll("supporting-text-label");

        supportingTextLabel.setText("Supporting Text");
    }

    private void setupStyleableProperties() {
        maxCharacterCount = StyleableIntegerPropertyCreator.createStyleableIntegerProperty(50, "maxCharacterCount", this, STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-max-char-count"),
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

        maxCharCountState = SimpleStyleableObjectPropertyCreator.createSimpleStyleableObjectProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-max-char-count-state"), this,
                                                                                                     "maxCharacterCountEnabled", EnabledStatus.DISABLED);

        supportingTextState = SimpleStyleableObjectPropertyCreator.createSimpleStyleableObjectProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-supporting-text-state"), this,
                                                                                                       "supportingTextState", EnabledStatus.DISABLED);

        floatMode = SimpleStyleableObjectPropertyCreator.createSimpleStyleableObjectProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-float-mode"), this, "floatMode", FloatMode.DISABLED,
                                                                                             (prop) -> {
                                                                                                 FloatMode mode = prop.get();
                                                                                                 pseudoClassStateChanged(FLOAT_DISABLED_MODE_PSEUDO_CLASS, mode == FloatMode.DISABLED);
                                                                                                 pseudoClassStateChanged(FLOAT_BORDER_MODE_PSEUDO_CLASS, mode == FloatMode.BORDER);
                                                                                                 pseudoClassStateChanged(FLOAT_INSIDE_MODE_PSEUDO_CLASS, mode == FloatMode.INSIDE);
                                                                                                 pseudoClassStateChanged(FLOAT_ABOVE_MODE_PSEUDO_CLASS, mode == FloatMode.ABOVE);
                                                                                             });

        textFieldMode = StyleableObjectPropertyCreator.createStyleableObjectProperty(TextFieldMode.OUTLINED, "textFieldMode", this, STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-text-field-mode"),
                                                                                     (prop, oldValue, oldValueSetter) -> {
                                                                                         TextFieldMode mode = prop.get();
                                                                                         if (mode == TextFieldMode.FILLED && floatMode.get() == FloatMode.BORDER) {
                                                                                             if (prop.isBound()) {
                                                                                                 prop.unbind();
                                                                                             }
                                                                                             prop.setValue(oldValue);
                                                                                             throw new IllegalArgumentException(
                                                                                                     "Float Mode cannot be set to border mode when the text field mode is FILLED");
                                                                                         }
                                                                                         pseudoClassStateChanged(TEXT_FIELD_OUTLINE_MODE_PSEUDO_CLASS, mode == TextFieldMode.OUTLINED);
                                                                                         pseudoClassStateChanged(TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS, mode == TextFieldMode.FILLED);
                                                                                         if (oldValue != mode) {
                                                                                             oldValueSetter.accept(mode);
                                                                                         }
                                                                                     });
    }

    public int getMaxCharacterCount() {
        return maxCharacterCount.get();
    }

    public StyleableIntegerProperty maxCharacterCountProperty() {
        return maxCharacterCount;
    }

    public void setMaxCharacterCount(int maxCharacterCount) {
        this.maxCharacterCount.set(maxCharacterCount);
    }

    private EnabledStatus getMaxCharCountState() {
        return maxCharCountState.get();
    }

    public StyleableObjectProperty<EnabledStatus> maxCharCountStateProperty() {
        return maxCharCountState;
    }

    private void setMaxCharCountState(EnabledStatus maxCharCountState) {
        this.maxCharCountState.set(maxCharCountState);
    }

    public boolean isMaxCharCountEnabled() {
        return maxCharCountState.get() == EnabledStatus.ENABLED;
    }

    private EnabledStatus getSupportingTextState() {
        return supportingTextState.get();
    }

    public StyleableObjectProperty<EnabledStatus> supportingTextStateProperty() {
        return supportingTextState;
    }

    private void setSupportingTextState(EnabledStatus supportingTextState) {
        this.supportingTextState.set(supportingTextState);
    }

    public boolean isSupportingTextEnabled() {
        return supportingTextState.get() == EnabledStatus.ENABLED;
    }

    private FloatMode getFloatMode() {
        return floatMode.get();
    }

    public StyleableObjectProperty<FloatMode> floatModeProperty() {
        return floatMode;
    }

    private void setFloatMode(FloatMode floatMode) {
        this.floatMode.set(floatMode);
    }

    private TextFieldMode getTextFieldMode() {
        return textFieldMode.get();
    }

    public StyleableObjectProperty<TextFieldMode> textFieldModeProperty() {
        return textFieldMode;
    }

    private void setTextFieldMode(TextFieldMode textFieldMode) {
        this.textFieldMode.set(textFieldMode);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return STYLEABLE_PROPERTIES_MANAGER.getCssMetaDataList();
    }
}
