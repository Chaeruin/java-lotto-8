package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import lotto.mapper.AmountMapper;
import lotto.util.InputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IssueTest {

    @Test
    @DisplayName("로또 발행에서 금액에 맞는 숫자의 로또 갯수 발행 및 로또 generate 정상적으로 이루어짐 검증")
    void test1() {
        Issue issue = new Issue(new RandomLottoNumberGenerator());
        Amount amount = AmountMapper.toEntity(InputParser.parseAmount("3000"));

        List<Lotto> lottery = issue.issueLottery(amount);

        assertEquals(amount.getCountByAmount(), lottery.size());

        for (Lotto lotto : lottery) {
            assertEquals(6, lotto.getNumbers().size());
        }
    }
}