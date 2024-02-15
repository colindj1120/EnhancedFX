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
package io.github.colindj1120.materialdesignui.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.layout.Region;
import javafx.stage.Screen;

import java.util.Optional;

/**
 * Utility class providing static methods to perform common operations on JavaFX {@link Node} objects,
 * such as retrieving preferred dimensions, checking alignment properties, and ensuring readiness of
 * control skins and scenes. This class serves as a toolkit for JavaFX UI development, simplifying the
 * process of UI manipulation and layout tasks within the MaterialDesignUI library.
 *
 * <p>Key functionalities include:</p>
 * <ul>
 *     <li>Calculation of preferred sizes for nodes with optional offsets and default values.</li>
 *     <li>Checking for specific alignment types (left, right, center) of nodes.</li>
 *     <li>Determining the actual width and height of {@link Region} objects, ensuring they are laid out
 *     before measurement.</li>
 *     <li>Asynchronous operations to wait for a control's skin to be initialized or for a node to be
 *     added to a scene, enabling further manipulation or initialization steps.</li>
 *     <li>Checking the activation state of CSS pseudo-classes on controls, aiding in dynamic styling
 *     adjustments.</li>
 * </ul>
 *
 * <p>These utilities facilitate the dynamic and responsive design of JavaFX applications, ensuring
 * components are correctly sized, positioned, and styled according to the design requirements. By
 * abstracting common tasks into utility methods, developers can write cleaner, more maintainable UI
 * code.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * double width = NodeUtils.getNodePrefWidth(node);
 * boolean isRightAligned = NodeUtils.isRightAlignment(node.getAlignment());
 * NodeUtils.waitForSkin(control, () -> {
 *     // Perform actions after the control's skin is fully initialized
 * }, true);
 * </pre>
 *
 * <p>Note:</p>
 * This class is part of the MaterialDesignUI library, aimed at providing enhanced UI components and
 * utilities following Material Design guidelines in JavaFX applications.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Node
 * @see Control
 * @see Region
 * @see Pos
 * @see PseudoClass
 */
public class NodeUtils {
    private NodeUtils() {}

    /**
     * Retrieves the preferred width of a Node. If the Node is null, it returns 0.0.
     *
     * @param node
     *         the Node for which to get the preferred width
     *
     * @return the preferred width of the Node, or 0.0 if the Node is null
     */
    public static double getNodePrefWidth(Node node) {
        return Optional.ofNullable(node)
                       .map(n -> n.prefWidth(-1))
                       .orElse(0.0);
    }

    /**
     * Retrieves the preferred width of a Node. If the Node is null, it returns 0.0 plus the width offset.
     *
     * @param node        the Node for which to get the preferred width
     * @param widthOffset the offset to add to the preferred width
     * @return the preferred width of the Node, or 0.0 plus the width offset if the Node is null
     */
    public static double getNodePrefWidth(Node node, double widthOffset) {
        return Optional.ofNullable(node)
                       .map(n -> n.prefWidth(-1) + widthOffset)
                       .orElse(0.0);
    }

    /**
     * Retrieves the preferred width of a Node. If the Node is null, the default value is returned.
     *
     * @param defaultValue
     *         the default value to return if the Node is null
     * @param node
     *         the Node for which to calculate the preferred width
     *
     * @return the preferred width of the Node or the default value if the node is null
     */
    public static double getNodePrefWidth(double defaultValue, Node node) {
        return Optional.ofNullable(node)
                       .map(n -> n.prefWidth(-1))
                       .orElse(defaultValue);
    }

    /**
     * Retrieves the preferred width of a Node. If the Node is null, it returns the default value plus the width offset.
     *
     * @param defaultValue the default value to return if the Node is null
     * @param node the Node for which to calculate the preferred width
     * @param widthOffset the offset to add to the preferred width
     * @return the preferred width of the Node or the default value plus the width offset if the node is null
     */
    public static double getNodePrefWidth(double defaultValue, Node node, double widthOffset) {
        return Optional.ofNullable(node)
                       .map(n -> n.prefWidth(-1) + widthOffset)
                       .orElse(defaultValue);
    }

    /**
     * Retrieves the preferred height of a Node. If the Node is null, returns 0.0.
     *
     * @param node
     *         the Node of which to retrieve the preferred height
     *
     * @return the preferred height of the Node, or 0.0 if the Node is null
     */
    public static double getNodePrefHeight(Node node) {
        return Optional.ofNullable(node)
                       .map(n -> n.prefHeight(-1))
                       .orElse(0.0);
    }

    /**
     * Retrieves the preferred height of a Node. If the Node is null, it returns 0.0 plus the height offset.
     *
     * @param node the Node for which to get the preferred height
     * @param heightOffset the offset to add to the preferred height
     * @return the preferred height of the Node, or 0.0 plus the height offset if the Node is null
     */
    public static double getNodePrefHeight(Node node, double heightOffset) {
        return Optional.ofNullable(node)
                       .map(n -> n.prefHeight(-1)+heightOffset)
                       .orElse(0.0);
    }

