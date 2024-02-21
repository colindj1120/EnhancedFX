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
package io.github.colindj1120.materialdesignui.shapes;

import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.List;

/**
 * Represents a shape with asymmetrically rounded corners, extending the JavaFX Path class. This class allows for the creation of rectangles where each corner can have a distinct horizontal and
 * vertical radius, enabling the design of unique graphical elements for user interfaces.
 *
 * <p>
 * The {@code AsymmetricRoundedRectangle} is highly versatile, supporting dynamic changes to its properties such as size, position, and corner radii. It can be styled using JavaFX CSS, making it
 * suitable for a wide range of applications, from simple decorative shapes to complex UI components.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li><em>Individual Corner Radii:</em> Supports setting different radii for each corner's horizontal and vertical dimensions, allowing for the creation of complex shapes.</li>
 *     <li><em>Dynamic Property Binding:</em> All geometric properties (size, position, and corner radii) are exposed as bindable JavaFX properties, enabling reactive UI designs.</li>
 *     <li><em>Automatic Redrawing:</em> Automatically updates the graphical representation in response to property changes, ensuring visual consistency.</li>
 *     <li><em>Styling and Customization:</em> Can be styled through JavaFX CSS or programmatically via property setters, offering flexibility in design.</li>
 *     <li><em>Integration with JavaFX Scene Graph:</em> As a subclass of {@link Path}, it seamlessly integrates into the JavaFX scene graph, benefiting from its rendering optimizations and event
 *     handling.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * AsymmetricRoundedRectangle rectangle = new AsymmetricRoundedRectangle(100, 50, new CornerRadii(15, 25, 35, 45, false));
 * rectangle.setX(10);
 * rectangle.setY(20);
 * rectangle.setFill(Color.LIGHTGREY);
 * rectangle.setStroke(Color.DARKGREY);
 * rectangle.setStrokeWidth(2);
 *
 * // Adding to a scene
 * Pane pane = new Pane();
 * pane.getChildren().add(rectangle);
 * }</pre>
 *
 * <p>This class is designed to be straightforward to use while providing a powerful tool for UI development, making it a valuable addition to any JavaFX application requiring custom graphical
 * components.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Path
 * @see CornerRadii
 */
public class AsymmetricRoundedRectangle extends Path {
    private static final String                     STYLE_CLASS   = "AsymmetricRoundedRectangle";
    private static final StyleablePropertiesManager stylesManager = new StyleablePropertiesManager(Path.getClassCssMetaData());

    private final DoubleProperty x                          = new SimpleDoubleProperty(this, "x");
    private final DoubleProperty y                          = new SimpleDoubleProperty(this, "y");
    private final DoubleProperty width                      = new SimpleDoubleProperty(this, "width");
    private final DoubleProperty height                     = new SimpleDoubleProperty(this, "height");
    private final DoubleProperty upperLeftHorizontalRadius  = new SimpleDoubleProperty(this, "upperLeftHorizontalRadius");
    private final DoubleProperty upperLeftVerticalRadius    = new SimpleDoubleProperty(this, "upperLeftVerticalRadius");
    private final DoubleProperty upperRightHorizontalRadius = new SimpleDoubleProperty(this, "upperRightHorizontalRadius");
    private final DoubleProperty upperRightVerticalRadius   = new SimpleDoubleProperty(this, "upperRightVerticalRadius");
    private final DoubleProperty lowerRightHorizontalRadius = new SimpleDoubleProperty(this, "lowerRightHorizontalRadius");
    private final DoubleProperty lowerRightVerticalRadius   = new SimpleDoubleProperty(this, "lowerRightVerticalRadius");
    private final DoubleProperty lowerLeftHorizontalRadius  = new SimpleDoubleProperty(this, "lowerLeftHorizontalRadius");
    private final DoubleProperty lowerLeftVerticalRadius    = new SimpleDoubleProperty(this, "lowerLeftVerticalRadius");

