package edu.dongguk.nusinsa.dto.response

import edu.dongguk.nusinsa.domain.Order
import edu.dongguk.nusinsa.domain.Point

data class OrderedItemsDto(
    val totalPrice: Long,
    val usedPoint: Int,
    val savedPoint: Int,
    val orderDate: String,
    val paymentType: String,
    val dataList: List<OrderedItemDto>
) {
    companion object {
        fun of (order: Order, point: Point, dataList: List<OrderedItemDto>) = OrderedItemsDto(
            order.getTotalPrice(),
            point.getUseAmount(),
            point.getSaveAmount(),
            order.getCreatedAt().toString(),
            order.getPaymentType().getPaymentType(),
            dataList
        )
    }
}
