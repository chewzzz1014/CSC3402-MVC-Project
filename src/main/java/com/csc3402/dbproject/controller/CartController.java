package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.model.CartForm;
import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.model.OrderProductId;
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
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    CartController(OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @GetMapping("display/{customer_id}")
    public String displayCartByCustomerId(@PathVariable("customer_id") long customer_id, Model model){
        // get the customer's order id (with latest date)
        long order_id = orderRepository.getCustomerLatestOrderId((int) customer_id);

        // then get all products in the cart using the order id obtained (from order_product)
        List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart((int) order_id);
        CartForm cartForm = new CartForm(products_in_cart);

        model.addAttribute("order_id", (int) order_id);
        model.addAttribute("cartForm", cartForm);

//        System.out.println(products_in_cart.get(0).getProduct());
//        System.out.println(products_in_cart.get(0).getOrder());
//        System.out.println(products_in_cart.get(0).getQuantity());
        return "cart";
    }

    @GetMapping("edit")
    public String displayCartByOrderId(@RequestParam("order_id") long order_id, Model model){
        List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart((int) order_id);
        CartForm cartForm = new CartForm(products_in_cart);
        model.addAttribute("order_id", (int) order_id);
        model.addAttribute("cartForm", cartForm);

        return "cart";
    }

    // update product's quantity in cart
    @PostMapping("update")
    public RedirectView updateCartProductQuantity(@RequestParam("order_id") long order_id, @RequestParam("product_id") long product_id, @RequestParam("index") long index, @Valid @ModelAttribute("cartForm") CartForm cartForm, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()){
            System.out.println("There was a error "+result);
        }

        List<OrderProduct> updated_cart = cartForm.getCartList();
        OrderProduct updated_cart_product = updated_cart.get((int)index);
        updated_cart_product.setId(
                new OrderProductId(updated_cart_product.getOrder().getOrderId(),
                                   updated_cart_product.getProduct().getProductId())
        );

        // update quantity
        orderProductRepository.save(updated_cart_product);

        // redirect back
        attributes.addAttribute("order_id", (int) order_id);
        return new RedirectView("/cart/edit") ;
    }

    // delete product from cart
    @GetMapping("delete")
    public RedirectView deleteCartProduct(@RequestParam("order_id") long order_id, @RequestParam("product_id") long product_id, @Valid @ModelAttribute("product") OrderProduct product, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()){
            System.out.println("There was a error "+result);
        }
        System.out.println("yoyoyoyoyyoyo");

        attributes.addAttribute("order_id", (int) order_id);
        return new RedirectView("/cart/edit") ;
    }

}
