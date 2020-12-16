package com.kyueun.apis.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRegisterVO {
    private String name;
    private String description;
    private int listPrice;
    private int price;

    @Override
    public String toString() {
        return String.format(
                "ProductRegisterVO[name='%s', description='%s', listPrice=%d, price=%d",
                name, description, listPrice, price
        );
    }
}