    /**
     * Constructs an {@code AsymmetricRoundedRectangle} with specified width, height, and corner radii. This constructor initializes the rectangle at the origin (0,0).
     *
     * @param width
     *         the width of the rectangle
     * @param height
     *         the height of the rectangle
     * @param cornerRadii
     *         the radii of each corner, allowing for asymmetric corner rounding
     */
    public AsymmetricRoundedRectangle(double width, double height, CornerRadii cornerRadii) {
        setupAsymmetricRoundedRectangle(0.0, 0.0, width, height, cornerRadii);
    }

    /**
     * Constructs an {@code AsymmetricRoundedRectangle} with specified position, width, height, and corner radii.
     *
     * @param x
     *         the X coordinate for the upper-left corner of the rectangle
     * @param y
     *         the Y coordinate for the upper-left corner of the rectangle
     * @param width
     *         the width of the rectangle
     * @param height
     *         the height of the rectangle
     * @param cornerRadii
     *         the radii of each corner, allowing for asymmetric corner rounding
     */
    public AsymmetricRoundedRectangle(double x, double y, double width, double height, CornerRadii cornerRadii) {
        setupAsymmetricRoundedRectangle(x, y, width, height, cornerRadii);
    }

    /**
     * Initializes the {@code AsymmetricRoundedRectangle} with the given parameters and sets up the default styling and listeners.
     *
     * @param x
     *         the X coordinate for the upper-left corner of the rectangle
     * @param y
     *         the Y coordinate for the upper-left corner of the rectangle
     * @param width
     *         the width of the rectangle
     * @param height
     *         the height of the rectangle
     * @param cornerRadii
     *         the radii of each corner, specifying the rounded corners
     */
    private void setupAsymmetricRoundedRectangle(double x, double y, double width, double height, CornerRadii cornerRadii) {
        this.x.set(x);
        this.y.set(y);
        this.width.set(width);
        this.height.set(height);

        setCornerRadii(cornerRadii);

        setStroke(Color.BLACK);
        setFill(Color.WHITE);
        setStrokeWidth(1.0);

        draw();

        setupListeners();

        getStyleClass().setAll(STYLE_CLASS);
    }

    /**
     * Sets up listeners for the rectangle's properties to redraw the shape whenever any property changes.
     */
    private void setupListeners() {
        addDrawListener(x);
        addDrawListener(y);
        addDrawListener(width);
        addDrawListener(height);
        addDrawListener(upperLeftHorizontalRadius);
        addDrawListener(upperLeftVerticalRadius);
        addDrawListener(upperRightHorizontalRadius);
        addDrawListener(upperRightVerticalRadius);
        addDrawListener(lowerRightHorizontalRadius);
        addDrawListener(lowerRightVerticalRadius);
        addDrawListener(lowerLeftHorizontalRadius);
        addDrawListener(lowerLeftVerticalRadius);
    }

    /**
     * Adds a listener to a {@code DoubleProperty} that triggers the {@code draw} method upon property invalidation.
     *
     * @param doubleProperty
     *         the property to listen to
     */
    private void addDrawListener(DoubleProperty doubleProperty) {
        doubleProperty.addListener(invalidated -> draw());
    }

    /**
     * Redraws the {@code AsymmetricRoundedRectangle} based on its current properties. This method calculates the path to represent the rectangle with individually rounded corners.
     */
    private void draw() {
        getElements().clear();

        getElements().add(new MoveTo(x.get() + upperLeftHorizontalRadius.get(), y.get()));
        getElements().add(new LineTo(x.get() + width.get() - upperRightHorizontalRadius.get(), y.get()));
        getElements().add(new QuadCurveTo(x.get() + width.get(), y.get(), x.get() + width.get(), y.get() + upperRightVerticalRadius.get()));
        getElements().add(new LineTo(x.get() + width.get(), y.get() + height.get() - lowerRightVerticalRadius.get()));
        getElements().add(new QuadCurveTo(x.get() + width.get(), y.get() + height.get(), x.get() + width.get() - lowerRightHorizontalRadius.get(), y.get() + height.get()));
        getElements().add(new LineTo(x.get() + lowerLeftHorizontalRadius.get(), y.get() + height.get()));
        getElements().add(new QuadCurveTo(x.get(), y.get() + height.get(), x.get(), y.get() + height.get() - lowerLeftVerticalRadius.get()));
        getElements().add(new LineTo(x.get(), y.get() + upperLeftVerticalRadius.get()));
        getElements().add(new QuadCurveTo(x.get(), y.get(), x.get() + upperLeftHorizontalRadius.get(), y.get()));
        getElements().add(new ClosePath());
    }

