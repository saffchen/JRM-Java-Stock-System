package saffchen.reports;

import com.itextpdf.text.BaseColor;

/**
 * @author saffchen created on 21.08.2022
 */
public class LightThemePDF implements CommandTheme {

    private final Theme theme;

    public LightThemePDF(Theme theme) {
        this.theme = theme;
    }

    @Override
    public BaseColor execute() {
        return theme.lightTheme();
    }
}
