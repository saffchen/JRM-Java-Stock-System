package saffchen.product;

import saffchen.dto.ProductDtoReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductAdapterReport {
    private RawProduct rawProduct;
    private ProductDtoReport product;

    public ProductAdapterReport(RawProduct rawProduct) {
        this.rawProduct = rawProduct;
    }

    public ProductAdapterReport(ProductDtoReport product) {
        this.product = product;
    }

    private List<String> parseTags(String tagStr, String separator) {
        ArrayList<String> splittedTags = new ArrayList<>();
        Collections.addAll(splittedTags, rawProduct.getTags().trim().split(separator));

        return splittedTags;
    }

    private String tagsToString(List<String> tags) {
        String result = tags.stream()
                .map(x -> x + ",")
                .collect(Collectors.joining());
        return result.substring(0, result.length() - 1);
    }

    public ProductDtoReport getProduct() {
        ProductDtoReport product = new ProductDtoReport();
        product.setId(String.valueOf(rawProduct.getId()));
        product.setTitle(rawProduct.getTitle());
        product.setCategory(rawProduct.getCategory());
        product.setPrice(Double.valueOf(rawProduct.getPrice()));
        product.setDescription(rawProduct.getDescription());
        product.setCount(Integer.valueOf(rawProduct.getCount()));
        product.setTags(parseTags(rawProduct.getTags(), ","));
        product.setSatelliteName(rawProduct.getSatellite());

        return product;
    }

    public RawProduct setDataToRawProduct() {
        RawProduct rawProduct = new RawProduct();
        rawProduct.setId(String.valueOf(product.getId()));
        rawProduct.setTitle(product.getTitle());
        rawProduct.setCategory(product.getCategory());
        rawProduct.setPrice(product.getPrice().toString());
        rawProduct.setDescription(product.getDescription());
        rawProduct.setCount(product.getCount().toString());
        rawProduct.setTags(tagsToString(product.getTags()));
        rawProduct.setSatellite(product.getSatelliteName());

        return rawProduct;
    }
}
