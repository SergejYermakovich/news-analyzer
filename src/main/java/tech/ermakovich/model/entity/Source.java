package tech.ermakovich.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Source {
    private String id;
    private String name;
    private String url;
}
