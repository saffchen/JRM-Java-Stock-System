package saffchen.controller.store;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import saffchen.AbstractControllerTest;
import saffchen.repository.StoreRepository;

import static saffchen.controller.store.StoreTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StoreControllerTest extends AbstractControllerTest {

    private static final String REST_URL = StoreController.REST_URL + '/';

    private StoreRepository repository;

    @Test
    void getAll() throws Exception {
        repository.save(store1);
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(STORE_MATCHER.contentJson(store2, store1));
    }

    @Test
    void get() throws Exception {
    }
}