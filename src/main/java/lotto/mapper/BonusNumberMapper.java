package lotto.mapper;

import lotto.domain.BonusNumber;
import lotto.dto.BonusNumberDto;

public class BonusNumberMapper {

    public static BonusNumber toEntity(BonusNumberDto bonusNumberDto) {
        return new BonusNumber(bonusNumberDto.bonusNumber());
    }
}
