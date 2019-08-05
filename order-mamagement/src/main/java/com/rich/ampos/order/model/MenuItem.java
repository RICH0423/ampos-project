package com.rich.ampos.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @Min(1)
    private int quantity;

    private BigDecimal price;

    private long createdTime;
}
