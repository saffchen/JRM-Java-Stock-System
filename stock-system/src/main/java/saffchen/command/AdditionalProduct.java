package saffchen.command;

import javax.validation.constraints.*;

public class AdditionalProduct {

    @NotEmpty
    @Size(max = 255)
    String title;

    @NotEmpty
    String description;

    @NotEmpty
    @Positive
    int price;

    @NotEmpty
    String tags;

    @NotEmpty
    String category;

    @NotEmpty
    @Positive
    int count;

    @NotEmpty
    @City
    String satellite;
}