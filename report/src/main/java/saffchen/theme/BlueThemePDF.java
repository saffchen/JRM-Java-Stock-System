package saffchen.theme;

import com.itextpdf.text.BaseColor;

public class BlueThemePDF implements Theme{
    @Override
    public BaseColor getBaseColor() {
        return BaseColor.BLUE;
    }

    @Override
    public String getName() {
        return "BlueTheme";
    }
}