    /**
     * Returns the {@code CornerRadii} object representing the current radii of the rectangle's corners.
     *
     * @return the current corner radii of the rectangle
     */
    public CornerRadii getCornerRadii() {
        return new CornerRadii(upperLeftHorizontalRadius.get(), upperRightHorizontalRadius.get(), lowerRightHorizontalRadius.get(), lowerLeftHorizontalRadius.get(), upperLeftVerticalRadius.get(),
                               upperRightVerticalRadius.get(), lowerRightVerticalRadius.get(), lowerLeftVerticalRadius.get(), false, false, false, false, false, false, false, false);
    }

    /**
     * Sets the corner radii of the rectangle. This method takes a {@code CornerRadii} object as parameter and applies its values to the rectangle.
     *
     * @param cornerRadii
     *         the new corner radii to apply
     */
    public void setCornerRadii(CornerRadii cornerRadii) {
        upperLeftHorizontalRadius.set(cornerRadii.getTopLeftHorizontalRadius());
        upperLeftVerticalRadius.set(cornerRadii.getTopLeftVerticalRadius());
        upperRightHorizontalRadius.set(cornerRadii.getTopRightHorizontalRadius());
        upperRightVerticalRadius.set(cornerRadii.getTopRightVerticalRadius());
        lowerRightHorizontalRadius.set(cornerRadii.getBottomRightHorizontalRadius());
        lowerRightVerticalRadius.set(cornerRadii.getBottomRightVerticalRadius());
        lowerLeftHorizontalRadius.set(cornerRadii.getBottomLeftHorizontalRadius());
        lowerLeftVerticalRadius.set(cornerRadii.getBottomLeftVerticalRadius());
    }

    /**
     * Returns the X coordinate for the upper-left corner of the rectangle.
     *
     * @return the X coordinate of the upper-left corner
     */
    public double getX() {
        return x.get();
    }

    /**
     * Returns the x property of the AsymmetricRoundedRectangle.
     *
     * @return the x property
     */
    public DoubleProperty xProperty() {
        return x;
    }

    /**
     * Sets the X coordinate for the upper-left corner of the rectangle.
     *
     * @param x
     *         the new X coordinate
     */
    public void setX(double x) {
        this.x.set(x);
    }

    /**
     * Returns the Y coordinate for the upper-left corner of the rectangle.
     *
     * @return the Y coordinate for the upper-left corner of the rectangle
     */
    public double getY() {
        return y.get();
    }

    /**
     * Returns the yProperty of the AsymmetricRoundedRectangle.
     *
     * @return the DoubleProperty representing the Y coordinate for the upper-left corner of the rectangle
     */
    public DoubleProperty yProperty() {
        return y;
    }

    /**
     * Sets the Y coordinate for the upper-left corner of the rectangle.
     *
     * @param y
     *         the new Y coordinate of the upper-left corner
     */
    public void setY(double y) {
        this.y.set(y);
    }

    /**
     * Returns the height of the AsymmetricRoundedRectangle.
     *
     * @return the height of the AsymmetricRoundedRectangle
     */
    public double getHeight() {
        return height.get();
    }

    /**
     * Returns the height property of the AsymmetricRoundedRectangle. This property represents the height of the rectangle.
     *
     * @return the height property of the rectangle
     */
    public DoubleProperty heightProperty() {
        return height;
    }

    /**
     * Sets the height of the AsymmetricRoundedRectangle.
     *
     * @param height
     *         the new height of the AsymmetricRoundedRectangle
     */
    public void setHeight(double height) {
        this.height.set(height);
    }

    /**
     * Returns the width of the AsymmetricRoundedRectangle.
     *
     * @return the width of the AsymmetricRoundedRectangle
     */
    public double getWidth() {
        return width.get();
    }

