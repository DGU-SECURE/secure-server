package edu.dongguk.nusinsa.entity.type

/**
 * 주문 상태
 * 상품 주문 프로세스: 입금 확인 -> 입금 완료 -> 출고 처리 -> 출고 완료 -> 배송중 -> 배송 완료
 * 상품 환불 프로세스: 환불 처리 -> 환불 완료
 */
enum class OrderState(
    private val orderState: String
) {
    DEPOSIT_CHECK("입금 확인"),
    DEPOSIT_COMPLETE("입금 완료"),
    RELEASE_PROCESS("출고 처리"),
    RELEASE_COMPLETE("출고 완료"),
    DELIVERY("배송중"),
    DELIVERY_COMPLETE("배송 완료"),
    REFUND_PROCESS("환불 처리"),
    REFUND_COMPLETE("환불 완료")
}