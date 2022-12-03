package saffchen.controller.store;

import saffchen.MatcherFactory;
import saffchen.dto.StoreDto;
import saffchen.entities.ProductEntity;
import saffchen.entities.StoreEntity;
import saffchen.MatcherFactory.Matcher;

import java.util.List;

/**
 * @author alex_jd on 10/1/22
 * @project JRM-Java-Stock-System
 */
public class StoreTestData {

    public static final Matcher<StoreEntity> STORE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(StoreEntity.class, "products");
    public static final Matcher<StoreDto> STORE_MATCHER_WITH_PRODUCTS = MatcherFactory.usingIgnoringFieldsComparator(StoreDto.class, "");

    public static final Matcher<ProductEntity> PRODUCT_REF_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(ProductEntity.class, "");

    public static final long STORE_ID1 = 1;
    public static final long STORE_ID2 = 2;
    public static final long STORE_ID3 = 3;

    public static final StoreEntity store1 = new StoreEntity(STORE_ID1, "North Shore", "test description");
    public static final StoreEntity store2 = new StoreEntity(STORE_ID2, "Burnie", "test description");
    public static final StoreEntity store3 = new StoreEntity(STORE_ID3, "Lasbela", "test description");

    public static final ProductEntity store1_1 = new ProductEntity(1L, "Daihatsu", "test description", 678D, "Car", 33, store1);
    public static final ProductEntity store1_2 = new ProductEntity(2L, "Plymouth", "test description", 700D, "Car", 35, store1);
    public static final ProductEntity store1_3 = new ProductEntity(3L, "Intrepid", "test description", 702D, "Car", 37, store1);

    public static final ProductEntity store2_1 = new ProductEntity(4L, "Dodge", "test description", 3977D, "Car", 42, store2);
    public static final ProductEntity store2_2 = new ProductEntity(5L, "Lincoln", "test description", 568D, "Car", 21, store2);
    public static final ProductEntity store2_3 = new ProductEntity(6L, "Lancia", "test description", 5685D, "Car", 25, store2);

    public static final ProductEntity store3_1 = new ProductEntity(7L, "MINI", "test description", 2342D, "Car", 47, store3);
    public static final ProductEntity store3_2 = new ProductEntity(8L, "Volkswagen", "test description", 2085D, "Car", 54, store3);
    public static final ProductEntity store3_3 = new ProductEntity(9L, "Dacia", "test description", 1500D, "Car", 8, store3);

    static {
        store1.setProducts(List.of(store1_1, store1_2, store1_3));
        store2.setProducts(List.of(store2_1, store2_2, store2_3));
        store3.setProducts(List.of(store3_1, store3_2, store3_3));
    }

    public static StoreEntity getNew() {
        return new StoreEntity(null, "New Store", "not defined");
    }

    public static StoreEntity getUpdated() {
        return new StoreEntity(STORE_ID1, "North Shore updated", "test description updated");
    }

    public static ProductEntity getNewProductEntity() {
        return new ProductEntity(null, "New car name", "new description", 12000D, "New Car", 8, store1);
    }

    public static ProductEntity getUpdatedProductEntity() {
        return new ProductEntity(store1.id(), "New Car updated", "description updated", 13500D, "New Car updated", 9, store1);
    }
}
