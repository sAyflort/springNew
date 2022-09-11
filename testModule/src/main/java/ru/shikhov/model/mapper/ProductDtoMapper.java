package ru.shikhov.model.mapper;

import ru.shikhov.model.Product;
import ru.shikhov.model.dto.ProductDto;

public class ProductDtoMapper {
    // не выходит запустить код, когда этот класс сам генерируется. Про это проблему писал в лс Вам
    public static ProductDto map(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( product.getId() != null ) {
            productDto.setId( product.getId() );
        }
        if ( product.getTitle() != null ) {
            productDto.setTitle( product.getTitle() );
        }
        if ( product.getPrice() != null ) {
            productDto.setPrice( product.getPrice() );
        }

        return productDto;
    }

    public static Product map(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();


        if ( dto.getId() != null ) {
            product.setId(dto.getId() );
        }
        if ( dto.getTitle() != null ) {
            product.setTitle( dto.getTitle() );
        }
        if ( dto.getPrice() != null ) {
            product.setPrice( dto.getPrice() );
        }

        return product;
    }
}
