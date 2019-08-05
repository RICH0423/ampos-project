package com.rich.ampos.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderData {

    List<NewMenu> items;
}
