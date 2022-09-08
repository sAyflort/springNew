package ru.shikhov.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.shikhov.persist.Product;
import ru.shikhov.persist.ProductRepository;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        try{
            productRepository.update(product);
        } catch (NullPointerException e) {
            productRepository.insert(product);
        }
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        /*model.addAttribute("product", new Product("", null, "01.01.0001", "01.01.0001"));*/
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productRepository.delete(id);
        return "redirect:/";
    }
}
