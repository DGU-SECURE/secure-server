package edu.dongguk.nusinsa.controller

import edu.dongguk.nusinsa.dto.ResponseDto
import edu.dongguk.nusinsa.dto.request.OrderItemsDto
import edu.dongguk.nusinsa.dto.response.*
import edu.dongguk.nusinsa.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    /**
     * 구매 내역 목록 조회
     */
    @GetMapping("/histories")
    fun getOrderLogs(@RequestParam page: Int, @RequestParam size: Int): ResponseDto<OrderLogsDto> {
        val id: Long = 1
        return ResponseDto.success(customerService.getOrderLogs(id, page, size))
    }

    /**
     * 구매 내역 상세 조회
     */
    @GetMapping("/histories/{orderId}")
    fun getDetailOrderLogs(@PathVariable orderId: Long): ResponseDto<DetailOrderLogDto> {
        val id: Long = 1
        return ResponseDto.success(customerService.getDetailOrderLog(id, orderId))
    }

    /**
     * 구매 취소
     */
    @PostMapping("/histories/{orderId}")
    fun updateRefund(@PathVariable orderId: Long): ResponseDto<RefundOrderDto> {
        val id: Long = 1
        return ResponseDto.success(customerService.cancelOrder(id, orderId))
    }
}