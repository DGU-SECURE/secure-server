package edu.dongguk.nusinsa.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import edu.dongguk.nusinsa.domain.Order

data class OrderLogDto(
    @JsonProperty("order_id")
    val orderId: Long,
    @JsonProperty("item_name")
    val itemName: String,
    @JsonProperty("item_count")
    val itemCount: Int,
    @JsonProperty("order_code")
    val orderCode: String,
    @JsonProperty("order_date")
    val orderDate: String,
    @JsonProperty("total_price")
    val totalPrice: Long,
    @JsonProperty("order_status")
    val orderStatus: String
) {
    companion object {
        fun of(order: Order) = OrderLogDto(
            order.getId()!!,
            order.getOrderItems().get(0).getItem().getName(),
            order.getOrderItems().size - 1,
            order.getOrderItems().get(0).getOrderCode(),
            order.getCreatedAt().toString(),
            order.getTotalPrice(),
            order.getOrderState().getOrderState()
        )
    }
}
