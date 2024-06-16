package edu.dongguk.nusinsa.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PageInfoDto(
    val page: Int,
    val size: Int,
    @JsonProperty("total_elements")
    val totalElements: Int,
    @JsonProperty("total_pages")
    val totalPages: Int
)