package saffchen.product;

import saffchen.entities.ProductEntity;

import java.util.*;
import java.util.stream.Collectors;

public class ProductAdapter {
    private RawProduct rawProduct;
    private ProductEntity product;

    public ProductAdapter(RawProduct rawProduct) {
        this.rawProduct = rawProduct;
    }

    public ProductAdapter(ProductEntity product) {
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

    public ProductEntity getProduct() {
        ProductEntity product = new ProductEntity();
        product.setId(Long.valueOf(rawProduct.getId()));
        product.setTitle(rawProduct.getTitle());
        product.setCategory(rawProduct.getCategory());
        product.setPrice(Double.valueOf(rawProduct.getPrice()));
        product.setDescription(rawProduct.getDescription());
        product.setCount(Integer.valueOf(rawProduct.getCount()));
        product.setTags(parseTags(rawProduct.getTags(), ","));
        product.setSatellite(rawProduct.getSatellite());

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
        rawProduct.setSatellite(product.getSatellite());

        return rawProduct;
    }
}
