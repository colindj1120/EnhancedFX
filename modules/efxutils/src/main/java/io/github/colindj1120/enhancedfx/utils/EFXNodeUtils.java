/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/EnhancedFX).
 *
 * EnhancedFX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EnhancedFX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with EnhancedFX.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.utils;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Provides utility methods for calculating the dimensions of {@link Node} objects within JavaFX applications, and other node-related operations.
 *
 * <p>This class includes functionalities to calculate both width and height of {@link Node} instances, taking into account possible offsets and default values in cases where actual dimensions cannot be
 * directly obtained. It facilitates operations that require precise control over the visual layout and appearance of UI components before they are rendered on the scene.</p>
 *
 * <h2>Key features include:</h2>
 * <ul>
 *   <li>Dynamic calculation of node dimensions with or without offsets.</li>
 *   <li>Utility methods for determining alignment and applying CSS and layout in a temporary scene context.</li>
 *   <li>Facilitating the execution of actions once a property is not null.</li>
 *   <li>Checking whether a {@link PseudoClass} is active or not</li>
 * </ul>
 *
 * <p>This class is designed to support scenarios where node dimensions need to be known prior to rendering, such as in layout calculations or when dynamically adjusting the size or position of components
 * based on their content or container. It also aids in handling UI components that require initialization actions to be deferred until they are attached to a scene or have a fully initialized skin.</p>
 *
 * <p>Method overloads provide flexibility in usage, allowing for default dimension values to be specified and offsets to be applied, accommodating a wide range of layout and design requirements.</p>
 *
 * <p>Note: This is a static utility class, and thus it cannot be instantiated. All methods are accessible directly via the class name.</p>
 *
 * <h2>Usage examples:</h2>
 * <pre>
 * {@code
 *     double width = EFXNodeUtils.getNodeWidth(myNode, 10.0); // Calculate width with offset
 *     double height = EFXNodeUtils.getNodeHeight(myNode); // Calculate height without offset
 *     EFXNodeUtils.waitForScene(myNode, () -> {
 *         // Action to perform once myNode is part of a scene
 *     }, true);
 * }
 * </pre>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Node
 * @see Scene
 * @see Control
 * @see PseudoClass
 */
public class EFXNodeUtils {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private EFXNodeUtils() {}

    //region Get Node Height Functions
    //*****************************************************************
    // Get Node Height Functions
    //*****************************************************************

    /**
     * Calculates the height of a given {@link Node} with an additional height offset.
     *
     * <p>If the node's actual height cannot be determined (e.g., it is not yet displayed on a scene), a specified default value is returned.</p>
     *
     * @param node
     *         The {@link Node} whose height is to be calculated.
     * @param heightOffset
     *         The offset to add to the node's height.
     * @param defaultValue
     *         The default value to return if the node's height cannot be determined.
     *
     * @return The calculated height of the node plus the offset, or the default value if the height cannot be determined.
     */
    public static double getNodeHeight(Node node, double heightOffset, double defaultValue) {
        return getNodeMeasurement(node, mapToHeight(node, heightOffset), addToSceneAndGetHeight(node, heightOffset),
                                  defaultValue);
    }

    /**
     * Overloaded method to calculate the height of a given {@link Node}, returning a specified default value if the node's actual height cannot be determined.
     *
     * @param defaultValue
     *         The default value to return if the node's height cannot be determined.
     * @param node
     *         The {@link Node} whose height is to be calculated.
     *
     * @return The calculated height of the node, or the default value if the height cannot be determined.
     */
    public static double getNodeHeight(double defaultValue, Node node) {
        return getNodeHeight(node, 0.0, defaultValue);
    }

    /**
     * Overloaded method to calculate the height of a given {@link Node} with an additional height offset. If the node's actual height cannot be determined, zero is returned as the default value.
     *
     * @param node
     *         The {@link Node} whose height is to be calculated.
     * @param heightOffset
     *         The offset to add to the node's height.
     *
     * @return The calculated height of the node plus the offset, or zero if the height cannot be determined.
     */
    public static double getNodeHeight(Node node, double heightOffset) {
        return getNodeHeight(node, heightOffset, 0.0);
    }

