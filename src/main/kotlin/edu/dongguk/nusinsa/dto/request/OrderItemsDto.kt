package edu.dongguk.nusinsa.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import edu.dongguk.nusinsa.domain.type.PaymentType

data class OrderItemsDto(
    val point: Int,
    @JsonProperty("total_price")
    val totalPrice: Long,
    @JsonProperty("payment_type")
    val paymentType: PaymentType,
    val items: List<OrderItemDto>
)