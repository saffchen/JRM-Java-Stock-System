package saffchen.controller.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import saffchen.AbstractControllerTest;
import saffchen.repository.StoreRepository;

import static saffchen.controller.store.StoreTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StoreControllerTest extends AbstractControllerTest {

    private static final String REST_URL = StoreController.REST_URL + '/';

    @Autowired
    private StoreRepository repository;

    @Test
    void getAll() throws Exception {
        repository.save(store1);
        repository.save(store2);
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(STORE_MATCHER.contentJson(store1, store2));
    }

}