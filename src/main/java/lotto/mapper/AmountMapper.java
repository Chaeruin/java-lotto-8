package lotto.mapper;

import lotto.domain.Amount;
import lotto.dto.AmountDto;

public class AmountMapper {

    public static Amount toEntity(AmountDto amountDto) {
        return new Amount(amountDto.amount());
    }
}
