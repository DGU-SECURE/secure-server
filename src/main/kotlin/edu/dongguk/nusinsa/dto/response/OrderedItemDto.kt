package edu.dongguk.nusinsa.dto.response

import edu.dongguk.nusinsa.domain.OrderItem

data class OrderedItemDto(
    val itemId: Long,
    val itemCode: String,
    val itemName: String,
    val itemQuantity: Int,
    val imageUuidName: String,
    val itemPrice: Long,
    val orderStatus: String
) {
    companion object {
        fun of(orderItem: OrderItem) = OrderedItemDto(
            orderItem.getItem().getId()!!,
            orderItem.getOrderCode(),
            orderItem.getItem().getName(),
            orderItem.getCounts(),
            orderItem.getItem().getImage().getUuidName(),
            orderItem.getItem().getPrice(),
            orderItem.getOrder().getOrderState().toString()
        )
    }
}
