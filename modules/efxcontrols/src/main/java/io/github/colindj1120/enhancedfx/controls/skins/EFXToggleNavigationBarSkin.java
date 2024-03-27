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
package io.github.colindj1120.enhancedfx.controls.skins;

import io.github.colindj1120.enhancedfx.base.collections.ObservableLinkedList;
import io.github.colindj1120.enhancedfx.base.collections.base.UpdateActions;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.efxbuttons.EFXToggleButton;
import io.github.colindj1120.enhancedfx.controls.complexcontrol.EFXToggleNavigationBar;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A custom skin for the {@link EFXToggleNavigationBar} control in the EnhancedFX framework, designed to dynamically adjust the layout and presentation of a navigation bar comprising toggle buttons. This skin
 * supports both horizontal and vertical orientations and dynamically updates to reflect changes in the control's properties and the toggle buttons it contains.
 *
 * <p>The {@code EFXToggleNavigationBarSkin} extends {@link SkinBase} and integrates closely with {@link EFXToggleNavigationBar}, providing a flexible and customizable appearance for the navigation bar. It
 * manages a collection of {@link EFXToggleButton} instances, arranging them according to the specified orientation of the navigation bar and responding to user interactions.</p>
 *
 * <p><em>Key Features:</em></p>
 * <ul>
 *   <li><em>Dynamic Layout Management:</em> Automatically switches between horizontal ({@link HBox}) and vertical ({@link VBox})
 *   containers based on the orientation property of the {@link EFXToggleNavigationBar}.</li>
 *   <li><em>Responsive to Changes:</em> Listens for modifications to the list of toggle buttons (additions, removals, replacements)
 *   and updates the UI accordingly, ensuring that the navigation bar's appearance is always current.</li>
 *   <li><em>Customizable Appearance:</em> Leverages JavaFX CSS for styling, allowing developers to customize the look and feel
 *   of the navigation bar and its buttons through external stylesheets.</li>
 *   <li><em>Enhanced User Experience:</em> Supports the grouping of toggle buttons into a {@link ToggleGroup}, enabling the
 *   creation of mutually exclusive selection groups for application navigation.</li>
 * </ul>
 *
 * <p><em>Implementation Details:</em></p>
 * The skin uses two primary containers (`HBox` for horizontal orientation and `VBox` for vertical orientation) to arrange the toggle buttons.
 * A property-binding mechanism is employed to switch between these containers based on the navigation bar's orientation property.
 * This skin also registers a {@link ListChangeListener} to the {@link ObservableLinkedList} of {@link EFXToggleButton},
 * handling the dynamic addition and removal of buttons from the navigation bar.
 *
 * <p>Action listeners are attached to the {@link EFXToggleNavigationBar}'s properties and the list of toggle buttons to ensure that any changes (such as orientation change or button list
 * modifications) are reflected in the UI without requiring manual updates.</p>
 *
 * <p><em>Usage:</em></p>
 * This skin is automatically applied to the {@link EFXToggleNavigationBar} upon its instantiation and requires no additional setup from the developer. However, customization can be achieved through
 * CSS and by manipulating the {@link EFXToggleNavigationBar}'s properties and toggle button list programmatically.
 *
 * <p><em>Example:</em></p>
 * <pre>
 * EFXToggleNavigationBar navBar = new EFXToggleNavigationBar();
 * navBar.setOrientation(Orientation.HORIZONTAL); // Sets the orientation to horizontal
 * navBar.addToggleButton("Home", event -> System.out.println("Home selected"));
 * </pre>>
 *
 * <p><em>Customization:</em></p>
 * The appearance of the navigation bar and its toggle buttons can be customized using JavaFX CSS. For example, to
 * change the background color of the navigation bar, you can define the following rule in your CSS file:
 * ```css
 * .toggle-navigation-bar {
 *     -fx-background-color: #f0f0f0;
 * }
 * ```
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXToggleNavigationBar
 * @see EFXToggleButton
 * @see ObservableLinkedList
 * @see SkinBase
 */
