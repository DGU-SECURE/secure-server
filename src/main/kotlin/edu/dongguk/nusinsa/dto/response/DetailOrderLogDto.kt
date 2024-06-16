package edu.dongguk.nusinsa.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import edu.dongguk.nusinsa.domain.Order

data class DetailOrderLogDto(
    @JsonProperty("order_id")
    val orderId: Long,
    @JsonProperty("item_name")
    val itemName: String,
    @JsonProperty("item_count")
    val itemCount: Int,
    @JsonProperty("order_code")
    val orderCode: String,
//    val address: String,
    @JsonProperty("order_date")
    val orderDate: String,
    @JsonProperty("order_status")
    val orderStatus: String,
    @JsonProperty("payment_type")
    val paymentType: String,
    @JsonProperty("used_money")
    val usedMoney: Long,
    @JsonProperty("used_point")
    val usedPoint: Int,
    @JsonProperty("total_price")
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
            order.getPaymentType().getPaymentType(),
            order.getTotalPrice() - order.getPoint().getUseAmount().toLong(),
            order.getPoint().getUseAmount(),
            order.getTotalPrice()
        )
    }
}
