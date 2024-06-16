package edu.dongguk.nusinsa.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class StoreDto(
    @JsonProperty("store_id")
    val storeId: Long,
    @JsonProperty("store_name")
    val storeName: String,
)