public class EFXToggleNavigationBarSkin extends SkinBase<EFXToggleNavigationBar> {
    private final HBox                       horizontalContainer   = new HBox();
    private final VBox                       verticalContainer     = new VBox();
    private       SimpleObjectProperty<Pane> currentContainer;
    private final ListChangeListener<Node>   layoutRequestListener = change -> getSkinnable().requestLayout();

    public static EFXToggleNavigationBarSkin create(EFXToggleNavigationBar control) {
        EFXToggleNavigationBarSkin efxToggleNavigationBarSkin = new EFXToggleNavigationBarSkin(control);
        efxToggleNavigationBarSkin.initialize();
        return efxToggleNavigationBarSkin;
    }

    private void initialize() {
        EFXToggleNavigationBar control = this.getSkinnable();
        this.currentContainer = new SimpleObjectProperty<>(this, "currentContainer");

        this.currentContainer.addListener(this.createNavigationBarChangeListener());
        this.currentContainer.bind(Bindings.createObjectBinding(() -> control.isHorizontal() ? this.horizontalContainer : this.verticalContainer, control.isHorizontalProperty()));
        this.addActionListener(control.getToggleButtonList());
        this.getSkinnable()
            .requestLayout();
    }

    /**
     * Constructs a new skin for the {@link EFXToggleNavigationBar}. This skin implementation manages the layout and styling of the {@link EFXToggleNavigationBar} control, dynamically adjusting its appearance
     * and behavior based on the control's properties and the list of toggle buttons it contains.
     *
     * <p>
     * Upon instantiation, this constructor initializes and binds listeners to the control's properties, including the orientation (horizontal or vertical) and the list of toggle buttons. It ensures that the
     * navigation bar's content and layout are updated in response to changes in these properties.
     * </p>
     *
     * <p>
     * Specifically, it:
     * <ul>
     *     <li>Binds a listener to the current container property. This listener responds to changes in the navigation bar's container, swapping between horizontal and vertical layouts as needed
     *     based on the control's orientation.</li>
     *     <li>Creates a binding for the current container, determining whether to use a horizontal or vertical container based on the {@code isHorizontal} property of the {@link EFXToggleNavigationBar}
     *     control. This binding ensures that the appropriate container is used to display the toggle buttons according to the current orientation.</li>
     *     <li>Registers an action listener to the list of toggle buttons. This listener handles the addition, removal, and replacement of toggle buttons in the navigation bar, updating the UI
     *     accordingly.</li>
     *     <li>Requests a layout update for the {@link EFXToggleNavigationBar} control, ensuring that its appearance is refreshed to reflect any initial settings or changes.</li>
     * </ul>
     * </p>
     *
     * @param control
     *         the {@link EFXToggleNavigationBar} control this skin is associated with. This control provides the necessary context and properties that influence the skin's behavior and appearance.
     */
    public EFXToggleNavigationBarSkin(EFXToggleNavigationBar control) {
        super(control);
    }

    /**
     * Creates a change listener for the navigation bar's container. This listener is responsible for handling changes to the navigation bar's container pane.
     *
     * <p>When the container pane changes, the listener will remove the old container from the navigation bar's children (if non-null) and add the new container to the navigation bar's children (if
     * non-null). It ensures that layout request listeners are properly managed during the transition.</p>
     *
     * @return a {@link ChangeListener} of {@link Pane} that handles replacing the navigation bar's container pane.
     */
    private ChangeListener<Pane> createNavigationBarChangeListener() {
        return (obs, oldContainer, newContainer) -> {
            removeOldContainer(oldContainer);
            addNewContainer(newContainer);
        };
    }

    /**
     * Removes the old container pane from the navigation bar's children. It also detaches any layout request listeners from the old container to prevent memory leaks or undesired behavior.
     *
     * <p>This method is a part of the container change handling process, ensuring that the UI is updated correctly when the navigation bar's container is replaced.</p>
     *
     * @param oldContainer
     *         the old container {@link Pane} to be removed.
     */
    private void removeOldContainer(Pane oldContainer) {
        if (Objects.nonNull(oldContainer)) {
            getChildren().remove(oldContainer);
            oldContainer.getChildren()
                        .removeListener(layoutRequestListener);
        }
    }

