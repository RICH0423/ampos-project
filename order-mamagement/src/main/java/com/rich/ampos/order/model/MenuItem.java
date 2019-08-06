package com.rich.ampos.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

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
    @Min(1)
    private int quantity;
    private String name;
    private BigDecimal price;
    private long createdTime;

    public BigDecimal caculatePaymentPrice() {
        return this.getPrice().multiply(new BigDecimal(this.getQuantity()));
    }
}
