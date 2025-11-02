package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountTest {

    @Test
    @DisplayName("입력 값이 100,000원 초과 일 경우 예외")
    void test1() {
        assertThatThrownBy(() -> new Amount(1000000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값이 1000원 단위로 나눠지지 않을 경우 예외")
    void test2() {
        assertThatThrownBy(() -> new Amount(1700))
                .isInstanceOf(IllegalArgumentException.class);
    }

}