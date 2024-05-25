package edu.dongguk.nusinsa.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import edu.dongguk.nusinsa.domain.OrderItem

data class OrderedItemDto(
    val id: Long,
    @JsonProperty("item_code")
    val itemCode: String,
    val name: String,
    @JsonProperty("item_quantity")
    val itemQuantity: Int,
    val thumbnail: String,
    val price: Long,
    @JsonProperty("order_status")
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
