package lotto.util;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lotto.dto.AmountDto;
import lotto.dto.BonusNumberDto;
import lotto.dto.WinningNumberDto;
import lotto.exception.ParseErrorCode;

public class InputParser {

    public static WinningNumberDto parseWinningNumbers(String input) {
        Validator.winningNumbersValid(input);
        return new WinningNumberDto(
                Arrays.stream(input.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
    }

    public static AmountDto parseAmount(String input) {
        Validator.amountValid(input);
        return new AmountDto(Integer.parseInt(input));
    }

    public static BonusNumberDto parseBonusNumber(String input) {
        Validator.bonusNumberValid(input);
        return new BonusNumberDto(Integer.parseInt(input));
    }


    private static class Validator {

        private static final Pattern VALID_PATTERN = Pattern.compile("^\\d+(,\\d+)*$");

        public static void winningNumbersValid(String input) {
            delimiterParseValid(input);
            parseIntNumbersValid(input);
        }

        private static void delimiterParseValid(String input) {
            if (!VALID_PATTERN.matcher(input).matches()) {
                throw new IllegalArgumentException(ParseErrorCode.WINNING_NUMBER_PARSING_ERROR.getErrorMessage());
            }

            Arrays.stream(input.split(","))
                    .forEach(s -> {
                        try {
                            Integer.parseInt(s);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException(ParseErrorCode.WINNING_NUMBER_PARSING_ERROR.getErrorMessage());
                        }
                    });
        }

        private static void parseIntNumbersValid(String input) {
            Arrays.stream(input.split(","))
                    .forEach(s -> {
                        try {
                            Integer.parseInt(s);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException(ParseErrorCode.WINNING_NUMBER_IS_NOT_INT.getErrorMessage());
                        }
                    });
        }

        public static void amountValid(String input) {
            try {
                Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ParseErrorCode.PURCHASE_AMOUNT_IS_NOT_INT.getErrorMessage());
            }
        }

        public static void bonusNumberValid(String input) {
            try {
                Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ParseErrorCode.BONUS_NUMBER_IS_NOT_INT.getErrorMessage());
            }
        }
    }
}
