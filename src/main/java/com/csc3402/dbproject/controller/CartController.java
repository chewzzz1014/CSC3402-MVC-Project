package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.model.Product;
import com.csc3402.dbproject.repository.OrderProductRepository;
import com.csc3402.dbproject.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private EntityManager entityManager;

    CartController(OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @GetMapping("display/{customer_id}")
    public String displayCart(@PathVariable("customer_id") long customer_id, Model model){
        // get the customer's order id (with latest date)
        long order_id = orderRepository.getCustomerLatestOrderId((int) customer_id);

        // then get all products in the cart using the order id obtained (from order_product; make sure has_check_out = 0)
        List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart((int) order_id);
        model.addAttribute("cart_products", products_in_cart);

        model.addAttribute("new_product", new OrderProduct());
//        System.out.println(products_in_cart.get(0).getProduct());
//        System.out.println(products_in_cart.get(0).getOrder());
//        System.out.println(products_in_cart.get(0).getQuantity());
//        System.out.println(products_in_cart.get(0).getProduct().getProductname());
        return "cart";
    }


    // update product's quantity in cart
    // @ModelAttribute("product")
    @PostMapping("update")
    public String updateCartProductQuantity(@RequestParam("order_id") long order_id, @RequestParam("product_id") long product_id, @Valid @ModelAttribute("product") OrderProduct product, BindingResult result, Model model) {
        if(result.hasErrors()){
            System.out.println("There was a error "+result);
        }
        System.out.println(product);
//        return "redirect:/";
        return "";
    }

    @GetMapping("delete")
    public String deleteCartProduct(@RequestParam("order_id") long order_id, @RequestParam("product_id") long product_id, @Valid @ModelAttribute("product") OrderProduct product, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            System.out.println("There was a error "+result);
        }
        System.out.println("yoyoyoyoyyoyo");
        return "redirect:/";
    }

}
