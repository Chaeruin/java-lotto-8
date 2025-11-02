package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import lotto.enums.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomLottoNumberGeneratorTest {

    @Test
    @DisplayName("6개의 숫자 정상 생성")
    void test1() {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        List<Integer> numbers = generator.generate();

        assertEquals(LottoNumber.LOTTO_SIZE.getNumber(), numbers.size());
    }

    @Test
    @DisplayName("생성된 숫자가 1 이상 45 이하로 정상 생성되었는지 확인")
    void test2() {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        List<Integer> numbers = generator.generate();

        for (int num : numbers) {
            assertAll(
                    () -> assertTrue(num >= LottoNumber.MIN_LOTTO_NUMBER.getNumber()),
                    () -> assertTrue(num <= LottoNumber.MAX_LOTTO_NUMBER.getNumber())
            );
        }
    }

    @Test
    @DisplayName("생성된 숫자 중 중복 숫자가 없는지 확인")
    void test3() {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        List<Integer> numbers = generator.generate();

        Set<Integer> uniqueNumbers = Set.copyOf(numbers);
        assertEquals(numbers.size(), uniqueNumbers.size());
    }

}