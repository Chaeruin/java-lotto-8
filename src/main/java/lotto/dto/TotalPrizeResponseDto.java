package lotto.dto;

import java.util.Map;
import lotto.domain.TotalPrize;
import lotto.enums.Prize;

public record TotalPrizeResponseDto(
        Map<Prize, Integer> winnings
) {

    public static TotalPrizeResponseDto of(TotalPrize totalPrize) {
        return new TotalPrizeResponseDto(totalPrize.getWinnings());
    }
}
