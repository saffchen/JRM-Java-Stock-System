package saffchen.reports;

import com.itextpdf.text.BaseColor;

/**
 * @author saffchen created on 21.08.2022
 */
public class SwitchTheme {

    private final CommandTheme darkThemeCommand;
    private final CommandTheme lightThemeCommand;

    public SwitchTheme(CommandTheme darkThemeCommand, CommandTheme lightThemeCommand) {
        this.darkThemeCommand = darkThemeCommand;
        this.lightThemeCommand = lightThemeCommand;
    }

    public BaseColor darkTheme() {
        return darkThemeCommand.execute();
    }

    public BaseColor lightTheme() {
        return lightThemeCommand.execute();
    }
}
