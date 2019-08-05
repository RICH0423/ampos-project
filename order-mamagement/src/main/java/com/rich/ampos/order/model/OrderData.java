package com.rich.ampos.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderData {

    @Valid
    @NotEmpty
    List<NewMenu> items;
}
