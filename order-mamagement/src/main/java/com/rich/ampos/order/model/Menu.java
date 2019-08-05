package com.rich.ampos.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class Menu {

    private String id;
    private String name;
    private String image;
    private String description;
    private BigDecimal price;
    private List<String> type;

}
