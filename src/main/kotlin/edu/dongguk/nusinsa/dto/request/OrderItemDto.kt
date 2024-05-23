package edu.dongguk.nusinsa.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderItemDto(
    @JsonProperty("item_id")
    val itemId: Long,
    @JsonProperty("item_quantity")
    val itemQuantity: Int
)
