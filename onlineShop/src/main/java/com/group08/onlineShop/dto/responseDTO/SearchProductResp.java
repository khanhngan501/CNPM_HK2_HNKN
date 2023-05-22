package com.group08.onlineShop.dto.responseDTO;

import com.group08.onlineShop.model.Category;
import com.group08.onlineShop.model.TypeProduct;
import lombok.Data;

import java.util.List;

@Data
public class SearchProductResp {
    private Long id;
    private String productName;
    private Double price;
    private Long category;
    private TypeProduct type;
    private List<String> imgLink;
}
