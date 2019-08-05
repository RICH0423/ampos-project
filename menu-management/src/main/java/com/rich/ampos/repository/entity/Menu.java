package com.rich.ampos.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "menu")
public class Menu {

    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private BigDecimal price;
    private List<String> type;

}
