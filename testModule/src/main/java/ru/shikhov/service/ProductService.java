package ru.shikhov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.shikhov.model.dto.ProductDto;
import ru.shikhov.model.mapper.ProductDtoMapper;
import ru.shikhov.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductDto> productByFilter(Double minPriceFilter, Double maxPriceFilter, Integer page, Integer size) {
        return productRepository.productByFilter(minPriceFilter, maxPriceFilter, PageRequest.of(page, size)).map(ProductDtoMapper::map);
    }

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(ProductDtoMapper::map);
    }

    public void save(ProductDto dto) {
        productRepository.save(ProductDtoMapper.map(dto));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
