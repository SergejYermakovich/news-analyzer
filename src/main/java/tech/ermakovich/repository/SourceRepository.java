package tech.ermakovich.repository;

import org.springframework.stereotype.Repository;
import tech.ermakovich.model.entity.Source;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SourceRepository {

    private final Map<String, Source> SOURCES = new HashMap<>();

    public List<Source> findAll() {
        return List.of(
                Source.builder()
                        .id("1")
                        .url("https://newsapi.org/v2")
                        .name("News API")
                        .build(),
                Source.builder()
                        .id("2")
                        .url("test.com/1")
                        .name("test 1")
                        .build(),
                Source.builder()
                        .id("3")
                        .url("test.com/2")
                        .name("test 2")
                        .build()
        );
    }
}
