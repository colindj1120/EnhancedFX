package io.github.colindj1120.materialdesignui.controls;

import io.github.colindj1120.materialdesignui.factories.styling.CssFactory;
import io.github.colindj1120.materialdesignui.factories.styling.StyleableObjectPropertyFactory;
import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.EnumConverter;
import javafx.geometry.Orientation;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static io.github.colindj1120.materialdesignui.utils.PropertyUtils.checkProperty;

public class ToggleNavigationBar extends Pane {
    protected static final StyleablePropertiesManager STYLES_MANAGER = new StyleablePropertiesManager(HBox.getClassCssMetaData());

    protected static final Orientation DEFAULT_ORIENTATION = Orientation.HORIZONTAL;
    private final          HBox        horizontalContainer = new HBox();
    private final          VBox        verticalContainer   = new VBox();

    private final ToggleGroup                         toggleGroup     = new ToggleGroup();
    private final LinkedHashMap<String, ToggleButton> toggleButtonMap = new LinkedHashMap<>();

    private final StyleableObjectProperty<Orientation> orientation = StyleableObjectPropertyFactory.<Orientation>builder()
                                                                                                   .bean(this)
                                                                                                   .name("orientation")
                                                                                                   .cssMetaData(STYLES_MANAGER.findCssMetaData("-orientation"))
                                                                                                   .defaultValue(DEFAULT_ORIENTATION)
                                                                                                   .build();

    private final BooleanProperty isHorizontal = new SimpleBooleanProperty(this, "isHorizontal", true);
    private final BooleanProperty isVertical   = new SimpleBooleanProperty(this, "isVertical", false);

    static {
        STYLES_MANAGER.addCssMetaData(CssFactory.<ToggleNavigationBar, Orientation>create()
                                                .property("-orientation")
                                                .converter(EnumConverter.getEnumConverter(Orientation.class))
                                                .initialValue(Orientation.HORIZONTAL)
                                                .isSettableFunction(node -> checkProperty(node.orientation))
                                                .propertyGetterFunction(node -> node.orientation));
    }

    public ToggleNavigationBar() {
        super();

        isHorizontal.bind(orientation.isEqualTo(Orientation.HORIZONTAL));
        isVertical.bind(orientation.isEqualTo(Orientation.VERTICAL));

        orientation.set(Orientation.HORIZONTAL);
        Circle ripple = new Circle(5);

        EnhancedToggleButton toggleButton = new EnhancedToggleButton("Toggle");

        horizontalContainer.getChildren()
                           .add(toggleButton);
        getChildren().add(horizontalContainer);
    }

    public void createToggleButton(String key, String buttonText, Consumer<Boolean> action) {
        ToggleButton toggleButton = new ToggleButton(buttonText);
        toggleButton.selectedProperty()
                    .addListener((obs, oldVal, newVal) -> action.accept(newVal));
        toggleButton.setToggleGroup(toggleGroup);
        toggleButtonMap.put(key, toggleButton);
    }

    public Optional<ToggleButton> getToggleButton(String key) {
        return Optional.ofNullable(toggleButtonMap.get(key));
    }

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    public ReadOnlyBooleanProperty isHorizontalProperty() {
        return isHorizontal;
    }

    public boolean isHorizontal() {
        return isHorizontal.get();
    }

    public ReadOnlyBooleanProperty isVerticalProperty() {
        return isVertical;
    }

    public boolean isVertical() {
        return isVertical.get();
    }

    //endregion Getters and Setters

    //region Style Properties
    //*****************************************************************
    // Style Properties
    //*****************************************************************

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    //@Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {return getClassCssMetaData();}

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {return STYLES_MANAGER.getCssMetaDataList();}

    //endregion Style Properties
}
