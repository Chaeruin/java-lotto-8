package lotto.mapper;

import java.util.List;
import lotto.domain.Lotto;
import lotto.dto.LottoResponseDto;
import lotto.dto.WinningNumberDto;

public class LottoMapper {

    public static Lotto toEntity(WinningNumberDto winningNumberDto) {
        return new Lotto(winningNumberDto.winningNumbers());
    }

    public static Lotto toEntity(LottoResponseDto lottoResponseDto) {
        return new Lotto(lottoResponseDto.numbers());
    }

    public static List<LottoResponseDto> toDto(List<Lotto> lottery) {
        return lottery.stream()
                .map(LottoResponseDto::of)
                .toList();
    }

    public static List<Lotto> toEntityList(List<LottoResponseDto> lottery) {
        return lottery.stream()
                .map(LottoMapper::toEntity)
                .toList();
    }
}
