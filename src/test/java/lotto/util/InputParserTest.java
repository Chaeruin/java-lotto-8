package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import lotto.dto.AmountDto;
import lotto.dto.BonusNumberDto;
import lotto.dto.WinningNumberDto;
import lotto.exception.ParseErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    @DisplayName("당첨 번호 정상 파싱")
    void test1() {
        String input = "1,2,3,4,5,6";

        WinningNumberDto winningNumbers = InputParser.parseWinningNumbers(input);

        assertNotNull(winningNumbers);
        assertEquals(6, winningNumbers.winningNumbers().size());
        assertIterableEquals(List.of(1,2,3,4,5,6), winningNumbers.winningNumbers());
    }

    @Test
    @DisplayName("당첨 번호 입력 시 구분자 불량 예외")
    void test2() {
        assertThatThrownBy(() -> InputParser.parseWinningNumbers("1,2,3,4,5.6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ParseErrorCode.WINNING_NUMBER_PARSING_ERROR.getErrorMessage());
    }

    @Test
    @DisplayName("당첨 번호 입력 시 정수 아닌 것 입력 예외")
    void test3() {
        assertThatThrownBy(() -> InputParser.parseWinningNumbers("1,2,3,4,5,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ParseErrorCode.WINNING_NUMBER_PARSING_ERROR.getErrorMessage());
    }

    @Test
    @DisplayName("구입 금액 정상 파싱")
    void test4() {
        String input = "5000";

        AmountDto amount = InputParser.parseAmount(input);

        assertNotNull(amount);
        assertEquals(5000, amount.amount());
    }

    @Test
    @DisplayName("구입 금액 입력 시 정수 아닌 것 입력 예외")
    void test5() {
        assertThatThrownBy(() -> InputParser.parseAmount("aaaa"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ParseErrorCode.PURCHASE_AMOUNT_IS_NOT_INT.getErrorMessage());
    }

    @Test
    @DisplayName("보너스 번호 정상 파싱")
    void test6() {
        String input = "11";

        BonusNumberDto bonusNumber = InputParser.parseBonusNumber(input);

        assertNotNull(bonusNumber);
        assertEquals(11, bonusNumber.bonusNumber());
    }

    @Test
    @DisplayName("보너스 번호 입력 시 정수 아닌 것 입력 예외")
    void test7() {
        assertThatThrownBy(() -> InputParser.parseBonusNumber("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ParseErrorCode.BONUS_NUMBER_IS_NOT_INT.getErrorMessage());
    }
}