package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public record LottoResponseDto(
        List<Integer> numbers
) {

    public static LottoResponseDto of(Lotto lotto) {
        return new LottoResponseDto(lotto.getNumbers());
    }
}
