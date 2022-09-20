package ru.shikhov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank
    private String title;

    @Positive
    private BigDecimal price;

    public ProductDto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }
}
