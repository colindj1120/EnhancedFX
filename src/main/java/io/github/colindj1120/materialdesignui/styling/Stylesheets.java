package io.github.colindj1120.materialdesignui.styling;

import io.github.colindj1120.materialdesignui.styling.base.Style;

import java.util.Objects;

public enum Stylesheets implements Style {
    CHARACTER_LIMITED_TEXT_FIELD("/css/CharacterLimitedTextField.css"),
    MD_TEXT_FIELD("/css/MDTextField.css");

    private final String stylesheet;

    Stylesheets(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getStyleName() {
        return this.name();
    }

    @Override
    public String getStyleSheet() {
        return Objects.requireNonNull(getClass().getResource(stylesheet))
                      .toExternalForm();
    }
}
