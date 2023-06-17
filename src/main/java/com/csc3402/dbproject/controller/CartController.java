package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.model.CartForm;
import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.model.Order;
import com.csc3402.dbproject.model.OrderProductId;
import com.csc3402.dbproject.model.Product;
import com.csc3402.dbproject.repository.OrderProductRepository;
import com.csc3402.dbproject.repository.OrderRepository;
import com.csc3402.dbproject.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    // TODO: customer_id should based on customer_id of current login user
    private Integer customer_id = 2;

    CartController(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    // display cart based on customer id
    @GetMapping("display")
    public String displayCartByCustomerId(Model model){
        try{
            // get the customer's cart order id
            Integer order_id = orderRepository.getCustomerLatestOrderId2((int) customer_id);
            System.out.println(order_id);

            // check if has order
            if(order_id != null && order_id != -1) {
                // then get all products in the cart using the order id obtained (from order_product)
                List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart(order_id);
                CartForm cartForm = new CartForm(products_in_cart);

                float total_price = 0;
                for(int i = 0; i<products_in_cart.size(); i++) {
                    total_price += products_in_cart.get(i).getProduct().getPrice() * products_in_cart.get(i).getQuantity();
                }

                model.addAttribute("totalPrice", total_price);
                model.addAttribute("order_id", order_id);
                model.addAttribute("cartForm", cartForm);
                model.addAttribute("has_item", "true");

                // if no more product in cart
                if (products_in_cart.size() == 0) {
                    model.addAttribute("has_item", "false");
                } else {
                    model.addAttribute("has_item", "true");
                }
            } else {
                model.addAttribute("has_item", "false");
            }
            model.addAttribute("is_invalid_quantity", 0);
            model.addAttribute("product_name", "");
        }catch(Exception e){
            model.addAttribute("has_item", "false");
        }
        return "cart";
    }

    // display cart based on order id
    @GetMapping("edit")
    public String displayCartByOrderId(@RequestParam("order_id") long order_id, @RequestParam("invalid_quantity") Integer invalid_quantity, @RequestParam("invalid_quantity_product") String invalid_quantity_product, Model model){
        try{
            List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart((int) order_id);
            CartForm cartForm = new CartForm(products_in_cart);

            float total_price = 0;
            for(int i = 0; i<products_in_cart.size(); i++) {
                total_price += products_in_cart.get(i).getProduct().getPrice() * products_in_cart.get(i).getQuantity();
            }

            model.addAttribute("totalPrice", total_price);
            model.addAttribute("order_id", (int) order_id);
            model.addAttribute("cartForm", cartForm);

            // if no more product in cart
            if (products_in_cart.size() == 0) {
                model.addAttribute("has_item", "false");
            } else {
                model.addAttribute("has_item", "true");
            }

            if (invalid_quantity != null && invalid_quantity_product != null) {
                model.addAttribute("invalid_quantity", invalid_quantity);
                model.addAttribute("invalid_quantity_product", invalid_quantity_product);
            }
        } catch(Exception e) {
            model.addAttribute("has_item", "false");
            e.printStackTrace();
        }

        return "cart";
    }

    // update product's quantity in cart
    @PostMapping("update")
    public RedirectView updateCartProductQuantity(@RequestParam("order_id") long order_id, @RequestParam("product_id") long product_id, @RequestParam("index") long index, @Valid @ModelAttribute("cartForm") CartForm cartForm, BindingResult result, Model model, RedirectAttributes attributes) {
        try{
            if(result.hasErrors()){
                System.out.println("There was a error "+result);
            }

            List<OrderProduct> updated_cart = cartForm.getCartList();
            OrderProduct updated_cart_product = updated_cart.get((int)index);

            Product product = productRepository.findById((int)product_id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + product_id));

            System.out.println(updated_cart_product.getQuantity());
            System.out.println(product.getStockquantity());

            // pass back error when attempting to have quantity > quantity in stock
            if (updated_cart_product.getQuantity() > product.getStockquantity()) {
                attributes.addAttribute("invalid_quantity", 1);
                attributes.addAttribute("invalid_quantity_product", product.getProductname());
            } else {
                updated_cart_product.setId(
                        new OrderProductId(updated_cart_product.getOrder().getOrderId(), (int)product_id)
                );

                // update quantity
                orderProductRepository.save(updated_cart_product);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        attributes.addAttribute("order_id", (int) order_id);
        return new RedirectView("/cart/edit") ;
    }

    // delete product from cart
    @GetMapping("delete")
    public RedirectView deleteCartProduct(@RequestParam("order_id") long order_id, @RequestParam("product_id") long product_id, Model model, RedirectAttributes attributes) {
        try{
            Order order = orderRepository.findById((int)order_id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + order_id));
            Product product = productRepository.findById((int)product_id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + product_id));
            orderProductRepository.deleteByOrderAndProduct(order, product);
        } catch(Exception e) {
            e.printStackTrace();
        }

        attributes.addAttribute("order_id", (int) order_id);
        return new RedirectView("/cart/edit") ;
    }

}
