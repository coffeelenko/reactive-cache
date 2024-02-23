package ru.coffelenko.caffeinecache.controller;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.coffelenko.caffeinecache.model.BookEntity;
import ru.coffelenko.caffeinecache.repo.BooksRepo;
import ru.coffelenko.caffeinecache.util.CacheUtil;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final BooksRepo repo;

    private final AsyncCache<Long, BookEntity> BOOKS_CACHE =
            Caffeine
                    .newBuilder()
                    .expireAfterWrite(Duration.ofMinutes(2))
                    .maximumSize(1000)
                    .buildAsync();

    @GetMapping("/test")
    public Mono<BookEntity> test() {
        return CacheUtil
                .getCache(BOOKS_CACHE, 1L, repo.getBookById(1L));
    }
}
