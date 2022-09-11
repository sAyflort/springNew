package ru.shikhov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shikhov.exceptions.EntityNotFoundException;
import ru.shikhov.model.Product;
import ru.shikhov.model.dto.ProductDto;
import ru.shikhov.service.ProductService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class RestController {

    private final ProductService service;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 20; i++) {
           //service.save(new ProductDto("Product"+(i+1), new BigDecimal((i+1)*100)));
        }
    }

    @GetMapping
    public String listPage(
            @RequestParam(required = false) Double minPriceFilter,
            @RequestParam(required = false) Double maxPriceFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            Model model
    ) {
        Integer pageValue = page.orElse(1)-1;
        Integer sizeValue = size.orElse(10);
        model.addAttribute("products", service.productByFilter(minPriceFilter, maxPriceFilter, pageValue, sizeValue));
        return "product";
    }


    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        Model model1 = model.addAttribute("product", service.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto("New product", new BigDecimal(0)));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid @ModelAttribute("product") ProductDto product) {
        service.save(product);
        return "redirect:/product";
    }

}
