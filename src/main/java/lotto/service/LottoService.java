package lotto.service;

import java.util.List;
import lotto.domain.Issue;
import lotto.domain.ReturnRate;
import lotto.domain.WinningLottery;
import lotto.dto.AmountDto;
import lotto.dto.BonusNumberDto;
import lotto.dto.LottoResponseDto;
import lotto.dto.TotalPrizeResponseDto;
import lotto.dto.WinningNumberDto;
import lotto.mapper.AmountMapper;
import lotto.mapper.BonusNumberMapper;
import lotto.mapper.LottoMapper;

public class LottoService {

    private final Issue issue;
    private final ReturnRate returnRate;
    private final WinningLottery winningLottery;

    public LottoService(Issue issue, ReturnRate returnRate, WinningLottery winningLottery) {
        this.issue = issue;
        this.returnRate = returnRate;
        this.winningLottery = winningLottery;
    }

    public List<LottoResponseDto> getLottery(AmountDto amount) {
        return LottoMapper.toDto(issue.issueLottery(AmountMapper.toEntity(amount)));
    }

    public TotalPrizeResponseDto getTotalPrize(
            List<LottoResponseDto> lottery, WinningNumberDto winningNumber, BonusNumberDto bonusNumber) {
        return TotalPrizeResponseDto.of(winningLottery.getTotalWinnings(
                LottoMapper.toEntityList(lottery), LottoMapper.toEntity(winningNumber),
                BonusNumberMapper.toEntity(bonusNumber)));
    }

    public double getReturnRate(AmountDto amount, TotalPrizeResponseDto totalPrize) {
        return returnRate.getReturnRate(AmountMapper.toEntity(amount), totalPrize.totalPrizeMoney());
    }
}
