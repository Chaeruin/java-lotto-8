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
import lotto.view.OutputView;

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
            Amount amount = getAmount();
            // 구입 금액에 따른 로또 발행
            List<Lotto> lottery = issue.issueLottery(amount);
            // 발행 로또 리스트 출력
            List<LottoResponseDto> lottoResponseDto = getLottoResponseDto(lottery);
            OutputView.printLottery(amount.getCountByAmount(), lottoResponseDto);

            // 당첨 번호 입력
            Lotto winningNumber = getWinningNumber();
            // 보너스 번호 입력
            BonusNumber bonusNumber = getBonusNumber();

            // 로직 !@#$!@#$
            TotalPrize totalPrize = winningLottery.getTotalWinnings(lottery, winningNumber, bonusNumber);
            // 당첨 통계 포멧애 맞게 출력
            OutputView.printTotalPrize(TotalPrizeResponseDto.of(totalPrize));

            // 수익률 출력
            OutputView.printReturnRate(returnRate.getReturnRate(amount, totalPrize.calculateTotalPrizeAmount()));
        } finally {
            InputView.closeConsole();
        }
    }

    private Amount getAmount() {
        while (true) {
            try {
                return Amount.from(InputParser.parseAmount(InputView.getAmount()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto getWinningNumber() {
        while (true) {
            try {
                return Lotto.from(InputParser.parseWinningNumbers(InputView.getWinningNumbers()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber getBonusNumber() {
        while (true) {
            try {
                return BonusNumber.from(InputParser.parseBonusNumber(InputView.getBonusNumber()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<LottoResponseDto> getLottoResponseDto(List<Lotto> lottery) {
        return lottery.stream()
                .map(LottoResponseDto::of)
                .toList();
    }
}
