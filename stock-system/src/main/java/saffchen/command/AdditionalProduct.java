package saffchen.command;

import javax.validation.constraints.*;

public class AdditionalProduct {

    @NotEmpty
    @Size(max = 255)
    String title;

    @NotEmpty
    @Size(max = 1024)
    String description;

    @NotEmpty
    @Positive
    int price;

    @NotEmpty
    @Max(20)
    String tags;

    @NotEmpty
    @Size(max = 50)
    String category;

    @NotEmpty
    @Positive
    int count;

    @NotEmpty
    String satellite;
}