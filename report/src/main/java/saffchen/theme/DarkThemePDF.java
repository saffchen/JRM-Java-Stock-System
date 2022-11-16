package saffchen.theme;

import com.itextpdf.text.BaseColor;

/**
 * @author saffchen created on 21.08.2022
 */
public class DarkThemePDF implements Theme {

    @Override
    public BaseColor getBaseColor() {
        return BaseColor.GRAY;
    }

    @Override
    public String getName() {
        return "DarkTheme";
    }
}
