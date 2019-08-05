package com.rich.ampos.order.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class NewMenu {

    @NotBlank
    private String menuId;

    @Min(1)
    private int quantity;
}
