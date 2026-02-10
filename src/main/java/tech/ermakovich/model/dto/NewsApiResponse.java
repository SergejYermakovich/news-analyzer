package tech.ermakovich.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsApiResponse {
    private List<Article> articles;
}
