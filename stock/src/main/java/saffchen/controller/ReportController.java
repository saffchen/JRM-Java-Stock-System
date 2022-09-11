package saffchen.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.ProductDto;
import saffchen.mapper.ProductMapper;
import saffchen.service.ProductService;
import saffchen.template.ReportTemplate;

import java.util.List;

@RestController
@RequestMapping (value = ReportController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ReportController {
    static final String REST_URL = "/api/v1/report";
    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping("/satellites")
    public ResponseEntity<String> getAllProducts(@RequestBody ReportTemplate template ) {
        try {
            List<ProductDto> data = productMapper.toProductsDtoList(productService.getAllProducts());
            return ResponseEntity.ok("Report was send to the broker");
        } catch (Exception e) {
            return ResponseEntity.ok("Report wasn't send to the broker");
        }
    }

    @GetMapping("/satellites/{id}")
    public ResponseEntity<List<ProductDto>> getProductsBySatelliteId(@PathVariable Long id){
        return ResponseEntity.ok(productMapper.toProductsDtoList(productService.getProductsBySatelliteId(id)));
    }

}
