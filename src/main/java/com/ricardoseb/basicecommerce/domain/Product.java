package com.ricardoseb.basicecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Ricardo Quiroga on 29-10-21
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {
    private String name;
    private String id;
    private Map<Object, Object> atrr;
    private List<Item> items;
}