    /**
     * Overloaded method to calculate the height of a given {@link Node}. If the node's actual height cannot be determined, zero is returned as the default value.
     *
     * @param node
     *         The {@link Node} whose height is to be calculated.
     *
     * @return The calculated height of the node, or zero if the height cannot be determined.
     */
    public static double getNodeHeight(Node node) {
        return getNodeHeight(node, 0.0, 0.0);
    }

    //endregion Get Node Height Functions

    //region Get Node Width Functions
    //*****************************************************************
    // Get Node Width Functions
    //*****************************************************************

    /**
     * Calculates the width of a given {@link Node} with an additional width offset.
     *
     * <p>If the node's actual width cannot be determined (e.g., it is not yet displayed on a scene), a specified default value is returned.</p>
     *
     * @param node
     *         The {@link Node} whose width is to be calculated.
     * @param widthOffset
     *         The offset to add to the node's width.
     * @param defaultValue
     *         The default value to return if the node's width cannot be determined.
     *
     * @return The calculated width of the node plus the offset, or the default value if the width cannot be determined.
     */
    public static double getNodeWidth(Node node, double widthOffset, double defaultValue) {
        return getNodeMeasurement(node, mapToWidth(node, widthOffset), addToSceneAndGetWidth(node, widthOffset), defaultValue);
    }

    /**
     * Overloaded method to calculate the width of a given {@link Node}, returning a specified default value if the node's actual width cannot be determined.
     *
     * @param defaultValue
     *         The default value to return if the node's width cannot be determined.
     * @param node
     *         The {@link Node} whose width is to be calculated.
     *
     * @return The calculated width of the node, or the default value if the width cannot be determined.
     */
    public static double getNodeWidth(double defaultValue, Node node) {
        return getNodeWidth(node, 0.0, defaultValue);
    }

    /**
     * Overloaded method to calculate the width of a given {@link Node} with an additional width offset. If the node's actual width cannot be determined, zero is returned as the default value.
     *
     * @param node
     *         The {@link Node} whose width is to be calculated.
     * @param widthOffset
     *         The offset to add to the node's width.
     *
     * @return The calculated width of the node plus the offset, or zero if the width cannot be determined.
     */
    public static double getNodeWidth(Node node, double widthOffset) {
        return getNodeWidth(node, widthOffset, 0.0);
    }

    /**
     * Overloaded method to calculate the width of a given {@link Node}. If the Node's actual width cannot be determined, zero is returned as the default value.
     *
     * @param node
     *         The {@link Node} whose width is to be calculated.
     *
     * @return The calculated width of the node, or zero if the width cannot be determined.
     */
    public static double getNodeWidth(Node node) {
        return getNodeWidth(node, 0.0, 0.0);
    }

    //endregion Get Node Width Functions

    //region Alignment Functions
    //*****************************************************************
    // Alignment Functions
    //*****************************************************************

    /**
     * Checks whether the given alignment is right alignment.
     *
     * @param alignment
     *         the alignment to check
     *
     * @return true if the alignment is right alignment, false otherwise
     */
    public static boolean isRightAlignment(Pos alignment) {
        return alignment == Pos.BASELINE_RIGHT || alignment == Pos.BOTTOM_RIGHT || alignment == Pos.CENTER_RIGHT ||
               alignment == Pos.TOP_RIGHT;
    }

    /**
     * Determines if the given alignment is left alignment.
     *
     * @param alignment
     *         the alignment to check
     *
     * @return true if the alignment is left alignment, false otherwise
     */
    public static boolean isLeftAlignment(Pos alignment) {
        return alignment == Pos.BASELINE_LEFT || alignment == Pos.BOTTOM_LEFT || alignment == Pos.CENTER_LEFT ||
               alignment == Pos.TOP_LEFT;
    }

