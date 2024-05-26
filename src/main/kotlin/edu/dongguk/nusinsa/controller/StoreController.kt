package edu.dongguk.nusinsa.controller

import edu.dongguk.nusinsa.dto.ResponseDto
import edu.dongguk.nusinsa.dto.response.StoreDto
import edu.dongguk.nusinsa.service.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/customers")
class StoreController(
    private val storeService: StoreService
) {
    @GetMapping("/store")
    fun getStore(
        @RequestParam(value = "name") name: String?
    ): ResponseDto<List<StoreDto>> {
        return ResponseDto.success(storeService.findStores(name))
    }
}