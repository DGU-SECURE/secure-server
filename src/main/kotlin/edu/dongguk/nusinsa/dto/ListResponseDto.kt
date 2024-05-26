package edu.dongguk.nusinsa.dto

import edu.dongguk.nusinsa.domain.Item

class ListResponseDto(
    var datalist: List<ItemDto>,
    val pageInfo: PageInfoDto
){

}