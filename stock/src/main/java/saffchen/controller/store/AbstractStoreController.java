package saffchen.controller.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import saffchen.mapper.StoreMapper;
import saffchen.service.StoreService;

/**
 * @author alex_jd on 9/13/22
 * @project JRM-Java-Stock-System
 */
@Slf4j
public class AbstractStoreController {

    @Autowired
    protected StoreMapper mapper;

    @Autowired
    protected StoreService service;
}
