package ru.shikhov.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.shikhov.model.dto.ProductDto;
import ru.shikhov.service.ProductService;

import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class RestController {

    /*private final ProductService service;

    @GetMapping
    public Page<ProductDto> listPage(
            @RequestParam(required = false) Double minPriceFilter,
            @RequestParam(required = false) Double maxPriceFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size
    ) {
        Integer pageValue = page.orElse(1)-1;
        Integer sizeValue = size.orElse(10);
        return service.productByFilter(minPriceFilter, maxPriceFilter, pageValue, sizeValue);
    }


    @GetMapping("/{id}/id")
    public ProductDto form(@PathVariable("id") long id) {
        return service.findProductById(id).orElse(new ProductDto());
    }


    @DeleteMapping("/{id}")
    public int deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
        return HttpStatus.OK.value();
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        service.save(product);
        return product;
    }
    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) throws IllegalAccessException {
        if(product.getId() != null) {
            throw new IllegalAccessException("Created product shouldn`t have id");
        }
        service.save(product);
        return product;
    }*/

}
