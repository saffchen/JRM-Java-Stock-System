package saffchen.reports;

import com.itextpdf.text.BaseColor;

/**
 * @author saffchen created on 21.08.2022
 */
public class DarkThemePDF implements CommandTheme {

    private final Theme theme;

    public DarkThemePDF(Theme theme) {
        this.theme = theme;
    }

    @Override
    public BaseColor execute() {
        return theme.getBaseColor(BaseColor.BLACK);
    }
}