    /**
     * Adds the new container pane to the navigation bar's children. It sets the new container to be unmanaged, meaning that its layout will not be automatically computed during the layout pass, and attaches a
     * layout request listener to it. This method also ensures that any child elements of the navigation bar are added to the new container.
     *
     * <p>This operation is a key part of updating the navigation bar's UI to reflect a change in its container pane.</p>
     *
     * @param newContainer
     *         the new container {@link Pane} to be added.
     */
    private void addNewContainer(Pane newContainer) {
        if (Objects.nonNull(newContainer)) {
            newContainer.setManaged(false);
            newContainer.getChildren()
                        .addListener(layoutRequestListener);
            getChildren().add(newContainer);
            addChildElementsToNewContainer(newContainer);
        }
    }

    /**
     * Adds child elements of the navigation bar to the new container. This typically involves iterating over a collection of UI elements associated with the navigation bar (e.g., toggle buttons) and adding
     * them to the new container's children.
     *
     * <p>This method ensures that after a container change, all relevant child elements are present in the new container, maintaining the navigation bar's functionality and appearance.</p>
     *
     * @param newContainer
     *         the new container {@link Pane} that will receive the child elements.
     */
    private void addChildElementsToNewContainer(Pane newContainer) {
        getSkinnable().getToggleButtonList()
                      .forEach(element -> newContainer.getChildren()
                                                      .add(element));
    }

    /**
     * Registers an action listener to an {@link ObservableLinkedList} of {@link EFXToggleButton}. This listener responds to changes in the list, such as additions, removals, and replacements of toggle buttons.
     * Based on the type of change, it delegates to specific methods to update the UI accordingly.
     *
     * @param buttonList
     *         the {@link ObservableLinkedList} to which the listener is added.
     */
    private void addActionListener(ObservableLinkedList<EFXToggleButton> buttonList) {
        buttonList.addActionListener((listChangeItem) -> {
            switch (listChangeItem.getListAction()) {
                case UpdateActions.ADDED -> addToggleButton(listChangeItem.getNewElement());
                case UpdateActions.BULK_ADD -> addBulkToggleButtons(listChangeItem.getNewList());
                case UpdateActions.REMOVED -> removeToggleButton(listChangeItem.getOldElement());
                case UpdateActions.BULK_REMOVE -> removeBulkToggleButtons(listChangeItem.getOldList(), listChangeItem.getNewList());
                case UpdateActions.REPLACED -> replaceToggleButton(listChangeItem.getOldElement(), listChangeItem.getNewElement());
                case UpdateActions.BULK_REPLACED -> replaceBulkToggleButtons(listChangeItem.getOldList(), listChangeItem.getNewList());
                case UpdateActions.CLEARED -> clearContainer();
            }
        });
    }

    /**
     * Replaces a list of old toggle buttons with a new list of toggle buttons in bulk. This method validates that the old and new lists have the same size before proceeding with replacements to maintain
     * consistency.
     *
     * @param oldList
     *         the list of toggle buttons being replaced.
     * @param newList
     *         the list of toggle buttons that will replace the old buttons.
     *
     * @throws IllegalArgumentException
     *         if the old and new lists do not have the same size.
     */
    private void replaceBulkToggleButtons(List<EFXToggleButton> oldList, List<EFXToggleButton> newList) {
        if (oldList.size() != newList.size()) {
            throw new IllegalArgumentException("Old and new lists must have the same size");
        }
        IntStream.range(0, oldList.size())
                 .filter(i -> !oldList.get(i)
                                      .equals(newList.get(i)))
                 .boxed()
                 .toList()
                 .forEach(i -> replaceToggleButton(oldList.get(i), newList.get(i)));
    }

