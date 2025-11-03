package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import lotto.mapper.AmountMapper;
import lotto.util.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReturnRateTest {

    @Test
    @DisplayName("정상적인 수익률 계산 반환")
    void test1() {
        ReturnRate returnRate = new ReturnRate();
        Amount amount = AmountMapper.toEntity(InputParser.parseAmount("7000"));
        int totalPrize = 55000;
        double returnRating = returnRate.getReturnRate(amount, totalPrize);

        assertEquals(785.7, returnRating);
    }

}