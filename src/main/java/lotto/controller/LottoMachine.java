package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Amount;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.TotalPrize;
import lotto.dto.AmountDto;
import lotto.dto.LottoResponseDto;
import lotto.dto.TotalPrizeResponseDto;
import lotto.service.Issue;
import lotto.service.ReturnRate;
import lotto.service.WinningLottery;
import lotto.util.InputParser;
import lotto.view.InputView;

public class LottoMachine {

    private final LottoNumberGenerator generator;
    private final Issue issue;
    private final ReturnRate returnRate;
    private final WinningLottery winningLottery;

    public LottoMachine(LottoNumberGenerator generator, Issue issue,
                        ReturnRate returnRate, WinningLottery winningLottery) {
        this.generator = generator;
        this.issue = issue;
        this.returnRate = returnRate;
        this.winningLottery = winningLottery;
    }

    public void run() {
        try {
            // 구입 금액 입력
            Amount amount = Amount.from(InputParser.parseAmount(InputView.getAmount()));
            // 구입 금액에 따른 로또 발행
            List<Lotto> lottery = issue.issueLottery(amount);
            // 발행 로또 리스트 출력
            List<LottoResponseDto> lottoResponseDto = getLottoResponseDto(lottery);


            // 당첨 번호 입력
            Lotto winningNumber = Lotto.from(InputParser.parseWinningNumbers(InputView.getWinningNumbers()));
            // 보너스 번호 입력
            BonusNumber bonusNumber = BonusNumber.from(InputParser.parseBonusNumber(InputView.getBonusNumber()));

            // 로직 !@#$!@#$
            TotalPrize totalPrize = winningLottery.getTotalWinnings(lottery, winningNumber, bonusNumber);
            // 당첨 통계 포멧애 맞게 출력
            TotalPrizeResponseDto totalPrizeResponseDto = TotalPrizeResponseDto.of(totalPrize);

            // 수익률 출력
            double returnRating = returnRate.getReturnRate(amount, totalPrize.calculateTotalPrizeAmount());
        } finally {
            InputView.closeConsole();
        }
    }

    private List<LottoResponseDto> getLottoResponseDto(List<Lotto> lottery) {
        return lottery.stream()
                .map(LottoResponseDto::of)
                .toList();
    }
}
