package saffchen.controller.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import saffchen.mapper.SatelliteMapper;
import saffchen.service.SatelliteService;

/**
 * @author alex_jd on 9/13/22
 * @project JRM-Java-Stock-System
 */
@Slf4j
public class AbstractStoreController {

    @Autowired
    protected SatelliteMapper mapper;

    @Autowired
    protected SatelliteService service;
}
