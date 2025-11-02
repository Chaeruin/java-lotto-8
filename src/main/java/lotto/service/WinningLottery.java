package lotto.service;

import java.util.Arrays;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.TotalPrize;
import lotto.enums.Prize;
import lotto.exception.ValidateErrorCode;

public class WinningLottery {

    private final int WINNING_COUNT = 1;

    public TotalPrize getTotalWinnings(List<Lotto> lottery, Lotto winningNumber, BonusNumber bonusNumber) {
        validateDuplicateNumber(winningNumber, bonusNumber);
        TotalPrize totalPrize = new TotalPrize();
        lottery.stream()
                .map(lotto -> compareWinningDetails(lotto, winningNumber, bonusNumber))
                .forEach(prize -> addWinningDetails(totalPrize, prize));

        return totalPrize;
    }

    public Prize compareWinningDetails(Lotto lotto, Lotto winningNumber, BonusNumber bonusNumber) {
        int winningCount = lotto.containsNumbers(winningNumber);
        boolean isBonus = false;
        if (winningCount == 5) {
            isBonus = lotto.contains(bonusNumber.getBonusNumber());
        }

        return matchPrize(winningCount, isBonus);
    }

    public void addWinningDetails(TotalPrize totalPrize, Prize winningPrize) {
        if (winningPrize == null) {
            return;
        }
        totalPrize.addPrize(winningPrize, WINNING_COUNT);
    }

    private void validateDuplicateNumber(Lotto winningNumber, BonusNumber bonusNumber) {
        if (winningNumber.contains(bonusNumber.getBonusNumber())) {
            throw new IllegalArgumentException(
                    ValidateErrorCode.WINNING_NUMBER_IS_DUPLICATED_WITH_BONUS_NUMBER.getErrorMessage());
        }
    }

    private Prize matchPrize(int winningCount, boolean isBonus) {
        return Arrays.stream(Prize.values())
                .filter(prize -> prize.getBalls() == winningCount && prize.isBonus() == isBonus)
                .findFirst()
                .orElse(null);
    }
}