    /**
     * Determines if the given alignment is a center alignment.
     *
     * @param alignment
     *         the position alignment to check
     *
     * @return true if the alignment is center alignment, false otherwise
     */
    public static boolean isCenterAlignment(Pos alignment) {
        return alignment == Pos.BASELINE_CENTER || alignment == Pos.BOTTOM_CENTER || alignment == Pos.CENTER ||
               alignment == Pos.TOP_CENTER;
    }

    //endregion Alignment Functions

    //region Wait For Functions
    //*****************************************************************
    // Wait For Functions
    //*****************************************************************

    /**
     * Waits for the skin of a {@link Control} to be initialized before executing a specified action.
     *
     * <p>If the skin is already initialized, the action is executed immediately. Otherwise, a listener is added to the control's skin property to defer the action until the skin is initialized. Optionally,
     * this listener can be removed after its first invocation to prevent the action from being executed multiple times.</p>
     *
     * @param control
     *         The {@link Control} whose skin initialization is to be awaited.
     * @param action
     *         The {@link Runnable} action to be executed once the skin is initialized.
     * @param removeListener
     *         A {@code boolean} flag indicating whether to remove the listener after executing the action.
     */
    public static void waitForSkin(Control control, Runnable action, boolean removeListener) {
        waitForNotNullValue(control.skinProperty(), action, removeListener);
    }

    /**
     * Waits for a {@link Node} to be attached to a {@link Scene} before executing a specified action.
     *
     * <p>If the node is already part of a scene, the action is executed immediately. Otherwise, a listener is added to the node's scene property to defer the action until the node is added to a scene.
     * Optionally, this listener can be removed after its first invocation to prevent the action from being executed multiple times.</p>
     *
     * @param node
     *         The {@link Node} whose attachment to a scene is to be awaited.
     * @param action
     *         The {@link Runnable} action to be executed once the node is part of a scene.
     * @param removeListener
     *         A {@code boolean} flag indicating whether to remove the listener after executing the action.
     */
    public static void waitForScene(Node node, Runnable action, boolean removeListener) {
        waitForNotNullValue(node.sceneProperty(), action, removeListener);
    }

