package edu.dongguk.nusinsa.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import edu.dongguk.nusinsa.domain.OrderItem

data class OrderedItemDto(
    @JsonProperty("item_id")
    val itemId: Long,
    @JsonProperty("item_code")
    val itemCode: String,
    @JsonProperty("item_name")
    val itemName: String,
    @JsonProperty("item_quantity")
    val itemQuantity: Int,
    @JsonProperty("image_uuid_name")
    val imageUuidName: String,
    @JsonProperty("item_price")
    val itemPrice: Long,
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
