package tech.ermakovich.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Subscriptions {
    private List<String> sources;
    private List<String> categories;
    private List<String> keywords;
}
