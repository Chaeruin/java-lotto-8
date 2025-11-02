package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import lotto.enums.Prize;
import lotto.exception.ValidateErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLotteryTest {

    @Test
    @DisplayName("총 당첨 내역 정상적으로 발행")
    void test1() {
        WinningLottery winningLottery = new WinningLottery();

        List<Lotto> lottery = List.of(new Lotto(List.of(1,2,3,4,5,6)), new Lotto(List.of(1,2,3,7,8,9)));
        Lotto winningNumber = new Lotto(List.of(1,2,3,4,11,12));
        BonusNumber bonusNumber = new BonusNumber(45);

        TotalPrize totalPrize = winningLottery.getTotalWinnings(lottery, winningNumber, bonusNumber);
        Map<Prize, Integer> winnings = totalPrize.getWinnings();

        assertAll(
                () -> assertEquals(1, winnings.get(Prize.FIFTH)),
                () -> assertEquals(1, winnings.get(Prize.FOURTH)),
                () -> assertEquals(0, winnings.get(Prize.THIRD)),
                () -> assertEquals(0, winnings.get(Prize.SECOND)),
                () -> assertEquals(0, winnings.get(Prize.FIRST))
        );
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호 사이 중복 존재 시 예외")
    void test2() {
        WinningLottery winningLottery = new WinningLottery();

        List<Lotto> lottery = List.of(new Lotto(List.of(1,2,3,4,5,6)), new Lotto(List.of(1,2,3,7,8,9)));
        Lotto winningNumber = new Lotto(List.of(1,2,3,4,11,12));
        BonusNumber bonusNumber = new BonusNumber(1);

        assertThatThrownBy(() -> winningLottery.getTotalWinnings(lottery, winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidateErrorCode.WINNING_NUMBER_IS_DUPLICATED_WITH_BONUS_NUMBER.getErrorMessage());

    }
}