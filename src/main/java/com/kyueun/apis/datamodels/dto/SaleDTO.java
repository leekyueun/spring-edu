package com.kyueun.apis.datamodels.dto;

import com.kyueun.apis.model.Sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaleDTO {
    private int saleId;
    private int userId;
    private int productId;
    private int paidPrice;
    private int listPrice;
    private int amount;


    public SaleDTO(Sale sale) {
        this.saleId = sale.getSaleId();
        this.userId = sale.getUserId();
        this.productId = sale.getProductId();
        this.paidPrice = sale.getPaidPrice();
        this.listPrice = sale.getListPrice();
        this.amount = sale.getAmount();
    }

    @Override
    public String toString() {
        return String.format(
                "SaleDTO[saleId=%d, userId=%d, productId=%d, paidPrice=%d, listPrice=%d, amount=%d]",
                this.saleId, this.userId, this.productId, this.paidPrice, this.listPrice, this.amount
        );
    }
}