    /**
     * Retrieves the preferred height of the given Node. If the Node is null, the default value is returned.
     *
     * @param defaultValue
     *         the default value to return if the Node is null
     * @param node
     *         the Node for which to retrieve the preferred height
     *
     * @return the preferred height of the Node, or the default value if the Node is null
     */
    public static double getNodePrefHeight(double defaultValue, Node node) {
        return Optional.ofNullable(node)
                       .map(n -> n.prefHeight(-1))
                       .orElse(defaultValue);
    }

    /**
     * Retrieves the preferred height of a Node. If the Node is null, it returns the default value plus the height offset.
     *
     * @param defaultValue the default value to return if the Node is null
     * @param node the Node for which to get the preferred height
     * @param heightOffset the offset to add to the preferred height
     * @return the preferred height of the Node or the default value plus the height offset if the node is null
     */
    public static double getNodePrefHeight(double defaultValue,Node node, double heightOffset) {
        return Optional.ofNullable(node)
                       .map(n -> n.prefHeight(-1)+heightOffset)
                       .orElse(defaultValue);
    }

    /**
     * Retrieves the region height if it isn't still laid out.
     *
     * @param region
     *         the Region of which to know the height
     *
     * @return the calculated height
     */
    public static double getRegionHeight(Region region) {
        Group group = new Group(region);
        Scene scene = new Scene(group);
        group.applyCss();
        group.layout();

        group.getChildren()
             .clear();
        return region.getHeight();
    }

    /**
     * Retrieves the region width if it isn't still laid out.
     *
     * @param region
     *         the Region of which to know the width
     *
     * @return the calculated width
     */
    public static double getRegionWidth(Region region) {
        Group group = new Group(region);
        Scene scene = new Scene(group);
        group.applyCss();
        group.layout();

        group.getChildren()
             .clear();
        return region.getWidth();
    }

    /**
     * Checks whether the given alignment is right alignment.
     *
     * @param alignment
     *         the alignment to check
     *
     * @return true if the alignment is right alignment, false otherwise
     */
    public static boolean isRightAlignment(Pos alignment) {
        return alignment == Pos.BASELINE_RIGHT || alignment == Pos.BOTTOM_RIGHT || alignment == Pos.CENTER_RIGHT || alignment == Pos.TOP_RIGHT;
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
        return alignment == Pos.BASELINE_LEFT || alignment == Pos.BOTTOM_LEFT || alignment == Pos.CENTER_LEFT || alignment == Pos.TOP_LEFT;
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
        return alignment == Pos.BASELINE_CENTER || alignment == Pos.BOTTOM_CENTER || alignment == Pos.CENTER || alignment == Pos.TOP_CENTER;
    }

    /**
     * Waits for the skin of the given control to be initialized and then performs the specified action.
     *
     * @param control
     *         the control to check for skin initialization
     * @param action
     *         the action to perform when the skin is not null
     * @param removeListener
     *         specifies whether to remove the listener after the action is performed
     */
    public static void waitForSkin(Control control, Runnable action, boolean removeListener) {
        Optional.ofNullable(control.getSkin())
                .ifPresentOrElse(skin -> action.run(), () -> control.skinProperty()
                                                                    .addListener(new ChangeListener<>() {
                                                                        @Override
                                                                        public void changed(ObservableValue<? extends Skin<?>> observable, Skin<?> oldSkin, Skin<?> newSkin) {
                                                                            Optional.ofNullable(newSkin)
                                                                                    .ifPresent(skin -> {
                                                                                        action.run();
                                                                                        if (removeListener) {
                                                                                            control.skinProperty()
                                                                                                   .removeListener(this);
                                                                                        }
                                                                                    });
                                                                        }
                                                                    }));
    }

    public static void waitForScene(Node node, Runnable action, boolean removeListener) {
        Optional.ofNullable(node.getScene())
                .ifPresentOrElse(scene -> action.run(), () -> node.sceneProperty()
                                                                  .addListener(new ChangeListener<>() {
                                                                      @Override
                                                                      public void changed(ObservableValue<? extends Scene> observable, Scene oldScene, Scene newScene) {
                                                                          Optional.ofNullable(newScene)
                                                                                  .ifPresent(skin -> {
                                                                                      action.run();
                                                                                      if (removeListener) {
                                                                                          node.sceneProperty()
                                                                                              .removeListener(this);
                                                                                      }
                                                                                  });
                                                                      }
                                                                  }));
    }


    /**
     * Determines if the specified pseudo class is active on the given control.
     *
     * @param control the control to check for the pseudo class
     * @param pseudoClass the pseudo class to check
     * @return true if the specified pseudo class is active on the control, false otherwise.
     */
    public static boolean isPseudoClassActive(Control control, PseudoClass pseudoClass) {
        return control.getPseudoClassStates()
                      .contains(pseudoClass);
    }
}
