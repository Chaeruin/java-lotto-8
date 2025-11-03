package lotto.exception;

public enum ParseErrorCode {

    // 당첨 번호 입력 파싱 에러 예외 처리
    WINNING_NUMBER_PARSING_ERROR("[ERROR] 입력이 올바르지 않습니다."),

    // 구입 금액 파싱 에러 예외 처리
    PURCHASE_AMOUNT_IS_NOT_INT("[ERROR] 구입 금액이 정수가 아닙니다."),

    // 보너스 번호 파싱 에러 예외 처리
    BONUS_NUMBER_IS_NOT_INT("[ERROR] 보너스 번호가 정수가 아닙니다."),;


    private final String errorMessage;

    ParseErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
