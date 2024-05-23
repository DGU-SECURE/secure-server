package edu.dongguk.nusinsa.controller

import edu.dongguk.nusinsa.dto.ResponseDto
import edu.dongguk.nusinsa.dto.request.OrderItemsDto
import edu.dongguk.nusinsa.dto.response.OrderedItemsDto
import edu.dongguk.nusinsa.dto.response.PointBalanceDto
import edu.dongguk.nusinsa.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    /**
     * 잔액 및 포인트 조회
     */
    @GetMapping("/payment")
    fun getPointAndBalance(): ResponseDto<PointBalanceDto> {
        val id: Long = 1
        return ResponseDto.success(customerService.getPointAndBalance(id))
    }

    /**
     * 상품 구매
     */
    @PostMapping("/payment")
    fun createOrder(@RequestBody orderItemsDto: OrderItemsDto): ResponseDto<OrderedItemsDto> {
        val id: Long = 1
        return ResponseDto.success(customerService.orderItems(id, orderItemsDto))
    }
}