    /**
     * Returns the width property of the AsymmetricRoundedRectangle.
     *
     * @return the width property
     */
    public DoubleProperty widthProperty() {
        return width;
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width
     *         the new width of the rectangle
     */
    public void setWidth(double width) {
        this.width.set(width);
    }

    /**
     * Returns the horizontal radius of the upper left corner of this AsymmetricRoundedRectangle.
     *
     * @return the horizontal radius of the upper left corner
     */
    public double getUpperLeftHorizontalRadius() {
        return upperLeftHorizontalRadius.get();
    }

    /**
     * Returns the property for the upper left horizontal radius of the {@code AsymmetricRoundedRectangle}. This property allows modifying the upper left horizontal radius of the rectangle.
     *
     * @return the property for the upper left horizontal radius
     */
    public DoubleProperty upperLeftHorizontalRadiusProperty() {
        return upperLeftHorizontalRadius;
    }

    /**
     * Sets the upper-left horizontal radius of the {@code AsymmetricRoundedRectangle}.
     *
     * @param upperLeftHorizontalRadius
     *         the new upper left horizontal radius to apply
     */
    public void setUpperLeftHorizontalRadius(double upperLeftHorizontalRadius) {
        this.upperLeftHorizontalRadius.set(upperLeftHorizontalRadius);
    }

    /**
     * Returns the upper left vertical radius of the {@code AsymmetricRoundedRectangle}.
     *
     * @return the upper left vertical radius
     */
    public double getUpperLeftVerticalRadius() {
        return upperLeftVerticalRadius.get();
    }

    /**
     * Returns the DoubleProperty representing the upper left vertical radius of the AsymmetricRoundedRectangle.
     *
     * @return the DoubleProperty representing the upper left vertical radius
     */
    public DoubleProperty upperLeftVerticalRadiusProperty() {
        return upperLeftVerticalRadius;
    }

    /**
     * Sets the upper left vertical radius of the AsymmetricRoundedRectangle.
     *
     * @param upperLeftVerticalRadius
     *         the new upper left vertical radius to apply
     */
    public void setUpperLeftVerticalRadius(double upperLeftVerticalRadius) {
        this.upperLeftVerticalRadius.set(upperLeftVerticalRadius);
    }

    /**
     * Returns the horizontal radius of the upper right corner of this AsymmetricRoundedRectangle.
     *
     * @return the horizontal radius of the upper right corner
     */
    public double getUpperRightHorizontalRadius() {
        return upperRightHorizontalRadius.get();
    }

    /**
     * Returns the property for the upper right horizontal radius of the {@code AsymmetricRoundedRectangle}.
     *
     * @return the property for the upper right horizontal radius
     */
    public DoubleProperty upperRightHorizontalRadiusProperty() {
        return upperRightHorizontalRadius;
    }

    /**
     * Sets the upper right horizontal radius of the AsymmetricRoundedRectangle.
     *
     * @param upperRightHorizontalRadius
     *         the new upper right horizontal radius to apply
     */
    public void setUpperRightHorizontalRadius(double upperRightHorizontalRadius) {
        this.upperRightHorizontalRadius.set(upperRightHorizontalRadius);
    }

    /**
     * This method returns the upper right vertical radius of an AsymmetricRoundedRectangle.
     *
     * @return the upper right vertical radius of the rectangle
     */
    public double getUpperRightVerticalRadius() {
        return upperRightVerticalRadius.get();
    }

    /**
     * Returns the {@code DoubleProperty} representing the upper right vertical radius of the {@code AsymmetricRoundedRectangle}.
     *
     * @return the {@code DoubleProperty} representing the upper right vertical radius
     */
    public DoubleProperty upperRightVerticalRadiusProperty() {
        return upperRightVerticalRadius;
    }

    /**
     * Sets the upper right vertical radius of the {@code AsymmetricRoundedRectangle}. This method allows modifying the upper right vertical radius of the rectangle, which controls the roundness of
     * the upper right corner.
     *
     * @param upperRightVerticalRadius
     *         the new upper right vertical radius to apply
     */
    public void setUpperRightVerticalRadius(double upperRightVerticalRadius) {
        this.upperRightVerticalRadius.set(upperRightVerticalRadius);
    }

    /**
     * Returns the lower right horizontal radius of the AsymmetricRoundedRectangle.
     *
     * @return the lower right horizontal radius
     */
    public double getLowerRightHorizontalRadius() {
        return lowerRightHorizontalRadius.get();
    }

    /**
     * Returns the DoubleProperty representing the lower right horizontal radius of the AsymmetricRoundedRectangle.
     *
     * @return the DoubleProperty representing the lower right horizontal radius
     */
    public DoubleProperty lowerRightHorizontalRadiusProperty() {
        return lowerRightHorizontalRadius;
    }

    /**
     * Sets the lower right horizontal radius of the AsymmetricRoundedRectangle.
     *
     * @param lowerRightHorizontalRadius
     *         the new lower right horizontal radius to apply
     */
    public void setLowerRightHorizontalRadius(double lowerRightHorizontalRadius) {
        this.lowerRightHorizontalRadius.set(lowerRightHorizontalRadius);
    }

    /**
     * Returns the lower right vertical radius of the AsymmetricRoundedRectangle.
     *
     * @return the lower right vertical radius
     */
    public double getLowerRightVerticalRadius() {
        return lowerRightVerticalRadius.get();
    }

    /**
     *
     */
    public DoubleProperty lowerRightVerticalRadiusProperty() {
        return lowerRightVerticalRadius;
    }

    /**
     * Sets the lower right vertical radius of the AsymmetricRoundedRectangle. This method takes a double value as a parameter and applies it to the lower right vertical radius of the rectangle.
     *
     * @param lowerRightVerticalRadius
     *         the new lower right vertical radius to apply
     */
    public void setLowerRightVerticalRadius(double lowerRightVerticalRadius) {
        this.lowerRightVerticalRadius.set(lowerRightVerticalRadius);
    }

    /**
     * Returns the lower left horizontal radius of the AsymmetricRoundedRectangle.
     *
     * @return the lower left horizontal radius
     */
    public double getLowerLeftHorizontalRadius() {
        return lowerLeftHorizontalRadius.get();
    }

    /**
     * Returns the property for the lower left horizontal radius of the {@code AsymmetricRoundedRectangle}. This property allows modifying the lower left horizontal radius of the rectangle.
     *
     * @return the property for the lower left horizontal radius
     */
    public DoubleProperty lowerLeftHorizontalRadiusProperty() {
        return lowerLeftHorizontalRadius;
    }

    /**
     * Sets the lower left horizontal radius of the {@code AsymmetricRoundedRectangle}.
     *
     * @param lowerLeftHorizontalRadius
     *         the new lower left horizontal radius to apply
     */
    public void setLowerLeftHorizontalRadius(double lowerLeftHorizontalRadius) {
        this.lowerLeftHorizontalRadius.set(lowerLeftHorizontalRadius);
    }

    /**
     * Returns the lower left vertical radius of the AsymmetricRoundedRectangle.
     *
     * @return the lower left vertical radius
     */
    public double getLowerLeftVerticalRadius() {
        return lowerLeftVerticalRadius.get();
    }

    /**
     * Returns the DoubleProperty representing the lower left vertical radius of the {@code AsymmetricRoundedRectangle}.
     *
     * @return the DoubleProperty representing the lower left vertical radius
     */
    public DoubleProperty lowerLeftVerticalRadiusProperty() {
        return lowerLeftVerticalRadius;
    }

    /**
     * Sets the lower left vertical radius of the {@code AsymmetricRoundedRectangle}.
     *
     * @param lowerLeftVerticalRadius
     *         the new lower left vertical radius to apply
     */
    public void setLowerLeftVerticalRadius(double lowerLeftVerticalRadius) {
        this.lowerLeftVerticalRadius.set(lowerLeftVerticalRadius);
    }

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {return getClassCssMetaData();}

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {return stylesManager.getCssMetaDataList();}
}
