package saffchen.product;

import org.springframework.beans.factory.annotation.Autowired;
import saffchen.entities.ProductEntity;
import saffchen.service.StoreService;

import java.util.*;
import java.util.stream.Collectors;

public class ProductAdapter {
    private RawProduct rawProduct;
    private ProductEntity product;
    @Autowired
    private StoreService storeService;

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
        product.setName(rawProduct.getTitle());
        product.setCategory(rawProduct.getCategory());
        product.setPrice(Double.valueOf(rawProduct.getPrice()));
        product.setDescription(rawProduct.getDescription());
        product.setCount(Integer.valueOf(rawProduct.getCount()));
        product.setTags(parseTags(rawProduct.getTags(), ","));
        product.setStore(storeService.getByName(rawProduct.getStore()));
        return product;
    }

    public RawProduct setDataToRawProduct() {
        RawProduct rawProduct = new RawProduct();
        rawProduct.setTitle(product.getName());
        rawProduct.setCategory(product.getCategory());
        rawProduct.setPrice(product.getPrice().toString());
        rawProduct.setDescription(product.getDescription());
        rawProduct.setCount(product.getCount().toString());
        rawProduct.setTags(tagsToString(product.getTags()));
        rawProduct.setStore(product.getStore().getName());
        return rawProduct;
    }
}
