package io.github.colindj1120.enhancedfx.controls.css;

public enum EFXTheme {
    LIGHT_THEME("/css/light"),
    DARK_THEME("/css/dark");

    private final String themeCssDirectory;

    EFXTheme(String themeCssDirectory) {
        this.themeCssDirectory = themeCssDirectory;
    }

    public String getThemeCssDirectory() {
        return themeCssDirectory;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Css Directory: %s", name(), getThemeCssDirectory());
    }

    public String toStringLowerCase() {
        return String.format("Name: %s | Css Directory: %s", name().toLowerCase(), getThemeCssDirectory());
    }

    public String toStringTitleCase() {
        String titleCaseName = getTitleCaseName();
        return String.format("Name: %s | Css Directory: %s", titleCaseName, getThemeCssDirectory());
    }

    public String lowerCaseName() {
        return name().toLowerCase();
    }

    public String titleCaseName() {
        return getTitleCaseName();
    }

    private String getTitleCaseName() {
        String lowerCase = name().toLowerCase();
        return lowerCase.substring(0, 1)
                        .toUpperCase() + lowerCase.substring(1);
    }
}
