package edu.dongguk.nusinsa.dto.response

import edu.dongguk.nusinsa.domain.Order

data class DetailOrderLogDto(
    val orderId: Long,
    val itemName: String,
    val itemCount: Int,
    val orderCode: String,
//    val address: String,
    val orderDate: String,
    val orderStatus: String,
    val usedMoney: Long,
    val usedPoint: Int,
    val totalPrice: Long
) {
    companion object {
        fun of(order: Order) = DetailOrderLogDto(
            order.getId()!!,
            order.getOrderItems().get(0).getItem().getName(),
            order.getOrderItems().size - 1,
            order.getOrderItems().get(0).getOrderCode(),
            order.getCreatedAt().toString(),
            order.getOrderState().getOrderState(),
            order.getTotalPrice() - order.getPoint().getUseAmount().toLong(),
            order.getPoint().getUseAmount(),
            order.getTotalPrice()
        )
    }
}
