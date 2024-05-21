package edu.dongguk.nusinsa.domain.type

/**
 * 상품 결제 타입
 * 포인트로만 결제했으면 POINT, 아니라면 CARD
 */
enum class PaymentType(
    private val paymentType: String
) {
    CARD("신용/체크카드 결제"),
    POINT("포인트 결제")
}