    /**
     * Waits for a specified property to have a non-null value before executing a given action.
     *
     * <p>If the property's value is already non-null, the action is executed immediately. Otherwise, a listener is added to the property to defer the action until the property's value becomes non-null.
     * Optionally, this listener can be removed after its first invocation to ensure the action is executed only once, even if the property's value changes multiple times.</p>
     *
     * @param <T>
     *         The type of the property's value.
     * @param property
     *         The {@link ReadOnlyProperty} to be monitored for a non-null value.
     * @param action
     *         The {@link Runnable} action to be executed once the property has a non-null value.
     * @param removeListener
     *         A {@code boolean} flag indicating whether to remove the listener after executing the action.
     */
    public static <T> void waitForNotNullValue(ReadOnlyProperty<T> property, Runnable action, boolean removeListener) {
        Optional.ofNullable(property.getValue())
                .ifPresentOrElse(v -> action.run(), () -> property.addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
                        Optional.ofNullable(newValue)
                                .ifPresent(newVal -> {
                                    action.run();
                                    if (removeListener) {
                                        property.removeListener(this);
                                    }
                                });
                    }
                }));
    }

    //endregion Wait For Functions

    //region PseudoClass Functions
    //*****************************************************************
    // PseudoClass Functions
    //*****************************************************************

    /**
     * Determines if the specified pseudo class is active on the given control.
     *
     * @param control
     *         the control to check for the pseudo class
     * @param pseudoClass
     *         the pseudo class to check
     *
     * @return true if the specified pseudo class is active on the control, false otherwise.
     */
    public static boolean isPseudoClassActive(Control control, PseudoClass pseudoClass) {
        return control.getPseudoClassStates()
                      .contains(pseudoClass);
    }

    //endregion PseudoClass Functions

    //region Private Functions
    //*****************************************************************
    // Private Functions
    //*****************************************************************

    /**
     * Maps a {@link Scene} to the preferred height of a specified {@link Node} with an additional offset.
     *
     * @param node
     *         The {@link Node} whose preferred height is to be calculated.
     * @param offset
     *         The offset to add to the node's preferred height.
     *
     * @return A {@link Function} that, when applied to a {@link Scene}, returns the preferred height of the node plus the offset.
     */
    private static Function<Scene, Double> mapToHeight(Node node, double offset) {
        return scene -> node.prefHeight(-1) + offset;
    }

    /**
     * Maps a {@link Scene} to the preferred width of a specified {@link Node} with an additional offset.
     *
     * @param node
     *         The {@link Node} whose preferred width is to be calculated.
     * @param offset
     *         The offset to add to the node's preferred width.
     *
     * @return A {@link Function} that, when applied to a {@link Scene}, returns the preferred width of the node plus the offset.
     */
    private static Function<Scene, Double> mapToWidth(Node node, double offset) {
        return scene -> node.prefWidth(-1) + offset;
    }

    /**
     * Creates a {@link Supplier} that, when called, adds a {@link Node} to a temporary scene to calculate its actual height with an additional offset.
     *
     * <p>This is useful for getting the actual rendered height of a node that is not yet part of a scene.</p>
     *
     * @param node
     *         The {@link Node} to be added to a temporary scene.
     * @param offset
     *         The offset to add to the node's height.
     *
     * @return A {@link Supplier} of {@link Optional<Double>} that returns the height of the node plus the offset.
     */
    private static Supplier<Optional<Double>> addToSceneAndGetHeight(Node node, double offset) {
        return () -> {
            addToScene(node);
            return Optional.of(node.prefHeight(-1) + offset);
        };
    }

    /**
     * Creates a {@link Supplier} that, when called, adds a {@link Node} to a temporary scene to calculate its actual width with an additional offset.
     *
     * <p>This is useful for getting the actual rendered width of a node that is not yet part of a scene.</p>
     *
     * @param node
     *         The {@link Node} to be added to a temporary scene.
     * @param offset
     *         The offset to add to the node's width.
     *
     * @return A {@link Supplier} of {@link Optional<Double>} that returns the width of the node plus the offset.
     */
    private static Supplier<Optional<Double>> addToSceneAndGetWidth(Node node, double offset) {
        return () -> {
            addToScene(node);
            return Optional.of(node.prefWidth(-1) + offset);
        };
    }

    /**
     * Retrieves the measurement (either width or height) of a {@link Node}, taking into account its current scene.
     *
     * <p>If the node is not part of a scene, it temporarily adds the node to a scene to calculate the measurement. This method combines both direct measurement and a fallback strategy to ensure a value is
     * always returned.</p>
     *
     * @param node
     *         The {@link Node} whose measurement is to be calculated.
     * @param measurementFunction
     *         A {@link Function} that calculates the measurement based on the node's current scene.
     * @param afterAddingToSceneFunc
     *         A {@link Supplier} that provides the measurement after adding the node to a temporary scene.
     * @param defaultValue
     *         The default value to return if no measurement can be calculated.
     *
     * @return The calculated measurement (width or height) of the node, or the default value if the measurement cannot be determined.
     */
    private static double getNodeMeasurement(Node node, Function<Scene, Double> measurementFunction,
                                             Supplier<Optional<Double>> afterAddingToSceneFunc, double defaultValue) {
        return Optional.ofNullable(node)
                       .flatMap(r -> Optional.ofNullable(r.getScene())
                                             .map(measurementFunction)
                                             .or(afterAddingToSceneFunc))
                       .orElse(defaultValue);
    }

    /**
     * Temporarily adds a {@link Node} to a new {@link Scene} to allow CSS and layout passes to be applied.
     *
     * <p>This method is used internally to calculate actual rendered dimensions of a node that is not yet part of a scene. After applying CSS and layout, the node is removed from the temporary scene.</p>
     *
     * @param node
     *         The {@link Node} to be temporarily added to a new scene.
     */
    private static void addToScene(Node node) {
        Group group = new Group(node);
        Scene scene = new Scene(group);
        group.applyCss();
        group.layout();

        group.getChildren()
             .clear();
    }

    //endregion Private Functions
}
