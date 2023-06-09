package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.model.CheckoutForm;
import com.csc3402.dbproject.model.Order;
import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.model.Product;
import com.csc3402.dbproject.repository.OrderProductRepository;
import com.csc3402.dbproject.repository.OrderRepository;
import com.csc3402.dbproject.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public CheckoutController(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("edit")
    public String checkoutOrder(@RequestParam("order_id") long order_id, Model model) {
        List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart((int) order_id);
        Order order = orderRepository.findById((int) order_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + order_id));

        float total_price = 0;
        for(int i = 0; i<products_in_cart.size(); i++) {
            total_price += products_in_cart.get(i).getProduct().getPrice() * products_in_cart.get(i).getQuantity();
        }
        order.setTotalprice(total_price);

        CheckoutForm checkoutForm = new CheckoutForm(products_in_cart, order);
        System.out.println(checkoutForm.getOrder());
        System.out.println(checkoutForm.getItemList());

        model.addAttribute("user_order", order);
        model.addAttribute("totalPrice", total_price);
        model.addAttribute("checkout_form", checkoutForm);

        return "checkout";
    }

    @PostMapping("update")
    public RedirectView checkoutOrder(@RequestParam("order_id") long order_id, @ModelAttribute("checkout_form") CheckoutForm checkoutForm, BindingResult result, Model model, RedirectAttributes attributes) {
        try{
            if (result.hasErrors()) {
                System.out.println("There was a error "+result);
            }

            // get order details and list of items from form
            Order foundOrder = orderRepository.findById((int) order_id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + order_id));
            Order order = checkoutForm.getOrder();
            List<OrderProduct> itemList = checkoutForm.getItemList();

            // in case the itemList returned is null
            if(itemList == null || itemList.size() == 0){
                List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart((int)order_id);
                itemList = products_in_cart;
            }

            System.out.println(order);
            System.out.println(itemList);

            // update product quantity in stock
            for(OrderProduct op : itemList) {
                int product_id = op.getProduct().getProductId();
                Product foundProduct = productRepository.findById(product_id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + product_id));
                foundProduct.setStockquantity(foundProduct.getStockquantity() - op.getQuantity());
                productRepository.save(foundProduct);
            }

            // update order info
            foundOrder.setTotalprice(order.getTotalprice());
            foundOrder.setDate(new Timestamp(System.currentTimeMillis()));
            foundOrder.setPaymentmethod(order.getPaymentmethod());
            foundOrder.setAddress(order.getAddress());
            foundOrder.setPhonenum(order.getPhonenum());
            foundOrder.setHascheckout(1);
            orderRepository.save(foundOrder);
        } catch(Exception e) {
            e.printStackTrace();
        }

        attributes.addAttribute("order_id", (int) order_id);
        return new RedirectView("/orderlist/view") ;
    }
}
