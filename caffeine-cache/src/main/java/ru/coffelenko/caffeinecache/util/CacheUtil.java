package ru.coffelenko.caffeinecache.util;

import com.github.benmanes.caffeine.cache.AsyncCache;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

import static java.util.Objects.nonNull;

@UtilityClass
@Slf4j
public class CacheUtil {
    public static <T, K> Mono<T> addToCache(AsyncCache<K, T> cache, K key, Mono<T> obj) {
        if (!cache.asMap().containsKey(key)) {
            cache.put(key, obj.toFuture());
            return obj;
        }  else {
            return getCache(cache, key);
        }
    }

    public static <K,T> Mono<T> getCache(AsyncCache<K, T> cache, K key) {
        CompletableFuture<T> future = cache.getIfPresent(key);
        return nonNull(future)
                ? Mono.fromFuture(future)
                : Mono.error(new RuntimeException("Unable to get cache!"));
    }


    public static <T, K> Mono<T> getCache(AsyncCache<K, T> cache, K key, Mono<T> data) {
        return cache.asMap().containsKey(key)
                ? getCache(cache, key)
                : addToCache(cache, key, data);
    }
}
