package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import lotto.enums.Prize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalPrizeTest {

    @Test
    @DisplayName("당첨 내역이 정상적으로 생성/추가되는지 검증")
    void test1() {
        TotalPrize totalPrize = new TotalPrize();
        totalPrize.addPrize(Prize.FIFTH, 2);
        totalPrize.addPrize(Prize.SECOND, 1);

        Map<Prize, Integer> winnings = totalPrize.getWinnings();

        assertAll(
                () -> assertEquals(0, winnings.get(Prize.FIRST)),
                () -> assertEquals(1, winnings.get(Prize.SECOND)),
                () -> assertEquals(0, winnings.get(Prize.THIRD)),
                () -> assertEquals(0, winnings.get(Prize.FOURTH)),
                () -> assertEquals(2, winnings.get(Prize.FIFTH))
        );
    }

    @Test
    @DisplayName("당첨 내역에 대해 총 당첨 금액 산출 검증")
    void test2() {
        TotalPrize totalPrize = new TotalPrize();
        totalPrize.addPrize(Prize.FIFTH, 2);
        totalPrize.addPrize(Prize.SECOND, 1);

        int totalPrizeMoney = totalPrize.calculateTotalPrizeAmount();
        assertEquals(30000000 + 10000, totalPrizeMoney);
    }

}