package lotto.domain;

import java.util.Comparator;
import java.util.List;
import lotto.enums.LottoNumber;
import lotto.exception.ValidateErrorCode;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortedNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers); // 외부에 내부 컬렉션 노출 방지
    }

    public int containsNumbers(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public static Lotto create(LottoNumberGenerator generator) {
        return new Lotto(generator.generate());
    }

    private List<Integer> sortedNumbers(List<Integer> numbers) {
        return numbers.stream().sorted(Comparator.naturalOrder()).toList();
    }

    private void validate(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateNumbersInRange(numbers);
        validateNumbersDuplicated(numbers);
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if (numbers.size() != LottoNumber.LOTTO_SIZE.getNumber()) {
            throw new IllegalArgumentException(
                    ValidateErrorCode.LOTTO_INTEGER_LIST_SIZE_IS_NOT_VALID.getErrorMessage());
        }
    }

    private void validateNumbersInRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(num -> !isInRange(num))) {
            throw new IllegalArgumentException(ValidateErrorCode.LOTTO_NUMBER_IS_NOT_IN_RANGE.getErrorMessage());
        }
    }

    private void validateNumbersDuplicated(List<Integer> numbers) {
        if (numbers.stream().distinct().toList().size() != numbers.size()) {
            throw new IllegalArgumentException(ValidateErrorCode.LOTTO_INTEGER_LIST_IS_DUPLICATED.getErrorMessage());
        }
    }

    private boolean isInRange(int num) {
        return num >= LottoNumber.MIN_LOTTO_NUMBER.getNumber() && num <= LottoNumber.MAX_LOTTO_NUMBER.getNumber();
    }
}
