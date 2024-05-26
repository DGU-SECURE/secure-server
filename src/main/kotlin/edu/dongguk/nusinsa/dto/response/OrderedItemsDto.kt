package edu.dongguk.nusinsa.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import edu.dongguk.nusinsa.domain.Order
import edu.dongguk.nusinsa.domain.Point

data class OrderedItemsDto(
    @JsonProperty("total_price")
    val totalPrice: Long,
    @JsonProperty("used_point")
    val usedPoint: Int,
    @JsonProperty("saved_point")
    val savedPoint: Int,
    @JsonProperty("order_date")
    val orderDate: String,
    @JsonProperty("payment_type")
    val paymentType: String,
    @JsonProperty("data_list")
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
