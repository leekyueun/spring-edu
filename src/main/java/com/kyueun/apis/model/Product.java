package com.kyueun.apis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(length = 80, nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private int listPrice;

    @Column
    private int price;

    @OneToMany
    @JoinColumn(name="review_key", referencedColumnName = "product_id")
    private Collection<Review> reviews;

    @Builder
    public Product(String name, String description, int listPrice, int price) {
        this.name = name;
        this.description = description;
        this.listPrice = listPrice;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[productId='%s', name='%s', description='%s', listPrice=%d, price=%d]",
                this.productId, this.name, this.description, this.listPrice, this.price
        );
    }
}