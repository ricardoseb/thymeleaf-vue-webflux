package com.ricardoseb.basicecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Ricardo Quiroga on 29-10-21
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Item{

    private String name;
    private BigDecimal price;
}
