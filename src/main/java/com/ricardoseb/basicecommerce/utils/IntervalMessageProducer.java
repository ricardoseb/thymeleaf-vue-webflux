package com.ricardoseb.basicecommerce.utils;

import lombok.Data;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ricardo Quiroga on 30-11-21
 */
public abstract class IntervalMessageProducer {

    public static Flux<String> produce(int c) {
        return produce().take(c);
    }

    public static Flux<String> produce() {
        return doProduceCountAndStrings().map(CountAndString::getMessage);
    }

    private static Flux<CountAndString> doProduceCountAndStrings() {
        var counter = new AtomicLong();
        return Flux //
                .interval(Duration.ofSeconds(1)) // <1>
                .map(i -> new CountAndString(counter.incrementAndGet())); //
    }

}

@Data
class CountAndString {

    private final String message;

    private final long count;

    CountAndString(long c) {
        this.count = c;
        this.message = "# " + this.count;
    }
}
