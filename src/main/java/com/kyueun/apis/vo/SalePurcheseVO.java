package com.kyueun.apis.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SalePurcheseVO {
    int userId;
    int productId;
    int paidPrice;
    int listPrice;
    int amount;

    @Override
    public String toString() {
        return String.format(
                "Sale[userId=%d, productId=%d, paidPrice=%d, listPrice=%d, amount=%d]",
                this.userId, this.productId, this.paidPrice, this.listPrice, this.amount
        );
    }
}
