package tech.ermakovich.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Subscriptions {
    private Set<String> sources;
    private Set<String> categories;
    private Set<String> keywords;
}
