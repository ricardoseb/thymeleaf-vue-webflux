/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ricardoseb.basicecommerce.controller;

import com.ricardoseb.basicecommerce.domain.Item;
import com.ricardoseb.basicecommerce.domain.Product;
import com.ricardoseb.basicecommerce.utils.IntervalMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.result.view.Rendering;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

/**
 * @author Ricardo Quiroga
 */
@Slf4j
@Controller
public class HomeController {


    @GetMapping
    Mono<Rendering> home() {
        Product p = new Product();
        p.setId("1");
        p.setName("zapatillas");
        p.setAtrr(Map.of("color", "rojito", "talla", 41));

        return Mono.just(Rendering.view("poc.html")
                .modelAttribute("pageTitle",
                        "titulo")
                .modelAttributes("searchTerm", "thymeleaf")
                .modelAttribute("title",
                        "titulo")
                .modelAttribute("scientists",
                        List.of("Albert Einstein",
                                "Niels Bohr",
                                "James Clerk Maxwell"))
                .modelAttribute("product", p)
                .modelAttribute("product2", Product.builder()
                        .name("zapatos")
                        .atrr(Map.of("color y tonalidad", "cafe, oscuro"))
                        .items(List.of(Item.builder().name("rebook n200").price(new BigDecimal("123")).build()))
                        .build())


                .build());
    }

    @GetMapping(path = "/test/{id}")
    Mono<Rendering> home2(@PathVariable String id) {
        log.info("ID: {}", id);
        return Mono.just(Rendering.view("test.html")
                .modelAttribute("product", Product.builder()
                        .name("zapatos")
                        .atrr(Map.of("color y tonalidad", "cafe, oscuro"))
                        .items(List.of(Item.builder().name("rebook n200").price(new BigDecimal("123")).build()))
                        .build())
                .modelAttributes("id", id)
                .build());
    }

    @GetMapping(produces = TEXT_EVENT_STREAM_VALUE, value = "/ticker-stream")
    String streamingUpdates(Model model) {
        var producer = IntervalMessageProducer.produce();
        var updates = new ReactiveDataDriverContextVariable(producer, 1);
        model.addAttribute("updates", updates);
        return "test :: #updateBlock";
    }

}
