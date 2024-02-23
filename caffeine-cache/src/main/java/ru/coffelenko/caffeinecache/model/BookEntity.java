package ru.coffelenko.caffeinecache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("books")
public class BookEntity {
    @Id
    @Column("id")
    private Long id;

    @Column("title")
    private String title;
}
