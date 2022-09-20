package ru.shikhov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shikhov.exceptions.EntityNotFoundException;
import ru.shikhov.model.dto.ProductDto;
import ru.shikhov.service.CartService;
import ru.shikhov.service.ProductService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final CartService cartService;

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
            @RequestParam(required = false) Optional<String> sortField,
            Model model
    ) {
        Integer pageValue = page.orElse(1)-1;
        Integer sizeValue = size.orElse(10);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");
        model.addAttribute("products", service.productByFilter(minPriceFilter, maxPriceFilter, pageValue, sizeValue, sortFieldValue));
        return "product";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        Model model1 = model.addAttribute("product", service.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found")));
        return "product_form";
    }
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto("New product", new BigDecimal(0)));
        return "product_form";
    }
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
        return "redirect:/product";
    }
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @PostMapping
    public String saveProduct(@Valid @ModelAttribute("product") ProductDto product) {
        service.save(product);
        return "redirect:/product";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable long id) {
        ProductDto pr = service.findProductById(id).get();
        cartService.addProductToCart(id);
        cartService.getCartProducts().forEach(product -> System.out.println(product.getTitle()));
        System.out.println("-*--*--*--*--*-");
        return "redirect:/product";
    }

}
