package saffchen.theme;

import com.itextpdf.text.BaseColor;

/**
 * @author saffchen created on 21.08.2022
 */
public class LightThemePDF implements Theme {

    @Override
    public BaseColor getBaseColor() {
        return BaseColor.WHITE;
    }

    @Override
    public String getName() {
        return "LightTheme";
    }
}
