package lotto.exception;

public enum ValidateErrorCode {

    // Lotto 검증 에러 예외 처리
    LOTTO_INTEGER_LIST_SIZE_IS_NOT_VALID("로또 발행 사이즈가 알맞지 않습니다."),
    LOTTO_NUMBER_IS_NOT_IN_RANGE("로또 숫자가 알맞은 범위 내애 있지 않습니다."),
    LOTTO_INTEGER_LIST_IS_DUPLICATED("중복된 숫자가 존재합니다."),

    // 보너스 넘버 범위 에러 예외 처리
    BONUS_NUMBER_IS_NOT_IN_RANGE("보너스 숫자가 알맞은 범위 내애 있지 않습니다."),

    // 입력한 당첨 번호와 보너스 넘버 비교하여 중복 에러 예외 처리
    WINNING_NUMBER_IS_DUPLICATED_WITH_BONUS_NUMBER("당첨 번호 중 보너스 번호와 중복되는 숫자가 있습니다."),

    // 구매 금액에 대한 에러 예외 처리
    PURCHASE_AMOUNT_IS_OVER_RANGE("입력 값이 100,000원을 초과했습니다."),
    PURCHASE_AMOUNT_IS_NOT_DIVIDED("입력 값이 1000원 단위가 아닙니다.");


    private final String errorMessage;

    ValidateErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
