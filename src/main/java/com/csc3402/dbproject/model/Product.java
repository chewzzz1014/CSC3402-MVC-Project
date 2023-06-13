package com.csc3402.dbproject.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productname;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity_in_stock")
    private Integer stockquantity;

    @Column(name = "image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();



    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(Integer stockquantity) {
        this.stockquantity = stockquantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Product() {
        super();
    }

    public Product(String productname, String description, Double price, Integer stockquantity, String image, Category category) {
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.stockquantity = stockquantity;
        this.image = image;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productname='" + productname + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockquantity=" + stockquantity +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }
}
