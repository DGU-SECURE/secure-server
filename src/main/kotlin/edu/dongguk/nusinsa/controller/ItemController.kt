package edu.dongguk.nusinsa.controller

import edu.dongguk.nusinsa.dto.ListResponseDto
import edu.dongguk.nusinsa.dto.ResponseDto
import edu.dongguk.nusinsa.service.ItemService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customers")
class ItemController(
    private val itemService: ItemService
) {
    @GetMapping("/items/{store_id}", "/items")
    fun searchItems(
        @PathVariable("store_id") storeId: Long?,
        @RequestParam(value = "name") name: String?,
        @RequestParam(value = "category") category: String?,
        @RequestParam(value = "page_num") pageNum: Int
    ): ResponseDto<ListResponseDto> {
        return ResponseDto.success(itemService.findItems(storeId, name, category, pageNum))
    }
}
