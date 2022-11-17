package saffchen.theme;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author saffchen created on 21.08.2022
 */
public class ThemeSelectionService {

    public static final Set<Theme> THEMES = new HashSet<>();

    static {
        THEMES.add(new LightThemePDF());
        THEMES.add(new DarkThemePDF());
        THEMES.add(new BlueThemePDF());
    }

    public Theme getTheme(@NonNull String themeName) {
        return THEMES.stream()
                .filter(t -> t != null && StringUtils.isNotBlank(t.getName()))
                .filter(t -> themeName.equals(t.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Requested theme " + themeName + " has not be found."));
    }
}
