package saffchen.controller.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import saffchen.mapper.ProductMapper;
import saffchen.service.ProductService;

/**
 * @author alex_jd on 9/13/22
 * @project JRM-Java-Stock-System
 */

@Slf4j
public class AbstractProductController {

    @Autowired
    protected ProductMapper mapper;

    @Autowired
    protected ProductService service;
}
