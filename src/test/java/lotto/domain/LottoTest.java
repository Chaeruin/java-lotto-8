package lotto.domain;

import lotto.enums.LottoNumber;
import lotto.exception.ValidateErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidateErrorCode.LOTTO_INTEGER_LIST_SIZE_IS_NOT_VALID.getErrorMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidateErrorCode.LOTTO_INTEGER_LIST_IS_DUPLICATED.getErrorMessage());
    }

    @Test
    @DisplayName("로또 번호에 1 이상 45 이하가 아닌 요소가 있을 경우 예외_1 미만")
    void test1() {
        assertThatThrownBy(() -> new Lotto(List.of(LottoNumber.MIN_LOTTO_NUMBER.getNumber() - 1, 2, 3, 4, 5, 45)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidateErrorCode.LOTTO_NUMBER_IS_NOT_IN_RANGE.getErrorMessage());
    }

    @Test
    @DisplayName("로또 번호에 1 이상 45 이하가 아닌 요소가 있을 경우 예외_45 초과")
    void test2() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, LottoNumber.MAX_LOTTO_NUMBER.getNumber() + 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidateErrorCode.LOTTO_NUMBER_IS_NOT_IN_RANGE.getErrorMessage());
    }
}
