package saffchen.reports;

import com.itextpdf.text.BaseColor;
import lombok.NoArgsConstructor;

/**
 * @author saffchen created on 21.08.2022
 */
@NoArgsConstructor
public class Theme {
    public BaseColor darkTheme() {
        return BaseColor.GRAY;
    }

    public BaseColor lightTheme() {
        return BaseColor.WHITE;
    }
}
