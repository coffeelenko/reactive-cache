package ru.coffelenko.caffeinecache.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import ru.coffelenko.caffeinecache.model.BookEntity;


public interface BooksRepo extends R2dbcRepository<BookEntity, Long> {
    Mono<BookEntity> getBookById(long id);
}
