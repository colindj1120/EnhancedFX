package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.scene.ParentConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Collection;
import java.util.function.Predicate;

public interface ParentConfig<T extends ParentConfigurator<T>> extends NodeConfig<T> {
    @Override
    T getConfigurator();

    @Override
    Parent getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().needsLayoutProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        getNode().needsLayoutProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        getNode().getChildrenUnmodifiable()
                 .addListener(listChangeListener);
        return getConfigurator();
    }

    default T addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getChildrenUnmodifiable()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        getNode().getStylesheets()
                 .addListener(listChangeListener);
        return getConfigurator();
    }

    default T addStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getStylesheets()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().needsLayoutProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        getNode().needsLayoutProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        getNode().getChildrenUnmodifiable()
                 .removeListener(listChangeListener);
        return getConfigurator();
    }

    default T removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getChildrenUnmodifiable()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        getNode().getStylesheets()
                 .removeListener(listChangeListener);
        return getConfigurator();
    }

    default T removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getStylesheets()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    default T requestLayout() {
        getNode().requestLayout();
        return getConfigurator();
    }

    default T Layout() {
        getNode().layout();
        return getConfigurator();
    }

    //endregion Layout Functions

    //region Add EFXStyle Functions
    //*****************************************************************
    // Add EFXStyle Functions
    //*****************************************************************

    default T addFirstStylesheet(String stylesheet) {
        getNode().getStylesheets()
                 .addFirst(stylesheet);
        return getConfigurator();
    }

    default T addLastStylesheet(String stylesheet) {
        getNode().getStylesheets()
                 .addLast(stylesheet);
        return getConfigurator();
    }

    default T addStylesheet(String stylesheet) {
        getNode().getStylesheets()
                 .add(stylesheet);
        return getConfigurator();
    }

    default T addStylesheet(int index, String stylesheet) {
        getNode().getStylesheets()
                 .add(index, stylesheet);
        return getConfigurator();
    }

    default T addAllStylesheets(String... stylesheets) {
        getNode().getStylesheets()
                 .addAll(stylesheets);
        return getConfigurator();
    }

    default T addAllStylesheets(Collection<? extends String> c) {
        getNode().getStylesheets()
                 .addAll(c);
        return getConfigurator();
    }

    default T addAllStylesheets(int index, Collection<? extends String> c) {
        getNode().getStylesheets()
                 .addAll(index, c);
        return getConfigurator();
    }

    //endregion Add EFXStyle Functions

    //region Remove EFXStyle Functions
    //*****************************************************************
    // Remove EFXStyle Functions
    //*****************************************************************

    default T removeFirstStylesheet() {
        getNode().getStylesheets()
                 .removeFirst();
        return getConfigurator();
    }

    default T removeLastStylesheet() {
        getNode().getStylesheets()
                 .removeLast();
        return getConfigurator();
    }

    default T removeStylesheet(String stylesheet) {
        getNode().getStylesheets()
                 .remove(stylesheet);
        return getConfigurator();
    }

    default T removeStylesheets(int from, int to) {
        getNode().getStylesheets()
                 .remove(from, to);
        return getConfigurator();
    }

    default T removeStylesheetsIf(Predicate<? super String> filter) {
        getNode().getStylesheets()
                 .removeIf(filter);
        return getConfigurator();
    }

    default T removeAllStylesheets(String... stylesheets) {
        getNode().getStylesheets()
                 .removeAll(stylesheets);
        return getConfigurator();
    }

    default T removeAllStylesheets(Collection<? extends String> c) {
        getNode().getStylesheets()
                 .removeAll(c);
        return getConfigurator();
    }

    //endregion Remove EFXStyle Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setStylesheets(int index, String element) {
        getNode().getStylesheets()
                 .set(index, element);
        return getConfigurator();
    }

    default T setAllStylesheets(String... elements) {
        getNode().getStylesheets()
                 .setAll(elements);
        return getConfigurator();
    }

    default T setAllStylesheets(Collection<? extends String> col) {
        getNode().getStylesheets()
                 .setAll(col);
        return getConfigurator();
    }

    //endregion Set Functions
}