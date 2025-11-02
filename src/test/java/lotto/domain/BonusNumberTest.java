package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.enums.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {

    @Test
    @DisplayName("1 이상 45 이하가 아니면 예외_1 미만")
    void test1() {
        assertThatThrownBy(() -> new BonusNumber(LottoNumber.MIN_LOTTO_NUMBER.getNumber() - 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1 이상 45 이하가 아니면 예외_45 초과")
    void test2() {
        assertThatThrownBy(() -> new BonusNumber(LottoNumber.MAX_LOTTO_NUMBER.getNumber() + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

}