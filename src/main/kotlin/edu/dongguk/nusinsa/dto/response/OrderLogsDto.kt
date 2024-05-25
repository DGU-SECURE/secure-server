package edu.dongguk.nusinsa.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import edu.dongguk.nusinsa.dto.PageInfoDto

data class OrderLogsDto(
    @JsonProperty("data_list")
    val dataList: List<OrderLogDto>,
    @JsonProperty("page_info")
    val pageInfo: PageInfoDto
)