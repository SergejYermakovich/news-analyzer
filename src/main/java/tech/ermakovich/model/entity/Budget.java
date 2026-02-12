package tech.ermakovich.model.entity;

import lombok.Getter;
import lombok.Setter;
import tech.ermakovich.model.enums.Currency;

@Getter
@Setter
public class Budget {
    private Integer min;
    private Integer max;
    private Currency currency;
}
