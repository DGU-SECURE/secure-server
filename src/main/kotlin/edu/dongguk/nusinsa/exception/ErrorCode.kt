package edu.dongguk.nusinsa.exception

/**
 * 커스텀 에러 코드 정의, 부족한 에러 코드는 추가
 */
enum class ErrorCode(
    private val code: String,
    private val message: String
) {
    NOT_END_POINT("400", "잘못된 요청입니다."),

    // Not Found Error
    NOT_FOUND_CUSTOMER("401", "사용자를 찾을 수 없습니다."),
    NOT_FOUND_ERROR("404", "찾을 수 없습니다."),
    NOT_FOUND_ITEM("405", "상품을 찾을 수 않습니다."),
    NOT_FOUND_STORE("406", "매장을 찾을 수 않습니다."),
    NOT_FOUND_ORDER("408", "주문 내역을 찾을 수 없습니다."),

    // Bad Request Error
    NOT_ENOUGH_STOCK_ERROR("410", "주문하신 상품의 재고가 부족합니다."),
    NOT_CHOOSE_ITEMS("411", "상품을 선택하지 않으셨습니다."),
    INVALID_ARGUMENT("412", "잘못된 값 입니다."),
    WRONG_CATEGORY_ERROR("413", "알 수 없는 카테고리입니다."),
    WRONG_PAYMENT_TYPE_ERROR("414", "알 수 없는 결제 수단입니다."),
    NOT_ENOUGH_BALANCE_ERROR("417", "잔고가 부족합니다."),
    NOT_ENOUGH_POINT_BALANCE_ERROR("418", "포인트 잔고가 부족합니다."),
    EMPTY_IMAGE_ERROR("419", "상품 등록시에는 반드시 이미지 파일이 등록되어야 합니다."),
    DUPLICATION_ITEM_ERROR("421", "이미 존재하는 상품입니다."),

    // Security Error
//    ACCESS_DENIED_ERROR("430", "접근 권한이 없습니다."),
//    TOKEN_INVALID_ERROR("431", "유효하지 않은 토큰입니다."),
//    TOKEN_MALFORMED_ERROR("432", "잘못된 토큰 입니다."),
//    TOKEN_EXPIRED_ERROR("433", "만료된 토큰입니다."),
//    TOKEN_TYPE_ERROR("434", "잘못된 형식의 토큰 입니다."),
//    TOKEN_UNSUPPORTED_ERROR("435", "지원되지 않는 토큰입니다."),
//    TOKEN_UNKNOWN_ERROR("436", "토큰 에러입니다."),

    // Server Error
    SERVER_ERROR("500", "서버 내부 오류입니다."),
    DUPLICATION_STORE("506", "이미 존재하는 매장입니다"),

    //Image Error
    IMAGE_SAVING_ERROR("510", "이미지 저장에 실패하였습니다."),
    IMAGE_DELETE_ERROR("511", "이미지 삭제에 실패하였습니다.");


    fun getCode() = this.code

    fun getMessage() = this.message
}