    /**
     * Replaces an old toggle button with a new toggle button within the UI container. If the old toggle button is found within the container's children, it is replaced by the new toggle button at the same
     * index.
     *
     * @param oldElement
     *         the toggle button being replaced.
     * @param newElement
     *         the toggle button that replaces the old button.
     */
    private void replaceToggleButton(EFXToggleButton oldElement, EFXToggleButton newElement) {
        ObservableList<Node> children = getCurrentContainer().getChildren();
        int                  index    = children.indexOf(oldElement);
        if (index != -1) {
            children.set(index, newElement);
        }
    }

    /**
     * Clears all toggle buttons from the current container. This method is typically called when the list of toggle buttons is cleared, and it ensures that the UI reflects this change by removing all button
     * nodes.
     */
    private void clearContainer() {
        getCurrentContainer().getChildren()
                             .clear();
    }

    /**
     * Adds a list of toggle buttons to the UI container in bulk. This method filters the list to include only instances of {@link EFXToggleButton} and then adds each button to the container.
     *
     * @param buttonList
     *         the list of buttons to be added to the container.
     */
    private void addBulkToggleButtons(List<?> buttonList) {
        buttonList.stream()
                  .filter(EFXToggleButton.class::isInstance)
                  .map(EFXToggleButton.class::cast)
                  .forEach(this::addToggleButton);
    }

    /**
     * Adds a single toggle button to the current UI container. This method is typically invoked when a new toggle button is added to the list and needs to be reflected in the UI.
     *
     * @param toggleButton
     *         the {@link EFXToggleButton} to be added to the container.
     */
    private void addToggleButton(EFXToggleButton toggleButton) {
        getCurrentContainer().getChildren()
                             .add(toggleButton);
    }

    /**
     * Removes a single toggle button from the current UI container. This method is typically invoked when a toggle button is removed from the list and should no longer be displayed in the UI.
     *
     * @param toggleButton
     *         the {@link EFXToggleButton} to be removed from the container.
     */
    private void removeToggleButton(EFXToggleButton toggleButton) {
        getCurrentContainer().getChildren()
                             .remove(toggleButton);
    }

    /**
     * Removes a list of old toggle buttons from the UI container in bulk, based on a comparison between an old list and a new list of toggle buttons. This method calculates which buttons have been removed by
     * comparing the two lists and then removes those buttons from the container.
     *
     * @param oldList
     *         the list of toggle buttons before the bulk removal operation.
     * @param newList
     *         the list of toggle buttons after the bulk removal operation.
     */
    private void removeBulkToggleButtons(List<EFXToggleButton> oldList, List<EFXToggleButton> newList) {
        List<EFXToggleButton> removedToggleButtons = getRemovedToggleButtons(oldList, newList);
        removedToggleButtons.forEach(this::removeToggleButton);
    }

    /**
     * Determines which toggle buttons have been removed by comparing an old list of toggle buttons to a new list. This method is used in bulk removal operations to identify which buttons need to be removed
     * from the UI container.
     *
     * @param oldList
     *         the list of toggle buttons before the removal operation.
     * @param newList
     *         the list of toggle buttons after the removal operation.
     *
     * @return a list of {@link EFXToggleButton} that have been removed.
     */
    private List<EFXToggleButton> getRemovedToggleButtons(List<EFXToggleButton> oldList, List<EFXToggleButton> newList) {
        return oldList.stream()
                      .filter(button -> !newList.contains(button))
                      .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computeMinWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        double width = getCurrentContainer().prefWidth(-1);

        return width + rightInset + leftInset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().getMaxWidth() == Double.MAX_VALUE) {return Double.MAX_VALUE;}
        return getSkinnable().prefWidth(-1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        double height = getCurrentContainer().prefHeight(-1);

        return height + topInset + bottomInset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().getMaxHeight() == Double.MAX_VALUE) {return Double.MAX_VALUE;}
        return getSkinnable().prefHeight(-1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        getCurrentContainer().resizeRelocate(x, y, w, h);
    }

    /**
     * Returns the current container pane.
     *
     * @return the current container pane as a Pane object.
     */
    private Pane getCurrentContainer() {
        return currentContainer.get();
    }
}
