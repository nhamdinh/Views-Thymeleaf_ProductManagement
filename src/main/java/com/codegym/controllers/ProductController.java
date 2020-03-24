package com.codegym.controllers;

import com.codegym.service.productDao;
import com.codegym.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ProductController {
    private productDao productDao = new productDao();

    @GetMapping("/home")
    public ModelAndView index() {
        ArrayList<Product> productList = productDao.getProductList();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

//    @GetMapping("/home")
//    public String index(Model model) {
//        ArrayList<Product> productList = productDao.getProductList();
//        model.addAttribute("productList", productList);
//        return "index";
//    }

    @GetMapping("/newProduct")
    public ModelAndView getAddForm() {
        Product product = new Product();
        ModelAndView modelAndView = new ModelAndView("addNewProduct",
                "product", product);
        return modelAndView;
    }

    @PostMapping("/doAdd")
    public String doAddProduct(Product product) {
        productDao.AddProduct(product);
        return "redirect:/home";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productDao.deleteProduct(id);
        return "redirect:/home";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getEditForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("editForm");
        modelAndView.addObject("product", productDao.getProductById(id));
        return modelAndView;
    }

    @PostMapping("/doEdit")
    public String doEdit(Product product) {
        productDao.updateProductInfo(product);
        return "redirect:/home";
    }

    @GetMapping("/{id}/view")
    public ModelAndView getSelectedInfo(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("product", productDao.getProductById(id));
        return modelAndView;
    }
}
