package tech.ermakovich.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {
    private String role;
    private String content;
}
