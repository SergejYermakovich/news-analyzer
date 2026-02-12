package tech.ermakovich.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliverySettings {
    private String format;    // short, detailed, meme
    private String frequency; // instant, daily, weekly
    private String time;
}
