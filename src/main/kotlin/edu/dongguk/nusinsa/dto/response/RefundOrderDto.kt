package edu.dongguk.nusinsa.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class RefundOrderDto(
    @JsonProperty("itme_id")
    val itemId: Long
)
