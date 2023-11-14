package br.com.fiap.productCatalog.controller;

import br.com.fiap.productCatalog.model.Product;
import br.com.fiap.productCatalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("product", new Product());
        return "create-product";
    }

    @GetMapping
    public String listProduct(Model model) {
        model.addAttribute("products", service.findAll());
        return "list-product";
    }

    @PostMapping
    public String addProduct(@ModelAttribute @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "create-product";
        }
        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = service
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        model.addAttribute("product", product);
        return "create-product";
    }

    @PostMapping("edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/products";
    }
}
