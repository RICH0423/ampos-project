package com.rich.ampos.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    private String menuId;
    private int quantity;
    private long createdTime;
}
