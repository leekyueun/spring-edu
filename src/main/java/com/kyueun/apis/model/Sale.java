package com.kyueun.apis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int saleId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int productId;

    @Column(nullable = false)
    private int paidPrice;

    @Column(nullable = false)
    private int listPrice;

    @Column(nullable = false)
    private int amount;

    @Builder
    public Sale(int saleId, int userId, int productId, int listPrice, int amount) {
        this.saleId = saleId;
        this.userId = userId;
        this.productId = productId;
        this.listPrice = listPrice;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[saleId=%d, userId=%d, productId=%d, listPrice=%d, amount=%d]",
                this.saleId, this.userId, this.productId, this.listPrice, this.amount
        );
    }
}
