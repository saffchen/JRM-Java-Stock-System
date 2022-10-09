package saffchen.store;

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

    public static final Matcher<StoreEntity> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(StoreEntity.class, "menuItems");
    public static final Matcher<StoreDto> RESTAURANT_MATCHER_WITH_MENU = MatcherFactory.usingIgnoringFieldsComparator(StoreDto.class, "dishRefs.restaurant");

    public static final Matcher<ProductEntity> DISH_REF_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(ProductEntity.class, "restaurant");

    public static final long STORE_ID1 = 1;
    public static final long STORE_ID2 = 2;
    public static final long STORE_ID3 = 3;

    public static final StoreEntity store1 = new StoreEntity(STORE_ID1, "North Shore", "test description");
    public static final StoreEntity store2 = new StoreEntity(STORE_ID2, "Burnie", "test description");
    public static final StoreEntity store3 = new StoreEntity(STORE_ID3, "Lasbela", "test description");

    public static final ProductEntity store1_1 = new ProductEntity(1L, "Daihatsu", "test description", 678D, "Car", 33, store1);
    public static final ProductEntity store1_2 = new ProductEntity(2L, "Plymouth", "test description", 700D, "Car", 35, store1);
    public static final ProductEntity store1_3 = new ProductEntity(3L, "Intrepid", "test description", 702D, "Car", 37, store1);

    static {
        store1.setProducts(List.of(store1_1, store1_2, store1_3));
    }
}
