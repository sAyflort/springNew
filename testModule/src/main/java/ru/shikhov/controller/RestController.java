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
import ru.shikhov.repository.ProductRepository;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.math.BigDecimal;


@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class RestController {

    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 20; i++) {
           productRepository.save(new Product("Product"+(i+1), new BigDecimal((i+1)*100)));
        }
    }

    @GetMapping
    public String listPage(
            @RequestParam(required = false) Double minPriceFilter,
            @RequestParam(required = false) Double maxPriceFilter,
            Model model
    ) {

        model.addAttribute("products", productRepository.productByFilter(minPriceFilter, maxPriceFilter));
        return "product";
    }


    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        Model model1 = model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", new BigDecimal(0)));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid @ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }

}
