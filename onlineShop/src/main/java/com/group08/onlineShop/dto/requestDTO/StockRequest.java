package com.group08.onlineShop.dto.requestDTO;

import lombok.Data;

@Data
public class StockRequest {
    private Long product;
    private String size;
    private String color;
    private Integer quantity;
}
