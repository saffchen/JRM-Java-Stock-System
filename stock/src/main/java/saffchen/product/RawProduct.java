package saffchen.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawProduct {
    private String id;
    private String title;
    private String description;
    private String price;
    private String tags;
    private String category;
    private String count;
    private String store;

    public String showInfo() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", store='" + store + '\'' +
                '}';
    }

    public String toCSVString(String sep) {
        return "\n" + title + sep +
                description + sep +
                price + sep +
                tags + sep +
                category + sep +
                count + sep +
                store;
    }
}
